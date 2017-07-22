package eason.littletest.bean;

/**
 * @author sxx
 * @brief 测试spring 注入的简单bean
 * @details
 * @date 2017-07-19 13:47
 */
public class MyTestBean {

    private String testStr = "myStr";

    public String getTestStr() {
        System.out.println("MyTestbBean被调用");
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    public static void main(String[] args) throws Exception {
        try {
            MyTestBean myTestBean = new MyTestBean();
            String resourceUrl = myTestBean.getClass().getClassLoader().getResource("").getPath();
            System.out.println(resourceUrl);
        } catch (Exception e) {

        }

    }
}
