package com.vicning.android.bibilicopycat.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neil on 2016/8/18.
 */
public class SearchCompreInfo {
    public Integer code;
    public String seid;
    public Integer pagesize;
    public Integer page;
    public PageCaches pageCaches;
    public Pageinfo pageinfo;
    public String suggest_keyword;
    public List<Object> sel_subType = new ArrayList<>();
    public TopTlist top_tlist;
    public List<SubTlist> sub_tlist = new ArrayList<>();
    public List<Object> sub2_tlist = new ArrayList<>();
    public Result result;
    public Cost cost;

    public static class PageCaches {
        public Boolean hitCache;
    }

    public static class Pageinfo {
        public Video video;
        public Special special;
        public Bangumi bangumi;
        public Movie movie;
        public Tvplay tvplay;
        public Topic topic;
        public Upuser upuser;
        public Pgc pgc;
    }

    public static class TopTlist {
        public Integer video;
        public Integer bangumi;
        public Integer movie;
        public Integer tvplay;
        public Integer special;
        public Integer topic;
        public Integer upuser;
        public Integer pgc;
    }

    public static class SubTlist {
        public String name;
        public Integer count;
        public Integer tid;
    }

    public static class Result {
        public List<Video_> video = new ArrayList<>();
        public List<Object> special = new ArrayList<>();
        public List<Bangumi_> bangumi = new ArrayList<>();
        public List<Movie_> movie = new ArrayList<>();
        public List<Object> tvplay = new ArrayList<>();
        public List<Topic_> topic = new ArrayList<>();
        public List<Object> upuser = new ArrayList<>();
    }

    public static class Cost {
        public String timer;
        public String total;
        public String chk_params;
        public String rcache;
        public String state;
    }

    public static class Special {
        public Integer total;
        public Integer numResults;
        public Integer pages;
    }

    public static class Bangumi {
        public Integer total;
        public Integer numResults;
        public Integer pages;
    }

    public static class Movie {
        public Integer total;
        public Integer numResults;
        public Integer pages;
    }

    public static class Tvplay {
        public Integer total;
        public Integer numResults;
        public Integer pages;
    }

    public static class Topic {
        public Integer total;
        public Integer numResults;
        public Integer pages;
    }

    public static class Upuser {
        public Integer total;
        public Integer numResults;
        public Integer pages;
    }

    public static class Pgc {
        public Integer total;
        public Integer numResults;
        public Integer pages;
    }

    public static class Video {
        public Integer total;
        public Integer numResults;
        public Integer pages;
    }

    public static class Video_ {
        public String type;
        public long id;
        public String author;
        public long mid;
        public String typeid;
        public String typename;
        public String arcurl;
        public String aid;
        public String description;
        public String title;
        public String arcrank;
        public String pic;
        public String play;
        public long video_review;
        public long favorites;
        public String tag;
        public Integer review;
        public long pubdate;
        public long senddate;
        public String duration;
        public Boolean badgepay;
        public long rank_score;
    }

    public static class Bangumi_ {
        public String type;
        public Integer season_id;
        public Integer bangumi_id;
        public Integer spid;
        public String title;
        public String brief;
        public String styles;
        public String cv;
        public String staff;
        public String evaluate;
        public String cover;
        public String typeurl;
        public Integer favorites;
        public Integer is_finish;
        public Integer newest_ep_id;
        public String newest_ep_index;
        public Integer play_count;
        public Integer danmaku_count;
        public Integer total_count;
        public Integer pubdate;
        public List<Object> eplist = new ArrayList<>();
        public List<Object> bgmlist = new ArrayList<>();
        public Catlist catlist;
        public String newest_cat;
        public String newest_season;
    }

    public static class Movie_ {
        public String type;
        public Integer id;
        public Integer aid;
        public String title;
        public String originName;
        public String searchKeywords;
        public String description;
        public String actors;
        public String staff;
        public String cover;
        public String arcurl;
        public String screenDate;
        public Integer length;
        public String area;
        public Integer status;
    }

    public static class Topic_ {
        public Integer tp_id;
        public Integer tp_type;
        public long mid;
        public String author;
        public String title;
        public String keyword;
        public String arcurl;
        public String cover;
        public String description;
        public long click;
        public long review;
        public long favourite;
        public long pubdate;
        public long update;

    }

    public static class Catlist {
        public Integer tv;
        public Integer movie;
        public Integer ova;
    }
}
