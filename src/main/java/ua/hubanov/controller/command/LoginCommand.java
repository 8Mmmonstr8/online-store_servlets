package ua.hubanov.controller.command;

import org.mindrot.jbcrypt.BCrypt;
import ua.hubanov.exceptions.UserNotFoundException;
import ua.hubanov.model.entity.User;
import ua.hubanov.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private final UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null)
            return "/views/login_page.jsp";

        User user = null;
        boolean hasError = false;
        String errorString = null;

        if (email == null || password == null || email.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required Email and password!";
        } else {
            try {
                // Найти user в DB.
                user = userService.findUserByEmail(email);
                boolean isPasswordValid = BCrypt.checkpw(password, user.getPassword());

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
            // Сохранить информацию в request attribute и forward.
            request.setAttribute("errorString", errorString);
            return "/views/login_page.jsp";
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("loggedUser", user);
            session.setAttribute("role", user.getRole().name());

            // Redirect на страницу /userInfo.
            return "redirect:/store/user_home";
        }
    }
}
