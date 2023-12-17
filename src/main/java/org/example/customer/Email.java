package org.example.customer;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.example.common.Result;
import org.example.common.ServerError;

@Embeddable
@Getter
public class Email {

    private String email;

    protected Email() {
    }

    Email(String email) {
        this.email = email;
    }

    public static Result<Email> of(String address) {
        if (!address.contains("@")) {
            return Result.error(ServerError.unprocessableEntity(1, "Invalid email"));
        }

        return Result.success(new Email(address));
    }

    @Override
    public String toString() {
        return email;
    }
}
