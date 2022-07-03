package local.pbaranowski.jpa;

import jakarta.persistence.Embeddable;

import java.util.UUID;

public interface EntityToStore {
    UUID getId();
}
