package com.hjj.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hjj.server.pojo.Employee;
import com.hjj.server.pojo.RespBean;
import com.hjj.server.pojo.RespPageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2022-08-23
 */
public interface IEmployeeService extends IService<Employee> {

    RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    RespBean maxWorkID();

    RespBean addEmp(Employee employee);

    List<Employee> getEmployee(Integer id);
}
