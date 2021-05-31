package value_objects;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class DateVO {

    private final Integer day;
    private final Integer month;
    private final Integer year;

    public DateVO(String stringDate) {
        if (isValid(stringDate)) {
            String[] parts = stringDate.split("-");

            this.year = Integer.parseInt(parts[0]);
            this.month = Integer.parseInt(parts[1]);
            this.day = Integer.parseInt(parts[2]);
            return;
        }
        throw new IllegalArgumentException("Invalid Date. Valid format is: yyyy-MM-dd");
    }

    public String getDateSting() {
        return year.toString() + "-" + month.toString() + "-" + day.toString();
    }

    /**
     * Date validity check. Checks for the format as well as if the date actually exists
     *
     * @param date the date to check
     * @return whether the date is valid
     */
    private boolean isValid(String date) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateVO dateVO = (DateVO) o;
        return Objects.equals(day, dateVO.day) && Objects.equals(month, dateVO.month) && Objects.equals(year, dateVO.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }
}
