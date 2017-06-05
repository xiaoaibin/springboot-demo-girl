package com.aibinxiao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by aibin on 2017/6/5.
 */
@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    // 事务操作，同时操作多条数据，一个操作失败，其他操作都失败
    @Transactional
    public void insertTwo(){
        Girl girlA = new Girl();
        girlA.setAge(18);
        girlA.setCupSize("F");
        girlRepository.save(girlA);

        Girl girlB = new Girl();
        girlB.setAge(19);
        girlB.setCupSize("AAAA");// 数据库该字段长度为1，报错，没有加事务前，girlA会被添加到数据库，而girlB不会；添加事务后，girB添加时出错，同时girlA也不会添加
        girlRepository.save(girlB);

    }
}
