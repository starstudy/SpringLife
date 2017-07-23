package eason.springframework.core.io;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 * @author sxx
 * @brief spring资源处理的公用接口类
 * @detailsø
 * @date 2017-07-22 17:53
 */
public interface Resource extends InputStreamSource {

    /**
     * resource  if exists
     */
    boolean exists();

    /**
     * resource if be readed
     */
    boolean isReadable();

    /**
     * resource  if open
     */
    boolean isOpen();

    /**
     * return a URL handle for the  resource
     */
    URL getURL() throws IOException;

    /**
     * return a URI Handle for the resource
     */
    URI getURI() throws IOException;

    /**
     * return a File Handle for this resource
     */
    File getFile() throws IOException;

    /**
     * 资源内容的长度
     */
    long contentLength() throws IOException;

    /**
     * 资源最后被修改的时间
     */
    long lastModified() throws IOException;

    /**
     * 根据相对路径创建资源
     */
    Resource createRelative(String relativePath) throws IOException;


    /**
     * 返回一个资源的描述，在返回错误的时候使用
     * 鼓励集成的方法去返回这个值
     * @return
     */
    String getDescription();
}
