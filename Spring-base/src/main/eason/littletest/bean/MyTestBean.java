package main.eason.littletest.bean;

/**
 * @author sxx
 * @brief
 * @details
 * @date 2017-07-19 13:47
 */
public class MyTestBean {

    private String testStr = "myStr";

    public String getTestStr() {
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
