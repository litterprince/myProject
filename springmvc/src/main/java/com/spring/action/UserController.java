package com.spring.action;

import com.spring.po.UserBean;
import com.spring.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {
    static Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value="/index")
    public String index(HttpServletRequest request){
        /*UserBean param = new UserBean();
        param.setUserName("123");
        List<UserBean> userList = userService.findByParam(param);*/
        logger.error("error");
        return "index";
    }
}
