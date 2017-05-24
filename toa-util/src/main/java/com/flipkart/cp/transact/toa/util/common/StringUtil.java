package com.flipkart.cp.transact.toa.util.common;

import org.apache.commons.lang.RandomStringUtils;

import java.util.Date;
import java.util.Random;

/**
 * Created by padmanabh.kulkarni on 02/06/15.
 */
public class StringUtil {
    public static String generateRandomCode() {
        Random rand = new Random();
        int randomNum = rand.nextInt((100000000 - 1) + 1) + 1;
        Long l = new Date().getTime();
        String randomString = l + randomNum + "";
        randomString = new StringBuilder(randomString).reverse().toString();
        System.out.println(randomString);

        return randomString;

    }

    public static final String randomAlphanumeric(int count) {
        String randomString = RandomStringUtils.randomAlphanumeric(count);
        return randomString;

    }
}
