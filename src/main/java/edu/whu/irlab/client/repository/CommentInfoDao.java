package edu.whu.irlab.client.repository;

import edu.whu.irlab.client.entity.APPUser;
import edu.whu.irlab.client.entity.CommentInfo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Roger on 2016/5/22.
 */
public interface CommentInfoDao
        extends PagingAndSortingRepository<CommentInfo, Integer>, JpaSpecificationExecutor<CommentInfo> {

    CommentInfo findByCommentCreatorAndInfoIdAndCommentType(APPUser appUser, Integer infoId, String commentType);
}
