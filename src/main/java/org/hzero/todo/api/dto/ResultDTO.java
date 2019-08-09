package org.hzero.todo.api.dto;

/**
 * @program: hzero-order-24520-dd->ResultDTO
 * @description: 返回结果DTO类
 * @author: 胡超男
 * @create: 2019-08-06 19:33
 **/
public class ResultDTO {

    private String message;

    public ResultDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultDTO{" +
                "message='" + message + '\'' +
                '}';
    }
}
