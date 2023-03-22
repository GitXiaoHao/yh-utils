package top.yh.servlet;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yuhao
 * @date 2023/3/15
 **/

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //完成方法转发
        //获取请求参数
        String uri = req.getRequestURI();
        //获取请求路径
        String methodName = uri.substring(uri.lastIndexOf("/") + 1);
        try {
            //获取方法对象
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    /**
     * 直接传入对象序列化为json 并写回客户端
     */
    public void writeValue(HttpServletResponse response, Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //设置json格式
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), object);
    }
    /**
     * 将传入的对象序列为json返回
     */
    public String writeValueAsString(Object object) throws JsonProcessingException {
        //序列化为Json
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
    public <T> T  getParam(HttpServletRequest request,T obj) throws IOException {
        // 1.接收请求传递的数据
        BufferedReader reader = request.getReader();
        return (T) JSON.parseObject( reader.readLine(), obj.getClass());
    }
}
