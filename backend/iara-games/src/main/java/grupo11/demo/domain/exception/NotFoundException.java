package grupo11.demo.domain.exception;

public class NotFoundException extends WebException {

    @Override
    public int getStatus() {
        return 404;
    }

    @Override
    public String getMessage() {
        return "Registro não encontrado";
    }
}
