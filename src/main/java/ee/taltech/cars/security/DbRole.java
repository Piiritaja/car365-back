package ee.taltech.cars.security;

public enum DbRole {
    USER, PREMIUM, ADMIN;

    public boolean isAdmin() {
        return this == ADMIN;
    }

    public boolean isPremium() {
        return this == PREMIUM;
    }

    public String toSpringRole() {
        return "ROLE_" + this.name();
    }
}

