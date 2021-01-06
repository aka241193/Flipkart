package exceptions;

@SuppressWarnings("serial")
public class UserPresentException extends Exception {
    public UserPresentException(String msg) {
        super(msg);
    }
}
