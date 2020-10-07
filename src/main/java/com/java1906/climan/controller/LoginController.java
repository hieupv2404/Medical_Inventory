package com.java1906.climan.controller;

import com.java1906.climan.data.model.User;
import com.java1906.climan.data.model.UserInfo;
import com.java1906.climan.dto.AuthenticationRequest;
import com.java1906.climan.dto.Credential;
import com.java1906.climan.exception.LogicException;
import com.java1906.climan.services.IUserInfoService;
import com.java1906.climan.services.IUserService;
import com.java1906.climan.utils.TokenManager;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserInfoService userInfoService;

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request) {
        return "login";
    }

    @PostMapping( value = "/doLogin")
    @ResponseBody
    public Object doLogin(@ModelAttribute Credential credential, HttpServletRequest request)  throws LogicException {

        Optional<User> optionalUser = userService.findByUsername(credential.getUserName());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String md5Hex = DigestUtils.md5Hex(credential.getUserPassword()).toUpperCase();
            if (user.getPassword().toUpperCase().equals(md5Hex)) {
                String token = tokenManager.createToken(user.getUsername(), user.getRole());
                request.getSession().setAttribute("role", user.getRole());
                request.getSession().setAttribute("AuthToken", token);
                request.getSession().setAttribute("Username", credential.getUserName());
                Optional<UserInfo> userInfo = userInfoService.get(user.getId());
                if (userInfo.get() != null) {
                    userInfo.get().setToken(token);
                }
                return userInfo.get();
            }
            return new LogicException("Invalid login", HttpStatus.NOT_FOUND);
        }

        return new LogicException("Invalid login", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/index")
    public String index()
    {
        return "index";
    }

    @GetMapping("/logout")
    @ResponseBody
    public ResponseEntity logout(HttpSession session) {
        session.removeAttribute("role");
        session.removeAttribute("AuthToken");
        session.removeAttribute("Username");
        return ResponseEntity.ok(" ");
    }
}
// set session co 3 thu: role la gi, auth cho user da dang nhap hay chua,
