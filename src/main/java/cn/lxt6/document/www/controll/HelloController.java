package cn.lxt6.document.www.controll;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试
 */
@Controller
public class HelloController {
    @RequestMapping({"/","/index", "/index.html"})
    public Object init(){
        return "index";
    }

    @ResponseBody
    @GetMapping("/test")
    public Object test(){

        return "assaas";
    }

    @ResponseBody
    @GetMapping("/hello")
    public Object hello(){
        return "hello";
    }

    @ResponseBody
    @GetMapping("/testTransaction")
    public Object testTransaction(){
        return "testTransaction";
    }
}
