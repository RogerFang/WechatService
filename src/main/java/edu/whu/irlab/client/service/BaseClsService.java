package edu.whu.irlab.client.service;

import edu.whu.irlab.client.entity.BaseCls;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Roger on 2016/5/22.
 */
public interface BaseClsService {
    public Page<BaseCls> findAllClasses();

    public List<BaseCls> findAllClassesAsList();

    public BaseCls findById(Integer id);
}
