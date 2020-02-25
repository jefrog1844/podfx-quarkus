package com.jcr.podfx.business.dfmeas.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Version;

public class DfmeaDetail implements Serializable {

    private static final long serialVersionUID = -47949268619774633L;

    private long id;

    private String number;
    private String title;
    private String type;
    private String originator;
    private LocalDate originated;
    
    @Version
    private int version;
    
    private LocalDate revised;
    private String teamMembers;
    private String partNumber;

    public DfmeaDetail() {

    }

    public DfmeaDetail(long id, String number, String title, String type, String originator, LocalDate originated,
            LocalDate revised, String teamMembers, String partNumber) {
        this.id = id;
        this.number = number;
        this.title = title;
        this.type = type;
        this.originator = originator;
        this.originated = originated;
        this.revised = revised;
        this.teamMembers = teamMembers;
        this.partNumber = partNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public LocalDate getOriginated() {
        return originated;
    }

    public void setOriginated(LocalDate originated) {
        this.originated = originated;
    }

    public int getVersion() {
        return version;
    }

    public LocalDate getRevised() {
        return revised;
    }

    public void setRevised(LocalDate revised) {
        this.revised = revised;
    }

    public String getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(String teamMembers) {
        this.teamMembers = teamMembers;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

}
