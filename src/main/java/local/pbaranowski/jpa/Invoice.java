package local.pbaranowski.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
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

    @ElementCollection
    private Set<InvoiceItem> items = new HashSet<>();

    private LocalDate date;

    public Invoice() {

    }

    void add(InvoiceItem item) {
        items.add(item);
    }

    public Invoice(InvoiceStatus status) {
        this.id = UUID.randomUUID();
        this.status = status;
        this.date = LocalDate.now();
    }

}
