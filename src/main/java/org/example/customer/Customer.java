package org.example.customer;

import jakarta.persistence.*;
import org.example.common.Result;
import org.example.common.ServerError;
import org.example.common.Tuple3;

import java.util.Optional;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Email email;

    @Embedded
    private Balance balance;

    protected Customer() {
    }

    private Customer(Long id, Email email, Balance balance) {
        this.id = id;
        this.email = email;
        this.balance = balance;
    }

    public static Result<Customer> of(RegisterCustomerRequestDto dto) {
        return of(null, dto.email, dto.balance);
    }

    public static Result<Customer> of(Optional<Customer> optCustomer) {
        return optCustomer
                .map(Result::success)
                .orElse(Result.error(ServerError.notFound(2, "customer.not_found")));
    }

    public static Result<Customer> of(Long id, String email, long balance) {
        return Result
                .zip(Result.success(id), Email.of(email), Balance.of(balance))
                .map(it -> new Customer(it.getT1(), it.getT2(), it.getT3()));
    }

    public Long getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }

    public Balance getBalance() {
        return balance;
    }
}
