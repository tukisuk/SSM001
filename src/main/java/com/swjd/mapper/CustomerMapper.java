package com.swjd.mapper;

import com.swjd.bean.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustomerMapper {
    public abstract List<Customer> getAll();
    //条件分页查询,当前是第几条开始
    public abstract List<Customer> findForSearch(@Param("map")Map<String,String> param,
                                                 @Param("page")Integer page,
                                                 @Param("rows")Integer rows);
    //总条数，满足条件的总条数
    public abstract int findForCount(@Param("map")Map<String,String> param);

    //新增的
    public abstract int addCourse(Customer customer);

    public abstract int delete(String []ids);

    public abstract Customer selectone(int id);

    public abstract int updateCourse(Customer customer);
}
