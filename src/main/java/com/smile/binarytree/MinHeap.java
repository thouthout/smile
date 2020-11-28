package com.smile.binarytree;

import com.smile.vo.BinaryTree;

/**
 * @author luweiming
 * @date 2020/11/21 7:48 下午
 * 最小堆问题
 */
public class MinHeap {

    public int[] data;


    public MinHeap(int[] data){
        this.data = data;
        buildHeap();
    }

    public void buildHeap(){
        for (int i = (data.length) / 2 - 1; i >= 0 ; i--) {
            heapify(i);
        }
    }

    public void heapify (int i){  //父节点数组下标
        int left = (i + 1) * 2 - 1;
        int right = (i + 1) * 2;

        int smallest = i;   //默认父节点为 为最小节点

        if(left <= data.length - 1 && data[smallest] < data[left]){
            //交换 smallest 和 left下标对应的数据
            smallest = left;
        }

        if(right <= data.length - 1 && data[smallest] < data[right]){
            smallest = right;
        }

        if(smallest == i){   //没有发生交换   父节点数据依然是最小的
            return;
        }
        swap(i, smallest);
        heapify(smallest);
    }

    public void swap(int i, int j){
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    /**
     * 求无序数组最小的前 n 有序的数
     * @param array  无序数组
     * @param n 前n
     *
     * 取无序数组前n个数构建最大堆，遍历剩余数据比较
     * @return
     */
    public static int[] topK(int[] array, int n) {
        //data = array;

        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = array[i];
        }

        MinHeap minHeap = new MinHeap(data);

        for (int i = n; i < array.length; i++) {
            if(array[i] < minHeap.data[0]){
                minHeap.data[0] = array[i];
                minHeap.heapify(0);
            }
        }
        return minHeap.data;
    }

    public static void main(String[] args) {
        // 源数据
        int[] data = {56,275,12,6,45,478,41,1236,456,12,546,45};

        // 获取Top5
        int[] top5 = topK(data, 8);

        for(int i=0;i<8;i++)
        {
            System.out.println(top5[i]);
        }
    }

}
