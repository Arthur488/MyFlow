package workflow.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import workflow.models.Customer;
import workflow.system.customer.dao.CustomerRepository;


@Component
public class Utility {

    private static CustomerRepository customerRepository;

    public Utility(CustomerRepository customerRepository) {
        Utility.customerRepository = customerRepository;
    }

    public static Customer getCustomerByEmailFromRequest(HttpServletRequest request) {
        String email = request.getUserPrincipal().getName();
        return customerRepository.findByEmail(email);
    }

    public static Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

}
