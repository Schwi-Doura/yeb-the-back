package com.hjj.server;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjj.server.mapper.MenuRoleMapper;
import com.hjj.server.pojo.MenuRole;
import com.hjj.server.pojo.RespBean;
import org.apache.ibatis.transaction.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class YebTest {

    @Autowired
    private MenuRoleMapper menuRoleMapper;

    @Test
    @Transactional
    public void testUpdateMenu() {
        Integer rid = 4;
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid", rid));
        //11,8,7
        //test
        Integer[] mids = {11, 8};
        try {
            if (mids == null || mids.length == 0) {
                System.out.println(RespBean.success("更新成功"));
                throw new Exception();
            }
            Integer result = menuRoleMapper.insertRecord(rid, mids);
            if (result == mids.length) {
                System.out.println(RespBean.success("更新成功"));
                throw new Exception();
            }
            System.out.println(RespBean.error("更新失败"));
            throw new Exception();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public String loginErrorTest() {
        try {
            if (1 == 2) {
            } else {
                throw new Exception("异常");
            }
            System.out.println("异常");
            return "这是返回的成功结果";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "这是返回的失败结果";
        }
    }

    @Test
    public void resultTest() {
        System.out.println(loginErrorTest());
    }
}
