package com.hjj.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjj.server.mapper.EmployeeMapper;
import com.hjj.server.mapper.MailLogMapper;
import com.hjj.server.pojo.*;
import com.hjj.server.service.IEmployeeService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2022-08-23
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MailLogMapper mailLogMapper;

    @Override
    public RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope) {
        //开启分页
        Page<Employee> page = new Page<>(currentPage, size);
        IPage<Employee> employeeByPage = employeeMapper.getEmployeeByPage(page, employee, beginDateScope);
        RespPageBean respPageBean = new RespPageBean(employeeByPage.getTotal(), employeeByPage.getRecords());
        return respPageBean;
    }

    @Override
    public RespBean maxWorkID() {
        List<Map<String, Object>> maps = employeeMapper.selectMaps(new QueryWrapper<Employee>().select("max(workID)"));
        //得到最大工号
        Integer newWorkID = Integer.parseInt(maps.get(0).get("max(workID)").toString()) + 1;
        return RespBean.success(null, String.format("%08d", newWorkID));
    }

    @Override
    public RespBean addEmp(Employee employee) {
        //处理合同期限，保留两位小数
        LocalDate beginContract = employee.getBeginContract();
        LocalDate endContract = employee.getEndContract();
        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(days / 365.00)));
        if (employeeMapper.insert(employee) == 1) {
            Employee emp = employeeMapper.getEmployee(employee.getId()).get(0);

            //数据库记录发送的消息
            String msgId = UUID.randomUUID().toString();
            MailLog mailLog = new MailLog();
            mailLog.setMsgId(msgId);
            mailLog.setEid(employee.getId());
            mailLog.setStatus(0);
            mailLog.setRouteKey(MailConstants.MAIL_ROUTING_KEY_NAME);
            mailLog.setExchange(MailConstants.MAIL_EXCHANGE_NAME);
            mailLog.setCount(0);
            mailLog.setTryTime(LocalDateTime.now().plusMinutes(MailConstants.MSG_TIMEOUT));
            mailLog.setCreateTime(LocalDateTime.now());
            mailLog.setUpdateTime(LocalDateTime.now());
            mailLogMapper.insert(mailLog);



            //发送信息
            rabbitTemplate.convertAndSend("MailConstants.MAIL_EXCHANGE_NAME",
                    MailConstants.MAIL_ROUTING_KEY_NAME,emp,new CorrelationData(msgId));
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @Override
    public List<Employee> getEmployee(Integer id) {
        return employeeMapper.getEmployee(id);
    }


}
