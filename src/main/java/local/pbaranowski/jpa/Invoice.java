package local.pbaranowski.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@ToString
public class Invoice implements EntityToStore {
    @Getter
    @Id
    private UUID id;

    @Setter
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    public Invoice(InvoiceStatus status) {
        this(UUID.randomUUID(),status);
    }

    public Invoice(UUID id, InvoiceStatus status) {
        this.id = id;
        this.status = status;
    }

    private Invoice() {
    }

}
