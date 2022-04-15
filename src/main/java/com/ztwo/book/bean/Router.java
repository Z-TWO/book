package com.ztwo.book.bean;

import javax.persistence.Id;
import java.io.Serializable;

public class Router implements Serializable {
    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 请求路径
     */
    private String url;

    /**
     * 请求类型
     */
    private String method;

    /**
     * 权限 0-通用 1-用户 2-商家
     */
    private Integer type;

    private static final long serialVersionUID = 1L;

    public Router() {
    }

    public Router(String url, String method) {
        this.url = url;
        this.method = method;
    }

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取请求路径
     *
     * @return url - 请求路径
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置请求路径
     *
     * @param url 请求路径
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取请求类型
     *
     * @return method - 请求类型
     */
    public String getMethod() {
        return method;
    }

    /**
     * 设置请求类型
     *
     * @param method 请求类型
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 获取权限  0-通用 1-用户 2-商家
     *
     * @return type - 权限 0-通用 1-用户 2-商家
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置权限 0-通用 1-用户 2-商家
     *
     * @param type 权限 0-通用 1-用户 2-商家
     */
    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", url=").append(url);
        sb.append(", method=").append(method);
        sb.append(", type=").append(type);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Router router = (Router) o;

        if (router.method.equalsIgnoreCase("delete")) {
            router.url = router.url.substring(0, router.url.lastIndexOf('/') + 1);
        } else if (!url.equals(router.url)) {
            return false;
        }
        return method.equalsIgnoreCase(router.method);
    }

    @Override
    public int hashCode() {
        int result = url.hashCode();
        result = 31 * result + method.hashCode();
        return result;
    }
}