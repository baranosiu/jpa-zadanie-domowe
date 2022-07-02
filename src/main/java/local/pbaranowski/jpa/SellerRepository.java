package local.pbaranowski.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;

public class SellerRepository {
    private final EntityManager entityManager;

    public SellerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    UUID save(Seller seller) {
        return doInTransaction(EntityManager::persist, seller);
    }

    void update(Seller seller) {
        doInTransaction(EntityManager::merge, seller);
    }

    void update(UUID id) {
        find(id).forEach(seller -> update(seller));
    }

    void delete(Seller seller) {
        doInTransaction(EntityManager::remove, seller);
    }

    void delete(UUID id) {
        find(id).forEach(seller -> delete(seller));
    }

    UUID doInTransaction(BiConsumer<EntityManager, Seller> biConsumer, Seller seller) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        biConsumer.accept(entityManager, seller);
        transaction.commit();
        return seller.getId();
    }

    List<Seller> find(UUID id) {
        return entityManager.createQuery("SELECT s FROM Seller s " + (id == null ? "" : "WHERE s.id = '" + id + "'")).getResultList();
    }

}
