package grupo11.demo.domain.game;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByIdIn(List<Long> ids);
}
