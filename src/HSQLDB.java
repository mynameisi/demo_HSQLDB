

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HSQLDB {
	private final static Logger logger = LoggerFactory.getLogger(HSQLDB.class);

	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;
		try {
			//1. 载入驱动
			Class.forName("org.hsqldb.jdbcDriver");
			
			//2. 通过URL, 用户名和密码获得数据库链接Connection
			//   注意: file:db/mydb URL会自动在当前目录下创建db目录，在db目录下创建一个数据库名叫mydb
			conn = DriverManager.getConnection("jdbc:hsqldb:file:db/mydb;shutdown=true", "sa", "");
			logger.debug("在db目录下创建数据库mydb");
			
			//3. 创建数据表
			String sql = "create table user(id integer, name varchar(20), pass varchar(20));";
			st = conn.createStatement();
			st.execute(sql);
			logger.debug("在mydb数据库中创建了数据表user(id, name, pass)");
			
			//4. 添加数据
			sql = "insert into user VALUES ('1', 'admin1', 'admin1pass');";
			sql = "insert into user VALUES ('2', 'admin2', 'admin2pass');";
			st.executeUpdate(sql);
			logger.debug("在数据表中插入了数据");

		} catch (Exception e) {
			e.printStackTrace();
			return;
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (st != null) {
					st.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}