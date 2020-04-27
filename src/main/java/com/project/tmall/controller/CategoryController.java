package com.project.tmall.controller;

import com.project.tmall.pojo.Category;
import com.project.tmall.service.CategoryService;
import com.project.tmall.util.ImageUtil;
import com.project.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;


/**
 * @author hx
 * @create 2020-03-23 22:28
 *
 * 类别的控制类
 */
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService ;

    /**
     * 分页查询类别数据
     * @param start
     *              开始数
     * @param size
     *              大小
     * @return
     *          返回值
     */
    @GetMapping("/categories")
    public Page4Navigator<Category> list(@RequestParam(value = "start", defaultValue = "0") int start
                                         ,@RequestParam(value = "size", defaultValue = "5") int size) throws Exception{
        //  判断开始数是否小于0， 如小于0 则从0开始
        //  否则从start数开始
        start = start<0?0:start ;

        //  调用servie层方方法查询
        //  5表示导航分页最多有5个，像 [1,2,3,4,5]
        Page4Navigator<Category> page = categoryService.list(start, size, 5) ;

        //  返回数据
        return page ;
    }

    /**
     * 查询类别全部数据
     * @return
     *        返回类别的全部数据
     */
    /*@GetMapping("/categories")
    public List<Category> list() throws Exception{
        //  返回类别全部数据
        return categoryService.list() ;
    }*/


    /**
     * 保存类别的方法
     * @param bean
     *              类别实体
     * @param image
     *              上传的文件
     * @param request
     *              http请求
     * @return
     *          返回对象
     * @throws Exception
     *                  异常处理
     */
    @PostMapping("/categories")
    public Object add(Category bean, MultipartFile image, HttpServletRequest request) throws Exception{

        //  保存类别
        categoryService.add(bean);

        //  调用上传图片的方法
        SaveOrUpdateImageFile(bean, image,request);

        //  返回值
        return bean ;
    }


    /**
     * 图片保存和修改方法
     * @param bean
     *             实体对象
     * @param image
     *              上传的图片
     * @param request
     *              http请求
     * @throws Exception
     *                  异常处理
     */
    public void SaveOrUpdateImageFile(Category bean, MultipartFile image, HttpServletRequest request) throws Exception{

        File imageFolder = new File(request.getServletContext().getRealPath("img/category")) ;
        File file = new File(imageFolder, bean.getId() + ".jpg") ;
        if (file.getParentFile().exists()){
            file.getParentFile().mkdirs() ;
        }

        image.transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file) ;

        ImageIO.write(img, "img", file) ;

    }

    /**
     * 根据类别id删除分类
     * @param id
     *          分类id
     * @param request
     *              http请求
     * @return
     *         返回值
     */
    @DeleteMapping("/categories/{id}")
    public String delete(@PathVariable("id") int id, HttpServletRequest request) throws Exception{

        //  调用service层方法，根据id删除分类
        categoryService.delete(id);
        //  根据id获取图片的id
        File imageFolder = new File(request.getServletContext().getRealPath("img/category")) ;
        File file = new File(imageFolder, id + ".jpg") ;

        //  将图片删除
        file.delete() ;
        return null ;
    }

    /**
     *  编辑分类
     * @param id
     *          编辑分类的id
     * @return
     *        返回分类对象
     * @throws Exception
     *                  抛出的异常
     */
    @GetMapping("/categories/{id}")
    public Category getCategoryById(@PathVariable("id") int id) throws Exception{

        Category bean = categoryService.getCategoryById(id) ;

        return bean ;
    }


    /**
     * 更新分类
     * @param bean
     *              需要更新分类的对象
     * @param image
     *              图片
     * @param request
     *              request请求
     * @return
     *          返回分类对象
     * @throws Exception
     *                  抛出的异常
     */
    @PutMapping("/categories/{id}")
    public Object update(Category bean, MultipartFile image, HttpServletRequest request) throws Exception{

        //  获取name参数
        String name = request.getParameter("name") ;
        bean.setName(name);

        //  更新操作
        categoryService.update(bean);

        //  判断图片是否为空
        if (image != null){
            SaveOrUpdateImageFile(bean, image, request);
        }

        //  返回分类对象
        return bean ;
    }

}
