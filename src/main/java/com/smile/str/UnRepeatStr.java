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
            right = i;
            while (right < origin.length() && !sonStr.contains(origin.charAt(right))) {// 不断地移动右指针
                sonStr.add(origin.charAt(right));
                ++right;
            }
            result = Math.max(result, right - i);// 第 i 到 rk 个字符是一个极长的无重复字符子串
        }
        return result;
    }

    public static void main(String[] args) {
        int count = getUnRepeatCount("abcdaefgd");
        System.out.println("count:" + count);

        /*UnRepeatStr kmp = new UnRepeatStr();
        String str = "abababdafdasabcfdfeaba";
        String pattern = "abc";
        System.out.println(kmp.kmp(str, pattern));*/

    }



    void getNext(String pattern, int next[]) {
        int j = 0;
        int k = -1;
        next[0] = -1;

        while (j < pattern.length() - 1) {
            if (k == -1 || pattern.charAt(k) == pattern.charAt(j)) {

                j++;
                k++;
                next[j] = k;
            } else {

                // 比较到第K个字符，说明p[0——k-1]字符串和p[j-k——j-1]字符串相等，而next[k]表示
                // p[0——k-1]的前缀和后缀的最长共有长度，所接下来可以直接比较p[next[k]]和p[j]
                k = next[k];
            }
        }

    }

    int kmp(String s, String pattern) {
        int i = 0;
        int j = 0;
        int slen = s.length();
        int plen = pattern.length();

        int[] next = new int[plen];

        getNext(pattern, next);

        while (i < slen && j < plen) {

            if (s.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                if (next[j] == -1) {
                    i++;
                    j = 0;
                } else {
                    j = next[j];
                }

            }

            if (j == plen) {
                return i - j;
            }
        }
        return -1;
    }

}
