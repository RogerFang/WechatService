package edu.whu.irlab.wechat.model.component;

/**
 * Created by Roger on 2016/3/26.
 */
public class ViewButton extends Button {
    private String type;
    private String url;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
