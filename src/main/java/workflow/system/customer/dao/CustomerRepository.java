package workflow.system.customer.dao;

import workflow.models.Customer;
import workflow.system.generics.dao.GenericRepository;

public interface CustomerRepository extends GenericRepository <Customer> {

    Customer findByEmail(String email);

}
