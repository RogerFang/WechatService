package edu.whu.irlab.client.service;

import edu.whu.irlab.client.entity.APPUser;
import edu.whu.irlab.client.entity.TopicInfo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Roger on 2016/5/22.
 */
public interface TopicInfoService {
    public Page<TopicInfo> findTopTopicInfos();

    public Page<TopicInfo> findTopTopicInfosWithClass(int topicClass, int pageNumber, int pageSize);

    public TopicInfo findEntity(int id);

    public Page<TopicInfo> getList(int pageNumber, int pageSize);

    public Page<TopicInfo> findByMod(int pageNumber, int pageSize, int topicType);

    public Page<TopicInfo> findByTitle(int pageNumber, int pageSize, String query);

    public int increasePraiseCount(String topicPraiseCount, Integer id);

    public int increaseCommentCount(String topicCommentCount, Integer id);

    public TopicInfo save(TopicInfo topicInfo);

    public List<TopicInfo> findByTopicCreator(APPUser appUser);
}
