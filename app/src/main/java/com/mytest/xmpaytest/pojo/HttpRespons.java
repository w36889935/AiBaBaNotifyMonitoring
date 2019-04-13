package com.mytest.xmpaytest.pojo;

import java.util.Vector;

/**
 * 类说明
 * HTTP响应对象
 * @author 王伟
 * @title HttpRespons
 * @package com.mytest.mytest.pojo
 * @date 2019年03月06日 19:32
 * @version 版本号 Copyright (c)  2014
 * Company 湖南慧明达信息技术有限公司
 */
public class HttpRespons {
    public String urlString;

    public int defaultPort;

    public String file;

    public String host;

    public String path;

    public int port;

    public String protocol;

    public String query;

    public String ref;

    public String userInfo;

    public String contentEncoding;

    public String content;

    public String contentType;

    public int code;

    public String message;

    public String method;

    public int connectTimeout;

    public int readTimeout;

    public Vector<String> contentCollection;

    public String getContent() {
        return content;
    }

    public String getContentType() {
        return contentType;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Vector<String> getContentCollection() {
        return contentCollection;
    }

    public String getContentEncoding() {
        return contentEncoding;
    }

    public String getMethod() {
        return method;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public String getUrlString() {
        return urlString;
    }

    public int getDefaultPort() {
        return defaultPort;
    }

    public String getFile() {
        return file;
    }

    public String getHost() {
        return host;
    }

    public String getPath() {
        return path;
    }

    public int getPort() {
        return port;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getQuery() {
        return query;
    }

    public String getRef() {
        return ref;
    }

    public String getUserInfo() {
        return userInfo;
    }

    @Override
    public String toString() {
        return "HttpRespons{" +
                "urlString='" + urlString + '\'' +
                ", defaultPort=" + defaultPort +
                ", file='" + file + '\'' +
                ", host='" + host + '\'' +
                ", path='" + path + '\'' +
                ", port=" + port +
                ", protocol='" + protocol + '\'' +
                ", query='" + query + '\'' +
                ", ref='" + ref + '\'' +
                ", userInfo='" + userInfo + '\'' +
                ", contentEncoding='" + contentEncoding + '\'' +
                ", content='" + content + '\'' +
                ", contentType='" + contentType + '\'' +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", method='" + method + '\'' +
                ", connectTimeout=" + connectTimeout +
                ", readTimeout=" + readTimeout +
                ", contentCollection=" + contentCollection +
                '}';
    }
}
