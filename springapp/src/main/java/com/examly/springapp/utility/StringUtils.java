package com.examly.springapp.utility;

public class StringUtils {
    public static String capitalize(String string) {
        string=string.toLowerCase().trim();
        char parts[]=string.toCharArray();
        parts[0]=(char)(parts[0]-32);
        for(int i=0;i<parts.length-1;i++)
            if(parts[i]==' ')
                parts[i+1]=(char)(parts[i+1]-32);
        return String.valueOf(parts);
    }
}
