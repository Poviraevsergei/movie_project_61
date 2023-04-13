package com.tms.security;

import com.tms.domain.User;
import com.tms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class JwtFilter extends GenericFilterBean {

    private final String AUTHORIZATION_HEADER = "Authorization";

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Autowired
    public JwtFilter(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = ((HttpServletRequest) request).getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(token) && token.startsWith("Bearer ") && jwtService.isValid(token.substring(7)) ) {
            String login = jwtService.getLoginFromToken(token.substring(7));
            User userFromDb = userRepository.findUserByLogin(login).orElseThrow(() -> new UsernameNotFoundException(login));

            UserDetails secUser = org.springframework.security.core.userdetails.User.builder()
                    .username(userFromDb.getLogin())
                    .password(userFromDb.getPassword())
                    .roles(userFromDb.getRole())
                    .build();
            UsernamePasswordAuthenticationToken authUser = new UsernamePasswordAuthenticationToken(secUser, null, secUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authUser);
        }
        chain.doFilter(request, response);
    }
}