package grupo11.demo.app.api.game;

import grupo11.demo.domain.game.Game;
import grupo11.demo.domain.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/game")
@CrossOrigin(origins = "*")
public class GamesController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<Game> list() {
        return gameService.list();
    }

    @GetMapping("/{id}")
    public Game find(@PathVariable Long id) {
        return gameService.find(id);
    }

    @PostMapping
    public Game create(@RequestBody Game game) {
        return gameService.create(game);
    }

    @PutMapping("/{id}")
    public Game update(@PathVariable Long id, @RequestBody Game game) {
        return gameService.update(id, game);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        gameService.delete(id);
    }
}
