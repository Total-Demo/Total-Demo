package com.barry.nacosprovider.util;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class ExceptionUtil {
    public static String handleException(String s,BlockException ex) {
        return "Oops, error occurred at " + s;
    }
}
