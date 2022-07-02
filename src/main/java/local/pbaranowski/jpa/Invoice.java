package local.pbaranowski.jpa;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@ToString
public class Invoice {

    @Getter
    @Id
    @Type(type = "uuid-char")
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
