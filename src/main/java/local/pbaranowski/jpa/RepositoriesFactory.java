package local.pbaranowski.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RepositoriesFactory {
    private final EntityManagerFactory entityManagerFactory;

    private RepositoriesFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public static RepositoriesFactory create() {
        return new RepositoriesFactory(Persistence.createEntityManagerFactory("Shop"));
    }

    public BuyerRepository BuyersRepository() {
        return new BuyerRepository(entityManagerFactory.createEntityManager());
    }

    public InvoiceRepository InvoiceRepository() {
        return new InvoiceRepository(entityManagerFactory.createEntityManager());
    }

    public SellerRepository SellerRepository() {
        return new SellerRepository(entityManagerFactory.createEntityManager());
    }

    public void close() {
        entityManagerFactory.close();
    }
}
