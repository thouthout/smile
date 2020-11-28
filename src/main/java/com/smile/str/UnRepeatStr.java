package com.smile.str;

import java.util.HashSet;
import java.util.Set;

/**
 * @author luweiming
 * @date 2020/11/27 8:55 下午
 */
public class UnRepeatStr {

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * @return
     */
    public static int getUnRepeatCount(String origin){
        Set<Character> sonStr = new HashSet(); // 哈希集合，记录每个字符是否出现过
        int right = -1;// 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int result = 0;
        for (int i = 0; i < origin.length(); ++i) {
            sonStr.clear();
            right = i - 1;
            while (right + 1 < origin.length() && !sonStr.contains(origin.charAt(right + 1))) {// 不断地移动右指针
                sonStr.add(origin.charAt(right + 1));
                ++right;
            }
            result = Math.max(result, right - i + 1);// 第 i 到 rk 个字符是一个极长的无重复字符子串
        }
        return result;
    }

    public static void main(String[] args) {
        int count = getUnRepeatCount("abcdaefgd");
        System.out.println("count:" + count);

    }
}
