package com.cristal.stefanie.cursomc.resources.utils;

import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {
//    public static List<Integer> decodeIntList(String s) { //decodificar para lista de inteiros
//        List<String> vet = Arrays.stream(s.trim().split(",")).filter(s1 -> ! s1.isBlank()).collect(Collectors.toList());
//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < vet.size(); i++) {
//            list.add(Integer.parseInt(vet.get(i)));
//        }
//        return list;
//    }

    public static String decodeParam(String s) {
        try {
            return URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
