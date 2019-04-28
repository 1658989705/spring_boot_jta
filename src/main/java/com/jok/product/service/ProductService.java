package com.jok.product.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jok.car.domain.Car;
import com.jok.car.mapper.CarMapper;
import com.jok.product.domain.Product;
import com.jok.product.mapper.ProductMapper;

@Service
public class ProductService {
	@Autowired
	public CarMapper carMapper;
	
	@Autowired
	public ProductMapper productMapper;
	
	@Transactional
	public void insert(Product product) {
		productMapper.insert(product);
	}
	@Transactional
	public void add(Car car, Product product) {
		carMapper.insert(car);
		productMapper.insert(product);
	}
}
