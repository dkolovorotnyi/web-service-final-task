
package com.web_final_task.entity.xml;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userDetails", propOrder = {
        "id",
        "name",
        "lastName",
        "email",
        "birthday",
        "addresses",
        "payments"
})
public class UserDetails {

    protected long id;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String lastName;
    @XmlElement(required = true)
    protected String email;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar birthday;
    protected UserDetails.Addresses addresses;
    protected UserDetails.Payments payments;

    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

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

    public UserDetails.Addresses getAddresses() {
        return addresses;
    }

    public void setAddresses(UserDetails.Addresses value) {
        this.addresses = value;
    }

    public UserDetails.Payments getPayments() {
        return payments;
    }

    public void setPayments(UserDetails.Payments value) {
        this.payments = value;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "address"
    })
    public static class Addresses {

        @XmlElement(required = true)
        protected List<ExistingAddress> address;

        public List<ExistingAddress> getAddress() {
            if (address == null) {
                address = new ArrayList<ExistingAddress>();
            }
            return this.address;
        }

    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "payment"
    })
    public static class Payments {

        @XmlElement(required = true)
        protected List<ExistingPayment> payment;

        public List<ExistingPayment> getPayment() {
            if (payment == null) {
                payment = new ArrayList<ExistingPayment>();
            }
            return this.payment;
        }

    }

}
