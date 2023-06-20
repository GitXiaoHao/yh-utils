package top.yh.utils.result;

import lombok.Data;

/**
 * @author yuhao
 * @description
 * @date 2023/6/18 21:57
 **/
@Data
public class Result<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 数据
     */
    private T data;

    // 构造私有化 外部不能new
    private Result() {
    }

    /**
     * 封装返回数据
     *
     * @param body           数据信息
     * @param resultCodeEnum 状态码
     * @param <T>            数据的类型
     * @return Result
     */
    public static <T> Result<T> build(T body, ResultCodeEnum resultCodeEnum) {
        Result<T> result = new Result<>();
        // 封装数据
        if (body != null) {
            result.setData(body);
        }
        // 状态码
        result.setCode(resultCodeEnum.getCode());
        //返回信息
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    /**
     * 成功 空结果
     */
    public static <T> Result<T> ok() {
        return build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * 成功 返回有数据的结果
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok(T data) {
        return build(data, ResultCodeEnum.SUCCESS);
    }

    /**
     * 失败
     * @return
     * @param <T>
     */
    public static <T> Result<T> fail() {
        return build(null, ResultCodeEnum.FAIL);
    }

    /**
     * 失败  返回有数据的结果
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(T data) {
        return build(data, ResultCodeEnum.FAIL);
    }

    public Result<T> message(String msg) {
        this.setMessage(msg);
        return this;
    }

    public Result<T> code(Integer code) {
        this.setCode(code);
        return this;
    }
}
