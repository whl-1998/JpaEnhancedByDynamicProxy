package whl.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class JpaUtil {
    private static EntityManagerFactory factory;

    static {
        factory = Persistence.createEntityManagerFactory("myJPA");
    }

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
