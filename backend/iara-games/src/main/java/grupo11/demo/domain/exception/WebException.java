package grupo11.demo.domain.exception;

public abstract class WebException extends RuntimeException {

    public abstract int getStatus();

    public abstract String getMessage();
}
