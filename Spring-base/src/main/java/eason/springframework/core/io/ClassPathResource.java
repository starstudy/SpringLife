package eason.springframework.core.io;

import org.jetbrains.annotations.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author sxx
 * @brief ClassPath加载类
 * @details
 * @date 2017-07-23 14:44
 */
public class ClassPathResource extends AbstractFileResolvingResource {

    private final String path;

    @Nullable
    private ClassLoader classLoader;

    @Nullable
    private Class<?> clazz;

    /**
     * 构造方法一
     * @param path
     * @param clazz
     */
    public ClassPathResource(String path, @Nullable Class<?> clazz) {
        Assert.notNull(path, "Path 必须不能为空");
        this.path = StringUtils.cleanPath(path);
        this.clazz = clazz;
    }

    /**
     * 构造方法二
     * @param path
     * @param classLoader
     * @param clazz
     */
    protected ClassPathResource(String path, @Nullable ClassLoader classLoader, @Nullable Class<?> clazz) {
        this.path = StringUtils.cleanPath(path);
        this.classLoader = classLoader;
        this.clazz = clazz;
    }

    /**
     * 构造方法三
     * @param path
     * @param classLoader
     */
    public ClassPathResource(String path, @Nullable ClassLoader classLoader) {
        Assert.notNull(path, "Path 一定不能为空");
        String pathToUse = StringUtils.cleanPath(path);
        if (pathToUse.startsWith("/")) {
            pathToUse = pathToUse.substring(1);
        }
        this.path = pathToUse;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    /**
     * 构造方法四
     * @param path
     */
    public ClassPathResource(String path) {
        this(path, (ClassLoader) null);
    }

    /**
     * 返回class  path的地址位置描述
     */
    @Override
    public String getDescription() {
        StringBuilder builder = new StringBuilder("class Path resource [");
        String pathToUse = path;
        if (this.clazz != null && !pathToUse.startsWith("/")) {
            builder.append(ClassUtils.classPackageAsResourcePath(this.clazz));
            builder.append("/");
        }
        if (pathToUse.startsWith("/")) {
            pathToUse = pathToUse.substring(1);
        }
        builder.append(pathToUse);
        builder.append("]");
        return builder.toString();
    }


    /**
     * 返回resource的inputstream
     */
    @Override
    public InputStream getInputStream() throws IOException {
        InputStream in;
        if (this.clazz != null) {
            in = this.clazz.getResourceAsStream(this.path);
        } else if (this.classLoader != null) {
            in = this.classLoader.getResourceAsStream(this.path);
        } else {
            in = this.classLoader.getSystemResourceAsStream(this.path);
        }
        if (in == null) {
            throw  new FileNotFoundException(getDescription() + "打不开文件，因为它并不存在");
        }
        return in;
    }
}
