package com.jcr.podfx.business.funktions.entity;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.jcr.podfx.business.dfmeas.entity.Dfmea;
import java.util.Objects;
import javax.persistence.Version;

@Entity
@NamedQueries({
    @NamedQuery(name = Funktion.FUNKTIONS_BY_DFMEA, query = "SELECT f FROM Funktion f where f.dfmea.id = :dfmeaId")
})
@Table(name = "FUNKTION")
public class Funktion implements Serializable {

    private static final long serialVersionUID = 6487459757818072343L;

    public static final String FUNKTIONS_BY_DFMEA = "funktionsByDfmea";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // need to add cascade annotation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DFMEA_ID")
    private Dfmea dfmea;

    @Version
    private int version;

    public Funktion() {

    }

    public Funktion(String name, Dfmea dfmea) {
        super();
        this.name = name;
        this.dfmea = dfmea;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonbTransient
    public Dfmea getDfmea() {
        return dfmea;
    }

    public void setDfmea(Dfmea dfmea) {
        this.dfmea = dfmea;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Funktion other = (Funktion) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
