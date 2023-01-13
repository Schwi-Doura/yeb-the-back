package com.hjj.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjj.server.mapper.MenuRoleMapper;
import com.hjj.server.pojo.MenuRole;
import com.hjj.server.pojo.RespBean;
import com.hjj.server.service.IMenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2022-08-23
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

    @Autowired
    private MenuRoleMapper menuRoleMapper;

    @Override
    @Transactional
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid",rid));
        if(mids == null || mids.length == 0) {
            return RespBean.success("更新成功!");
        }
        if(menuRoleMapper.insertRecord(rid,mids) == mids.length) {
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
}
