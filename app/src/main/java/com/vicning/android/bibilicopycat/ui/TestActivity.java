package com.vicning.android.bibilicopycat.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.vicning.android.bibilicopycat.R;

import java.io.IOException;
import java.io.InputStream;
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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Neil on 2016/8/11.
 */
public class TestActivity extends AppCompatActivity {

    @BindView(R.id.danmaku_view)
    DanmakuView mDanmakuView;

    private BaseDanmakuParser mDanmakuParser;
    private OkHttpClient okHttpClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        mDanmakuView.enableDanmakuDrawingCache(true);
        okHttpClient = new OkHttpClient();
        System.setProperty("org.xml.sax.driver", "org.xmlpull.v1.sax2.Driver");

        Observable<Object> danmakuOb = Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(final Subscriber<? super Object> subscriber) {
                try {
                    Request request = new Request.Builder()
                            .url("http://comment.bilibili.com/8655574.xml")
                            .build();
                    Response response = okHttpClient.newCall(request).execute();
                    InflaterInputStream inflated = new InflaterInputStream(
                            response.body().byteStream(),
                            new Inflater(true));
                    mDanmakuParser = createParser(inflated);
                    mDanmakuView.setCallback(new DrawHandler.Callback() {
                        @Override
                        public void prepared() {
                            subscriber.onCompleted();
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
                    mDanmakuView.prepare(mDanmakuParser, new DanmakuContext());

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        danmakuOb.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        mDanmakuView.start();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });

    }

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
            e.printStackTrace();
        }
        BaseDanmakuParser parser = new BiliDanmukuParser();
        IDataSource<?> dataSource = loader.getDataSource();
        parser.load(dataSource);
        return parser;

    }

}
