package eason.springframework.core.io.mainTest;

import java.io.IOException;
import java.io.InputStream;

import eason.springframework.core.io.ClassPathResource;
import eason.springframework.core.io.Resource;

/**
 * @author sxx
 * @brief
 * @details
 * @date 2017-07-23 22:19
 */
public class ClassPathResourceTest {

    private static String XMLPATH = "context/mytest-spring-bean.xml";

    /**
     * 加载配置文件
     * @throws IOException
     */
    public void testResourceLoad () throws IOException{
        Resource resource = new ClassPathResource(XMLPATH);
        InputStream is = resource.getInputStream();
    }

    /**
     * main
     * @param args
     * @throws Exception
     */
    public static void main (String[] args) throws Exception{
        ClassPathResourceTest test = new ClassPathResourceTest();
        test.testResourceLoad();
    }


}
