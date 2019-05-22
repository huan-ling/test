package book.test.controller;

import book.test.pojo.UserDto;
import book.test.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class TestController {

    @Autowired
    private TestService testService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @RequestMapping( value = "test", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String test(String str, HttpServletRequest request) throws UnsupportedEncodingException {
        String str1 = new String(str.getBytes("ISO8859-1"),"utf-8");
        LOGGER.info(str1);
        System.err.println("当前"+System.currentTimeMillis()+"  "+str+request.getCharacterEncoding());
        String test = testService.test();
        return test+"  "+str;
    }

    @GetMapping(value = "get")
    public String get(){
        return "index";
    }

    @GetMapping(value = "js")
    public String getJs(){
        return "js";
    }

    @PostMapping("register")
    public String register(UserDto userDto){
        LOGGER.info(""+userDto);
        return "end";
    }


}
