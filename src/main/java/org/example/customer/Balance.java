package org.example.customer;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import org.example.common.Result;
import org.example.common.ServerError;

@Embeddable
@Getter
public class Balance {

    private long balance;

    protected Balance() {
    }

    private Balance(long balance) {
        this.balance = balance;
    }

    public static Result<Balance> of(long balance) {
        if (balance < 0) {
            return Result.error(ServerError.unprocessableEntity(0, "Balance is less than 0"));
        }

        return Result.success(new Balance(balance));
    }
}
