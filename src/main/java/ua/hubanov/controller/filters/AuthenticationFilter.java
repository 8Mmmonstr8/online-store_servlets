package ua.hubanov.controller.filters;

import ua.hubanov.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String path = httpServletRequest.getRequestURI();
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("loggedUser");
        String userRole = (String) session.getAttribute("role");

        boolean isAccessedRequest = path.contains("/home") || path.contains("/login")
                || path.contains("/logout") || path.contains("/registration");

        if (path.equals("/store/")) {
            httpServletResponse.sendRedirect("/store/home");
            return;
        }

        if (user != null) {
            if (path.contains("/admin_home") && !userRole.equals("ADMIN")) {
                httpServletRequest
                        .getRequestDispatcher("/views/access_denied.jsp")
                        .forward(servletRequest, servletResponse);
            } else if (path.contains("/admin_home") && userRole.equals("ADMIN")) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else if (path.contains("/user_home")
                    && (userRole.equals("USER") || userRole.equals("ADMIN"))
                    && user.isNonLocked()) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else if (path.contains("/logout") || path.contains("/home")) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else if (path.contains("/login") || path.contains("/registration")) {
                httpServletRequest
                        .getRequestDispatcher("/views/logged_in_error.jsp")
                        .forward(servletRequest, servletResponse);
            } else {
                httpServletRequest
                        .getRequestDispatcher("/views/unsupported_page.jsp")
                        .forward(servletRequest, servletResponse);
            }
        } else if (user == null) {
            if (isAccessedRequest) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else if (path.contains("user_home") || path.contains("admin_home")) {
                httpServletRequest
                        .getRequestDispatcher("/views/access_denied.jsp")
                        .forward(servletRequest, servletResponse);
            } else {
                httpServletRequest
                        .getRequestDispatcher("/views/unsupported_page.jsp")
                        .forward(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
