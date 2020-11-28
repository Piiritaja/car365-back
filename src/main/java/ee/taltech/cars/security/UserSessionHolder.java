package ee.taltech.cars.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserSessionHolder {

    public static Object getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) return null;
        System.out.println(authentication.getPrincipal());
        return authentication.getPrincipal();
    }
}
