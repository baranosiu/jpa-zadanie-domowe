package local.pbaranowski.jpa;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
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
    @AttributeOverride(name = "phone", column = @Column(name = "seller_phone", length = 20))
    @AttributeOverride(name = "mail", column = @Column(name = "seller_mail", length = 150))
    private ContactDetails contactDetails;

    public Seller(UUID id, ContactDetails contactDetails) {
        this.id = id;
        this.contactDetails = contactDetails;
    }

    public Seller(ContactDetails contactDetails) {
        this(UUID.randomUUID(), contactDetails);
    }

    public Seller() {
    }
}
