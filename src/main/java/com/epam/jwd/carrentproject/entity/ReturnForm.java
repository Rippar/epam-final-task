package com.epam.jwd.carrentproject.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * The {@code ReturnForm} class represents a return form entity
 */
public class ReturnForm extends AbstractEntity {

    private int returnFormId;
    private int orderId;
    private LocalDate dateOfReturn;
    private String damageDescription;
    private BigDecimal billValue;

    public static class ReturnFormBuilder {

        private ReturnForm newReturnForm;

        public ReturnFormBuilder() {
            newReturnForm = new ReturnForm();
        }

        public ReturnFormBuilder withReturnFormId(int id) {
            newReturnForm.returnFormId = id;
            return this;
        }

        public ReturnFormBuilder withOrderId(int id) {
            newReturnForm.orderId = id;
            return this;
        }

        public ReturnFormBuilder withDateOfReturn(LocalDate dateOfReturn) {
            newReturnForm.dateOfReturn = dateOfReturn;
            return this;
        }

        public ReturnFormBuilder withDamageDescription(String damageDescription) {
            newReturnForm.damageDescription = damageDescription;
            return this;
        }

        public ReturnFormBuilder withBillValue(BigDecimal billValue) {
            newReturnForm.billValue = billValue;
            return this;
        }

        public ReturnForm build() {
            return newReturnForm;
        }

    }

    public int getReturnFormId() {
        return returnFormId;
    }

    public void setReturnFormId(int returnFormId) {
        this.returnFormId = returnFormId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(LocalDate dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public String getDamageDescription() {
        return damageDescription;
    }

    public void setDamageDescription(String damageDescription) {
        this.damageDescription = damageDescription;
    }

    public BigDecimal getBillValue() {
        return billValue;
    }

    public void setBillValue(BigDecimal billValue) {
        this.billValue = billValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReturnForm that = (ReturnForm) o;

        if (returnFormId != that.returnFormId) return false;
        if (orderId != that.orderId) return false;
        if (dateOfReturn != null ? !dateOfReturn.equals(that.dateOfReturn) : that.dateOfReturn != null) return false;
        if (damageDescription != null ? !damageDescription.equals(that.damageDescription) :
                that.damageDescription != null)
            return false;
        return billValue != null ? billValue.equals(that.billValue) : that.billValue == null;
    }

    @Override
    public int hashCode() {
        int result = returnFormId;
        result = 31 * result + orderId;
        result = 31 * result + (dateOfReturn != null ? dateOfReturn.hashCode() : 0);
        result = 31 * result + (damageDescription != null ? damageDescription.hashCode() : 0);
        result = 31 * result + (billValue != null ? billValue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "returnFormId=" + returnFormId +
                ", orderId=" + orderId +
                ", dateOfReturn=" + dateOfReturn +
                ", damageDescription='" + damageDescription + '\'' +
                ", billValue=" + billValue +
                '}';
    }
}
