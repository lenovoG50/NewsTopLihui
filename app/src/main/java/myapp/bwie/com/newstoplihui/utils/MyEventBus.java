package myapp.bwie.com.newstoplihui.utils;

/**
 * 类的用途:
 *
 * @author 李辉
 * @date 2017/2/24 15:01.
 */

public class MyEventBus {
    private String dayOrNitht;

    public MyEventBus(String dayOrNitht) {
        this.dayOrNitht = dayOrNitht;
    }

    public String getDayOrNitht() {
        return dayOrNitht;
    }

    public void setDayOrNitht(String dayOrNitht) {
        this.dayOrNitht = dayOrNitht;
    }
}
