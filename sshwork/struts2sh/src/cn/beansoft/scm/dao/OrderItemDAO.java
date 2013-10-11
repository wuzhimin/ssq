package cn.beansoft.scm.dao;
// default package

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import cn.beansoft.scm.entity.OrderItem;



@Transactional
public class OrderItemDAO extends BaseDAO  {

}