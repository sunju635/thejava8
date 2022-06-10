package study.thejava8.datetime.one;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

//기존 Date 관련 API 문제점들
public class App {

    public static void main(String[] args) throws InterruptedException {
        //이름이 정확하지 않다. date지만 시간까지 나타내고, timestampe도 표현한다.
        Date date = new Date();
        long time = date.getTime(); //날짜에서 time을 가져온다는게 이상하다. 그리고 해당 time은 epoch 기준 time
        System.out.println(date);
        System.out.println(time);

        Thread.sleep(1000*3);
        Date after3Seconds = new Date();
        System.out.println(after3Seconds);
        System.out.println(after3Seconds.getTime());
        after3Seconds.setTime(time); // mutable하다. multi thread환경에서 안전하지 않다. thread safe하지 않다.
        System.out.println(after3Seconds);
        System.out.println(after3Seconds.getTime());


        Calendar calendar = new GregorianCalendar(1982,7,15); //month가 0으로 시작한다. 그리고 타입 안전성이 없다(year int 아무거나 들어 올 수 있다. -1도 가능)
        Calendar calendar2 = new GregorianCalendar(1982,Calendar.JULY,15);
        System.out.println(calendar2.getTime()); //time인데 date가 나옴
        calendar2.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println(calendar2.getTime()); // mutable하다.


    }
}
