package com.example.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.common.ErrorCode;
import com.example.backend.common.Result;
import com.example.backend.common.ResultUtils;
import com.example.backend.exception.MyException;
import com.example.backend.model.domain.User;
import com.example.backend.model.domain.request.UserLoginRequest;
import com.example.backend.model.domain.request.UserRegisterRequest;
import com.example.backend.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.backend.contant.UserContant.ADMIN_ROLE;
import static com.example.backend.contant.UserContant.USER_LOGIN_STATE;

/**
 * 用户接口
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Result<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new MyException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return null;
        }
        long id = userService.userRegister(userAccount, userPassword, checkPassword);
        if (id > 0) {
            return new Result<>(0, id, "注册成功");
        } else {
            return new Result<>(1, id, "注册失败");
        }
    }

    @PostMapping("/login")
    public Result<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new MyException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new MyException(ErrorCode.NOTMATCH_ERROR);
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        return new Result<>(0, user, "登录成功");
    }

    @PostMapping("/logout")
    public Result<Integer> userLogout(HttpServletRequest request) {
        if (request == null) {
            return ResultUtils.fail(ErrorCode.PARAMS_ERROR);
        }
        int result = userService.userLogout(request);
        return new Result<>(0, result, "注销成功");
    }

    @GetMapping("/current")
    public Result<User> getCurrentUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null) {
            return ResultUtils.fail(ErrorCode.PARAMS_ERROR);
        }
        Long id = currentUser.getId();
        User user = userService.getById(id);
        return new Result<>(0, user, "成功获取当前用户");
    }

    @GetMapping("/search")
    public Result<List<User>> searchUsers(String username, HttpServletRequest request) {
        if (!isAdmin(request)) {
            return null;
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        List<User> list = userService.list(queryWrapper);
        return new Result<>(0, list, "成功获取用户");
    }

    @PostMapping("/delete")
    public Result<Boolean> deleteUser(@RequestBody long id, HttpServletRequest request) {
        if (!isAdmin(request)) {
            return new Result<>(1, false, "删除用户失败");
        }
        if (id <= 0) {
            return new Result<>(1, false, "删除用户失败");
        }
        boolean b = userService.removeById(id);
        return new Result<>(0, b, "成功删除用户");
    }

    @PostMapping("/update")
    public Result<String> updateUser(@RequestBody User user) {
        boolean result = userService.updateById(user);
        if (result) {
            return ResultUtils.success("用户信息修改成功");
        }
        return ResultUtils.fail("用户信息修改失败");
    }

    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    public Boolean isAdmin(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        if (user.getUserRole() != ADMIN_ROLE || user == null) {
            return false;
        }
        return true;
    }
}
