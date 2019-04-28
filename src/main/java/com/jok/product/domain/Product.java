package com.jok.product.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2612255679274567320L;
	/**
	 * 
	 */
	private Long id;
	private String name;
	private Double price;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date sale_date;
}
