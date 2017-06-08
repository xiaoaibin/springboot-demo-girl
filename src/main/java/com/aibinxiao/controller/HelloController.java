package com.aibinxiao.controller;

import com.aibinxiao.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


/**
 * Created by aibin on 2017/6/4.
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    // @RestController相当于@Controller+@ResponseBody

    // 映射application配置文件中的值
    // 配置文件默认是application.properties,但是因为.yml文件同前缀的可以省略并且也支持yml配置文件，所以推荐使用
    @Value("${cupSize}")
    private String cupSize;

    @Value("${age}")
    private Integer age;

    @Value("${content}")
    private String content;

    @Autowired
    private GirlProperties girlProperties;

    // 使用post请求方式时，我们可以使用chorme中的Postman应用进行测试
   @RequestMapping(value = "/say",method = RequestMethod.POST)
    public String say(){
        return "Hello Spring Boot!";
    }

    // 读取配置文件中的值
    @RequestMapping(value = "/cup",method = RequestMethod.GET)
    public String getCupSize(){
        return cupSize;
    }

    @RequestMapping(value = "/content",method = RequestMethod.GET)
    public String getContent(){
        return content;
    }

    @RequestMapping(value = "/girl",method = RequestMethod.GET)
    public String getGirl(){
        return "Girl:["+"cupSize:"+girlProperties.getCupSize()+" age:"+girlProperties.getAge()+"]";
    }

    // 直接在url后面加参数id，使用@PathVariable
    @RequestMapping(value = "/see/{id}",method = RequestMethod.GET)
    public String see(@PathVariable("id") Integer id){
        // url:http://localhost:8081/girl/hello/see/123
        return "see me id: " + id;
    }

    // 在url前面加参数id，使用@PathVariable
    // 注意@PathVariable中的id要和请求的url一致，但是形参是不需要一致吗比如myId
    @RequestMapping(value = "/{id}/talk",method = RequestMethod.GET)
    public String talk(@PathVariable("id") Integer myId){
        // url:http://localhost:8081/girl/hello/123/talk
        return "talk me id: " + myId;
    }

    // 使用普通加参数方法id，使用@RequestParam
    // 注意@RequestParam中的id要和请求的url一致，但是形参是不需要一致吗比如myId
    @RequestMapping(value = "/watch", method = RequestMethod.GET)
    public String watch(@RequestParam("id") Integer myId){
        // url:http://localhost:8081/girl/hello/watch?id=123
        return "watch me id: " + myId;
    }

    // 我们可以设置是否必须传id, 并且设置默认值
    /*@RequestMapping(value = "/sing", method = RequestMethod.GET)
    public String sing(@RequestParam(value = "id", required = false,defaultValue = "0") Integer myId){
        // url:http://localhost:8081/girl/hello/sing?id=123
        return "sing id: " + myId;
    }*/

    // 比如为了简化代码，我们可以将上面的简化成如下
    @GetMapping(value = "/sing")
    public String sing(@RequestParam(value = "id", required = false,defaultValue = "0") Integer myId){
        // url:http://localhost:8081/girl/hello/sing?id=123
        return "sing id: " + myId;
    }

}
