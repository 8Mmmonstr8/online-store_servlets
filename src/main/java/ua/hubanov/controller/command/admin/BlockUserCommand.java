package ua.hubanov.controller.command.admin;

import ua.hubanov.controller.command.Command;
import ua.hubanov.model.entity.User;
import ua.hubanov.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class BlockUserCommand implements Command {
    UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");
        String userRole = (String) request.getSession().getAttribute("role");

        request.setAttribute("user", loggedUser);
        request.setAttribute("role", userRole);

        Long userId = Long.valueOf(request.getParameter("userId"));

        userService.blockUser(userId);


        return "redirect:/store/admin_home/users";
    }
}
