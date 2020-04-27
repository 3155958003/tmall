package com.project.tmall.dao;

import com.project.tmall.pojo.Category;
import com.project.tmall.pojo.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hx
 * @create 2020-04-27 12:03
 *
 * 属性接口dao
 */
public interface PropertyDao extends JpaRepository<Property, Integer> {

    Page<Property> findByCategory(Category category, Pageable pageable) ;
}
