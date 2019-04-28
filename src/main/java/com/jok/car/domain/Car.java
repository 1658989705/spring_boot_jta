package com.jok.car.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


import lombok.Data;

@Data
public class Car implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6833715040880993591L;
	private Long id;
	private String name;
	private Double price;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date sale_date;
}
