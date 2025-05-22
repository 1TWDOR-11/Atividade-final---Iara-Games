package grupo11.demo.domain.order.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> { }
