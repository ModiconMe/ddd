package org.example.customer;

import lombok.RequiredArgsConstructor;
import org.example.common.Result;
import org.example.common.ServerError;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueryCustomerService {

    private final CustomerJpaRepository customerJpaRepository;

    public Result<CustomerDto> getCustomer(long id) {
        return Customer
                .of(customerJpaRepository.findById(id))
                .map(CustomerDto::new);
    }

}
