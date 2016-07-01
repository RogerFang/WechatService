/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package edu.whu.irlab.client.repository;

import edu.whu.irlab.client.entity.BaseCls;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BaseClsDao extends PagingAndSortingRepository<BaseCls, Integer>, JpaSpecificationExecutor<BaseCls> {
    
}
