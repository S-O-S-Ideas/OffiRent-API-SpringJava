package com.acme.offirent.domain.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.annotation.processing.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="payment_methods")
public class PaymentMethod{

    @Id
    @GeneratedValue(strategy=Generationtype.IDENTITY)
    private long id;

    @NotNull
    private String cardNumber;

    @NotNull
    private String ownerName;

    @NotNull
    private String dueDate;

    @NotNull
    private String cv;

    @OneToMany(mappedBy = "PaymentMethod") //TODO
    private List<AccountPaymentMethod> accountPaymentMethods;


    public long getId() {
        return id;
    }

    public PaymentMethod setId(long id) {
        this.id = id;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public PaymentMethod setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public PaymentMethod setOwnerName(String ownerName) {
        this.ownerName = ownerName;
        return this;
    }

    public String getDueDate() {
        return dueDate;
    }

    public PaymentMethod setDueDate(String dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public String getCv() {
        return cv;
    }

    public PaymentMethod setCv(String cv) {
        this.cv = cv;
        return this;
    }

    public List<AccountPaymentMethod> getAccountPaymentMethods() {
        return accountPaymentMethods;
    }

    public PaymentMethod setAccountPaymentMethods(List<AccountPaymentMethod> accountPaymentMethods) {
        this.accountPaymentMethods = accountPaymentMethods;
        return this;
    }
}