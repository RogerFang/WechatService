/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package edu.whu.irlab.client.repository;

import edu.whu.irlab.client.entity.APPUser;
import edu.whu.irlab.client.entity.TopicInfo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TopicInfoDao
		extends PagingAndSortingRepository<TopicInfo, Integer>, JpaSpecificationExecutor<TopicInfo> {

	@Transactional
	@Modifying
	@Query("update TopicInfo ti set ti.topicPraiseCount = ?1 where ti.id = ?2")
	int increasePraiseCount(String topicPraiseCount, Integer id);

	@Transactional
	@Modifying
	@Query("update TopicInfo ti set ti.topicCommentCount = ?1 where ti.id = ?2")
	int increaseCommentCount(String topicCommentCount, Integer id);

	List<TopicInfo> findByTopicCreator(APPUser appUser);
}
