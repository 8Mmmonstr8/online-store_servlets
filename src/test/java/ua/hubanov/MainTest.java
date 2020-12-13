package ua.hubanov;


import org.junit.Test;
import ua.hubanov.model.entity.User;
import ua.hubanov.model.service.UserService;

import java.util.List;

public class MainTest {
    UserService userService = new UserService();

    @Test
    public void findByEmailTest() throws Exception {
        User user = userService.findUserByEmail("test@test");
        System.out.println(user.getId());
    }

    @Test
    public void findAllUsersTest() {
        List<User> users = userService.getAllUsers();
        users.stream().forEach(x -> System.out.println(x.getId()));
    }
}
