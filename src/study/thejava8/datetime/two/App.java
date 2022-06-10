package study.thejava8.datetime.two;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class App {

    public static void main(String[] args) {
        //머신용 시간
        Instant instant = Instant.now();
        System.out.println(instant);
        ZoneId zone = ZoneId.systemDefault();
        System.out.println(zone);
        instant.atZone(zone);
        System.out.println(instant);

        //휴먼용 계산
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        LocalDateTime birthday = LocalDateTime.of(1992, Month.JULY, 15, 0, 0, 0);
        System.out.println(birthday);
        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println(nowInKorea);
        now.plus(10, ChronoUnit.DAYS);
        System.out.println("nowInKorea: " + nowInKorea);//Immutable하다. 변하지 않았음.
        LocalDateTime plusNow = now.plus(10, ChronoUnit.DAYS);
        System.out.println("plusNow: " + plusNow); //Immutable하다.

        Instant nowInstant = Instant.now();
        ZonedDateTime zonedDateTime = nowInstant.atZone(ZoneId.of("Asia/Seoul"));
        System.out.println(zonedDateTime);


        LocalDate today = LocalDate.now();
        LocalDate thisYearBirthday = LocalDate.of(2022, Month.OCTOBER, 3);

        //휴먼용 시간 계산
        Period period = Period.between(today, thisYearBirthday);
        System.out.println(period.getDays());

        Period until = today.until(thisYearBirthday);
        System.out.println(until.get(ChronoUnit.DAYS));

        //머신용 시간 계산
        Instant plus = instant.plus(10, ChronoUnit.SECONDS);
        Duration between2 = Duration.between(instant, plus);
        System.out.println(between2.getSeconds());

        // format
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(time.format(formatter));

        LocalDate parse = LocalDate.parse("07/15/1982", formatter);
        System.out.println(parse);

        //기존 Date API와 호환
        Date date = new Date();
        Instant instant2 = date.toInstant();

        Date newDate = Date.from(instant);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        LocalDateTime dateTime2 = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        ZonedDateTime zonedDateTime2 = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault());
        GregorianCalendar from = GregorianCalendar.from(zonedDateTime2);

        ZoneId zoneId = TimeZone.getTimeZone("PST").toZoneId();
        TimeZone timeZone = TimeZone.getTimeZone(zoneId);




    }
}
