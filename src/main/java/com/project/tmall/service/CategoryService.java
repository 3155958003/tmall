package com.project.tmall.service;

import com.project.tmall.dao.CategoryDao;
import com.project.tmall.pojo.Category;
import com.project.tmall.util.Page4Navigator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hx
 * @create 2020-03-23 22:20
 *
 * Category的服务层接口
 *
 */
@Service
public class CategoryService {

    @Autowired
    CategoryDao categoryDao ;


    /**
     * 分页查询类别
     * @param start
     *              开始数
     * @param size
     *              大小
     * @param navigePages
     *                    页数
     * @return
     *          返回值
     */
    public Page4Navigator<Category> list(int start, int size, int navigePages){
        //  根据id进行降序查询
        Sort sort = new Sort(Sort.Direction.DESC, "id") ;
        //  根据当前所在的页数去查询数据
        Pageable pageable = new PageRequest(start, size, sort) ;

        Page pageFromJPA = categoryDao.findAll(pageable) ;

        //  返回数据
        return new Page4Navigator<>(pageFromJPA, navigePages) ;
    }


    /**
     * 查询全部类别
     * @return
     *         返回所有的类别
     */
    public List<Category> list(){
        //  根据id进行降序排列
        Sort sort =  new Sort(Sort.Direction.DESC, "id") ;
        return categoryDao.findAll(sort) ;
    }

    /**
     * 增加类别
     * @param bean
     *              类别对象
     */
    public void add(Category bean){
        categoryDao.save(bean) ;
    }

    /**
     * 根据id删除类别
     * @param id
     *         传入的id值
     */
    public void delete(int id){
        categoryDao.delete(id);
    }

    /**
     * 通过id获取需要编辑的对象
     * @param id
     *          需要修改对象的id
     * @return
     *          返回对象
     */
    public Category getCategoryById(int id){
        //  通过id查询对象
        Category category = categoryDao.findOne(id) ;

        //  返回category对象
        return category ;
    }


    /**
     * 更新分类方法
     * @param bean
     *             分类实体对象
     */
    public void update(Category bean){
        categoryDao.save(bean) ;
    }
}
