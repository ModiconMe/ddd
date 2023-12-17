package org.example.customer;

import lombok.RequiredArgsConstructor;
import org.example.common.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class RegisterCustomerService {

    private final CustomerJpaRepository customerJpaRepository;

    public Result<RegisterCustomerResponseDto> handle(RegisterCustomerRequestDto dto) {
        Result<Customer> customer = Customer.of(dto);

        if (customer.isSuccess()) {
            customerJpaRepository.save(customer.getData());
        }

        return customer.map(RegisterCustomerResponseDto::new);
    }
}
