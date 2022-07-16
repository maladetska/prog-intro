import java.util.Objects;

public class NullableClient {
    private final String name;
    private final String passport;

    public NullableClient(final String name, final String passport) {
        this.name = name;
        this.passport = passport; //Objects.requireNonNull(passport);
    }

    public String getName() {
        return name;
    }

    public boolean checkPassport(final String passport) {
        if (this.passport != null) {
            return passport != null && this.passport.toUpperCase().equals(passport.toUpperCase());
        } else {
            return passport == null;
        }
    }
}