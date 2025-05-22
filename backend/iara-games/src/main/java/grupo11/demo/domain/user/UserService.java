package grupo11.demo.domain.user;

import grupo11.demo.domain.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> list() {
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public User find(long id) {
        return userRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Transactional
    public void update(long id, User user) {
        if (!userRepository.existsById(id)) throw new NotFoundException();

        user.setId(id);
        userRepository.save(user);
    }

    @Transactional
    public void delete(long id) {
        if (!userRepository.existsById(id)) throw new NotFoundException();
        userRepository.deleteById(id);
    }
}
