package com.wons.memotalk.util;

import android.content.Context;
import android.widget.Toast;

import com.wons.memotalk.R;

public class SystemUtil {
    private static boolean exit = false;
    public static boolean checkExit() {
        new Thread(() -> {
            exit = true;
            try {
                Thread.sleep(2500);
                exit = false;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        return exit;
    }
}
