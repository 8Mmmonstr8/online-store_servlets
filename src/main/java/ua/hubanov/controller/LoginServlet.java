package ua.hubanov.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.hubanov.exceptions.UserNotFoundException;
import ua.hubanov.model.entity.User;
import ua.hubanov.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/login_page.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = null;
        boolean hasError = false;
        String errorString = null;

        if (email == null || password == null || email.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            try {
                // Найти user в DB.
                user = userService.findUserByEmail(email);
                boolean isPasswordValid = bCryptPasswordEncoder.matches(password, user.getPassword());

                if (!isPasswordValid) {
                    hasError = true;
                    errorString = "Invalid password";
                }
            } catch (UserNotFoundException e) {
                hasError = true;
                errorString = e.getMessage();
            }
        }

        if (hasError) {
            // Сохранить информацию в request attribute перед forward.
            request.setAttribute("errorString", errorString);

            // Forward (перенаправить) к странице /WEB-INF/views/login.jsp
            request.getRequestDispatcher("/WEB-INF/views/login_page.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("loggedUser", user);

            // Redirect (Перенаправить) на страницу /userInfo.
            response.sendRedirect("/user_home");
        }
    }
}
