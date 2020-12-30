package ua.hubanov.exceptions;

public class AlreadyInCartException extends Throwable {
    final static String message = "Product is already in cart";

    public AlreadyInCartException() {
        super(message);
    }
}
