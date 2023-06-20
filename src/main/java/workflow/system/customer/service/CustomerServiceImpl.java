package workflow.system.customer.service;

import org.springframework.stereotype.Service;
import workflow.models.Customer;
import workflow.system.customer.dao.CustomerRepository;
import workflow.system.generics.service.GenericServiceImpl;

@Service
public class CustomerServiceImpl extends GenericServiceImpl<Customer> implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        super(customerRepository);
        this.customerRepository = customerRepository;
    }
}
