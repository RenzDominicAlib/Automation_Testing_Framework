package com.renz.helpers;

import java.io.File;

public class SystemHelpers {

    public SystemHelpers() {
        super();
    }

    public static String getCurrentDir() {
        String current = System.getProperty("user.dir") + File.separator;
        return current;
    }

}
