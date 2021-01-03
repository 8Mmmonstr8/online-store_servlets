package ua.hubanov;

import org.junit.Test;
import ua.hubanov.model.entity.User;
import ua.hubanov.model.service.UserService;

import java.util.List;

public class UserServiceTests {
    UserService userService = new UserService();

    @Test
    public void getAllUsersTest() {
        List<User> users = userService.getAllUsers();
        users.forEach(x -> System.out.println(x.getEmail() + " " + x.getRole().name()));
    }

    @Test
    public void blockUserTest() {
        userService.blockUser(37L);
    }

    @Test
    public void unblockUserTest() {
        userService.unblockUser(37L);
    }

    @Test
    public void deleteUserTest() {
        userService.deleteUser(37L);
    }
}
