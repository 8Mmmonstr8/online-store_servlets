package ua.hubanov.exceptions;

public class StockQuantityIsNotEnoughException extends Throwable {
    final static String message = "Stock quantity is not enough for this operation";

    public StockQuantityIsNotEnoughException() {
        super(message);
    }
}
