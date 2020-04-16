package com.jcr.podfx.business;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * <p>
 * Represents an entity with a generated ID field {@link #id} of type
 * {@link Long}. If your Hibernate entities extend this class they gain the ID
 * field and auto-generated accessors to all their public fields (unless
 * annotated with {@link Transient}), as well as all the useful methods from
 * {@link PanacheEntityBase}.
 * </p>
 * <p>
 * If you want a custom ID type or strategy, you can directly extend
 * {@link PanacheEntityBase} instead, and write your own ID field. You will
 * still get auto-generated accessors and all the useful methods.
 * </p>
 *
 * @author Stéphane Épardaud
 * @see PanacheEntityBase
 */
@MappedSuperclass
public abstract class PodfxEntity extends PanacheEntityBase {

    /**
     * The auto-generated ID field. This field is set by Hibernate ORM when this
     * entity is persisted.
     *
     * @see #persist()
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "<" + id + ">";
    }

}
