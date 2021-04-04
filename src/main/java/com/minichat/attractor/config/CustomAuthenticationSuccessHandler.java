package com.minichat.attractor.config;

import com.minichat.attractor.model.User;
import com.minichat.attractor.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
@Autowired
    private UserService userService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        String userName = authentication.getName();
        User theUser = userService.findByUserName(userName).orElseThrow(()->
                new UsernameNotFoundException("user is invalid"));
        HttpSession session = request.getSession();
        session.setAttribute("user", theUser);
        response.sendRedirect(request.getContextPath() + "/chats");
    }

}
