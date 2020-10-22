package com.swjd.controller;

import com.swjd.bean.Customer;
import com.swjd.service.CustomerService;
import com.swjd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/CustomerController")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    UserService userService;

    @RequestMapping("/tomain")
    public String tomain(Model model){
        List<Customer> list=customerService.getList();
        model.addAttribute("list",list);
        Customer customer=new Customer();
        model.addAttribute("customer",customer);
        System.out.println(list);
        return "main";
    }

    @RequestMapping("/findForSearch")
    public String findForSearch(@RequestParam Map<String,String> param, Model model, ModelMap modelMap){
//        第三次查条件查询的第二条,拿第二次输入的条件
        String customerName=param.get("customerName");//客户姓名的条件
        String customerId=param.get("customerId");//客户编号
        if (customerId==null||customerId.equals("")){
            customerId="0";
        }
        String customerSourse=param.get("customerSourse");//客户信息来源
        String customerDateBegin=param.get("customerDateBegin");//客户创建时间（最小）
        String customerDateEnd=param.get("customerDateEnd");//客户创建时间（最大）

        model.addAttribute("customerDateBegin",customerDateBegin);
        model.addAttribute("customerDateEnd",customerDateEnd);


        //第二次（param就有内容了）


        System.out.println(param);
        //第一次
        //每页显示的数据条数
        String rows="5";
        param.put("rows",rows);
        List<Customer> list=customerService.findForSearch(param);
        //总条数
        int totalRows=customerService.findForCount(param);

        //当前页数
        String pageNum=param.get("pageNum");
        if (pageNum==null||pageNum.equals("")){
            pageNum="1";
        }
        //总页数
        int totalPage=0;
        if (totalRows%Integer.parseInt(rows)==0){
            totalPage=totalRows/Integer.parseInt(rows);
        }else {
            totalPage=totalRows/Integer.parseInt(rows)+1;
        }

        modelMap.put("list",list);
        modelMap.put("totalRows",totalRows);
        modelMap.put("pageNum",pageNum);
        modelMap.put("totalPage",totalPage);

        Customer customer=new Customer();
        customer.setCustomerName(customerName);
        customer.setCustomerId(Integer.parseInt(customerId));
        customer.setCustomerSourse(customerSourse);
        model.addAttribute("customer",customer);
        return "main";
    }

    @RequestMapping("/toAdd")
    public String toAdd(Model model){
        Customer c=new Customer();
        model.addAttribute("customer",c);
        return "add";
    }

    @RequestMapping("/doAdd")
    public String doAdd(Customer customer, HttpSession session){
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String dateStr=simpleDateFormat.format(date);
        customer.setCustomerDate(dateStr);
        String uname=session.getAttribute("activeName").toString();
        int uid=userService.findUserId(uname);
        customer.setCustomerCreateId(uid);
        customer.setCustomerUserId(uid);
        int jg=customerService.addCustomer(customer);
        if (jg>0){
            return "redirect:/CustomerController/findForSearch";
        }else {
            return "redirect:/CustomerController/findForSearch";
        }
    }
    @RequestMapping("/dodelete")
    public String deleteCustomer(@RequestParam String[] selectCustomerId){
//        String[] ids=customer.getSelectCustomerId();
        Arrays.toString(selectCustomerId);
        System.out.println(Arrays.toString(selectCustomerId));
        int jg=customerService.delete(selectCustomerId);
        if (jg==0){
            return "redirect:/CustomerController/findForSearch";
        }else {
            return "redirect:/CustomerController/findForSearch";
        }
    }

    @RequestMapping("/toupdate")
    public String toupdate(@RequestParam("selectCustomerId") int selectCustomerId,Model model){
        Customer customer=customerService.selectone(selectCustomerId);
        model.addAttribute("customer",customer);
        return "update";
    }

    @RequestMapping("doupdate")
    public String doupdate(Customer customer){
        //调用修改的方法
        int jg=customerService.updateCourse(customer);
        return "redirect:/CustomerController/findForSearch";
    }
}
