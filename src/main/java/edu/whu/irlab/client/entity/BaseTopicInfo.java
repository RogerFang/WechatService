package edu.whu.irlab.client.entity;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 发布话题提交属性
 *
 * Created by Roger on 2016/5/27.
 */
public class BaseTopicInfo {

    private Integer topicClsId;
    private String topicContent;
    private List<MultipartFile> multipartFiles;

    public Integer getTopicClsId() {
        return topicClsId;
    }

    public void setTopicClsId(Integer topicClsId) {
        this.topicClsId = topicClsId;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public List<MultipartFile> getMultipartFiles() {
        return multipartFiles;
    }

    public void setMultipartFiles(List<MultipartFile> multipartFiles) {
        this.multipartFiles = multipartFiles;
    }
}
