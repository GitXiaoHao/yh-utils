package top.yh.utils.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author yuhao
 * @date 2023/3/21
 **/
public class RedisUtil {
    private static final JedisPool POOL;
    private RedisUtil() {
    }

    static {
        //读取配置文件
        InputStream resourceAsStream = RedisUtil.class.getClassLoader().getResourceAsStream("jedis.properties");
        //创建properties对象
        Properties properties = new Properties();
        //关联文件
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //获取数据 设置到config中
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(properties.getProperty(("maxTotal"))));
        config.setMaxIdle(Integer.parseInt(properties.getProperty(("maxIdle"))));
        POOL = new JedisPool(config, properties.getProperty("host"), Integer.parseInt(properties.getProperty(("port"))));
    }

    /**
     * 获取连接方法
     *
     * @return jedis
     */
    public static Jedis getJedis() {
        Jedis resource;
        try {
            resource = POOL.getResource();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return resource;
    }
    /**
     * 关闭连接
     */
    public static void closeJedis(){
        POOL.close();
        System.out.println("关闭jedis");
    }
}
