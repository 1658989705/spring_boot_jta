package com.jok.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "mysql.datasource.car")
public class DBCarConfig {
	  private String url;
	  private String username;
	  private String password;
	  private int minPoolSize;
	  private int maxPoolSize;
	  private int maxLifetime;
	  private int borrowConnectionTimeout;
	  private int loginTimeout;
	  private int maintenanceInterval;
	  private int maxIdleTime;
	  private String testQuery;
}
