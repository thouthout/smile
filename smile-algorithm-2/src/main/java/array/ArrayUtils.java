package array;

/**
 * @className: ArrayUtils
 * @description: TODO
 * @author: luweiming
 * @date: 2022/1/14
 **/
public class ArrayUtils {

    /**
     * 去除有序数组重复元素。返回个数,双指针
     * @param arr
     * @return
     */
    public static int removeDuplicates(int[] arr) {
        int size = 0;
        if (arr.length == 0){
            return size;
        }

        for (int i = 1; i < arr.length; i++) {

            if (arr[size] != arr[i]){
                size ++;
                arr[size] = arr[i];
            }

        }
        return size + 1;
    }

    public static void main(String[] args){
        int[] arr = {0,0,1, 3,4,6,6,7,7};
        System.out.println(removeDuplicates(arr));
    }


}
