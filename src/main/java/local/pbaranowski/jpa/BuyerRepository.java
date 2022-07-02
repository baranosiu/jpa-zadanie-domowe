package local.pbaranowski.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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

    void update(UUID id) {
        find(id).forEach(this::update);
    }

    void delete(Buyer buyer) {
        doInTransaction(EntityManager::remove, buyer);
    }

    void delete(UUID id) {
        find(id).forEach(this::delete);
    }

    UUID doInTransaction(BiConsumer<EntityManager, Buyer> biConsumer, Buyer buyer) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        biConsumer.accept(entityManager, buyer);
        transaction.commit();
        return buyer.getId();
    }

    List<Buyer> find() {
        return find(null);
    }

    List<Buyer> find(UUID id) {
        return entityManager.createQuery("SELECT b FROM Buyer b " + (id == null ? "" : "WHERE b.id = '" + id + "'")).getResultList();
    }
}
