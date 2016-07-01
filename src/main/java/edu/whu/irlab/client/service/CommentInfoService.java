package edu.whu.irlab.client.service;

import edu.whu.irlab.client.entity.APPUser;
import edu.whu.irlab.client.entity.CommentInfo;
import org.springframework.data.domain.Page;

/**
 * Created by Roger on 2016/5/22.
 */
public interface CommentInfoService {
    public Page<CommentInfo> getEventComments(int pageNumber, int pageSize, String eventType, int infoId);

    public CommentInfo save(CommentInfo commentInfo);

    public CommentInfo findByCommentCreatorAndInfoIdAndCommentType(APPUser appUser, Integer infoId, String commentType);
}
