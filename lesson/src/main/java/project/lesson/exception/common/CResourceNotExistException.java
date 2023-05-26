package project.lesson.exception.common;

public class CResourceNotExistException extends RuntimeException {

    public CResourceNotExistException() {
        super();
    }

    public CResourceNotExistException(String message) {
        super(message);
    }
}
