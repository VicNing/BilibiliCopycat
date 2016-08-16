
package com.vicning.android.bibilicopycat.model.entity;


import java.util.ArrayList;
import java.util.List;

public class VideoComment {
    public Integer code;
    public Data data;
    public String message;

    public static class Data {

        public List<Hot> hots = new ArrayList<Hot>();
        public Page page;
        public List<Reply> replies = new ArrayList<Reply>();
    }

    public static class Hot {

        public Integer action;
        public Content content;
        public Integer count;
        public Float ctime;
        public Integer floor;
        public Integer like;
        public Member member;
        public Float mid;
        public Float oid;
        public Integer parent;
        public String parent_str;
        public Integer rcount;
        public List<Object> replies = new ArrayList<Object>();
        public Integer root;
        public String root_str;
        public Float rpid;
        public String rpid_str;
        public Integer state;
        public Integer type;
    }

    public static class Page {

        public Integer acount;
        public Integer count;
        public Integer num;
        public Integer size;
    }

    public static class Reply {

        public Integer action;
        public Content content;
        public Integer count;
        public Long ctime;
        public Integer floor;
        public Integer like;
        public Member member;
        public Long mid;
        public Long oid;
        public Integer parent;
        public String parentStr;
        public Integer rcount;
        public List<Object> replies = new ArrayList<Object>();
        public Integer root;
        public String rootStr;
        public Long rpid;
        public String rpidStr;
        public Integer state;
        public Integer type;
    }

    public static class Content {

        public String device;
        public List<Object> members = new ArrayList<Object>();
        public String message;
        public Integer plat;
    }

    public class Member {

        public String DisplayRank;
        public String avatar;
        public LevelInfo level_info;
        public String mid;
        public Nameplate nameplate;
        public Pendant pendant;
        public String rank;
        public String sex;
        public String sign;
        public String uname;
    }

    public static class LevelInfo {

        public Integer current_exp;
        public Integer current_level;
        public Integer current_min;
        public String next_exp;
    }

    public static class Nameplate {

        public String condition;
        public String image;
        public String image_small;
        public String level;
        public String name;
        public Integer nid;

    }

    public static class Pendant {

        public Long expire;
        public String image;
        public String name;
        public Integer pid;

    }
}
