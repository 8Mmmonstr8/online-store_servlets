package ua.hubanov.controller;

import ua.hubanov.controller.command.*;
import ua.hubanov.controller.command.admin.AdminHomeCommand;
import ua.hubanov.controller.command.admin.CancelOrderCommand;
import ua.hubanov.controller.command.admin.OrderDetailsCommand;
import ua.hubanov.controller.command.admin.OrdersManagerCommand;
import ua.hubanov.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Servlet extends HttpServlet {
    private final Map<String, Command> commands = new HashMap<>();

    @Override
    public void init(){
        commands.put("logout", new LogOutCommand());
        commands.put("login", new LoginCommand());
        commands.put("", new HomeCommand());
        commands.put("user_home", new UserHomeCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("user_home/cart", new UserCartCommand());
        commands.put("admin_home", new AdminHomeCommand());
        commands.put("admin_home/orders", new OrdersManagerCommand());
        commands.put("admin_home/orders/details", new OrderDetailsCommand());
        commands.put("admin_home/orders/decline", new CancelOrderCommand());
//        commands.put("user/personal-account" , new PersonalAccount());
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        path = path.replaceAll(".*/store/", "");
        Command command = commands.getOrDefault(path ,
                (r)->"/views/unsupported_page.jsp");
        String page = command.execute(request);
        if (page.startsWith("redirect:")) {
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}

