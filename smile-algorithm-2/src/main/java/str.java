import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @className: str
 * @description: 不重复的最长子串
 * @author: luweiming
 * @date: 2022/3/14
 **/
public class str {

    public static int lengthOfLongestSubstring(String s) {

        HashMap<Character,Integer> hashMap=new HashMap<> ();
        int res=0;

        for(int i=0, j=0; i<s.length ();i++){
            char next = s.charAt(i);
            int nextCount = hashMap.getOrDefault(next, 0) + 1;
            hashMap.put (next, nextCount);

            while (hashMap.get(next) > 1){
                hashMap.put (s.charAt (j), hashMap.get (s.charAt (j)) - 1);
                j++;
            }

            res = Math.max (res, i-j+1);
        }

        return  res;
    }

  public static void main(String[] args) {
      int abcabcd = longestStr("abcabcdefgabcd");
        System.out.println(abcabcd);
  }


  public static Integer longestStr(String str){
        Integer max = 0;
        if (null == str || "".equals(str)){
            return max;
        }

        Set<Character> window = new HashSet<>();

        for (int i = 0, j=0; i < str.length(); i++) {
            char curr = str.charAt(i);

            boolean add = window.add(curr);
            while (!add) {
                window.remove(str.charAt(j));
                j++;
                add = window.add(curr);
            }
            max = Math.max(max, i-j+1);
        }

        return max;
  }





















}
