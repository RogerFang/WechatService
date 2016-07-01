package edu.whu.irlab.client.service.impl;

import com.google.common.collect.Maps;
import edu.whu.irlab.client.entity.APPUser;
import edu.whu.irlab.client.entity.CommentInfo;
import edu.whu.irlab.client.repository.CommentInfoDao;
import edu.whu.irlab.client.service.CommentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import java.util.Map;

/**
 * Created by Roger on 2016/5/22.
 */
@Service
@Transactional
public class CommentInfoServiceImpl implements CommentInfoService {
    @Autowired
    private CommentInfoDao commentInfoDao;

    @Override
    public Page<CommentInfo> getEventComments(int pageNumber, int pageSize, String eventType, int infoId) {
        Sort sort = new Sort(Sort.Direction.DESC, "commentCreatetime");
        Map<String, SearchFilter> filters = Maps.newHashMap();
        filters.put("commentMode", new SearchFilter("commentMode", SearchFilter.Operator.EQ, eventType));
        filters.put("infoId", new SearchFilter("infoId", SearchFilter.Operator.EQ, infoId));
        Specification<CommentInfo> spec = DynamicSpecifications.bySearchFilter(filters.values(), CommentInfo.class);
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, sort);
        return commentInfoDao.findAll(spec, pageRequest);
    }

    @Override
    public CommentInfo save(CommentInfo commentInfo) {
        return commentInfoDao.save(commentInfo);
    }

    @Override
    public CommentInfo findByCommentCreatorAndInfoIdAndCommentType(APPUser appUser, Integer infoId, String commentType) {
        return commentInfoDao.findByCommentCreatorAndInfoIdAndCommentType(appUser, infoId, commentType);
    }
}
