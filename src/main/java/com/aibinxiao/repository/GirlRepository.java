package com.aibinxiao.repository;

import com.aibinxiao.domain.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by aibin on 2017/6/5.
 */
public interface GirlRepository extends JpaRepository<Girl, Integer> {

    // 通过年龄进行查询
    public List<Girl> findByAge(Integer age);
}
