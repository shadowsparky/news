package ru.shadowsparky.news;

public class Validator {
    public static boolean isNullOrBlank(String text){
        return text == null || text.trim().length() == 0;
    }
}
