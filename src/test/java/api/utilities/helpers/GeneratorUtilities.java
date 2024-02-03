package api.utilities.helpers;

import java.util.Random;
import java.util.UUID;

public class GeneratorUtilities {
    public static String generateRandomString(int length) {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            char randomChar = chars[random.nextInt(chars.length)];
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }

    public static String generateRandomText(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        while (sb.length() < length) {
            sb.append(UUID.randomUUID().toString().replaceAll("-", ""));
        }
        return sb.substring(0, length);
    }

    public static long generateRandomNumber(long minValue, long maxValue) {
        Random random = new Random();

        return random.longs(minValue, maxValue)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid range"));
    }

    public static String generateRandomNumber(int length) {
        int min = (int) Math.pow(10, length - 1);
        int max = (int) Math.pow(10, length); // bound is exclusive

        Random random = new Random();

        return Integer.toString(random.nextInt(max - min) + min);
    }
}
