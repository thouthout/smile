package day0921;

import java.util.*;

/**
 * @className: Main
 * @description: TODO
 * @author: luweiming
 * @date: 2022/9/20
 * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * *
 * * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * *
 * * 示例 1：
 * *
 * * 输入：digits = "23"
 * * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * * 示例 2：
 * *
 * * 输入：digits = ""
 * * 输出：[]
 * * 示例 3：
 * *
 * * 输入：digits = "2"
 * * 输出：["a","b","c"]
 **/
public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        List<String> strings = main.letterCombinations("34");
        System.out.println(strings);
    }


    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        if (digits == null || digits.length() == 0) {
            return result;
        }

        char[] chars = digits.toCharArray();

        List<List<String>> lists = new ArrayList<>();
        for (char c : chars) {
            lists.add(char2String(c));
        }

        Node root = new Node("");

        //生成树
        tree(root, lists, 0);

        return ergodicTree(root);
    }

    //用于临时存储路径
    private StringBuilder sb = new StringBuilder();
    private List<String> res = new ArrayList<>();

    /**
     * 遍历树
     *
     * @param tree
     * @return
     */
    private List<String> ergodicTree(Node tree) {

        getCode(tree, tree.c, sb);
        return res;
    }

    private void getCode(Node node, String str, StringBuilder sb) {

        StringBuilder stringBuilder = new StringBuilder(sb);
        stringBuilder.append(str);

        if (node.nodes.size() > 0) {
            for (Node n : node.nodes) {
                getCode(n, n.c, stringBuilder);
            }
        } else {
            res.add(stringBuilder.toString());
        }

    }

    private void tree(Node node, List<List<String>> lls, int index) {

        List<String> cl = lls.get(index);
        for (String c : cl) {
            node.nodes.add(new Node(c));
        }
        index++;
        if (index < lls.size()) {
            for (Node n : node.nodes) {
                tree(n, lls, index);
            }
        }

    }


    private static List<String> char2String(char c) {
        List<String> characters = null;
        switch (c) {
            case '1':
                characters = Arrays.asList("");
                break;
            case '2':
                characters = Arrays.asList("a", "b", "c");
                break;
            case '3':
                characters = Arrays.asList("d", "e", "f");
                break;
            case '4':
                characters = Arrays.asList("g", "h", "i");
                break;
            case '5':
                characters = Arrays.asList("j", "k", "l");
                break;
            case '6':
                characters = Arrays.asList("m", "n", "o");
                break;
            case '7':
                characters = Arrays.asList("p", "q", "r", "s");
                break;
            case '8':
                characters = Arrays.asList("t", "u", "v");
                break;
            case '9':
                characters = Arrays.asList("w", "x", "y", "z");
                break;


        }
        return characters;
    }

    private class Node {

        String c;
        List<Node> nodes;

        public Node(String c) {
            this.c = c;
            nodes = new ArrayList<>();
        }

    }

}
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }












    public void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
            return;
        }
        char digit = digits.charAt(index);
        String letters = phoneMap.get(digit);
        for (int i = 0; i < letters.length(); i++) {
            combination.append(letters.charAt(i));
            backtrack(combinations, phoneMap, digits, index + 1, combination);
            combination.deleteCharAt(index);
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.letterCombinations("235");
    }
}

