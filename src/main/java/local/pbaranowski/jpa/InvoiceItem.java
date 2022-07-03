package local.pbaranowski.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Embeddable
public class InvoiceItem implements EntityToStore {
    @Id
    @Getter
    private UUID id;
    @Embedded
    private Product product;
    private Integer amount;

    public InvoiceItem(Product product, Integer amount) {
        this.id = UUID.randomUUID();
        this.product = product;
        this.amount = amount;
    }

    public InvoiceItem() {

    }
}
