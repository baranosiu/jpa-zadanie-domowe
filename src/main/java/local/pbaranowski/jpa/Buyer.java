package local.pbaranowski.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@ToString
@Entity
public class Buyer implements EntityToStore {
    @Id
    @Getter
    private UUID id;

    @Embedded
    @AttributeOverride(name = "login", column = @Column(name = "buy_login"))
    @AttributeOverride(name = "phone", column = @Column(name = "phone_number", length = 20))
    @AttributeOverride(name = "mail", column = @Column(name = "mail_address", length = 150))
    private ContactDetails contactDetails;

    public Buyer(UUID id, ContactDetails contactDetails) {
        this.id = id;
        this.contactDetails = contactDetails;
    }

    public Buyer(ContactDetails contactDetails) {
        this(UUID.randomUUID(), contactDetails);
    }

    public Buyer() {
    }

}
