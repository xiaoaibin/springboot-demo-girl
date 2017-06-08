package com.aibinxiao;

import com.aibinxiao.domain.Girl;
import com.aibinxiao.service.GirlService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by aibin on 2017/6/8.
 */
@RunWith(SpringRunner.class)// 表示我们要在测试环境里跑，底层使用的是Junit
@SpringBootTest// 表示将启动我们整个Spring的工程
public class GirlServiceTest {

    @Autowired
    private GirlService girlService;

    @Test
    public void findOneTest(){
        Girl girl = girlService.findOne(17);
        Assert.assertEquals(new Integer(9), girl.getAge());// 测试1 通过
        // Assert.assertEquals(new Integer(14), girl.getAge());// 测试2 不通过
    }
}
