package grupo11.demo.domain.cart;

import java.util.List;

import grupo11.demo.domain.game.Game;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartWithItems {

    private int itemsQuantity;

    private long userId;

    private List<Game> items;
}
