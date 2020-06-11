package whl.dao;

import whl.domain.Customer;
import whl.utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class CustomerDaoImpl implements CustomerDao {
    private EntityManager entityManager = JpaUtil.getEntityManager();
    private EntityTransaction entityTransaction = this.entityManager.getTransaction();

    @Override
    public Customer findCustomerById(Long custId) {
        return entityManager.getReference(Customer.class, custId);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return entityManager.merge(customer);
    }

    @Override
    public void deleteCustomer(Long custId) {
        Customer customer = entityManager.getReference(Customer.class, custId);

        entityManager.remove(customer);
    }

    @Override
    public List<Customer> findAllCustomers() {
        Query query = entityManager.createQuery("from Customer");
        return query.getResultList();
    }

    @Override
    public void saveCustomer(Customer customer) {
        entityManager.persist(customer);
    }

    public EntityTransaction getTransaction() {
        return this.entityTransaction;
    }
}
