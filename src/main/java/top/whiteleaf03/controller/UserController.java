package top.whiteleaf03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.whiteleaf03.modal.dto.UserLoginDto;
import top.whiteleaf03.service.UserService;
import top.whiteleaf03.utils.Result;

/**
 * @author WhiteLeaf03
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserLoginDto userLoginDto) {
        return userService.login(userLoginDto.getUsername(), userLoginDto.getPassword());
    }
}
