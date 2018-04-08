package com.mongcent.tnaot.api.v1;

import com.mongcent.tnaot.model.Permission;
import com.mongcent.tnaot.model.Role;
import com.mongcent.tnaot.model.User;
import com.mongcent.tnaot.service.UserServiceV1;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/sys/user/v1")
@ResponseBody
public class UserController {


    @Autowired
    private UserServiceV1 userServiceV1;

    /**
     * 登出
     * @return
     */
    @GetMapping("/login")
    public Object login(){
        return "login";
    }

    /**
     * 登入
     * @return
     */
    @PostMapping("/login")
    public Object login(@RequestBody Map map){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(map.get("userName").toString(), map.get("password").toString());
        subject.login(usernamePasswordToken);
        return "login";
    }

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    //登出
    @RequestMapping(value = "/logout")
    public String logout(){
        return "logout";
    }

    //错误页面展示
    @RequestMapping(value = "/error",method = RequestMethod.POST)
    public String error(){
        return "error ok!";
    }

    //数据初始化
    @RequestMapping(value = "/addUser")
    public String addUser(){
        Permission permission1 = new Permission();
        permission1.setPermission("create");
        Permission permission2 = new Permission();
        permission2.setPermission("update");

        Role role = new Role();
        role.setPermissions(Arrays.asList(permission1,permission2));
        role.setRoleName("admin");

        User user  = new User();
        user.setName("aaa");
        user.setPassword(123456);
        user.setRoles(Arrays.asList(role));

        User userInfo =userServiceV1.addUser(user);
        return "addUser is ok! \n" + userInfo;
    }

    //角色初始化
    @RequestMapping(value = "/addRole")
    public String addRole(){
        Permission permission1 = new Permission();
        permission1.setPermission("create");
        Permission permission2 = new Permission();
        permission2.setPermission("update");

        Role role = new Role();
        String roleName = "role_"+(new Random().nextInt(100) + 1);
        role.setRoleName(roleName);
        role.setPermissions(Arrays.asList(permission1,permission2));
        Role roleInfo = userServiceV1.addRole(role);
        return "addRole is ok! \n" + roleInfo;
    }

    //注解的使用
    @RequiresRoles("admin")
    @RequiresPermissions("create")
    @RequestMapping(value = "/create")
    public String create(){
        return "Create success!";
    }

    @GetMapping("/view/page")
    public String view(Model model){
        model.addAttribute("name","dajun");
        return "hello";
    }
}
