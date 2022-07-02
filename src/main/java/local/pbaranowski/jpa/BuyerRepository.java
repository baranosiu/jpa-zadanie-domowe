package local.pbaranowski.jpa;

import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;

public class BuyerRepository {
    private final EntityManager entityManager;

    public BuyerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    UUID save(Buyer buyer) {
        return doInTransaction(EntityManager::persist, buyer);
    }

    void update(Buyer buyer) {
        doInTransaction(EntityManager::merge, buyer);
    }

    void delete(Buyer buyer) {
        doInTransaction(EntityManager::remove, buyer);
    }

    void delete(UUID id) {
        List<Buyer> buyersToDelete = find(id);
        buyersToDelete.forEach(buyer -> delete(buyer));
    }

    UUID doInTransaction(BiConsumer<EntityManager, Buyer> biConsumer, Buyer buyer) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        biConsumer.accept(entityManager, buyer);
        transaction.commit();
        return buyer.getId();
    }

    List<Buyer> find(UUID id) {
        return entityManager.createQuery("SELECT b FROM Buyer b " + (id == null ? "" : "WHERE b.id = '" + id + "'")).getResultList();
    }
}
