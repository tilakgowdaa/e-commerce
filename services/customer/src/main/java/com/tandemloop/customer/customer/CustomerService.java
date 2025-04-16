package com.tandemloop.customer.customer;

import com.tandemloop.customer.customer.exceptions.CustomerNotFoundException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repo;
    private final CustomerMapper mapper;
    public String createCustomer( CustomerRequest request) {
        var customer=repo.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer=repo.findById(request.id())
                .orElseThrow(()->new CustomerNotFoundException(String.format("Cannot update customer:: No customer found with provided ID:: %s",request.id())));
        repo.save(mapper.toCustomer(request));
    }

    public List<CustomerResponse> findAllCustomers() {
        return repo.findAll().stream().map(mapper::fromCustomerToResponse).collect(Collectors.toList());
    }

    public Boolean existsById(String id) {
        return repo.findById(id).isPresent();
    }

    public CustomerResponse findById(String id) {
        return repo.findById(id).map(mapper::fromCustomerToResponse).orElseThrow(()->new CustomerNotFoundException(String.format("Cannot update customer:: No customer found with provided ID:: %s",id)));
    }

    public void deleteCustomer(String id) {
        repo.deleteById(id);
    }
}
