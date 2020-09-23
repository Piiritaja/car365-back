package ee.taltech.cars.Application.service;

import ee.taltech.cars.Application.exception.InvalidUserException;
import ee.taltech.cars.Application.exception.UserNotFoundException;
import ee.taltech.cars.models.User;
import ee.taltech.cars.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public User save(User user) {
        if (user.getId() != null) {
            throw new InvalidUserException("Id is already in use");
        }
        if (user.getFirstName().equals("") || user.getFirstName() == null) {
            throw new InvalidUserException("User missing first name");
        }
        if (user.getLastName().equals("") || user.getLastName() == null) {
            throw new InvalidUserException("User missing last name");
        }
        return userRepository.save(user);

    }

    public User update(User user, Long id) {
        if (user.getId() == null || user.getId().isEmpty()) {
            throw new InvalidUserException("User missing ID");
        }
        if (user.getFirstName().equals("") || user.getFirstName() == null) {
            throw new InvalidUserException("User missing first name");
        }
        if (user.getLastName().equals("") || user.getLastName() == null) {
            throw new InvalidUserException("User missing last name");
        }
        User userDb = findById(id);
        userDb = User.builder()
                .id(userDb.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .cars(user.getCars())
                .build();
        return userRepository.save(userDb);
    }

    public void delete(Long id) {
        userRepository.delete(findById(id));
    }

}
