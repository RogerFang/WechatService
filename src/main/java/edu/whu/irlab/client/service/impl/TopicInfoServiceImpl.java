package edu.whu.irlab.client.service.impl;

import com.google.common.collect.Maps;
import edu.whu.irlab.client.entity.APPUser;
import edu.whu.irlab.client.entity.TopicInfo;
import edu.whu.irlab.client.repository.TopicInfoDao;
import edu.whu.irlab.client.service.TopicInfoService;
import edu.whu.irlab.client.util.CDynamicSpecifications;
import edu.whu.irlab.client.util.CSearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import java.util.List;
import java.util.Map;

/**
 * Created by Roger on 2016/5/22.
 */
@Service
@Transactional
public class TopicInfoServiceImpl implements TopicInfoService {

    @Autowired
    private TopicInfoDao topicInfoDao;

    @Override
    public Page<TopicInfo> findTopTopicInfos() {
        Sort sort = new Sort(Sort.Direction.DESC, "topicCreatetime");
        Map<String, CSearchFilter> filters = Maps.newHashMap();
        filters.put("topicPic", new CSearchFilter("topicPic", CSearchFilter.COperator.NEQ, ""));
        Specification<TopicInfo> spec = CDynamicSpecifications.bySearchFilter(filters.values(), TopicInfo.class);
        PageRequest pageRequest = new PageRequest(0, 6, sort);
        return topicInfoDao.findAll(spec, pageRequest);
    }

    @Override
    public Page<TopicInfo> findTopTopicInfosWithClass(int topicClass, int pageNumber, int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "topicCreatetime");
        Map<String, CSearchFilter> filters = Maps.newHashMap();
        filters.put("topicCls", new CSearchFilter("topicCls", CSearchFilter.COperator.EQ, topicClass));
        filters.put("topicPic", new CSearchFilter("topicPic", CSearchFilter.COperator.NEQ, ""));
        Specification<TopicInfo> spec = CDynamicSpecifications.bySearchFilter(filters.values(), TopicInfo.class);
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, sort);
        return topicInfoDao.findAll(spec, pageRequest);
    }

    @Override
    public TopicInfo findEntity(int id) {
        return topicInfoDao.findOne(id);
    }

    @Override
    public Page<TopicInfo> getList(int pageNumber, int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "topicCreatetime");
        Map<String, CSearchFilter> filters = Maps.newHashMap();
        Specification<TopicInfo> spec = CDynamicSpecifications.bySearchFilter(filters.values(), TopicInfo.class);
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, sort);
        return topicInfoDao.findAll(spec, pageRequest);
    }

    @Override
    public Page<TopicInfo> findByMod(int pageNumber, int pageSize, int topicType) {
        Sort sort = new Sort(Sort.Direction.DESC, "topicCreatetime");
        Map<String, CSearchFilter> filters = Maps.newHashMap();
        filters.put("topicCls", new CSearchFilter("topicCls", CSearchFilter.COperator.EQ, topicType));
        Specification<TopicInfo> spec = CDynamicSpecifications.bySearchFilter(filters.values(), TopicInfo.class);
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, sort);
        return topicInfoDao.findAll(spec, pageRequest);
    }

    @Override
    public Page<TopicInfo> findByTitle(int pageNumber, int pageSize, String query) {
        Sort sort = new Sort(Sort.Direction.DESC, "topicCreatetime");
        Map<String, SearchFilter> filters = Maps.newHashMap();
        filters.put("topicContent", new SearchFilter("topicContent", SearchFilter.Operator.LIKE, query));
        Specification<TopicInfo> spec = DynamicSpecifications.bySearchFilter(filters.values(), TopicInfo.class);
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, sort);
        return topicInfoDao.findAll(spec, pageRequest);
    }

    @Override
    public int increasePraiseCount(String topicPraiseCount, Integer id) {
        return topicInfoDao.increasePraiseCount(topicPraiseCount, id);
    }

    @Override
    public int increaseCommentCount(String topicCommentCount, Integer id) {
        return topicInfoDao.increaseCommentCount(topicCommentCount, id);
    }

    @Override
    public TopicInfo save(TopicInfo topicInfo) {
        return topicInfoDao.save(topicInfo);
    }

    @Override
    public List<TopicInfo> findByTopicCreator(APPUser appUser) {
        return topicInfoDao.findByTopicCreator(appUser);
    }
}
