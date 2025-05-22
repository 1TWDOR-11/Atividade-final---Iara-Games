package grupo11.demo.domain.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(long userId);
}
