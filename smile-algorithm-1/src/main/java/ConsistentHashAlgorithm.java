

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @className: ConsistentHashAlgorithm
 * @description: hash一致性算法及虚拟环实现
 * @author: luweiming
 * @date: 2021/8/2
 **/
public class ConsistentHashAlgorithm {

    /**
     * 虚拟节点，key表示虚拟节点的hash值，value表示虚拟节点的名称
     */
    private static SortedMap<Long, String> virtualNodes = new TreeMap<>();

    /**
     * 当节点的数目很少时，容易造成数据的分布不均，所以增加虚拟节点来平均数据分布
     * 虚拟节点的数目；虚拟节点的生成主要是用来让数据尽量均匀分布,虚拟节点是真实节点的不同映射而已
     * 比如真实节点user1的hash值为100，那么我们增加3个虚拟节点user1-1、user1-2、user1-3，分别计算出来的hash值可能就为200，345，500；通过这种方式来将节点分布均匀
     */
    private static final int VIRTUAL_NODES = 1024;

    public ConsistentHashAlgorithm() {
    }

    /*public ConsistentHashAlgorithm(SortedMap<Long, String> virtualTableNodes, Collection<String> tableNodes) {
        if (Objects.isNull(virtualTableNodes)) {
            virtualTableNodes = initNodesToHashLoop(tableNodes);
        }

        this.virtualNodes = virtualTableNodes;
    }*/

    /**
     * 构建hash虚拟环
     * @param tableNodes
     * @return
     */
    public static SortedMap<Long, String> initNodesToHashLoop(Collection<String> tableNodes) {
        SortedMap<Long, String> virtualTableNodes = new TreeMap<>();
        int i = 0;
        for (String tableNode : tableNodes) {
            for (int j = 0; j < VIRTUAL_NODES; j++) {
                String virtualNodeKey =  i + ":" + j;
                long hash = getHash(virtualNodeKey);

                String virtualNodeName = tableNode + "-VN" + j;
                virtualTableNodes.put(hash, virtualNodeName);
            }
            i ++;
        }
        return virtualTableNodes;
    }

    public static void main(String[] args) {
        List<String> orderList = new ArrayList<>();
        String order = "idcard_info_";
        for (int i = 0; i <= 63; i++) {
            orderList.add(order + i);
        }
        SortedMap<Long, String> longStringSortedMap = initNodesToHashLoop(orderList);
        virtualNodes = longStringSortedMap;

        /*String tableNode = getTableNode("cdfc419a574a0e2e0471a6e78524ad85feca20508116d29eb48607a19f4a32be");
        System.out.println(tableNode);*/
        try{
            String uid = "";
            String[] split = uid.split(",");
            for (int i = 0; i < 1000; i++) {
                String tableNode = getTableNode("cdfc419a574a0e2e0471a6e78524ad85feca20508116d29eb48607a19f4a32be" + i);
                System.out.println("cdfc419a574a0e2e0471a6e78524ad85feca20508116d29eb48607a19f4a32be" + i + ":" + tableNode);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 通过计算key的hash
     * 计算映射的表节点
     *
     * @param key
     * @return
     */
    public static String getTableNode(String key) {
        String virtualNode = getVirtualTableNode(key);
        //虚拟节点名称截取后获取真实节点
        if (!"".equals(virtualNode) && virtualNode != null) {
            return virtualNode.substring(0, virtualNode.indexOf("-"));
        }
        return null;
    }

    /**
     * 获取虚拟节点
     * @param key
     * @return
     */
    public static String getVirtualTableNode(String key) {
        long hash = getHash(key);
        // 得到大于该Hash值的所有Map
        SortedMap<Long, String> subMap = virtualNodes.tailMap(hash);
        String virtualNode;
        if (subMap.isEmpty()) {
            //如果没有比该key的hash值大的，则从第一个node开始
            Long i = virtualNodes.firstKey();
            //返回对应的服务器
            virtualNode = virtualNodes.get(i);
        } else {
            //第一个Key就是顺时针过去离node最近的那个结点
            Long i = subMap.firstKey();
            //返回对应的服务器
            virtualNode = subMap.get(i);
        }

        return virtualNode;
    }

    public static long genValue (long[] bs){
        int length = 4;
        if(bs.length < length) {
            return 0;
        }
        long value = bs[3] << 24 | bs[2] << 16 | bs[1] << 8 | bs[0];
        return value;
    }

    /**
     * 使用sha1算法计算key的Hash值
     *
     * @param key
     * @return
     */
    public static long getHash(String key){
        long[] keyByte = SecuritySHA1Utils.shaEncode(key);
        long value = genValue(Arrays.copyOfRange(keyByte, 6, 10));
        return value;
    }
}
