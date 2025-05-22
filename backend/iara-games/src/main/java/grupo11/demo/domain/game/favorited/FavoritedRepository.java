package grupo11.demo.domain.game.favorited;

import grupo11.demo.domain.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface FavoritedRepository extends JpaRepository<Game, Long> {}
