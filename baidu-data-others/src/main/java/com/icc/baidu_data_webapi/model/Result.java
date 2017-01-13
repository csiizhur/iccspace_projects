package com.icc.baidu_data_webapi.model;

import java.util.List;

public interface Result<T> {

    public boolean isSuccess();

    public void setSuccess(boolean success);

    public String getMessage();

    public void setMessage(String message);

    public T getModel();

    public void setModel(T model);

    public List<T> getModels();

    public void setModels(List<T> models);

    public int getTotalRows();

    public void setTotalRows(int totalRows);

}
