package Tournament;

public class NotRegisteredException extends RuntimeException {
    public NotRegisteredException(String name) {
        super("Player " + name + " is not registered");
    }
}
