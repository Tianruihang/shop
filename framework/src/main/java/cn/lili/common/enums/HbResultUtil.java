package cn.lili.common.enums;


import cn.lili.common.vo.HbResultMessage;

/**
 * 返回结果工具类
 *
 * @author lili
 */
public class HbResultUtil<T> {

    /**
     * 抽象类，存放结果
     */
    private final HbResultMessage<T> resultMessage;
    /**
     * 正常响应
     */
    private static final Integer SUCCESS = 200;


    /**
     * 构造话方法，给响应结果默认值
     */
    public HbResultUtil() {
        resultMessage = new HbResultMessage<>();
        resultMessage.setSuccess(true);
        resultMessage.setReturnMsg("success");
        resultMessage.setCode(SUCCESS);
    }

    /**
     * 返回数据
     *
     * @param t 范型
     * @return 消息
     */
    public HbResultMessage<T> setData(T t) {
        this.resultMessage.setResult(t);
        return this.resultMessage;
    }


    /**
     * 返回成功消息
     *
     * @param resultCode 返回码
     * @return 返回成功消息
     */
    public HbResultMessage<T> setSuccessMsg(ResultCode resultCode) {
        this.resultMessage.setSuccess(true);
        this.resultMessage.setReturnMsg(resultCode.message());
        this.resultMessage.setCode(resultCode.code());
        return this.resultMessage;

    }

    /**
     * 抽象静态方法，返回结果集
     * @param t 范型
     * @param <T>  范型
     * @return 消息
     */
    public static <T> HbResultMessage<T> data(T t) {
        return new HbResultUtil<T>().setData(t);
    }

    /**
     * 返回成功
     *
     * @param resultCode 返回状态码
     * @return 消息
     */
    public static <T> HbResultMessage<T> success(ResultCode resultCode) {
        return new HbResultUtil<T>().setSuccessMsg(resultCode);
    }

    /**
     * 返回成功
     * @return 消息
     */
    public static <T> HbResultMessage<T> success() {
        return new HbResultUtil<T>().setSuccessMsg(ResultCode.SUCCESS);
    }

    /**
     * 返回失败
     *
     * @param resultCode 返回状态码
     * @return 消息
     */
    public static <T> HbResultMessage<T> error(ResultCode resultCode) {
        return new HbResultUtil<T>().setErrorMsg(resultCode);
    }

    /**
     * 返回失败
     *
     * @param code 状态码
     * @param msg  返回消息
     * @return 消息
     */
    public static <T> HbResultMessage<T> error(Integer code, String msg) {
        return new HbResultUtil<T>().setErrorMsg(code, msg);
    }

    /**
     * 服务器异常 追加状态码
     * @param resultCode 返回码
     * @return 消息
     */
    public HbResultMessage<T> setErrorMsg(ResultCode resultCode) {
        this.resultMessage.setSuccess(false);
        this.resultMessage.setReturnMsg(resultCode.message());
        this.resultMessage.setCode(resultCode.code());
        return this.resultMessage;
    }

    /**
     * 服务器异常 追加状态码
     *
     * @param code 状态码
     * @param msg  返回消息
     * @return 消息
     */
    public HbResultMessage<T> setErrorMsg(Integer code, String msg) {
        this.resultMessage.setSuccess(false);
        this.resultMessage.setReturnMsg(msg);
        this.resultMessage.setCode(code);
        return this.resultMessage;
    }

}
