package com.project.tmall.controller;

import com.project.tmall.pojo.Property;
import com.project.tmall.service.PropertyService;
import com.project.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hx
 * @create 2020-04-27 16:40
 */

@RestController
public class PropertyController {

    @Autowired
    private PropertyService propertyService ;

    /**
     * 属性列表查询 并分页
     * @param cid
     *            属性id
     * @param start
     *              开始值
     * @param size
     *           大小
     * @return
     *          返回值
     * @throws Exception
     *                  异常值
     */
    @GetMapping("/categories/{cid}/properties")
    public Page4Navigator<Property> list(@PathVariable("cid") int cid,
                                         @RequestParam(value = "start", defaultValue = "0") int start,
                                         @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {

        start = start<0?0:start;

        Page4Navigator<Property> page = propertyService.list(cid, start, size,5);

        return page;
    }

    /**
     * 根据id查询值
     * @param id
     *          属性id值
     * @return
     *         返回值
     * @throws Exception
     *                  异常处理
     */
    @GetMapping("/properties/{id}")
    public Property get(@PathVariable("id") int id) throws Exception {
        Property bean=propertyService.get(id);
        return bean;
    }

    /**
     * 新增属性
     * @param bean
     *              属性对象
     * @return
     *      返回值
     * @throws Exception
     *                  异常处理
     */
    @PostMapping("/properties")
    public Object add(@RequestBody  Property bean) throws Exception{
        propertyService.add(bean);
        return bean ;
    }

    /**
     * 根据id删除值
     * @param id
     *          属性id值
     * @param request
     *              request请求
     * @return
     *          返回值
     * @throws Exception
     *                  异常处理
     */
    @DeleteMapping("/properties/{id}")
    public String delete(@PathVariable("id") int id, HttpServletRequest request)  throws Exception {
        propertyService.delete(id);
        return null;
    }

    /**
     * 更新属性值
     * @param bean
     *              属性值对象
     * @return
     *          返回值
     */
    @PutMapping("/properties")
    public Object update(@RequestBody Property bean){
        propertyService.update(bean);
        return bean ;
    }
}
