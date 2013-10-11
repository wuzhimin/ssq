package cn.beansoft.scm.entity;
// default package

import java.util.Date;
import java.util.HashSet;
import java.util.Set;



/**
 * Order cn.beansoft.scm.entity. @author MyEclipse Persistence Tools
 */

public class Order  implements java.io.Serializable {


    // Fields    

     private Long id;
     private User scmUser;
     private Date addDate;
     private Double cost;
     private Integer status;
     private Set orderItems = new HashSet(0);


    // Constructors

    /** default constructor */
    public Order() {
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public User getScmUser() {
        return this.scmUser;
    }
    
    public void setScmUser(User scmUser) {
        this.scmUser = scmUser;
    }

    public Date getAddDate() {
        return this.addDate;
    }
    
    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Double getCost() {
        return this.cost;
    }
    
    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set getOrderItems() {
        return this.orderItems;
    }
    
    public void setOrderItems(Set scmOrderItems) {
        this.orderItems = scmOrderItems;
    }
   








}