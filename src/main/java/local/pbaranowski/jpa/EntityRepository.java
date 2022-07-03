package local.pbaranowski.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;

public abstract class EntityRepository<T extends EntityToStore> {
    private final EntityManager entityManager;
    private final String tableName;

    public EntityRepository(EntityManager entityManager, String tableName) {
        this.entityManager = entityManager;
        this.tableName = tableName;
    }

    UUID save(T entityToStore) {
        return doInTransaction(EntityManager::persist, entityToStore);
    }

    void update(T entityToStore) {
        doInTransaction(EntityManager::merge, entityToStore);
    }

    void update(UUID id) {
        find(id).forEach(this::update);
    }

    void delete(T entityToStore) {
        doInTransaction(EntityManager::remove, entityToStore);
    }

    void delete(UUID id) {
        find(id).forEach(this::delete);
    }

    UUID doInTransaction(BiConsumer<EntityManager, T> biConsumer, T entityToStore) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        biConsumer.accept(entityManager, entityToStore);
        transaction.commit();
        return entityToStore.getId();
    }

    List<T> find() {
        return find(null);
    }

    List<T> find(UUID id) {
        return entityManager.createQuery("SELECT e FROM " + tableName + " e " + (id == null ? "" : "WHERE e.id = '" + id + "'")).getResultList();
    }

}
