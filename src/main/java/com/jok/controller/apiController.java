package com.jok.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jok.car.domain.Car;
import com.jok.car.service.CarService;
import com.jok.product.domain.Product;
import com.jok.product.service.ProductService;

@RestController
public class apiController {
	@Autowired
	private CarService carListService;
	@Autowired
	private ProductService productServcie;
	@RequestMapping("/insert")
	public String insert() {
		Car car = new Car();
		car.setName("奔驰");
		car.setPrice(120.00);
		car.setSale_date(new Date());
//		carListService.insert(car);
		
		Product product = new Product();
		product.setName("手机");
		product.setPrice(220.00);
		product.setSale_date(new Date());
		
		productServcie.add(car,product);
		return "success";
	}
	
	
	@RequestMapping("/getList")
	public ResponseEntity<?> getList() {
		List<Car> list = carListService.getList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
