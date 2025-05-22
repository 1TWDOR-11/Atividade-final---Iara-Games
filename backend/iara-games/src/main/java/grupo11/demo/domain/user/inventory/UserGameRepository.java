package grupo11.demo.domain.user.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserGameRepository extends JpaRepository<UserGame, Long> {
    boolean existsByGameIdAndUserId(long gameId, long userId);
}
