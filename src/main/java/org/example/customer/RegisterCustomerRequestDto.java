package org.example.customer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterCustomerRequestDto {

    public final String email;
    public final long balance;

}
