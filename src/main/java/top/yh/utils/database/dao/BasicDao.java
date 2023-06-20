package top.yh.utils.database.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import top.yh.utils.database.druid.DruidUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author yuhao
 * @date 2023/4/26
 **/
public interface BasicDao<T> {
    QueryRunner queryRunner = new QueryRunner();

    /**
     *  开发通用的 DML 方法，针对任意的表
     *
     * @param sql        sql语句
     * @param parameters 替换sql语句的参数
     * @return 所成功执行的次数
     */
    default int update(String sql, Object... parameters) throws SQLException {
        Connection connection = null;
        try {
            connection = DruidUtils.getConnection();
            return queryRunner.update(connection, sql, parameters);
        } catch (SQLException e) {
            // 将编译异常转为运行异常抛出
            e.printStackTrace();
            return 0;
        } finally {
            DruidUtils.close(null, null, connection);
        }
    }


    /**
     * 返回多个对象（即查询的结果是多行的），针对任意表
     * @param sql：SQL语句，可以有 ?
     * @param tClass：传入一个类的 Class 对象，比如 Actor.class
     * @param parameters：传入 ? 的具体值，可以有多个
     * @return 根据 Actor.class 返回对应的 ArrayList 集合
     */
    default List<T> queryMultiple(String sql, Class<T> tClass, Object... parameters) throws SQLException {
        Connection connection = null;
        try {
            connection = DruidUtils.getConnection();
            return queryRunner.query(connection, sql, new BeanListHandler<>(tClass), parameters);
        } catch (SQLException e) {
            // 将编译异常转为运行异常
            e.printStackTrace();
            return null;
        } finally {
            DruidUtils.close(null, null, connection);
        }
    }

    /**
     * 查询单行结果
     *
     * @param sql        sql语句
     * @param tClass     传入一个类的 Class 对象，比如 Actor.class
     * @param parameters 参数
     * @return tClass的类型
     */
    default T querySingle(String sql, Class<T> tClass, Object... parameters) throws SQLException {
        Connection connection = null;
        try {
            connection = DruidUtils.getConnection();
            return queryRunner.query(connection, sql, new BeanHandler<>(tClass), parameters);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DruidUtils.close(null, null, connection);
        }
    }

    /**
     * 查询单行单列的方法，即返回的是单个值的方法
     *
     * @param sql        sql语句
     * @param parameters 参数
     * @return 任意类型的数据
     */
    default Object queryScalar(String sql, Object... parameters) throws SQLException {
        Connection connection = null;
        try {
            connection = DruidUtils.getConnection();
            return queryRunner.query(connection, sql, new ScalarHandler<>(), parameters);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DruidUtils.close(null, null, connection);
        }
    }
}
