package grupo11.demo.domain.cart.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByItemIdAndCartId(long itemId, long cartId);

    List<CartItem> findByCartId(long cartId);
}
