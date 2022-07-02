package local.pbaranowski.jpa;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@ToString
@Entity
public class Seller {

    @Getter
    @Id
    @Type(type = "uuid-char")
    private UUID id;

    @Setter
    @Embedded
    private ContactDetails contactDetails;

    public Seller(UUID id, ContactDetails contactDetails) {
        this.id = id;
        this.contactDetails = contactDetails;
    }

    public Seller(ContactDetails contactDetails) {
        this(UUID.randomUUID(),contactDetails);
    }

    public Seller() {
    }
}
