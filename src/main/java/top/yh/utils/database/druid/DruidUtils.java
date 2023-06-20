package top.yh.utils.database.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.io.Resources;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author yuhao
 * @date 2023/4/26
 * druid工具类
 **/
public class DruidUtils {
    private static DataSource dataSource;

    //静态代码块完成初始化
    static {
        Properties properties = new Properties();
        String resource = "druid.properties";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            properties.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * //关闭连接
     * //不是真正的关闭  放回连接池
     *
     * @param set
     * @param statement
     * @param connection
     */
    public static void close(ResultSet set, Statement statement, Connection connection) throws SQLException {
        //判断是否为空
        if (set != null) {
            set.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
