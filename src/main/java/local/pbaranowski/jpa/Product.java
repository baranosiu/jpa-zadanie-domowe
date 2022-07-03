package local.pbaranowski.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@ToString
@Embeddable
public class Product implements EntityToStore{

    @Id
    @Getter
    private UUID id;

    private String name;

    private BigDecimal price;

    public Product(String name, BigDecimal price) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
    }
    public Product() {
    }
}
