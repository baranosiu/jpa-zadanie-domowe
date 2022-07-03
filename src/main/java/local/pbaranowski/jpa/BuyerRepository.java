package local.pbaranowski.jpa;


import jakarta.persistence.EntityManager;

public class BuyerRepository extends EntityRepository<Buyer> {
    public BuyerRepository(EntityManager entityManager, String tableName) {
        super(entityManager,tableName);
    }
}
