package com.project.tmall.service;

import com.project.tmall.dao.PropertyDao;
import com.project.tmall.pojo.Category;
import com.project.tmall.pojo.Property;
import com.project.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author hx
 * @create 2020-04-27 12:14
 *
 * 分类服务层类
 */

@Service
public class PropertyService {

    @Autowired
    private PropertyDao propertyDao ;

    @Autowired
    private CategoryService categoryService ;

    /**
     * 分页查询值
     * @param cid
     *           属性id值
     * @param start
     *              开始数
     * @param size
     *              大小
     * @param navigatePages
     *                      页数
     * @return
     *          返回值
     */
    public Page4Navigator<Property> list(int cid, int start, int size,int navigatePages){
        Category category = categoryService.get(cid) ;

        Sort sort = new Sort(Sort.Direction.DESC, "id") ;

        Pageable pageable = new PageRequest(start, size, sort) ;

        Page<Property> pageFromJPA = propertyDao.findByCategory(category, pageable) ;

        return new Page4Navigator(pageFromJPA, navigatePages) ;
    }

    /**
     * 新增属性
     * @param bean
     *             属性对象
     */
    public void add(Property bean) {
        propertyDao.save(bean) ;
    }

    /**
     * 根据id删除属性
     * @param id
     *          传入的id值
     */
    public void delete(int id){
        propertyDao.delete(id);
    }

    /**
     * 更新属性
     * @param bean
     *              属性实体类
     */
    public void update(Property bean) {
        propertyDao.save(bean) ;
    }

    /**
     * 根据id查询属性值
     * @param id
     *          传入的id值
     * @return
     *          返回对象
     */
    public Property get(int id){
         return propertyDao.findOne(id) ;
    }
}
