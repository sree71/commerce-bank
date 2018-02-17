package seniorproject.commercebank2;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Widget {
    private String name;
    private String timeStamp;

    public Widget(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public String getTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    }
}
