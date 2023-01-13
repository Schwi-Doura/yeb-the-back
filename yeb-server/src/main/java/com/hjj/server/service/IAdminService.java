package com.hjj.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hjj.server.pojo.Admin;
import com.hjj.server.pojo.Menu;
import com.hjj.server.pojo.RespBean;
import com.hjj.server.pojo.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2022-08-23
 */
public interface IAdminService extends IService<Admin> {

    //用户登录
    RespBean login(String username, String password, String code, HttpServletRequest request);

    //根据用户名获取用户
    Admin getAdminByUserName(String username);

    //根据用户id查对于角色列表
    List<Role> getRoles(Integer adminId);

    //获取所有操作员
    List<Admin> getAllAdmins(String keywords);

    //更新操作员角色
    RespBean updateAdminRole(Integer adminId, Integer[] rids);
}
