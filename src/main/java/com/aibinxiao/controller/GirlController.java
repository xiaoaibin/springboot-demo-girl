package com.aibinxiao.controller;

import com.aibinxiao.aspect.HttpAspect;
import com.aibinxiao.domain.Girl;
import com.aibinxiao.repository.GirlRepository;
import com.aibinxiao.service.GirlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


    private final static Logger logger = LoggerFactory.getLogger(GirlController.class);
    /**
     * 获取所有女生的信息
     * @return
     */
    @GetMapping(value = "/girls")
    public List<Girl> girlList(){
        logger.info("girlList方法开始执行了");
        List<Girl> girls = girlRepository.findAll();
        return girls;
    }

    /**
     * 新增一个女生
     * @param girl
     * @return
     */
    // 当添加girl的参数很多时，我们如果在方法上写很多的参数，就显得很繁杂，我们这时候就可以直接写成Girl对象来接收
    @PostMapping(value = "/addgirl")
    public String addGirl(@Valid Girl girl, BindingResult bindingResult){
        if(bindingResult.hasErrors()){// 如果没有验证通过
            System.out.printf(bindingResult.getFieldError().getDefaultMessage());// 打印错误信息
            return null; // 验证不通过即不让它往下走
        }
        girl.setAge(girl.getAge());
        girl.setCupSize(girl.getCupSize());

        Girl newGirl = girlRepository.save(girl);
        return newGirl.toString();
    }
    /*@PostMapping(value = "/addgirl")
    public String addGirl(@RequestParam("cupSize") String cupSize, @RequestParam("age") Integer age){
        Girl girl = new Girl();
        girl.setAge(age);
        girl.setCupSize(cupSize);

        Girl newGirl = girlRepository.save(girl);
        return newGirl.toString();
    }*/

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
