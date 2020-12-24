package com.kshrd.derphsar_api.rest.utils;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class CommonUtils {

    public ModelMapper getMapper(){
        return new ModelMapper();
    }

    public Timestamp getCurrentTime(){
        return new Timestamp(System.currentTimeMillis());
    }
}
