package com.hjj.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjj.server.pojo.Menu;
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
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getMenusByAdminId(Integer adminId);

    List<Menu> getMenusWithRole();

    List<Menu> getAllMenus();
}
