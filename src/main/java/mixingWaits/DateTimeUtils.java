package mixingWaits;

import java.time.*;

public class DateTimeUtils {
    public static long calculateDuration(LocalDateTime start, LocalDateTime end) {
        return Duration.between(start, end).toMillis();
    }
}
