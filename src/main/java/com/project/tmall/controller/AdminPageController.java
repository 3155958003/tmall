package com.project.tmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author hx
 * @create 2020-03-23 22:39
 *
 * 因项目为前后端分离，所以
 * 后台页面的跳转服务全部放在AdminPageControllre类中
 *
 */

@Controller
public class AdminPageController {

    /**
     * admin账号登录时，重定向到查询列表界面
     * @return
     *        返回值
     */
    @GetMapping("/admin")
    public String admin(){
        return "redirect:admin_category_list" ;
    }

    /**
     * 访问时，跳转到列表界面
     * @return
     *         返回值
     */
    @GetMapping(value = "admin_category_list")
    public String listCategory(){
        return "admin/listCategory" ;
    }

    /**
     * 点击编辑时，跳转到修改页面
     * @return
     *          返回值
     */
    @GetMapping(value = "admin_category_edit")
    public String editCategory(){
        return "admin/editCategory" ;
    }
}
