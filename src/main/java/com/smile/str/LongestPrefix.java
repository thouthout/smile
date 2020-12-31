package com.smile.str;

import java.util.*;

/**
 * @author luweiming
 * @date 2020/12/31 下午3:22
 */
public class LongestPrefix {


    /**
     * 数组最大想通前缀
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if(Objects.isNull(strs) || strs.length == 0){
            return "";
        }
        String longest = "";
        String result = "";
        int size = 1;
        int strLength = strs[0].length();
        for (int i = 0; i < strLength; i++) {
            longest = strs[0].substring(0, size);
            for (int j = 1; j < strs.length; j++) {
                String str = strs[j];
                if(size > str.length()){
                    break;
                }
                String strsecond = str.substring(0, size);
                if(!Objects.equals(longest, strsecond)){
                    return result;
                }
            }
            result = longest;
            ++ size;
        }

        return result;
    }

    public static void main(String[] args) {
        String strArray = "([)]";
        Boolean s = isValid(strArray);
        System.out.println(s);

    }


    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效,使用栈的思路解决
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put('(',')');
        map.put('{','}');
        map.put('[',']');
        Stack<Character> stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            if(stack.empty()){
                stack.push(s.charAt(i));
                continue;
            }
            Character peek = stack.peek();
            Character character = map.get(peek);
            if(Objects.nonNull(character) && s.charAt(i) == character){
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }
        return stack.empty();
    }
}
