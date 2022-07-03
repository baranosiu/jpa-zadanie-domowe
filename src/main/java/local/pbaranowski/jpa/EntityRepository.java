package local.pbaranowski.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Table;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.Metamodel;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;

@Slf4j
public class EntityRepository<T extends EntityToStore> {
    private final EntityManager entityManager;
    private final Class<T> entityClass;

    public EntityRepository(EntityManager entityManager, Class<T> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    private <T> String getTableName(Class<T> entityClass) {
        Metamodel meta = entityManager.getMetamodel();
        EntityType<T> entityType = meta.entity(entityClass);
        Table t = entityClass.getAnnotation(Table.class);
        String tableName = (t == null) ? entityType.getName() : t.name();
        return tableName;
    }

    public String getTableName() {
        return getTableName(entityClass);
    }

    UUID save(T entity) {
        return doInTransaction(EntityManager::persist, entity);
    }

    void update(T entity) {
        doInTransaction(EntityManager::merge, entity);
    }

    void update(UUID id) {
        find(id).forEach(this::update);
    }

    void delete(T entity) {
        doInTransaction(EntityManager::remove, entity);
    }

    void delete(UUID id) {
        find(id).forEach(this::delete); // Można też przez createQuery() - zaoszczędzenie SELECT-a
    }

    UUID doInTransaction(BiConsumer<EntityManager, T> biConsumer, T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        biConsumer.accept(entityManager, entity);
        transaction.commit();
        return entity.getId();
    }

    List<T> find() {
        return find(null);
    }


    List<T> find(UUID id) {
        String query = "SELECT e FROM " + getTableName() + " e " + (id == null ? "" : "WHERE e.id = '" + id + "'");
        log.info("query: {}", query);
        return entityManager.createQuery(query).getResultList();
    }

}
