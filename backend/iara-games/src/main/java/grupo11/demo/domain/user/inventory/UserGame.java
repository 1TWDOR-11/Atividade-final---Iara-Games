package grupo11.demo.domain.user.inventory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long gameId;

    private long userId;
}
