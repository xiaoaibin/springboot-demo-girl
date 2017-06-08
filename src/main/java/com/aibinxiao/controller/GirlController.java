package com.aibinxiao.controller;

import com.aibinxiao.domain.Girl;
import com.aibinxiao.repository.GirlRepository;
import com.aibinxiao.service.GirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by aibin on 2017/6/5.
 */
@RestController
public class GirlController {
    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;


    /**
     * 获取所有女生的信息
     * @return
     */
    @GetMapping(value = "/girls")
    public List<Girl> girlList(){
        List<Girl> girls = girlRepository.findAll();
        return girls;
    }

    /**
     * 新增一个女生
     * @param cupSize
     * @param age
     * @return
     */
    @PostMapping(value = "/addgirl")
    public String addGirl(@RequestParam("cupSize") String cupSize, @RequestParam("age") Integer age){
        Girl girl = new Girl();
        girl.setAge(age);
        girl.setCupSize(cupSize);

        Girl newGirl = girlRepository.save(girl);
        return newGirl.toString();
    }

    /**
     * 根据id查询一个女生
     * @param id
     * @return
     */
    @GetMapping(value = "/getone/{id}")
    public Girl findOne(@PathVariable(value = "id") Integer id){
        return girlRepository.findOne(id);
    }

    /**
     * 根据id更新一个女生的信息
     * @param id
     * @param cupSize
     * @param age
     * @return
     */
    @PutMapping(value = "/update/{id}")
    public Girl updateGirl(@PathVariable(value = "id") Integer id,
                           @RequestParam(value = "cupSize") String cupSize,
                           @RequestParam(value = "age") Integer age){
        Girl girl = new Girl();
        girl.setId(id);
        girl.setCupSize(cupSize);
        girl.setAge(age);

        girlRepository.save(girl);
        return girlRepository.getOne(id);
    }

    /**
     * 根据id删除一个女生
     * @param id
     */
    @DeleteMapping(value = "/delete/{id}")
    public void deleteGirl(@PathVariable(value = "id") Integer id){
        girlRepository.delete(id);
    }

    /**
     * 根据年龄查询
     * @param age
     * @return
     */
    @GetMapping(value = "/findbyage/{age}")
    public List<Girl> findByAge(@PathVariable(value = "age") Integer age){
        return girlRepository.findByAge(age);
    }

    @PostMapping(value = "insettwo")
    public void insertTwoGirl(){
        girlService.insertTwo();
    }
}
