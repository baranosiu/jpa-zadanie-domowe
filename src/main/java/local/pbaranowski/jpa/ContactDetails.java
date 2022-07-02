package local.pbaranowski.jpa;

import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@ToString
@Embeddable
public class ContactDetails {
    @Column(nullable = false)
    private String login;

    @Setter
    @Column(length = 20)
    private String phone;
    @Setter
    @Column(length = 150)
    private String mail;

    public ContactDetails(String login, String phone, String mail) {
        this.login = login;
        this.phone = phone;
        this.mail = mail;
    }

    public ContactDetails() {}
}
