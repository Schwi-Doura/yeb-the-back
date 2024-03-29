package com.hjj.server;

import com.hjj.server.pojo.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

//操作员工具类
public class AdminUtils {

    //获取当前登录的操作员
    public static Admin getCurrentAdmin() {
        return (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
