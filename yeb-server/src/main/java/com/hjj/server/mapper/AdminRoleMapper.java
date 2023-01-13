package com.hjj.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjj.server.pojo.AdminRole;
import com.hjj.server.pojo.RespBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2022-08-23
 */
@Repository
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    //添加操作员角色
    Integer addAdminRole(@Param("adminId") Integer adminId, @Param("rids") Integer[] rids);
}
