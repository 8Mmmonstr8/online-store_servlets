package ua.hubanov.controller.command.admin;

import ua.hubanov.controller.command.Command;
import ua.hubanov.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class UnblockUserCommand implements Command {
    UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        Long userId = Long.valueOf(request.getParameter("userId"));

        userService.unblockUser(userId);

        return "redirect:/store/admin_home/users";
    }
}
