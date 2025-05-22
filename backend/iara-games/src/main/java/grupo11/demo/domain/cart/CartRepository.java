package grupo11.demo.domain.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(long userId);
}
