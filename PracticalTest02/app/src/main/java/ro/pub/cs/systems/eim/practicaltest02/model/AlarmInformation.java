package ro.pub.cs.systems.eim.practicaltest02.model;

public class AlarmInformation {

    private String hour;
    private String min;


    public AlarmInformation() {
        this.hour = null;
        this.min = null;

    }

    public AlarmInformation(String hour, String min) {
        this.hour = hour;
        this.min = min;

    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }



    @Override
    public String toString() {
        return "AlarmInformation{" +
                "hour='" + hour + '\'' +
                ", min='" + min + '\'' +
                '}';
    }

}
