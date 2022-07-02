package local.pbaranowski.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Setter;
import lombok.ToString;

@ToString
@Embeddable
public class ContactDetails {
    @Column(nullable = false)
    private String login;

    @Setter
    private String phone;

    @Setter
    private String mail;

    public ContactDetails(String login, String phone, String mail) {
        this.login = login;
        this.phone = phone;
        this.mail = mail;
    }

    public ContactDetails() {}
}
