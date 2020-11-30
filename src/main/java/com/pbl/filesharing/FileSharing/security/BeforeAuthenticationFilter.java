package com.pbl.filesharing.FileSharing.security;

import com.pbl.filesharing.FileSharing.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Beatrice V.
 * @created 28.11.2020 - 21:53
 * @project FileSharing
 */

public class BeforeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public BeforeAuthenticationFilter() {
        super.setUsernameParameter("email");
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        String email = request.getParameter("email");
        System.out.println("attemptAuthemtication email: " + email);

        return super.attemptAuthentication(request, response);
    }
}
