package com.paywell.demomaplerad.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.Serializable;
import java.util.Objects;

@Data
@ToString
@MappedSuperclass
@EnableJpaAuditing
@Getter
@Setter
public abstract class AbstractBaseEntity<T extends Serializable> {

    private static final long serialVersionUID = -5554308939380869754L;

    @Id
    @GeneratedValue
    private T id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractBaseEntity<?> that = (AbstractBaseEntity<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}