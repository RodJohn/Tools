package com.john.rod.booting.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.john.rod.booting.common.Context;
import com.john.rod.booting.common.CurUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Slf4j
//@Configuration
public class TokenFilter implements Filter {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void doFilter(ServletRequest srequest, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) srequest;
            httpRequest = new BufferedServletRequestWrapper(httpRequest);
            initCurUser(httpRequest);
            filterChain.doFilter(httpRequest,response);
        }finally {
            Context.clearCurUser();
        }
    }

    private void initCurUser(HttpServletRequest request) throws IOException {
        String tokenStr = request.getHeader(CurUser.CUR_USER_TOKEN);
        tokenStr = request.getParameter(CurUser.CUR_USER_TOKEN);
        String method = request.getMethod();
        if (method.equalsIgnoreCase("post") || method.equalsIgnoreCase("put") || method.equalsIgnoreCase("delete")) {
            ServletInputStream inputStream = request.getInputStream();
            @SuppressWarnings("rawtypes")
            Map map = objectMapper.readValue(inputStream, Map.class);
            tokenStr = (String) map.get(CurUser.CUR_USER_TOKEN);
        }
        if (tokenStr != null) {
            CurUser user = new CurUser();
            user.setToken(tokenStr);
            Context.setCurUser(user);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug(" token filter is initing ...");
    }

    @Override
    public void destroy() {
    }


}
