package ua.hubanov.exceptions;

public class UserNotFoundException extends RuntimeException {
    final static String message = "User with this Email not found";

    public UserNotFoundException() {
        super(message);
    }
}
