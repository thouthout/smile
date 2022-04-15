package doublePoint;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @className: Solution
 * @description: TODO
 * @author: luweiming
 * @date: 2022/4/2
 **/
public class Solution {

    /**
     * 最小覆盖子串
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * @param originStr
     * @param childStr
     */
    public String minWindow(String originStr, String childStr){
        Map<Character, Integer> childMapCount = new HashMap<>();

        char[] chars = childStr.toCharArray();

        int[] windows = new int[128];

        for (int i = 0; i < chars.length; i++) {
            childMapCount.put(chars[i], childMapCount.get(chars[i])  + 1);
        }

        int right = 0;
        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int valid = 0;
        int start = 0;

        while (right < originStr.length()){
            char rightChar = originStr.charAt(right);
            right ++;
            if (childMapCount.containsKey(rightChar)){
                windows[right] ++ ;
                if (windows[right] == childMapCount.get(rightChar)){
                    valid ++;
                }
            }

            while(valid == childMapCount.size()){
                if (right - left < minLen){
                    minLen = right - left;
                    start = left;
                }
                char leftStr = originStr.charAt(left);
                left ++;

                if (childMapCount.containsKey(leftStr)){
                    if (childMapCount.get(leftStr) == windows[left]){
                        valid --;
                    }
                    windows[left] --;
                }
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : originStr.substring(start, start + minLen);
    }
}
