package statck;

import java.util.Stack;

/**
 * @className: Solution
 * @description: TODO
 * @author: luweiming
 * @date: 2022/2/11
 **/
public class Solution {


    /**
     * 有效的括号
     * @param str
     */
    public Boolean isValid(String str){

        if (str == null || str.length() == 0){
            return Boolean.FALSE;
        }
        char[] chars = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '[' || chars[i] == '{' || chars[i] == '(' ){
                stack.push(chars[i]);
                break;
            }

            if (chars[i] == ']' && '[' != stack.pop()){
                return Boolean.FALSE;
            }

            if (chars[i] == '}' && '{' != stack.pop()){
                return Boolean.FALSE;
            }

            if (chars[i] == ')' && '(' != stack.pop()){
                return Boolean.FALSE;
            }
        }

        return stack.empty();

    }
}
