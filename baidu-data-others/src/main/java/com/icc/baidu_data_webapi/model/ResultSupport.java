package com.icc.baidu_data_webapi.model;

import java.io.Serializable;
import java.util.List;

public class ResultSupport<T> implements Result<T>, Serializable {

    private static final long serialVersionUID = 7507150342486016972L;
    private boolean           success          = true;
    private T                 model;
    private List<T>           models;
    private String            message;
    private int               totalRows;

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public List<T> getModels() {
        return models;
    }

    public void setModels(List<T> models) {
        this.models = models;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
