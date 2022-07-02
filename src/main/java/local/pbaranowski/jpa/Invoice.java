package local.pbaranowski.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@ToString
public class Invoice {

    @Getter
    @Id
    private UUID id;

    @Setter
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    public Invoice(InvoiceStatus status) {
        id = UUID.randomUUID();
        this.status = status;
    }

    public Invoice(UUID id, InvoiceStatus status) {
        this.id = id;
        this.status = status;
    }

    public Invoice() {
    }
}
