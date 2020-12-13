package ua.hubanov.controller;

import ua.hubanov.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Проверить, вошел ли пользователь в систему (login) или нет.
        User loggedUser = (User) session.getAttribute("loggedUser");

        // Если еще не вошел в систему (login).
        if (loggedUser == null) {
            // Redirect (Перенаправить) к странице login.
            response.sendRedirect("/login");
            return;
        }
        // Сохранить информацию в request attribute перед тем как forward (перенаправить).
        request.setAttribute("user", loggedUser);

        // Если пользователь уже вошел в систему (login), то forward (перенаправить) к странице
        // /WEB-INF/views/userInfoView.jsp
        request.getRequestDispatcher("/WEB-INF/views/user/user_home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
