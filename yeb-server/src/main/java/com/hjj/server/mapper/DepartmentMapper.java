package com.hjj.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjj.server.pojo.Department;
import com.hjj.server.pojo.RespBean;
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
public interface DepartmentMapper extends BaseMapper<Department> {

    List<Department> getAllDepartments(Integer parentId);

    RespBean addDep(Department dep);

    RespBean deleteDep(Department dep);
}
