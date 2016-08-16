package com.vicning.android.bibilicopycat.model.entity;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Neil on 2016/8/9.
 */
@Root
public class Video {
    @Element
    private String result;
    @Element
    private String timelength;
    @Element
    private String format;
    @Element(data = true)
    private String accept_format;
    @Element(data = true)
    private String accept_quality;
    @Element(data = true)
    private String from;
    @Element(data = true)
    private String seek_param;
    @Element(data = true)
    private String seek_type;
    @Element
    private Durl durl;

    public String getResult() {
        return result;
    }

    public String getTimelength() {
        return timelength;
    }

    public String getAccept_format() {
        return accept_format;
    }

    public String getAccept_quality() {
        return accept_quality;
    }

    public String getFrom() {
        return from;
    }

    public String getSeek_param() {
        return seek_param;
    }

    public String getSeek_type() {
        return seek_type;
    }

    public Durl getDurl() {
        return durl;
    }

    public String getFormat() {
        return format;
    }

    public static class Durl {
        @Element
        private int order;
        @Element
        private String length;
        @Element
        private String size;
        @Element(data = true)
        private String url;
        @ElementList
        private List<String> backup_url;

        public int getOrder() {
            return order;
        }

        public String getLength() {
            return length;
        }

        public String getSize() {
            return size;
        }

        public String getUrl() {
            return url;
        }

        public List<String> getBackup_url() {
            return backup_url;
        }
    }
}
