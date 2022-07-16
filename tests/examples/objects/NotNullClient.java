import java.util.Objects;

public class NotNullClient {
    private final String name;
    private final String passport;

    public NotNullClient(final String name, final String passport) {
        this.name = name;
        this.passport = Objects.requireNonNull(passport);
    }

    public String getName() {
        return name;
    }

    public boolean checkPassport(final String passport) {
        return this.passport.toUpperCase().equals(passport.toUpperCase());
    }
}