package com.hjj.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hjj.server.pojo.MenuRole;
import com.hjj.server.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2022-08-23
 */
public interface IMenuRoleService extends IService<MenuRole> {

    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
