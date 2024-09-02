import java.util.Calendar;
import java.util.GregorianCalendar;

// Program My Date generates year, month, date as well as elapsed time
// version 1.3
public class MyDate {
    private int year;
    private int month;
    private int day;

    // 10.14.B: No-arg constructor that creates a MyDate object for the current date
    public MyDate() {
        Calendar calendar = new GregorianCalendar();
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH);
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    // 10.14.C: Constructor that constructs a MyDate object with a specified elapsed time since midnight, January 1, 1970
    public MyDate(long elapsedTime) {
        setDate(elapsedTime);
    }

    // 10.14.D: Constructor that constructs a MyDate object with the specified year, month, and day
    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // 10.14.E: Retrieves variable year
    public int getYear() {
        return year;
    }

    // 10.14.E: Retrieves variable month
    public int getMonth() {
        return month;
    }

    // 10.14.E: Retrieves variable day
    public int getDay() {
        return day;
    }

    // 10.14.F: Method to set a new date for the object using the elapsed time
    public void setDate(long elapsedTime) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(elapsedTime);
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH);
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    // The Test program, at the time of writing it functions as intended
    public static void main(String[] args) {
        // Create a MyDate object using the no-arg constructor
        MyDate date1 = new MyDate();
        System.out.println("Date 1: " + date1.getYear() + "-" + (date1.getMonth() + 1) + "-" + date1.getDay());

        // Create a MyDate object using the elapsedTime constructor
        MyDate date2 = new MyDate(34355555133101L);
        System.out.println("Date 2: " + date2.getYear() + "-" + (date2.getMonth() + 1) + "-" + date2.getDay());
    }
}

