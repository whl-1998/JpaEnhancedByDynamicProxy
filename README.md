# JpaEnhancedByDynamicProxy
通过动态代理实现对Jpa的事务性增强

对 CustomerDao 进行事务的增强：

```java
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
```

在调用时通过增强后的 CustomDao 进行调用：

```java
public class CustomerDaoTest {

    private CustomerDao customerDao = new CustomerDaoImpl();

    {
        customerDao = (CustomerDao) Proxy.newProxyInstance(customerDao.getClass().getClassLoader(), customerDao.getClass().getInterfaces(), new ProxyJpaTransactionMethod(customerDao));
    }
    
    //...
}
```
