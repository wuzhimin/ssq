package cn.beansoft.scm.entity;

import java.util.HashSet;
import java.util.Set;


/**
 * Role cn.beansoft.scm.entity. @author MyEclipse Persistence Tools
 */

public class Role  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String roleName;
     private Set scmResources = new HashSet(0);


    // Constructors

    /** default constructor */
    public Role() {
    }

    
    /** full constructor */
    public Role(String roleName, Set scmResources) {
        this.roleName = roleName;
        this.scmResources = scmResources;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set getScmResources() {
        return this.scmResources;
    }
    
    public void setScmResources(Set scmResources) {
        this.scmResources = scmResources;
    }
   








}