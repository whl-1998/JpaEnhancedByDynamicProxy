package whl.dao;

import whl.domain.Customer;

import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public interface CustomerDao {
    Customer findCustomerById(Long custId);

    void saveCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    void deleteCustomer(Long custId);

    List<Customer> findAllCustomers();

    EntityTransaction getTransaction();
}
