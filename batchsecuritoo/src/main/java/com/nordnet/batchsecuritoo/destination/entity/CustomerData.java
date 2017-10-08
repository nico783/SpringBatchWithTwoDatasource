package com.nordnet.batchsecuritoo.destination.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "customer_data")
public class CustomerData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Permet de determiner si deux customerData sont égaux lorsque les objets n'ont pas encore d'id.
     */
    @Transient
    private UUID uuid = UUID.randomUUID();

    private String reference;

    @Column(name = "data_type")
    private String dataType;

    private String universe;

    public CustomerData() {
    }

    public CustomerData(String reference, String dataType, String universe) {
        this.reference = reference;
        this.dataType = dataType;
        this.universe = universe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getUniverse() {
        return universe;
    }

    public void setUniverse(String universe) {
        this.universe = universe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CustomerData that = (CustomerData) o;

        if (id == null) {
            return new EqualsBuilder().append(uuid, that.uuid).isEquals();
        } else {
            return new EqualsBuilder().append(id, that.id).isEquals();
        }

    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }

    @Override
    public String toString() {
        return "CustomerData{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", reference='" + reference + '\'' +
                ", dataType='" + dataType + '\'' +
                ", universe='" + universe + '\'' +
                '}';
    }
}
