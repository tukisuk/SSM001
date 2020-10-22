package com.swjd.service;

import com.swjd.bean.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    public abstract List<Customer> getList();
    public abstract List<Customer> findForSearch(Map<String,String> map);
    public abstract int findForCount(Map<String,String> map);
    public abstract int addCustomer(Customer customer);
    public abstract int delete(String []ids);
    public abstract Customer selectone(int id);
    public abstract int updateCourse(Customer customer);
}
