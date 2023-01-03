package day0922;

/**
 * @className: Main2
 * @description: TODO
 * @author: luweiming
 * @date: 2022/9/20
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * 示例 3：
 * <p>
 * 输入：s = "A", numRows = 1
 * 输出："A"
 **/
public class Main2 {


    public static void main(String[] args) {
        String paypalishiring = reverseStr("PAYPALISHIRING", 3);
        System.out.println(paypalishiring);
    }

    public String convert(String s, int numRows) {
        String result = "";
        StringBuilder[] resultBuild = new StringBuilder[numRows];

        for (int i = 0; i < numRows; i++) {
            resultBuild[i] = new StringBuilder();
        }

        Integer index = 0;
        Boolean reverse = false;
        for (int i = 0; i < s.length(); i++) {
            resultBuild[index].append(s.charAt(i));
            if (index == numRows - 1) {
                reverse = true;
            }
            if (index == 0) {
                reverse = false;
            }
            index = reverse ? index - 1 : index + 1;
        }

        for (int i = 0; i < numRows; i++) {
            result = result + resultBuild[i].toString();
        }

        return result;
    }


    public static String reverseStr(String originStr, Integer rowNum) {

        String result = "";
        StringBuilder[] resultBuild = new StringBuilder[rowNum];

        for (int i = 0; i < rowNum; i++) {
            resultBuild[i] = new StringBuilder();
        }

        Integer index = 0;
        Boolean reverse = false;
        for (int i = 0; i < originStr.length(); i++) {
            resultBuild[index].append(originStr.charAt(i));
            if (index == rowNum - 1) {
                reverse = true;
            }
            if (index == 0) {
                reverse = false;
            }
            index = reverse ? index - 1 : index + 1;
        }

        for (int i = 0; i < rowNum; i++) {
            result = result + resultBuild[i].toString();
        }

        return result;
    }





























   /* public String convert(String s, int numRows) {
        if (numRows == 1) return s;

        StringBuilder[] sbArray = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++)
            sbArray[i] = new StringBuilder();

        boolean reverse = false;
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            sbArray[index].append(s.charAt(i));
            if (index == numRows - 1) reverse = true;
            if (index == 0) reverse = false;
            index = reverse ? index - 1 : index + 1;
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder sb : sbArray)
            result = result.append(sb);
        return result.toString();
    }*/


}
