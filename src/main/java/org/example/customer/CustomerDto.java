package org.example.customer;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
public class CustomerDto {

    public final String email;
    public final long balance;

    public CustomerDto(Customer customer) {
        this.email = customer.getEmail().getEmail();
        this.balance = customer.getBalance().getBalance();
    }
}
