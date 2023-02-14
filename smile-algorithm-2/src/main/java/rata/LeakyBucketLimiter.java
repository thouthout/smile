package rata;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Project: AllForJava
 * Title:
 * Description:
 * Date: 2020-09-08 16:45
 * Copyright: Copyright (c) 2020
 *
 * @公众号: 超悦编程
 * @微信号：exzlco
 * @author: 超悦人生
 * @email: exzlc@139.com
 * @version 1.0
 **/
public class LeakyBucketLimiter {

    private int capaticy;//漏斗容量
    private int rate;//漏斗速率
    private int left;//剩余容量
    private LinkedList<Request> requestList;

    private LeakyBucketLimiter() {}

    public LeakyBucketLimiter(int capaticy, int rate) {
        this.capaticy = capaticy;
        this.rate = rate;
        this.left = capaticy;
        requestList = new LinkedList<>();

        //开启一个定时线程，以固定的速率将漏斗中的请求流出，进行处理
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if(!requestList.isEmpty()){
                        Request request = requestList.removeFirst();
                        handleRequest(request);
                        left++;
                    }
                    try {
                        Thread.sleep(1000 / rate); //睡眠
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 处理请求
     * @param request
     */
    private void handleRequest(Request request){
        request.setHandleTime(new Date());
        System.out.println(request.getCode() + "号请求被处理，请求发起时间："
                + request.getLaunchTime() + ",请求处理时间：" + request.getHandleTime() + ",处理耗时："
                + (request.getHandleTime().getTime()  - request.getLaunchTime().getTime()) + "ms");
    }

    public synchronized boolean tryAcquire(Request request){
        if(left <= 0){
            return false;
        }else{
            left --;
            requestList.addLast(request);
            return true;
        }
    }


    /**
     * 请求类，属性包含编号字符串、请求达到时间和请求处理时间
     */
    static class Request{
        private int code;
        private Date launchTime;
        private Date handleTime;

        private Request() { }

        public Request(int code,Date launchTime) {
            this.launchTime = launchTime;
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public Date getLaunchTime() {
            return launchTime;
        }

        public void setLaunchTime(Date launchTime) {
            this.launchTime = launchTime;
        }

        public Date getHandleTime() {
            return handleTime;
        }

        public void setHandleTime(Date handleTime) {
            this.handleTime = handleTime;
        }
    }

    public static void main(String[] args) {
        LeakyBucketLimiter leakyBucketLimiter = new LeakyBucketLimiter(5,2);
        for(int i = 1;i <= 10;i ++){
            Request request = new Request(i,new Date());
            if(leakyBucketLimiter.tryAcquire(request)){
                System.out.println(i + "号请求被接受");
            }else{
                System.out.println(i + "号请求被拒绝");
            }
        }
    }

    /**
     * 可以看到1-5号请求被接受，而6-10号请求被拒绝，说明此时漏斗已经溢出了，符合我们的预期。
     * 我们再关注下被接受的这5个请求的处理情况，可以看到这5个请求虽然被接受了，但是处理是一个一个被处理的（不一定是顺序的，取决于具体实现），大约每500ms处理一个。这就体现了漏斗算法的特点了，即虽然请求流量是瞬时产生的，但是请求以固定速率流出被处理。因为我们设定的漏斗速率为2个/秒，所以每500ms漏斗会漏出一个请求然后进行处理。
     * 特点分析
     *
     * 漏桶的漏出速率是固定的，可以起到整流的作用。即虽然请求的流量可能具有随机性,忽大忽小，但是经过漏斗算法之后，变成了有固定速率的稳定流量，从而对下游的系统起到保护作用。
     * 不能解决流量突发的问题。还是拿刚刚测试的例子，我们设定的漏斗速率是2个/秒，然后突然来了10个请求，受限于漏斗的容量，只有5个请求被接受，另外5个被拒绝。你可能会说，漏斗速率是2个/秒，然后瞬间接受了5个请求，这不就解决了流量突发的问题吗？不，这5个请求只是被接受了，但是没有马上被处理，处理的速度仍然是我们设定的2个/秒，所以没有解决流量突发的问题。而接下来我们要谈的令牌桶算法能够在一定程度上解决流量突发的问题，读者可以对比一下。

     */
}






 class Funnel {

    //漏斗容量
    int capacity;
    //漏水速度
    double leakingRate;
    //漏斗剩余空间(不是水的大小)
    int leftQuota;
    //上次漏斗漏水的时间
    long leakingTs;

    public Funnel(int capacity, double leakingRate, int leftQuota, long leakingTs) {
        this.capacity = capacity;
        this.leakingRate = leakingRate;
        this.leftQuota = leftQuota;
        this.leakingTs = leakingTs;
    }

    private void makeSpace() {
        long nowTs = System.currentTimeMillis();
        //这个是间隔的时间
        long deltaTs = nowTs - this.leakingTs;
        //漏掉的水
        int deltaQuota = (int)(deltaTs * leakingRate);
        //间隔时间太长,溢出
        if (deltaQuota < 0){
            this.leftQuota = capacity;
            this.leakingTs = nowTs;
            return;
        }
        //说明漏的时间不够
        if (deltaQuota < 1) {
            return;
        }
        this.leakingTs = nowTs;
        this.leftQuota = this.leftQuota + deltaQuota;
        if (this.leftQuota > this.capacity) {
            this.leftQuota = this.capacity;
        }
    }
    /**
     * 流入
     * @param quota
     * @return
     */
    public Boolean water(int quota) {
        makeSpace();
        //表示量充足
        if (leftQuota >= quota){
            leftQuota =  leftQuota - quota;
            return true;
        }
        //剩余量不够
        return false;
    }

}


 class FunnelLimitRate {

    private static Map<String,Funnel> funnels = new HashMap<>();


    public static void main(String[] args) throws InterruptedException {
        String userId= "2019";
        String actionKey = "rgister";
        int capacity = 8;
        double leakingRate = 0.001;
        int leftQuota = 5;
        Funnel funnel = funnels.get(userId);
        if (funnel == null) {
            funnel = new Funnel(capacity, leakingRate, leftQuota, System.currentTimeMillis());
        }
        //每1000ms(1s)，流入1的水
        //每1000ms，流出1000*leakingRate(而且要>1)的水
        for (int i = 0; i < 8; i++) {
            Boolean isBoolean = funnel.water(1);
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println(isBoolean + " " + funnel.leftQuota);
        }

    }

     /**
      * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
      *
      * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
      *
      * 返回容器可以储存的最大水量。
      *
      * 说明：你不能倾斜容器。
      */







}
