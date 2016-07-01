package edu.whu.irlab.client.service.impl;

import com.google.common.collect.Maps;
import edu.whu.irlab.client.entity.BaseCls;
import edu.whu.irlab.client.repository.BaseClsDao;
import edu.whu.irlab.client.service.BaseClsService;
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
public class BaseClsServiceImpl implements BaseClsService {

    @Autowired
    private BaseClsDao baseClsDao;

    @Override
    public Page<BaseCls> findAllClasses() {
        Sort sort = new Sort(Sort.Direction.ASC, "clsOrder");
        Map<String, SearchFilter> filters = Maps.newHashMap();
        filters.put("clsStatus", new SearchFilter("clsStatus", SearchFilter.Operator.EQ, 1));
        filters.put("clsMode", new SearchFilter("clsMode", SearchFilter.Operator.EQ, 12));
        Specification<BaseCls> spec = DynamicSpecifications.bySearchFilter(filters.values(), BaseCls.class);
        PageRequest pageRequest = new PageRequest(0, 20, sort);
        return baseClsDao.findAll(pageRequest);
    }

    @Override
    public List<BaseCls> findAllClassesAsList() {
        return findAllClasses().getContent();
    }

    @Override
    public BaseCls findById(Integer id) {
        return baseClsDao.findOne(id);
    }
}
