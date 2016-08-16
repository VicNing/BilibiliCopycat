package com.vicning.android.bibilicopycat.ui;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.vicning.android.bibilicopycat.R;
import com.vicning.android.bibilicopycat.common.Secret;
import com.vicning.android.bibilicopycat.model.entity.JJInfos;
import com.vicning.android.bibilicopycat.model.entity.Video;
import com.vicning.android.bibilicopycat.network.CommentServices;
import com.vicning.android.bibilicopycat.network.InterfaceServices;
import com.vicning.android.bibilicopycat.network.JJServices;
import com.vicning.android.bibilicopycat.network.RetrofitClients;
import com.vicning.android.bibilicopycat.ui.adapters.VideoDetailPagerAdapter;
import com.vicning.android.bibilicopycat.utils.DensityUtil;
import com.vicning.android.bibilicopycat.utils.MD5Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.loader.ILoader;
import master.flame.danmaku.danmaku.loader.IllegalDataException;
import master.flame.danmaku.danmaku.loader.android.DanmakuLoaderFactory;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.IDataSource;
import master.flame.danmaku.danmaku.parser.android.BiliDanmukuParser;
import master.flame.danmaku.ui.widget.DanmakuView;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by Neil on 2016/8/8.
 */
public class PortraitPlayerActivity extends AppCompatActivity implements View.OnClickListener,
        SeekBar.OnSeekBarChangeListener {

    @BindView(R.id.surface_view)
    SurfaceView surfaceView;
    @BindView(R.id.tv_video_info)
    TextView tvVideoInfo;
    @BindView(R.id.danmaku_view)
    DanmakuView danmakuView;
    @BindView(R.id.bt_rotation)
    Button btRotation;
    @BindView(R.id.fl_viedeo_holder)
    FrameLayout flVideoHolder;
    @BindView(R.id.rl_controller)
    RelativeLayout rlController;
    @BindView(R.id.bt_danmaku)
    Button btDanmaku;
    @BindView(R.id.bt_play_pause)
    Button btPlayPause;
    @BindView(R.id.bt_show)
    Button btShow;
    @BindView(R.id.seek_bar)
    SeekBar seekBar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.vp_video_detail)
    ViewPager vpVideoDetail;

    private SurfaceHolder holder;
    private IjkMediaPlayer mediaPlayer;
    private CompositeSubscription compositeSubscription;

    private static final String TAG = "LOLOL";
    //视频源切换
    private static final int VIDEO_SOURCE = 2;
    private boolean isControllerDisplayed = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portrait_player);
        ButterKnife.bind(this);

        final String aid = getIntent().getStringExtra("aid");

        VideoDetailPagerAdapter adapter = new VideoDetailPagerAdapter(getSupportFragmentManager(), this, aid);
        vpVideoDetail.setAdapter(adapter);
        tabLayout.setupWithViewPager(vpVideoDetail);

        btRotation.setOnClickListener(this);
        btPlayPause.setOnClickListener(this);
        btDanmaku.setOnClickListener(this);
        flVideoHolder.setOnClickListener(this);
        btShow.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(this);

        compositeSubscription = new CompositeSubscription();
        mediaPlayer = new IjkMediaPlayer();
        //TODO media player set options
        danmakuView.enableDanmakuDrawingCache(true);

        Subscription mainSub = Observable.merge(surfaceViewWorkflow(), requestCidWorkflow(aid))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        mediaPlayer.start();
                        danmakuView.start();
                        danmakuView.showFPS(true);
                        seekBar.setMax((int) mediaPlayer.getDuration());
                        Subscription subscribe = Observable.fromCallable(new Callable<Long>() {
                            @Override
                            public Long call() throws Exception {
                                return mediaPlayer.getCurrentPosition();
                            }
                        }).repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() {
                            @Override
                            public Observable<?> call(Observable<? extends Void> observable) {
                                return observable.delay(1, TimeUnit.SECONDS);
                            }
                        }).takeWhile(new Func1<Long, Boolean>() {
                            @Override
                            public Boolean call(Long aLong) {
                                if (!mediaPlayer.isPlaying() && aLong == mediaPlayer.getDuration()) {
                                    return false;
                                } else {
                                    seekBar.setProgress(aLong.intValue());
                                    return true;
                                }
                            }
                        }).subscribe();
                        compositeSubscription.add(subscribe);
                        threadlog("mainWorkflow, Everything Complete");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });
        compositeSubscription.add(mainSub);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            danmakuView.release();
        }
        compositeSubscription.clear();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            ViewGroup.LayoutParams layoutParams = flVideoHolder.getLayoutParams();
            layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
            flVideoHolder.setLayoutParams(layoutParams);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            ViewGroup.LayoutParams layoutParams = flVideoHolder.getLayoutParams();
            layoutParams.height = DensityUtil.dip2px(this, 203);
            flVideoHolder.setLayoutParams(layoutParams);
        }
    }

    public Observable<Object> surfaceViewWorkflow() {
        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(final Subscriber<? super Object> subscriber) {
                holder = surfaceView.getHolder();
                holder.addCallback(new SurfaceHolder.Callback() {
                    @Override
                    public void surfaceCreated(SurfaceHolder holder) {
                        subscriber.onCompleted();
                        threadlog("surfaceViewWorkflow, surfaceCreated");
                    }

                    @Override
                    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                    }

                    @Override
                    public void surfaceDestroyed(SurfaceHolder holder) {

                    }
                });
            }

        });
    }

    public Observable<Object> requestCidWorkflow(final String aid) {
        JJServices jjServices = RetrofitClients.getRetrofitJJ()
                .create(JJServices.class);
        return jjServices.getJJInfos(aid)
                .subscribeOn(Schedulers.io())
                .map(new Func1<ResponseBody, String>() {
                    @Override
                    public String call(ResponseBody responseBody) {
                        try {
                            String replace = responseBody.string().replace("&quot;", "\"");
                            Gson gson = new Gson();
                            JJInfos jjInfos = gson.fromJson(replace, JJInfos.class);
                            final Integer cid = jjInfos.cid;
                            runOnUiThread(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            tvVideoInfo.setText("aid = " + aid + ", cid = " + cid);
                                        }
                                    }
                            );
                            threadlog("requestCidWorkflow, cid = " + cid);
                            return String.valueOf(String.valueOf(cid));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return "0";
                    }
                })
                .flatMap(new Func1<String, Observable<?>>() {
                    @Override
                    public Observable<Object> call(String s) {
                        threadlog("requestCidWorkflow, merge videoSourceWorkflow and danmakuWorkflow");
                        HashMap<String, String> queryMap = generateStreamQueryMap(s);
                        return Observable.merge(videoSourceWorkflow(queryMap), danmakuWorkflow(s));
                    }
                });
    }

    public Observable<Object> videoSourceWorkflow(HashMap<String, String> querymap) {
        final InterfaceServices interfaceServices = RetrofitClients.getRetrofitInterface()
                .create(InterfaceServices.class);

        return interfaceServices.getVideoStream(querymap)
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<Video, Observable<?>>() {
                    @Override
                    public Observable<?> call(Video video) {
                        String[] urls = new String[3];
                        urls[0] = video.getDurl().getUrl();
                        urls[1] = video.getDurl().getBackup_url().get(0);
                        urls[2] = video.getDurl().getBackup_url().get(1);
                        threadlog("videoSourceWorkflow, getVideoUrl: " + urls[VIDEO_SOURCE]);
                        return meadiaPrepareWorkflow(urls);
                    }
                });

    }

    public Observable<Object> meadiaPrepareWorkflow(final String[] url) {
        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(final Subscriber<? super Object> subscriber) {
                try {
                    mediaPlayer.setDisplay(holder);
                    mediaPlayer.setScreenOnWhilePlaying(true);
                    mediaPlayer.setDataSource(url[VIDEO_SOURCE]);
                    mediaPlayer.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(IMediaPlayer iMediaPlayer) {
                            threadlog("mediaoPrepareWorkflow, media prepared ");
                            subscriber.onCompleted();
                            iMediaPlayer.pause();
                        }
                    });
                    mediaPlayer.prepareAsync();
                    threadlog("mediaoPrepareWorkflow, media prepared async...... ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Observable<Object> danmakuWorkflow(String cid) {
        CommentServices commentServices = RetrofitClients.getRetrofitComment()
                .create(CommentServices.class);

        return commentServices.getComments(cid)
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<ResponseBody, Observable<?>>() {
                    @Override
                    public Observable<?> call(ResponseBody responseBody) {
                        threadlog("danmakuWorkflow, getXML stream");
                        InflaterInputStream inflated = new InflaterInputStream(
                                responseBody.byteStream(), new Inflater(true));
                        return parserWorkflow(inflated);
                    }
                }, new Func1<Throwable, Observable<?>>() {
                    @Override
                    public Observable<?> call(Throwable throwable) {
                        return null;
                    }
                }, new Func0<Observable<?>>() {
                    @Override
                    public Observable<?> call() {
                        return null;
                    }
                });
    }

    public Observable<Object> parserWorkflow(final InputStream inputStream) {

        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(final Subscriber<? super Object> subscriber) {
                BaseDanmakuParser parser = createParser(inputStream);
                threadlog("parserWorkflow, parser complete");
                danmakuView.setCallback(new DrawHandler.Callback() {
                    @Override
                    public void prepared() {
                        subscriber.onCompleted();
                        threadlog("parserWorkflow, danmakuview compelte");
                    }

                    @Override
                    public void updateTimer(DanmakuTimer timer) {

                    }

                    @Override
                    public void danmakuShown(BaseDanmaku danmaku) {

                    }

                    @Override
                    public void drawingFinished() {

                    }
                });
                danmakuView.prepare(parser, DanmakuContext.create());

            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////                                                                             /////////
    //////////                   implements various of listeners                           /////////
    //////////                                                                             /////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fl_viedeo_holder:
                if (isControllerDisplayed) {
                    rlController.setVisibility(View.INVISIBLE);
                    isControllerDisplayed = false;
                } else {
                    rlController.setVisibility(View.VISIBLE);
                    isControllerDisplayed = true;
                }
                break;

            case R.id.bt_rotation:
                int orientation = getResources().getConfiguration().orientation;
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                break;

            case R.id.bt_danmaku:
                if (danmakuView.isShown()) {
                    danmakuView.hide();
                } else {
                    danmakuView.show();
                }
                break;

            case R.id.bt_play_pause:
                if (mediaPlayer.isPlaying() && !danmakuView.isPaused()) {
                    mediaPlayer.pause();
                    danmakuView.pause();
                    btPlayPause.setText("PLAY");
                } else if (!mediaPlayer.isPlaying() && danmakuView.isPaused()) {
                    mediaPlayer.start();
                    danmakuView.resume();
                    btPlayPause.setText("PAUSE");
                }
                break;

            case R.id.bt_show:
                Log.e(TAG, "getCurrentPosition" + mediaPlayer.getCurrentPosition());
                Log.e(TAG, "getDuration" + mediaPlayer.getDuration());
                Log.e(TAG, "getVideoCachedDuration" + mediaPlayer.getVideoCachedDuration());
                Log.e(TAG, "getAudioCachedDuration" + mediaPlayer.getAudioCachedDuration());
                Log.e(TAG, "getVideoCachedBytes" + mediaPlayer.getVideoCachedBytes());
                Log.e(TAG, "getVideoWidth" + mediaPlayer.getVideoWidth());
                Log.e(TAG, "getVideoHeight" + mediaPlayer.getVideoHeight());
                Log.e(TAG, "isPlaying" + mediaPlayer.isPlaying());
                Log.e(TAG, "danmakuGetCurrentTime" + danmakuView.getCurrentTime());
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, final int progress, boolean fromUser) {
        if (fromUser) {
            mediaPlayer.pause();
            mediaPlayer.setOnSeekCompleteListener(new IMediaPlayer.OnSeekCompleteListener() {
                @Override
                public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                    mediaPlayer.start();
                    danmakuView.start(progress);
                }
            });
            mediaPlayer.seekTo(progress);
            danmakuView.stop();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////                                                                              /////////
    /////////                           implementation ends here                           /////////
    /////////                                                                              /////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onBackPressed() {
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 生成InterfaceServices的QueryMap
     *
     * @param cid 视频cid
     * @return queryMap
     */
    public HashMap<String, String> generateStreamQueryMap(String cid) {
        HashMap<String, String> queryMap = new HashMap<>();
        queryMap.put("appkey", Secret.appKey);
        queryMap.put("cid", cid);
        String sign = MD5Util.string2MD5("appkey=" + Secret.appKey + "&cid=" + cid + Secret.appSecret);
        queryMap.put("sign", sign);
        return queryMap;
    }

    /**
     * 传入流，加载弹幕
     *
     * @param stream 弹幕流
     * @return 弹幕parser
     */
    private static BaseDanmakuParser createParser(InputStream stream) {
        if (stream == null) {
            return new BaseDanmakuParser() {
                @Override
                protected Danmakus parse() {
                    return new Danmakus();
                }
            };
        }

        ILoader loader = DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_BILI);

        try {
            loader.load(stream);
        } catch (IllegalDataException e) {
            throw new RuntimeException(e);
        }
        BaseDanmakuParser parser = new BiliDanmukuParser();
        IDataSource<?> dataSource = loader.getDataSource();
        parser.load(dataSource);
        return parser;

    }

    /**
     * workflow线程日志
     *
     * @param info 日志信息
     */
    public void threadlog(String info) {
        long threadID = Thread.currentThread().getId();
        Log.e(TAG + " " + threadID, info);
    }
}
