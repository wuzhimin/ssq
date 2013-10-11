package cn.beansoft.scm.entity;

import java.util.Date;


/**
 * 资源实体类.
 */

public class Resource  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Role scmRole;
     private String uri;
     private Date addDate;
     private String note;


    // Constructors

    /** default constructor */
    public Resource() {
    }

	/** minimal constructor */
    public Resource(Date addDate) {
        this.addDate = addDate;
    }
    
    /** full constructor */
    public Resource(Role scmRole, String uri, Date addDate, String note) {
        this.scmRole = scmRole;
        this.uri = uri;
        this.addDate = addDate;
        this.note = note;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Role getScmRole() {
        return this.scmRole;
    }
    
    public void setScmRole(Role scmRole) {
        this.scmRole = scmRole;
    }

    public String getUri() {
        return this.uri;
    }
    
    public void setUri(String uri) {
        this.uri = uri;
    }

    public Date getAddDate() {
        return this.addDate;
    }
    
    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
   








}