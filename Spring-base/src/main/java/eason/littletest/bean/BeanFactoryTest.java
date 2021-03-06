package eason.littletest.bean;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author sxx
 * @brief 测试bean加载机制
 * @details
 * @date 2017-07-19 15:58
 */
public class BeanFactoryTest {

    private static String BEAN_XML_PATH = "context/mytest-spring-bean.xml";

    /**
     * main测试
     */
    public static void main(String[] args) throws Exception {
        BeanFactoryTest.testSimpleLoad();
    }

    /**
     * 测试简单的spring加载
     */
    public static void testSimpleLoad() {
        /**
         * way 1 (Out of date)
         */
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource(BEAN_XML_PATH));
        MyTestBean myTestBean = (MyTestBean) bf.getBean("myTestBean");
        myTestBean.getTestStr();

        /**
         * way 2
         */
        ApplicationContext context = new ClassPathXmlApplicationContext(BEAN_XML_PATH);
        MyTestBean myTestBeanExtend = (MyTestBean) context.getBean("myTestBean");
        myTestBeanExtend.getTestStr();
    }
}
