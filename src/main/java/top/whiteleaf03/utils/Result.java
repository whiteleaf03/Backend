package top.whiteleaf03.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author WhiteLeaf03
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Result {

    /**
     * 状态码
     * status:0   success
     * status:1   auth failed
     * status:3   refuse
     * status:5   error
     */
    @JsonProperty(index = 1)
    private Integer status;

    /**
     * 提示信息
     */
    @JsonProperty(index = 2)
    private String msg;

    /**
     * 数据
     */
    @JsonProperty(index = 3)
    private Object data;

    public Result(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Result(Integer status, Object data) {
        this.status = status;
        this.data = data;
    }

    public Result(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功访问
     */
    public static Result success() {
        return new Result(0, "OK");
    }

    /**
     * 成功访问，并返回数据
     */
    public static Result success(Object data) {
        return new Result(0, "OK", data);
    }

    /**
     * 验证失败
     */
    public static Result authFailed(String msg) {
        return new Result(1, msg);
    }

    /**
     * 拒绝访问
     */
    public static Result refuse(String msg) {
        return new Result(3, msg);
    }

    /**
     * 访问错误
     */
    public static Result error() {
        return new Result(5, "ERROR");
    }

    /**
     * 访问错误
     */
    public static Result error(String msg) {
        return new Result(5, msg);
    }
}
