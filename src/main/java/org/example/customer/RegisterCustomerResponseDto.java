package org.example.customer;

public class RegisterCustomerResponseDto {

    public final String email;
    public final long balance;

    public RegisterCustomerResponseDto(Customer customer) {
        this.email = customer.getEmail().getEmail();
        this.balance = customer.getBalance().getBalance();
    }
}
