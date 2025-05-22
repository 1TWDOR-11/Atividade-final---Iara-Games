package grupo11.demo.domain.game;

import grupo11.demo.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> list() {
        return gameRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Game find(long id) {
        return gameRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Game create(Game game) {
        return gameRepository.save(game);
    }

    public Game update(Long id, Game game) {
        game.setId(id);
        return gameRepository.save(game);
    }

    public void delete(Long id) {
        gameRepository.deleteById(id);
    }
}
