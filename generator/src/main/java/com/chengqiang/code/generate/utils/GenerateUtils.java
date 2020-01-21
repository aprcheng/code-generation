package com.chengqiang.code.generate.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerateUtils {

    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * 下划线转驼峰
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return firstUpperCase(sb.toString());
    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String firstUpperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);

    }

    /**
     * 首字母小写
     *
     * @param str
     * @return
     */
    public static String firstLowerCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'A' && ch[0] <= 'Z') {
            ch[0] = (char) (ch[0] + 32);
        }
        return new String(ch);

    }
}
