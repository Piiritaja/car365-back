package ee.taltech.cars.service;

import ee.taltech.cars.dto.LoginOwnerDto;
import ee.taltech.cars.dto.LoginOwnerResponse;
import ee.taltech.cars.dto.RegisterOwnerDto;
import ee.taltech.cars.exception.InvalidUserException;
import ee.taltech.cars.exception.LoginException;
import ee.taltech.cars.exception.UserNotFoundException;
import ee.taltech.cars.models.Owner;
import ee.taltech.cars.repository.OwnerRepository;
import ee.taltech.cars.security.DbRole;
import ee.taltech.cars.security.JwtTokenProvider;
import ee.taltech.cars.security.MyUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
@AllArgsConstructor
public class OwnerService {

    @Autowired
    private OwnerRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

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

    public Owner save(RegisterOwnerDto owner) {
        if (owner.getFirstName() == null || owner.getFirstName().equals("") ||
                owner.getLastName() == null || owner.getLastName().equals("")) {
            throw new InvalidUserException();
        }
        Owner owner1 = Owner.builder()
                .firstName(owner.getFirstName())
                .lastName(owner.getLastName())
                .email(owner.getEmail())
                .password(passwordEncoder.encode(owner.getPassword()))
                .phone(owner.getPhone())
                .role(DbRole.USER)
                .build();
        return userRepository.save(owner1);
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

    public LoginOwnerResponse login(LoginOwnerDto loginDto) {
        if (isBlank(loginDto.getEmail())) {
            throw new LoginException("missing email");
        }
        if (isBlank(loginDto.getPassword())) {
            throw new LoginException("missing password");
        }
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        MyUser myUser = (MyUser) authenticate.getPrincipal();
        String token = jwtTokenProvider.generateToken(myUser);
        return LoginOwnerResponse.builder()
                .id(myUser.getId())
                .email(myUser.getUsername())
                .token(token)
                .role(myUser.getDbRole())
                .build();

    }
}

