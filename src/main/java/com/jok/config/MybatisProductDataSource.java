package com.jok.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;

@Configuration
@MapperScan(basePackages = "com.jok.product.mapper",sqlSessionTemplateRef = "productSqlSessionTemplate")
public class MybatisProductDataSource {
	
	//配置数据源信息
	@Bean(name = "productDatasource")
	public DataSource  productDataSource(DBProductConfig config) throws SQLException {
		MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
		mysqlXADataSource.setURL(config.getUrl());
		mysqlXADataSource.setUser(config.getUsername());
		mysqlXADataSource.setPassword(config.getPassword());
		
		//将mysqlXADataSource 叫Atomikos来管理
		AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
		atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
		atomikosDataSourceBean.setUniqueResourceName("productDatasource");
		
		//设置数据源其他参数信息
		atomikosDataSourceBean.setMinPoolSize(config.getMinPoolSize());
		atomikosDataSourceBean.setMaxPoolSize(config.getMaxPoolSize());
		atomikosDataSourceBean.setMaxLifetime(config.getMaxLifetime());
		atomikosDataSourceBean.setBorrowConnectionTimeout(config.getBorrowConnectionTimeout());
		atomikosDataSourceBean.setLoginTimeout(config.getLoginTimeout());
		atomikosDataSourceBean.setMaintenanceInterval(config.getMaintenanceInterval());
		atomikosDataSourceBean.setMaxIdleTime(config.getMaxIdleTime());
		atomikosDataSourceBean.setTestQuery(config.getTestQuery());
		return atomikosDataSourceBean;
	}

	//创建一个sql的会话工厂
	@Bean(name = "productSqlSessionFactory")
	public SqlSessionFactory  productSqlSessionFactory(@Qualifier("productDatasource") DataSource datasource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		//建立会话工厂之后，需要把数据源装配进来
		bean.setDataSource(datasource);
		return bean.getObject();
	}
	
	//需要一个装饰会话工厂的装饰器
	@Bean(name = "productSqlSessionTemplate")
	public SqlSessionTemplate productSqlSessionTemplate(@Qualifier("productSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
