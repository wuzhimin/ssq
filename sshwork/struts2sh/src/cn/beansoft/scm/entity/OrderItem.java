package cn.beansoft.scm.entity;
// default package

import java.util.Date;



/**
 * OrderItem cn.beansoft.scm.entity. @author MyEclipse Persistence Tools
 */

public class OrderItem  implements java.io.Serializable {


    // Fields    

     private Long id = 0L;
     private Order scmOrder;
     private Product product;
     private Date addDate;
     private Integer amount = 0;
     private Double price = 0.0;
     private Double rebate = 0.0;
     private Double rate = 0.0;
     private Double deduct = 0.0;
     
     // 计算订单项的总价格
     public double getCost() {
    	 if(amount == null) {
    		 amount = 0;
    	 }
    	 return product.getPrice()
    	 * product.getRebate()
    	 * amount;
     }
     
     /**
      * 计算订单项分成.
      * @return
      */
     public Double getDeduct() {
         return getCost() * getRate();
     }
     
     public void setDeduct(Double deduct) {
         this.deduct = deduct;
     }     


    // Constructors

    /** default constructor */
    public OrderItem() {
    }

	/** minimal constructor */
    public OrderItem(Date addDate) {
        this.addDate = addDate;
    }
    
    /** full constructor */
    public OrderItem(Order scmOrder, Product product, Date addDate, Integer amount, Double price, Double rebate, Double rate, Double deduct) {
        this.scmOrder = scmOrder;
        this.product = product;
        this.addDate = addDate;
        this.amount = amount;
        this.price = price;
        this.rebate = rebate;
        this.rate = rate;
        this.deduct = deduct;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Order getScmOrder() {
        return this.scmOrder;
    }
    
    public void setScmOrder(Order scmOrder) {
        this.scmOrder = scmOrder;
    }

    public Product getProduct() {
        return this.product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getAddDate() {
        return this.addDate;
    }
    
    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Integer getAmount() {
        return this.amount;
    }
    
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return this.price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRebate() {
        return this.rebate;
    }
    
    public void setRebate(Double rebate) {
        this.rebate = rebate;
    }

    public Double getRate() {
        return this.rate;
    }
    
    public void setRate(Double rate) {
        this.rate = rate;
    }


}