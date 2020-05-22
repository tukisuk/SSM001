package com.swjd.controller;

import com.swjd.bean.Customer;
import com.swjd.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/CustomerController")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @RequestMapping("/tomain")
    public String tomain(Model model){
        List<Customer> list=customerService.getList();
        model.addAttribute("customers",list);
        Customer customer=new Customer();
        model.addAttribute("customer",customer);
        System.out.println(list);
        return "main";
    }
}
