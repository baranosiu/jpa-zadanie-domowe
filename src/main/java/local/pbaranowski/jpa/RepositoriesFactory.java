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

    public BuyerRepository BuyersRepository() {
        return new BuyerRepository(entityManagerFactory.createEntityManager(), Buyer.TABLE_NAME);
    }

    public InvoiceRepository InvoiceRepository() {
        return new InvoiceRepository(entityManagerFactory.createEntityManager(), Invoice.TABLE_NAME);
    }

    public SellerRepository SellerRepository() {
        return new SellerRepository(entityManagerFactory.createEntityManager(), Seller.TABLE_NAME);
    }

    public void close() {
        entityManagerFactory.close();
    }
}
