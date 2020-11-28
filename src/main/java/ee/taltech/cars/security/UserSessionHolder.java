package ee.taltech.cars.security;

import ee.taltech.cars.exception.AccessForbiddenException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

public class UserSessionHolder {

    public static MyUser getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) return null;
        return (MyUser) authentication.getPrincipal();
    }

    public static boolean validateAccessByID(UUID id) {
        if (getLoggedInUser().getDbRole().isAdmin() ||
                getLoggedInUser().getId().equals(id)) {
            return true;
        }
        System.out.println("id isadmin compare id");
        System.out.println(getLoggedInUser().getId());
        System.out.println(getLoggedInUser().getDbRole().isAdmin());
        System.out.println(id);
        throw new AccessForbiddenException();
    }
}
