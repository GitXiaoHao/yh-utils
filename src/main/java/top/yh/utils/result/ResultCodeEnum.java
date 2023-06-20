package top.yh.utils.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yuhao
 * @description 返回结果对象的枚举
 * @date 2023/6/18 21:51
 **/
@AllArgsConstructor
@Getter
public enum ResultCodeEnum {
    /**
     *
     */
    SUCCESS(ResultCode.SUCCESS_CODE, "成功"),
    FAIL(ResultCode.FAIL_CODE, "失败"),
    SERVICE_ERROR(ResultCode.SERVICE_ERROR_CODE, "服务异常"),
    DATA_ERROR(ResultCode.DATA_ERROR_CODE, "数据异常"),
    LOGIN_AUTH(ResultCode.LOGIN_AUTH_CODE, "未登录"),
    PERMISSION(ResultCode.PERMISSION_CODE, "没有权限");
    /**
     * 状态码
     */
    private final Integer code;
    /**
     * 信息
     */
    private final String message;

}
