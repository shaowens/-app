package com.example.myapplication5;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Utils {
    /**
     * 正则匹配手机号码:
     */
    public static boolean checkTel(String tel){
        Pattern p = Pattern.compile("^[1][3,4,5,7,8,9][0-9]{9}$");
        Matcher matcher = p.matcher(tel);
        return matcher.matches();
    }
}
