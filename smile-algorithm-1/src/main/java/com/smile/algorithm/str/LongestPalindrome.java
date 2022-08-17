package com.smile.algorithm.str;

/**
 * @className: LongestPalindrome
 * @description: 最长回文子串
 * @author: luweiming
 * @date: 2021/10/15
 **/
public class LongestPalindrome {

    public String longest(String s){

        if("".equals(s)) {
            return s;
        }

        int n = s.length(), start = 0, maxlen = 1;

        for(int i =0;i<n;i++){
            for(int j =i+1;j<n;j++){
                int temp1 = i,temp2 = j;
                while(temp1 <temp2 && s.charAt(temp1) == s.charAt(temp2)){
                    temp1++; temp2--;
                }

                if(temp1 >= temp2 && j-i+1 >maxlen){
                    maxlen = j-i+1;
                    start = i;
                }
            }
        }

        return s.substring(start,start+maxlen);
    }
































}
