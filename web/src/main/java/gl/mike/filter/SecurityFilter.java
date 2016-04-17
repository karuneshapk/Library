package gl.mike.filter;

import gl.mike.servlets.LoginAction;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {
    private static final String LOGIN_PAGE = "/servlets/login";

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String uri = httpRequest.getRequestURI();
        System.out.println("start filter");

        if (uri.equals(LOGIN_PAGE)) {
            if (isAuthCompleted(httpRequest)) {
                Cookie[] cookies = ((HttpServletRequest) request).getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        cookie.setValue(null);
                        cookie.setMaxAge(0);
                        ((HttpServletResponse) response).addCookie(cookie);
                    }
                }
                chain.doFilter(request, response);
            }
            chain.doFilter(request, response);
        } else {
            if (isAuthCompleted(httpRequest)) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse) response).sendRedirect(LOGIN_PAGE);
            }
        }
    }

    public void destroy() {

    }

    private boolean isAuthCompleted(HttpServletRequest httpRequest) {
        Cookie[] cookies = httpRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (LoginAction.AUTH_FLAG.equals(cookie.getName()) && "1234".equals(cookie.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }
}
