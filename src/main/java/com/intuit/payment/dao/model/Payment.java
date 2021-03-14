// Generated with g9.
package com.intuit.payment.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Entity(name="PAYMENT")
public class Payment implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @Column(unique=true, nullable=false, length=128)
    private String id;
    @Column(name="amount", length=22)
    private double amount;
    @Column(name="currency", length=50)
    private String currency;
    @Column(name="userId", nullable=false, length=128)
    private String userId;
    @Column(name="payeeId", nullable=false, length=128)
    private String payeeId;
    @Column(name="paymentMethodId", nullable=false, length=128)
    private String paymentMethodId;

    /** Default constructor. */
    public Payment() {
        super();
    }

    /**
     * Access method for id.
     *
     * @return the current value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method for id.
     *
     * @param aId the new value for id
     */
    public void setId(String aId) {
        id = aId;
    }

    /**
     * Access method for amount.
     *
     * @return the current value of runStatus
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Setter method for amount.
     *
     * @param aAmount the new value for amount
     */
    public void setAmount(double aAmount) {
        amount = aAmount;
    }

    /**
     * Access method for currency.
     *
     * @return the current value of currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Setter method for currency.
     *
     * @param aCurrency the new value for currency
     */
    public void setCurrency(String aCurrency) {
        currency = aCurrency;
    }

    /**
     * Access method for userId.
     *
     * @return the current value of userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Setter method for userId.
     *
     * @param aUserId the new value for userId
     */
    public void setUserId(String aUserId) {
        userId = aUserId;
    }

    /**
     * Access method for payeeId.
     *
     * @return the current value of payeeId
     */
    public String getPayeeId() {
        return payeeId;
    }

    /**
     * Setter method for payeeId.
     *
     * @param aPayeeId the new value for payeeId
     */
    public void setPayeeId(String aPayeeId) {
        payeeId = aPayeeId;
    }

    /**
     * Access method for paymentMethodId.
     *
     * @return the current value of paymentMethodId
     */
    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    /**
     * Setter method for paymentMethodId.
     *
     * @param aPaymentMethodId the new value for paymentMethodId
     */
    public void setPaymentMethodId(String aPaymentMethodId) {
        paymentMethodId = aPaymentMethodId;
    }

    /**
     * Compares the key for this instance with another Session.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Session and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Payment)) {
            return false;
        }
        Payment that = (Payment) other;
        Object myId = this.getId();
        Object yourId = that.getId();
        if (!Objects.equals(myId, yourId)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Session.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Payment)) return false;
        return this.equalKeys(other) && ((Payment)other).equalKeys(this);
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return Hash code
     */
    @Override
    public int hashCode() {
        int i;
        int result = 17;
        if (getId() == null) {
            i = 0;
        } else {
            i = getId().hashCode();
        }
        result = 37*result + i;
        return result;
    }

    /**
     * Returns a debug-friendly String representation of this instance.
     *
     * @return String representation of this instance
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[Session |");
        sb.append(" id=").append(getId());
        sb.append("]");
        return sb.toString();
    }

    /**
     * Return all elements of the primary key.
     *
     * @return Map of key names to values
     */
    public Map<String, Object> getPrimaryKey() {
        Map<String, Object> ret = new LinkedHashMap<String, Object>(6);
        ret.put("id", getId());
        return ret;
    }
}
