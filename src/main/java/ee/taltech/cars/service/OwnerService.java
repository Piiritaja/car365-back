package ee.taltech.cars.service;

import ee.taltech.cars.exception.InvalidUserException;
import ee.taltech.cars.exception.UserNotFoundException;
import ee.taltech.cars.models.Owner;
import ee.taltech.cars.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository userRepository;

    public List<Owner> findAll() {
        return userRepository.findAll();
    }

    public Owner findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public Owner save(Owner owner) {
        if (owner.getFirstName() == null || owner.getFirstName().equals("") ||
                owner.getLastName() == null || owner.getLastName().equals("")) {
            throw new InvalidUserException();
        }
        return userRepository.save(owner);

    }

    public Owner update(Owner owner, UUID id) {
        if (owner.getFirstName() == null || owner.getFirstName().equals("") ||
                owner.getLastName() == null || owner.getLastName().equals("")) {
            throw new InvalidUserException();
        }
        Owner ownerDb = findById(id);
        ownerDb = Owner.builder()
                .id(ownerDb.getId())
                .firstName(owner.getFirstName())
                .lastName(owner.getLastName())
                .email(owner.getEmail())
                .phone(owner.getPhone())
                .listings(owner.getListings())
                .build();
        return userRepository.save(ownerDb);
    }

    public void delete(UUID id) {
        userRepository.delete(findById(id));
    }

}
