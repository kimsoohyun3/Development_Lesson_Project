package project.lesson.exception.common;

public class CResourceNotExistException extends RuntimeException {

    public CResourceNotExistException(String message, Throwable t) {
        super(message, t);
    }

    public CResourceNotExistException(String message) {
        super(message);
    }

    public CResourceNotExistException() {
        super();
    }
}
