package com.mongcent.tnaot.api.v1;

import com.mongcent.tnaot.model.Permission;
import com.mongcent.tnaot.model.Role;
import com.mongcent.tnaot.model.User;
import com.mongcent.tnaot.service.UserServiceV1;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.Random;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceV1 userServiceV1;

    @GetMapping("/login")
    public String login() {
        logger.info("request input getLogin");
        return "login";
    }

    /**
     * 登入
     */
    @PostMapping("/login")
    public Object login(Model model, String userName, String password) {
        UsernamePasswordToken usernamePasswordToken = null;
        Subject subject = null;
        try {
            subject = SecurityUtils.getSubject();
            usernamePasswordToken = new UsernamePasswordToken(userName, password);
            subject.login(usernamePasswordToken);
            logger.info("账号：" + userName + "-密码：" + password + "sessionId:" + subject.getSession().getId());
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
        } catch (LockedAccountException e) {
            model.addAttribute("msg", "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            model.addAttribute("msg", "该用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //验证是否登录成功
        if (subject.isAuthenticated()) {
            logger.info("用户[" + userName + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
            model.addAttribute("msg", "用户【"+userName+"】登陆成功！sessionId: "+subject.getSession().getId());
            return "index";
        } else {
            usernamePasswordToken.clear();
            return "login";
        }
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    //登出
    @GetMapping("/logout")
    public String logout() {
        /*SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message", "您已安全退出");*/
        return "logout";
    }

    //错误页面展示
    @GetMapping("/error")
    public String error() {
        return "error";
    }

    //数据初始化
    @GetMapping("/addUser")
    public String addUser() {
        Permission permission1 = new Permission();
        permission1.setPermission("create");
        Permission permission2 = new Permission();
        permission2.setPermission("update");

        Role role = new Role();
        role.setPermissions(Arrays.asList(permission1, permission2));
        role.setRoleName("admin");

        User user = new User();
        user.setName("aaa");
        user.setPassword(123456);
        user.setRoles(Arrays.asList(role));

        User userInfo = userServiceV1.addUser(user);
        return "addUser is ok! \n" + userInfo;
    }

    //角色初始化
    @GetMapping("/addRole")
    public String addRole() {
        Permission permission1 = new Permission();
        permission1.setPermission("create");
        Permission permission2 = new Permission();
        permission2.setPermission("update");

        Role role = new Role();
        String roleName = "role_" + (new Random().nextInt(100) + 1);
        role.setRoleName(roleName);
        role.setPermissions(Arrays.asList(permission1, permission2));
        Role roleInfo = userServiceV1.addRole(role);
        return "addRole is ok! \n" + roleInfo;
    }

    //注解的使用
    @RequiresRoles("admin")
    @RequiresPermissions("create")
    @GetMapping("/create")
    public String create() {
        return "Create success!";
    }

    @GetMapping("/view/page")
    public String view(Model model) {
        model.addAttribute("name", "dajun");
        return "hello";
    }

    //设置用户登陆次数
    public int setUserLoginNum(){
        return 0;
    }
}
