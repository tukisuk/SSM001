package com.swjd.bean;

public class Customer {
    private int customerId;
    private String customerName;
    private int customerUserId;
    //客户负责人的真实姓名
    private String customerUserName;
    private int customerCreateId;
    private String customerSourse;
    //客户信息来源
    private String customerSourseDict;

    private String customerIndustry;
    //客户行业
    private String customerIndustryDict;
    private String StringcustomerPhone;
    private String customerAddress;
    private String customerDate;

    //批量删除
    private String[] selectCustomerId;

    public String[] getSelectCustomerId() {
        return selectCustomerId;
    }

    public void setSelectCustomerId(String[] selectCustomerIds) {
        this.selectCustomerId = selectCustomerIds;
    }

    public Customer() {
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerUserId() {
        return customerUserId;
    }

    public void setCustomerUserId(int customerUserId) {
        this.customerUserId = customerUserId;
    }

    public String getCustomerUserName() {
        return customerUserName;
    }

    public void setCustomerUserName(String customerUserName) {
        this.customerUserName = customerUserName;
    }

    public int getCustomerCreateId() {
        return customerCreateId;
    }

    public void setCustomerCreateId(int customerCreateId) {
        this.customerCreateId = customerCreateId;
    }

    public String getCustomerSourse() {
        return customerSourse;
    }

    public void setCustomerSourse(String customerSourse) {
        this.customerSourse = customerSourse;
    }

    public String getCustomerSourseDict() {
        return customerSourseDict;
    }

    public void setCustomerSourseDict(String customerSourseDict) {
        this.customerSourseDict = customerSourseDict;
    }

    public String getCustomerIndustry() {
        return customerIndustry;
    }

    public void setCustomerIndustry(String customerIndustry) {
        this.customerIndustry = customerIndustry;
    }

    public String getCustomerIndustryDict() {
        return customerIndustryDict;
    }

    public void setCustomerIndustryDict(String customerIndustryDict) {
        this.customerIndustryDict = customerIndustryDict;
    }

    public String getStringcustomerPhone() {
        return StringcustomerPhone;
    }

    public void setStringcustomerPhone(String stringcustomerPhone) {
        StringcustomerPhone = stringcustomerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerDate() {
        return customerDate;
    }

    public void setCustomerDate(String customerDate) {
        this.customerDate = customerDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerUserId=" + customerUserId +
                ", customerUserName='" + customerUserName + '\'' +
                ", customerCreateId=" + customerCreateId +
                ", customerSourse='" + customerSourse + '\'' +
                ", customerSourseDict='" + customerSourseDict + '\'' +
                ", customerIndustry='" + customerIndustry + '\'' +
                ", customerIndustryDict='" + customerIndustryDict + '\'' +
                ", StringcustomerPhone='" + StringcustomerPhone + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerDate='" + customerDate + '\'' +
                '}';
    }
}
