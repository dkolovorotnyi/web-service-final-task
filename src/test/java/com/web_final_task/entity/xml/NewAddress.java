package com.web_final_task.entity.xml;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "newAddress", propOrder = {
    "state",
    "city",
    "zip",
    "line1",
    "line2"
})
@XmlSeeAlso({
    ExistingAddress.class
})
public class NewAddress {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected State state;
    @XmlElement(required = true)
    protected String city;
    @XmlElement(required = true)
    protected String zip;
    @XmlElement(required = true)
    protected String line1;
    @XmlElement(required = true)
    protected String line2;

    public State getState() {
        return state;
    }


    public void setState(State value) {
        this.state = value;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String value) {
        this.city = value;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String value) {
        this.zip = value;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String value) {
        this.line1 = value;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String value) {
        this.line2 = value;
    }

}
