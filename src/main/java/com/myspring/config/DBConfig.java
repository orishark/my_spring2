package com.myspring.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.myspring.model.board.dao.BoardDAOImpl;
import com.myspring.model.member.dao.MemberDAOImpl;
import com.myspring.model.reply.dao.ReplyDAOImpl;

@Configuration
@MapperScan(basePackageClasses=BoardDAOImpl.class)
@MapperScan(basePackageClasses=MemberDAOImpl.class)
@MapperScan(basePackageClasses=ReplyDAOImpl.class)
public class DBConfig {

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis-mapper/*.xml"));
		
		//Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mybatis-mapper/*.xml");
		//sessionFactoryBean.setMapperLocations(res);
		
		return sessionFactoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory factory) {
		return new SqlSessionTemplate(factory);
	}
}
