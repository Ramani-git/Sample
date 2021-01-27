package com.yamaha.org.dao;


import java.util.Objects;

public class Customer {
    private String  mobileNumber;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String CustomerName;

    public String getAlternateMobileNum() {
        return alternateMobileNum;
    }

    public void setAlternateMobileNum(String alternateMobileNum) {
        this.alternateMobileNum = alternateMobileNum;
    }

    private String address;
    private String alternateMobileNum;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return mobileNumber.equals(customer.mobileNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mobileNumber);
    }
}
