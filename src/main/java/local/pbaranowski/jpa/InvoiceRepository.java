package local.pbaranowski.jpa;
import jakarta.persistence.EntityManager;
import java.util.UUID;

public class InvoiceRepository extends EntityRepository<Invoice>{

    public InvoiceRepository(EntityManager entityManager, String tableName) {
        super(entityManager,tableName);
    }

    void changeStatus(UUID id, InvoiceStatus status) {
        find(id).forEach(invoice -> {
            invoice.setStatus(status);
            update(invoice);
        });
    }
}
