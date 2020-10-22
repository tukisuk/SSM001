package com.swjd.service;

import com.swjd.bean.Customer;
import com.swjd.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerMapper customerMapper;
    @Override
    public List<Customer> getList() {
        return customerMapper.getAll();
    }

    @Override
    public List<Customer> findForSearch(Map<String, String> map) {
        //获取当前试第几页
        String pageNum=map.get("page");
        //获取每页的条数
        String rows=map.get("rows");
        int row=Integer.parseInt(rows);
        //如果没有收到page，表示这是第一页
        if (pageNum==null||pageNum.equals("")){
            pageNum="1";
        }
        int pageNum1=Integer.parseInt(pageNum);
        //计算开始的下标
        int start=(pageNum1-1)*row;

        return customerMapper.findForSearch(map,start,row);
    }

    @Override
    public int findForCount(Map<String, String> map) {
        return customerMapper.findForCount(map);
    }

    @Override
    public int addCustomer(Customer customer) {
        return customerMapper.addCourse(customer);
    }

    @Override
    public int delete(String[] ids) {
        return customerMapper.delete(ids);
    }

    @Override
    public Customer selectone(int id) {
        return customerMapper.selectone(id);
    }

    @Override
    public int updateCourse(Customer customer) {
        return customerMapper.updateCourse(customer);
    }
}
