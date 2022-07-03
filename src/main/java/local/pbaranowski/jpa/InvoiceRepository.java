package local.pbaranowski.jpa;
import jakarta.persistence.EntityManager;
import java.util.UUID;

public class InvoiceRepository extends EntityRepository<Invoice>{

    public InvoiceRepository(EntityManager entityManager, Class<Invoice> invoiceClass) {
        super(entityManager,invoiceClass);
    }

    void changeStatus(UUID id, InvoiceStatus status) {
        find(id).forEach(invoice -> {
            invoice.setStatus(status);
            update(invoice);
        });
    }
}
