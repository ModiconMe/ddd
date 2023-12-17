package org.example.customer;

import lombok.RequiredArgsConstructor;
import org.example.common.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/customer/register")
@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final RegisterCustomerService registerCustomerService;
    private final QueryCustomerService queryCustomerService;

    @PostMapping
    public ResponseEntity<Result<RegisterCustomerResponseDto>> register(
            RegisterCustomerRequestDto dto
    ) {
        return registerCustomerService.handle(dto).toRestResponse();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result<CustomerDto>> register(@PathVariable long id) {
        return queryCustomerService.getCustomer(id).toRestResponse();
    }
}
