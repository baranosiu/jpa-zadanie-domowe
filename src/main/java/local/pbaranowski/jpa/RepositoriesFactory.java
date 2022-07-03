package local.pbaranowski.jpa;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class RepositoriesFactory {
    private final EntityManagerFactory entityManagerFactory;

    private RepositoriesFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public static RepositoriesFactory create() {
        return new RepositoriesFactory(Persistence.createEntityManagerFactory("Shop"));
    }

    public EntityRepository getRepository(Class entityClass) {
        return new EntityRepository(entityManagerFactory.createEntityManager(),entityClass);
    }

    public InvoiceRepository InvoiceRepository() {
        return new InvoiceRepository(entityManagerFactory.createEntityManager(), Invoice.class);
    }

    public void close() {
        entityManagerFactory.close();
    }
}
