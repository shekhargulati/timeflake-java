package timeflake;

import java.math.BigInteger;
import java.time.Instant;
import java.util.Random;
import java.util.UUID;

public class Timeflake {

    private static final String HEX = "0123456789abcdef";
    private static final String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final BigInteger value;

    public Timeflake(BigInteger value) {
        this.value = value;
    }

    public String hex() {
        return itoa(value, HEX, 32);
    }

    public String base62() {
        return itoa(value, BASE62, 22);
    }

    public long timestamp() {
        return this.value.shiftRight(80).longValue();
    }

    public UUID uuid() {
        return UUID.nameUUIDFromBytes(this.value.toByteArray());
    }

    public static Timeflake random() {
        var timestamp = BigInteger.valueOf(Instant.now().toEpochMilli());
        var bytes = new byte[10];
        new Random().nextBytes(bytes);
        var rand = new BigInteger(1, bytes);
        var value = timestamp.shiftLeft(80).or(rand);
        return new Timeflake(value);
    }

    @Override
    public String toString() {
        return String.format("Timeflake(%s)", this.hex());
    }

    private static String itoa(BigInteger value, String alphabet, int padding) {
        if (value.equals(BigInteger.ZERO)) {
            return String.valueOf(alphabet.charAt(0));
        } else {
            String result = "";
            var base = BigInteger.valueOf(alphabet.length());
            while (value.signum() > 0) {
                var value1 = value.divide(base);
                var rem = value.remainder(base);
                value = value1;
                result = String.valueOf(alphabet.charAt(rem.intValue())) + result;
            }
            if (padding > 0) {
                var fill = Math.max(padding - result.length(), 0);
                result = String.valueOf(alphabet.charAt(0)).repeat(fill) + result;
            }
            return result;
        }
    }
}
