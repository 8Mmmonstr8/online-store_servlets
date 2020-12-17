package ua.hubanov.controller.command;

import org.mindrot.jbcrypt.BCrypt;
import ua.hubanov.exceptions.UserNotFoundException;
import ua.hubanov.model.entity.User;
import ua.hubanov.model.entity.UserRole;
import ua.hubanov.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationCommand implements Command {
    private final UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        if (firstName == null)
            return "/views/registration.jsp";

        User newUser = null;
        boolean hasError = false;
        String errorString = null;
        String emailError = null;

        if (email == null || password == null || email.length() == 0 || password.length() == 0 ||
            firstName == null || lastName == null || firstName.length() == 0 || lastName.length() == 0) {
            hasError = true;
            errorString = "All fields required to be filled";
        } else if (!validateEmail(email)) {
            hasError = true;
            emailError = "Email should be valid. (Example: tom@gmail.com)";
        } else {
            try {
                // Найти user в DB.
                newUser = userService.findUserByEmail(email);
                if (newUser != null) {
                    hasError = true;
                    errorString = "User with this Email already exists";
                }
            } catch (UserNotFoundException e) {

            }
        }

        if (hasError) {
            // Сохранить информацию в request attribute и forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("emailError", emailError);
            return "/views/registration.jsp";
        } else {
//            HttpSession session = request.getSession();
//            session.setAttribute("loggedUser", user);
//            session.setAttribute("role", user.getRole().name());
            newUser = new User();
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setEmail(email);
            newUser.setRole(UserRole.USER);
            newUser.setNonLocked(true);
            newUser.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));

            // TODO create and add new cart

            if (userService.saveUser(newUser))
                return "redirect:/store/login";
            else {
                errorString = "User has not been created, please try again";
                request.setAttribute("errorString", errorString);
                return "/views/registration.jsp";
            }

        }


    }

    public boolean validateEmail(String email) {
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
