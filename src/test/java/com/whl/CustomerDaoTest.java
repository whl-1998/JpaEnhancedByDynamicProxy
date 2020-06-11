package com.whl;

import org.junit.Test;
import whl.dao.CustomerDao;
import whl.dao.CustomerDaoImpl;
import whl.domain.Customer;
import whl.utils.ProxyJpaTransactionMethod;

import java.lang.reflect.Proxy;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class CustomerDaoTest {

    private CustomerDao customerDao = new CustomerDaoImpl();

    {
        customerDao = (CustomerDao) Proxy.newProxyInstance(customerDao.getClass().getClassLoader(), customerDao.getClass().getInterfaces(), new ProxyJpaTransactionMethod(customerDao));
    }

    @Test
    public void testSave() {
        Customer customer = Customer.builder().
            custName("汪瀚霖").
            custAddress("斯顿雪域").
            custIndustry("狼族佣兵").
            custLevel("剑士").
            custPhone("08512275").
            build();
        customerDao.saveCustomer(customer);
    }

    @Test
    public void testFind() {
        System.out.println(customerDao.findCustomerById(5L));
    }

    @Test
    public void testFindAll() {
        customerDao.findAllCustomers().forEach(customer -> System.out.println(customer));
    }

    @Test
    public void testRemove() {
        customerDao.deleteCustomer(5L);
    }

    /**
     * 更新的时候, 最好先获取到原对象, 在这个原对象基础上操作
     * 否则, 没有 set 的属性在 merge 时会被置为 null 值
     */
    @Test
    public void testUpdate() {
        Customer customer = Customer.builder().
            custId(5L).
            custName("桂菊仁").
            custPhone("08512275").
            build();
        System.out.println(customerDao.updateCustomer(customer));
    }
}
