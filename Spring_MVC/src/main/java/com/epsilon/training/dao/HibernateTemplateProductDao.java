package com.epsilon.training.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.epsilon.training.entity.Product;

@Repository("htDao")
@SuppressWarnings({"unchecked","deprecation"})
public class HibernateTemplateProductDao  implements ProductDao{
	
	@Autowired
	private HibernateTemplate template;

	@Override 
	public long count() {
		// TODO Auto-generated method stub
		return Long.valueOf(template.find("select count(p) from Product p").get(0).toString()); 
	}

	@Override
	public  void create(Product product) throws DaoException
	{
	template.persist(product);
	}
	@Override
	public  Product findbyId(int id) throws DaoException
	{
		return template.get(Product.class, id);
	}
	@Override
	public  void update(Product product ) throws DaoException
	{
		template.merge(product);
	}
	@Override
	public  void delete(int id ) throws DaoException
	{
		Product p= this.findbyId(id);
		if(p==null)
		{
			throw new DaoException("NO prpduct found for that id");
		}
	}
	@Override
	public  List<Product> findAll( ) throws DaoException
	{
		return (List<Product>) template.find("from Product");
	}
	@Override
	public  List<Product> findByPriceRange(double min,double max ) throws DaoException
	{
		return (List<Product>) template.find("from Product where unitPrice between ?0 and ?1 ",min,max);
	}
	@Override
	public  List<Product> findByBrand(String Brand) throws DaoException
	{return (List<Product>) template.find("from Product where brand=?0 ",Brand);
	}
	@Override
	public  List<Product> findByCategory(String Category ) throws DaoException
	{
		return (List<Product>) template.find("from Product where category=?0 ",Category);
	}
}
