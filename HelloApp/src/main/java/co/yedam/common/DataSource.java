package co.yedam.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DataSource {
	private static SqlSessionFactory sqlSessionFactory;
	private DataSource() {
	}
	public static SqlSessionFactory getInstance() {
		String resource = "config/mybatis-config.xml"; //환경파일 읽음
		InputStream is = null; 
		
		try {
		 is = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		return sqlSessionFactory;
	}
}
