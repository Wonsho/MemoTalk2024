package com.wons.memotalk;

import org.junit.Test;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Date;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void checkDate() {
        System.out.println(Date.from(Instant.now()).toString() + "asdasd");
    }
}