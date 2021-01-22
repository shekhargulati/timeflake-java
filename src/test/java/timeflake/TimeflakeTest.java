package timeflake;

import org.junit.Test;

public class TimeflakeTest {

    @Test
    public void should_generate_timeflake_hex() {
        var random = Timeflake.random();
        System.out.println(random.hex());
        System.out.println(random.base62());
        System.out.println(random.timestamp());
        System.out.println(random.uuid());
    }
}
