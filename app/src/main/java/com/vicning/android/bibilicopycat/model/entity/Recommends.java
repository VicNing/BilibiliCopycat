package com.vicning.android.bibilicopycat.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neil on 2016/8/4.
 */
public class Recommends {

    public Integer code;
    public List<Result> result = new ArrayList<>();

    public static class Result {

        public String type;
        public Head head;
        public List<Body> body = new ArrayList<>();

    }

    public static class Head {

        public String param;
        public String _goto;
        public String style;
        public String title;

    }

    public static class Body {

        public String title;
        public String style;
        public String cover;
        public String param;
        public String _goto;
        public Integer width;
        public Integer height;
        public String play;
        public String danmaku;

    }
}
