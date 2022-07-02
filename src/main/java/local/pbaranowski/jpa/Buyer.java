package local.pbaranowski.jpa;

import local.pbaranowski.jpa.ContactDetails;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@ToString
@Entity
public class Buyer {
    @Id
    @Getter
    @Type(type = "uuid-char")
    private UUID id;

    @Embedded
    private ContactDetails contactDetails;

    public Buyer(UUID id, ContactDetails contactDetails) {
        this.id = id;
        this.contactDetails = contactDetails;
    }

    public Buyer(ContactDetails contactDetails) {
        this(UUID.randomUUID(),contactDetails);
    }

    public Buyer() {}
}
