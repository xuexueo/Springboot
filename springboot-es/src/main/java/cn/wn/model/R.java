package cn.wn.model;

import lombok.Data;

@Data
public class R<T> {
    private boolean success;
    private String message;
    private T obj;

    public R(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public R(boolean success) {
        this.success = success;
    }

    public R(boolean success, T obj) {
        this.success = success;
        this.obj = obj;
    }
}
