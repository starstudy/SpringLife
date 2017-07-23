package eason.springframework.core.io;

import org.springframework.core.NestedIOException;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author sxx
 * @brief 资源类的静态方法
 * @details
 * @date 2017-07-23 14:59
 */
public abstract class AbstractResource implements Resource {

    /**
     * 重写resource的exists方法
     *
     * @details 判断文件是否存在，如果判断过程中出现异常，则关闭文件流
     */
    @Override
    public boolean exists() {
        try {
            return getFile().exists();
        } catch (IOException e) {
            try {
                InputStream in = getInputStream();
                in.close();
                return true;
            } catch (Throwable ex) {
                return false;
            }
        }
    }

    /**
     * 直接返回可读
     */
    @Override
    public boolean isReadable() {
        return true;
    }

    /**
     * 直接返回未被打开
     */
    @Override
    public boolean isOpen() {
        return false;
    }

    /**
     * 返回异常，留给子类重写
     */
    @Override
    public URL getURL() throws IOException {
        throw new FileNotFoundException(getDescription() + "不能转化为 URL");
    }

    /**
     * 返回异常，留给子类重写
     */
    @Override
    public File getFile() throws IOException {
        throw new FileNotFoundException(getDescription() + "不能转化为 File");
    }

    /**
     * 返回uri
     */
    @Override
    public URI getURI() throws IOException {
        URL url = getURL();
        try {
            return ResourceUtils.toURI(url); //将url格式化后返回
        } catch (URISyntaxException ex) {
            throw new NestedIOException("无效的 URI [" + url + "]", ex);
        }
    }

    /**
     * 计算资源的长度，实际上就是计算该资源的所有字节长度
     */
    @Override
    public long contentLength() throws IOException {
        InputStream in = getInputStream();
        Assert.state(in != null, "资源输入流必须为非空的");

        try {
            long size = 0;
            byte[] buffer = new byte[255];
            int read;
            while ((read = in.read(buffer)) != -1) {
                size += read;
            }
            return size;
        } finally {
            try {
                in.close();
            } catch (IOException ex) {

            }
        }
    }


    /**
     * 返回资源的最后修改时间
     */
    @Override
    public long lastModified() throws IOException {
        long lastModified = getFileForLastModifiedCheck().lastModified();
        if (lastModified == 0L)
            throw new FileNotFoundException(getDescription() + "无法解析资源的最后修改的时间戳");
        return lastModified;
    }

    /**
     * 返回文件，给时间戳检查，要求子类重写
     */
    protected File getFileForLastModifiedCheck() throws IOException {
        return getFile();
    }

    /**
     * 留给子类重写
     */
    @Override
    public Resource createRelative(String relativePath) throws IOException {
        throw new FileNotFoundException(getDescription() + "无法创建资源");
    }

    /**
     * 返回描述
     */
    @Override
    public String toString() {
        return getDescription();
    }

    /**
     * 返回资源描述的hashcode
     * @return
     */
    @Override
    public int hashCode() {
        return getDescription().hashCode();
    }

    /**
     * 比较两个资源描述是否一样
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        return (obj == null || (obj instanceof Resource && ((Resource) obj).getDescription().equals(getDescription())));
    }
}
