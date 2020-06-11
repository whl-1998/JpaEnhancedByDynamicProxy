package whl.utils;

import whl.dao.CustomerDao;

import javax.persistence.EntityTransaction;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class ProxyJpaTransactionMethod implements InvocationHandler {
    private CustomerDao customerDao;

    public ProxyJpaTransactionMethod(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        EntityTransaction tx = customerDao.getTransaction();
        Object result = null;
        try {
            System.out.println("开启事务");
            tx.begin();
            result = method.invoke(customerDao, args);
            System.out.println("提交事务");
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("回滚事务");
            tx.rollback();
        }
        return result;
    }
}
