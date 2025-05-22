package grupo11.demo.domain.exception;

public class UnauthorizedException extends WebException {

    @Override
    public int getStatus() {
        return 401;
    }

    @Override
    public String getMessage() {
        return "Você não tem permissão para isso";
    }
}
