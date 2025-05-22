package grupo11.demo.domain.exception;

public class GameAlreadyPurchasedException extends WebException {

    @Override
    public int getStatus() {
        return 400;
    }

    @Override
    public String getMessage() {
        return "Você não pode comprar um jogo que já possui";
    }
}
