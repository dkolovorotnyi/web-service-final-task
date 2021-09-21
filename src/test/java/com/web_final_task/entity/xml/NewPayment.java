package com.web_final_task.entity.xml;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "newPayment", propOrder = {
    "cardNumber",
    "expiryYear",
    "expiryMonth",
    "cardholder",
    "cvv"
})
@XmlSeeAlso({
    ExistingPayment.class
})
public class NewPayment {

    @XmlElement(required = true)
    protected String cardNumber;
    protected int expiryYear;
    protected int expiryMonth;
    @XmlElement(required = true)
    protected String cardholder;
    @XmlElement(required = true)
    protected String cvv;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String value) {
        this.cardNumber = value;
    }

    public int getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(int value) {
        this.expiryYear = value;
    }

    public int getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(int value) {
        this.expiryMonth = value;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String value) {
        this.cardholder = value;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String value) {
        this.cvv = value;
    }
}
