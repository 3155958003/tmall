package com.project.tmall.dao;

import com.project.tmall.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hx
 * @create 2020-03-23 22:17
 *
 * CategoryDao接口继承了JpaRepository 其实现了增删改查
 *
 */
public interface CategoryDao extends JpaRepository<Category, Integer> {
}
