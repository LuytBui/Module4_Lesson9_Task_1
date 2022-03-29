package com.codegym.model;

import java.util.ArrayList;
import java.util.List;

public class BadWord {
    public static List<String> list = new ArrayList<>();
    static {
        list.add("a ");
        list.add("b ");
        list.add("c ");
        list.add("d ");
        list.add("luyt");
    }

    public static boolean check(String string){
        for (String word : list){
            boolean check = string.toLowerCase().contains(word.toLowerCase());
            if (check) return true;
        }
        return false;
    }
}
