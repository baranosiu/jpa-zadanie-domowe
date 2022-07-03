package local.pbaranowski.jpa;

import jakarta.persistence.EntityManager;

public class SellerRepository extends EntityRepository<Seller>{
    public SellerRepository(EntityManager entityManager, String tableName) {
        super(entityManager,tableName);
    }
}
