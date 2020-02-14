package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class jdbcUtils {

	public static DataSource getDb(){

			InputStream is=jdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
			Properties p= new Properties();
	
			DataSource db = null;
			
			try {
				p.load(is);
				db = DruidDataSourceFactory.createDataSource(p);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return db;
			
	}
	
	public static QueryRunner getQr(){

		return new QueryRunner(getDb());
		
	}
}
