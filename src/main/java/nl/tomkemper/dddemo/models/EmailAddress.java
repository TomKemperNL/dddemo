package nl.tomkemper.dddemo.models;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

public class EmailAddress {
    //ietwat naieve regex, zodat de regex nog enigszins leesbaar is...
    private static final String EMAIL_REGEX = "^(.+)@(.+)$";

    private String value;

    private EmailAddress(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailAddress that = (EmailAddress) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public static Optional<EmailAddress> parse(String value){
        if(Pattern.matches(EMAIL_REGEX, value)){
            return Optional.of(new EmailAddress(value));
        }else{
            return Optional.empty();
        }
    }

    static EmailAddress deserialize(String value){
        if(!Pattern.matches(EMAIL_REGEX, value)){
            throw new IllegalArgumentException(String.format("%s is not a valid emailaddress", value));
        }

        return new EmailAddress(value);
    }

    @Override
    public String toString() {
        return this.value;
    }
}
