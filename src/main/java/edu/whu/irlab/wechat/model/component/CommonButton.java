package edu.whu.irlab.wechat.model.component;

/**
 * Created by Roger on 2016/3/26.
 */
public class CommonButton extends Button {
    private String type;
    private String key;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
}
