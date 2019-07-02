package com.apps.developer.moblileappws.utils;

import org.junit.Test;


public class UtilsTest {

    private Utils utils = new Utils();

    @Test
    public void testUtils() {
        final String userId = utils.generateUserId(256);
        System.out.println(userId);
    }
}
