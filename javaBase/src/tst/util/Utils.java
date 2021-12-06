package tst.util;

public class Utils {
    public static void  seperator(int len){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<len; i++)
            sb.append("-");
        System.out.println(sb);
    }
}
