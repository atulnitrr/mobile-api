package com.apps.developer.moblileappws.utils;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class Utils {

    private static final String  ALPHABATE = "01234567689abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new
            SecureRandom();

    public String generateUserId(int length) {
        System.out.println(RANDOM.nextInt(12));
        return generateRandomString(length);
    }

    private String generateRandomString(final int length) {
        final StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            stringBuilder.append(ALPHABATE.charAt(RANDOM.nextInt(ALPHABATE.length())));
        }

        return stringBuilder.toString();
    }
}
