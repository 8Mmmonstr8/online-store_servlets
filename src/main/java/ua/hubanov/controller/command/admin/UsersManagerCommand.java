package ua.hubanov.controller.command.admin;

import ua.hubanov.controller.command.Command;
import ua.hubanov.model.entity.User;
import ua.hubanov.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class UsersManagerCommand implements Command {
    UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");
        String userRole = (String) request.getSession().getAttribute("role");

        request.setAttribute("user", loggedUser);
        request.setAttribute("role", userRole);

        request.setAttribute("users", userService.getAllUsers());

        return "/views/admin/users.jsp";
    }
}
