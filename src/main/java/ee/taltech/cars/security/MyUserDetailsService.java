package ee.taltech.cars.security;

import ee.taltech.cars.models.Owner;
import ee.taltech.cars.repository.OwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;


/**
 * to load user with username, password and roles (authorities)
 */
@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final OwnerRepository ownerRepository;

    @Override
    public MyUser loadUserByUsername(String email) throws UsernameNotFoundException {
        List<Owner> owners = ownerRepository.findAllByEmail(email);
        if (CollectionUtils.isEmpty(owners)){
            throw new UsernameNotFoundException(format("email not found: %s", email));
        }
        Owner user = owners.get(0);
        return new MyUser(user.getEmail(), user.getPassword(), getAuthorities(user), user.getId(), user.getRole(),
                user.getFirstName(), user.getLastName());
    }

    /**
     * convert db roles to GrantedAuthority object
     */
    private Collection<? extends GrantedAuthority> getAuthorities(Owner user) {
        return getRoles(user)
                .map(DbRole::toSpringRole)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    /**
     * if user is admin, then they get all the roles in application
     */
    private Stream<DbRole> getRoles(Owner user) {
        if (user.getRole().isAdmin()) {
            return Arrays.stream(DbRole.values());
        }
        return Stream.of(user.getRole());
    }
}
