package com.jok.product.mapper;

import org.apache.ibatis.annotations.Insert;

import com.jok.product.domain.Product;

public interface ProductMapper {
	@Insert("insert into product (id,name,price,sale_date) values (#{id},#{name},#{price},#{sale_date})")
	void insert(Product product);
}
