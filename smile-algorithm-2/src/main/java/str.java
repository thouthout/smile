import java.util.HashMap;

/**
 * @className: str
 * @description: TODO
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
      int abcabcd = lengthOfLongestSubstring("abcabcd");
        System.out.println(abcabcd);
  }
}
