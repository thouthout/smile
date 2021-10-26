import java.security.MessageDigest;

/**
 * @className: SecuritySHA1Utils
 * @description: sha1算法获取hash数组
 * @author: luweiming
 * @date: 2021/8/3
 **/
public class SecuritySHA1Utils {

    public static long[] shaEncode(String inStr) {
        MessageDigest sha = null;
        byte[] byteArray = null;
        try {
            sha = MessageDigest.getInstance("SHA");
            byteArray = inStr.getBytes("UTF-8");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return null;
        }

        byte[] md5Bytes = sha.digest(byteArray);
        long[] result = new long[md5Bytes.length];
        for (int i = 0; i < md5Bytes.length; i++) {
            long val = md5Bytes[i] & 0xff;
            result[i] = val;
        }
        return result;
    }
}
