package eason.springframework.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author sxx
 * @brief
 * @details
 * @date 2017-07-22 19:56
 */
public class MainTest {

    private static String BEAN_XML_PATH = "mytest-spring-bean.xml";

    /**
     * main测试
     */
    public static void main(String[] args) throws Exception {
        MainTest.testSimpleLoad();
    }

    /**
     * 测试简单的spring加载
     */
    public static void testSimpleLoad() {
        /**
         * way 1 (Out of date)
         *//*
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource(BEAN_XML_PATH));
        MyTestBean myTestBean = (MyTestBean) bf.getBean("myTestBean");
        myTestBean.getTestStr();*/

        /**
         * way 2
         */
        ApplicationContext context = new ClassPathXmlApplicationContext(BEAN_XML_PATH);
        TestOne myTestBeanExtend = (TestOne) context.getBean("TestOne");
        myTestBeanExtend.getMm();
    }
}
