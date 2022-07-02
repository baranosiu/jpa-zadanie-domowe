package local.pbaranowski.jpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;

public class InvoiceRepository {
    private final EntityManager entityManager;

    public InvoiceRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    UUID save(Invoice invoice) {
        return doInTransaction(EntityManager::persist, invoice);
    }

    void update(Invoice invoice) {
        doInTransaction(EntityManager::merge, invoice);
    }

    void update(UUID id) {
        find(id).forEach(this::update);
    }


    void delete(Invoice invoice) {
        doInTransaction(EntityManager::remove, invoice);
    }

    void delete(UUID id) {
        find(id).forEach(this::delete);
    }

    UUID doInTransaction(BiConsumer<EntityManager, Invoice> biConsumer, Invoice invoice) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        biConsumer.accept(entityManager, invoice);
        transaction.commit();
        return invoice.getId();
    }

    List<Invoice> find() {
        return find(null);
    }

    List<Invoice> find(UUID id) {
        return entityManager.createQuery("SELECT i FROM Invoice i " + (id == null ? "" : "WHERE i.id = '" + id + "'")).getResultList();
    }

    void changeStatus(UUID id, InvoiceStatus status) {
        find(id).forEach(invoice -> {
            invoice.setStatus(status);
            update(invoice);
        });
    }
}
