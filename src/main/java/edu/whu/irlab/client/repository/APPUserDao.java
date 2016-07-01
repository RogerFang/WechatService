package edu.whu.irlab.client.repository;

import edu.whu.irlab.client.entity.APPUser;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Roger on 2016/5/22.
 */
public interface APPUserDao extends PagingAndSortingRepository<APPUser, Integer>, JpaSpecificationExecutor<APPUser> {
    APPUser findByUserName(String userName);

    APPUser findByMobileAndEnabled(String mobile, Integer enabled);

    @Transactional
    @Modifying
    @Query("update APPUser user set user.userAgeDesc = ?1,user.userStar=?2,user.userArea=?3,user.userCareer=?4,user.text4=?5 where user.id = ?6")
    int completeInfo(String userAgeDesc, String userStar, String userArea, String userCareer, String text4, Integer id);

    @Transactional
    @Modifying
    @Query("update APPUser user set user.text3 = ?1 where user.id = ?2")
    int updateUserPhoto(String userPhoto, Integer id);

    @Query("select user from APPUser user where (user.userAgeDesc = ?1 or user.userStar = ?2 or user.userArea = ?3 or user.userCareer = ?4 or user.text4 = ?5) and user.id > 30000000")
    List<APPUser> findFriend(String userAgeDesc, String userStar, String userArea, String userCareer, String text4);

    @Transactional
    @Modifying
    @Query("update APPUser user set user.userName = ?1 where user.id = ?2")
    int updateUsername(String username, Integer id);
}
