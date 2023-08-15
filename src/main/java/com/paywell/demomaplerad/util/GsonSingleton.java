package com.paywell.demomaplerad.util;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GsonSingleton {
    private static Gson instance;

    private GsonSingleton() {
        // Private constructor to prevent instantiation from outside
    }

    public static synchronized Gson getInstance() {
        if (instance == null) {
            instance = new Gson();
        }
        return instance;
    }
}
