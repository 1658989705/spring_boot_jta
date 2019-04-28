package com.jok.car.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jok.car.domain.Car;
import com.jok.car.mapper.CarMapper;

@Service
public class CarService {
	@Autowired
	public CarMapper carMapper;
	
	@Transactional
	public void insert(Car car) {
		carMapper.insert(car);
	}
	
	public List<Car> getList() {
		List<Car> list = carMapper.findByParam();
		return list;
	}
}
