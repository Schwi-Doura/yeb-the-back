package com.hjj.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjj.server.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2022-08-23
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    //根据用户id查角色列表
    List<Role> getRoles(Integer adminId);
}
