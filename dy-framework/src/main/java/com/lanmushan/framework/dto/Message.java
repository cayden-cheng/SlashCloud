package com.lanmushan.framework.dto;

import com.github.pagehelper.PageInfo;
import com.lanmushan.framework.constant.HTTPCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 通用数据传输通道，数据传输容器
 *
 * @author dy
 */
public class Message<T> implements Serializable {
    private int code = HTTPCode.Fail.code;
    private String msg = HTTPCode.Fail.msg;
    private long time;
    private Integer currentPage;
    private Integer pageSize;
    private int isFirstPage;
    private int isLastPage;
    private int previusPage;
    private int nextPage;
    /**
     * 总页数 该属性用于bootstrap-table分页
     */
    private int page;
    /**
     * 总记录数，该属性用于bootstrap-table分页
     */
    private long total;
    private List<Integer> pageList;

    private List<Error> errors;
    private Object row;
    private Collection<T> rows;



    public Message() {
        this.time = System.currentTimeMillis();
    }

    public static
/**
 * 通用字段校验错误情况返回
 */
    class Error {
        private String errMsg;
        private String errName;

        public Error() {
        }

        @Override
        public String toString() {
            return "Error{" +
                    "errMsg='" + errMsg + '\'' +
                    ", errName='" + errName + '\'' +
                    '}';
        }

        public String getErrMsg() {
            return errMsg;
        }

        public void setErrMsg(String errMsg) {
            this.errMsg = errMsg;
        }

        public String getErrName() {
            return errName;
        }

        public void setErrName(String errName) {
            this.errName = errName;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getRow() {
        return row;
    }

    public Message setRow(Object row) {
        this.row = row;
        this.setHttpCode(HTTPCode.OK);
        return this;
    }
    public void setHttpCode(HTTPCode httpCode)
    {
        this.code=httpCode.code;
        this.msg=httpCode.msg;
    }

    public Collection<T> getRows() {
        return rows;
    }

    public Message setRows(List rows) {
        if (null != rows && rows.size() > 0) {
            PageInfo pageInfo = new PageInfo(rows);
            this.setCurrentPage(pageInfo.getPageNum());
            this.setPageSize(pageInfo.getPageSize());
            this.setTotal(pageInfo.getTotal());
            this.rows = rows;
            this.setHttpCode(HTTPCode.OK);
            this.success("查询成功");
        } else {
            this.setHttpCode(HTTPCode.OK204);
        }

        return this;
    }

    public Message error(String msg) {
        this.setHttpCode(HTTPCode.Fail);
        this.msg = msg;
        return this;
    }
    public Message error(HTTPCode httpCode,String msg) {
        this.setHttpCode(httpCode);
        this.msg = msg;
        return this;
    }
    public Message success(String msg) {
        this.setHttpCode(HTTPCode.OK);
        this.msg = msg;
        return this;
    }
    public void addError(String attrName, String attrMsg) {
        if (null == errors) {
            errors = new ArrayList<>();
        }
        Error error = new Error();
        error.setErrMsg(attrMsg);
        error.setErrName(attrName);
        this.errors.add(error);
    }

    public Message setTotal(long total) {
        this.total = total;
        if (currentPage == null) {
            return this;
        }
        if (currentPage == page) {
            isLastPage = 1;
        }
        this.setPage(((int) total) % pageSize == 0 ? ((int) total) / pageSize : (((int) total) / pageSize) + 1);
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public List<Integer> getPageList() {
        return pageList;
    }

    /**
     * 计算页码
     */
    private void calcPageList() {
        pageList = new ArrayList();
        int temp = page - currentPage - 2;
        int start = currentPage - 2 + (temp < 0 ? temp : 0);
        int n = 5 - ((start < 0 ? start : 0) - 1);
        for (int i = 0; i < n; i++) {
            if (start >= 1 && start <= page) {
                pageList.add(start);
            }
            start++;
        }
        if (this.currentPage < page) {
            this.nextPage = currentPage + 1;
        } else {
            this.nextPage = currentPage;
            isLastPage = 1;
        }
        if (this.currentPage >= 2) {
            this.previusPage = currentPage - 1;
        } else {
            this.previusPage = currentPage;
            isFirstPage = 1;
        }
    }

    public Message setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public String getMsg() {
        return msg;
    }

    private void setMsg(String msg) {
        this.msg = msg;
    }


    public static Message getInstance(String msg) {

        return new Message();
    }


}



