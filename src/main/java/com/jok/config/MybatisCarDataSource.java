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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;

@Configuration
@MapperScan(basePackages = {"com.jok.car.mapper"},sqlSessionTemplateRef = "carSqlSessionTemplate")
public class MybatisCarDataSource {
	static String MAPPER = "classpath:mybatis/car/*.xml";
	static String ALIAS = "com.jok.car.domain";
	
	//配置对应的数据源、
	@Bean(name="carDataSource")
	public DataSource  carDataSource(DBCarConfig config) throws SQLException {
		MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
		mysqlXADataSource.setURL(config.getUrl());
		mysqlXADataSource.setPassword(config.getPassword());
		mysqlXADataSource.setUser(config.getUsername());
		
		//将carDataSource 交给Atomikos统一管理
		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(mysqlXADataSource);
		xaDataSource.setUniqueResourceName("carDataSource");
		
		//设置数据源其他参数信息
		xaDataSource.setMinPoolSize(config.getMinPoolSize());
	    xaDataSource.setMaxPoolSize(config.getMaxPoolSize());
	    xaDataSource.setMaxLifetime(config.getMaxLifetime());
	    xaDataSource.setBorrowConnectionTimeout(config.getBorrowConnectionTimeout());
	    xaDataSource.setLoginTimeout(config.getLoginTimeout());
	    xaDataSource.setMaintenanceInterval(config.getMaintenanceInterval());
	    xaDataSource.setMaxIdleTime(config.getMaxIdleTime());
	    xaDataSource.setTestQuery(config.getTestQuery());
	    
		return xaDataSource;
	}
	
	
	//建立会话工厂
	@Bean(name = "carSqlSessionFactory")
	public SqlSessionFactory carSqlSessionFactory(@Qualifier("carDataSource") DataSource datasource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(datasource);
		
		//配置需要额外装载的映射文件xml
		bean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources(MybatisCarDataSource.MAPPER));
		//扫描加载需要关联的实体类
		bean.setTypeAliasesPackage(MybatisCarDataSource.ALIAS);
		return bean.getObject();
	}
	
	//会话工厂的装饰器
	@Bean(name = "carSqlSessionTemplate")
	public SqlSessionTemplate  carSqlSessionTemplate(@Qualifier("carSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory) ;
	}
}
