package com.hjj.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjj.server.pojo.MenuRole;
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
public interface MenuRoleMapper extends BaseMapper<MenuRole> {
    //批量更新
    Integer insertRecord(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
