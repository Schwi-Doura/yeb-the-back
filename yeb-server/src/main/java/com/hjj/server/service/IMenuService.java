package com.hjj.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hjj.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2022-08-23
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> getMenusByAdminId();

    //根据角色获取菜单列表
    List<Menu> getMenusWithRole();

    //查询所有菜单
    List<Menu> getAllMenus();
}
