package com.jok.car.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import com.jok.car.domain.Car;

public interface CarMapper {
	@Insert("insert into car(id,name,price,sale_date) values (#{id},#{name},#{price},#{sale_date})")
	void insert(Car car);
	
	List<Car> findByParam();
}
