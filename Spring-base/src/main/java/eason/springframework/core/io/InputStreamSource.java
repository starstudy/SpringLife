package eason.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author sxx
 * @brief 暂时理解为结合jdk的io控制流的基础类
 * @details
 * @date 2017-07-22 17:55
 */
public interface InputStreamSource {

    /**
     * 返回resource的inputstream
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;

}
