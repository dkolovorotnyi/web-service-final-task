package com.web_final_task.entity.xml;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;



@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name",
    "lastName",
    "email",
    "birthday",
    "addresses",
    "payments"
})
@XmlRootElement(name = "createUserRequest")
public class CreateUserRequest {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String lastName;
    @XmlElement(required = true)
    protected String email;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar birthday;
    protected CreateUserRequest.Addresses addresses;
    protected CreateUserRequest.Payments payments;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String value) {
        this.lastName = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public XMLGregorianCalendar getBirthday() {
        return birthday;
    }

    public void setBirthday(XMLGregorianCalendar value) {
        this.birthday = value;
    }

    public CreateUserRequest.Addresses getAddresses() {
        return addresses;
    }

    public void setAddresses(CreateUserRequest.Addresses value) {
        this.addresses = value;
    }

    public CreateUserRequest.Payments getPayments() {
        return payments;
    }

    public void setPayments(CreateUserRequest.Payments value) {
        this.payments = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "address"
    })
    public static class Addresses {

        @XmlElement(required = true)
        protected List<NewAddress> address;

        public List<NewAddress> getAddress() {
            if (address == null) {
                address = new ArrayList<NewAddress>();
            }
            return this.address;
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "payment"
    })
    public static class Payments {

        @XmlElement(required = true)
        protected List<NewPayment> payment;

        public List<NewPayment> getPayment() {
            if (payment == null) {
                payment = new ArrayList<NewPayment>();
            }
            return this.payment;
        }
    }
}
