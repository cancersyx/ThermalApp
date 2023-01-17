package com.zhang.administrator.thermal.ui.find;

import java.io.Serializable;
import java.util.List;

/**
 * Created by EWorld
 * 2021/11/13
 */
public class FindBean implements Serializable {
    /* public int code;
  public int thumb;
  public String thumbUrl;
  public String pageUrl;
  public String title;
  public String[] keywords;//话题,关键词*/

    private String reason;
    private String code;
    private List<DataDTO> data;
    private Integer error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public Integer getError_code() {
        return error_code;
    }

    public void setError_code(Integer error_code) {
        this.error_code = error_code;
    }

    public static class DataDTO implements Serializable{
        private List<String> keywords;
        private String thumbnail_img_url;
        private String title;
        private String page_url;
        private String date;

        public List<String> getKeywords() {
            return keywords;
        }

        public void setKeywords(List<String> keywords) {
            this.keywords = keywords;
        }

        public String getThumbnail_img_url() {
            return thumbnail_img_url;
        }

        public void setThumbnail_img_url(String thumbnail_img_url) {
            this.thumbnail_img_url = thumbnail_img_url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPage_url() {
            return page_url;
        }

        public void setPage_url(String page_url) {
            this.page_url = page_url;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }




}
