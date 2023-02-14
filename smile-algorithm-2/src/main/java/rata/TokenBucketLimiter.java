package rata;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Project: AllForJava
 * Title:
 * Description:
 * Date: 2020-09-08 19:22
 * Copyright: Copyright (c) 2020
 *
 * @公众号: 超悦编程
 * @微信号：exzlco
 * @author: 超悦人生
 * @email: exzlc@139.com
 * @version 1.0
 **/
public class TokenBucketLimiter {

    private int capaticy;//令牌桶容量
    private int rate;//令牌产生速率
    private int tokenAmount;//令牌数量

    public TokenBucketLimiter(int capaticy, int rate) {
        this.capaticy = capaticy;
        this.rate = rate;
        tokenAmount = capaticy;
        new Thread(new Runnable() {
            @Override
            public void run() {
                //以恒定速率放令牌
                while (true){
                    synchronized (this){
                        tokenAmount ++;
                        if(tokenAmount > capaticy){
                            tokenAmount = capaticy;
                        }
                    }
                    try {
                        Thread.sleep(1000 / rate);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public synchronized boolean tryAcquire(Request request){
        if(tokenAmount > 0){
            tokenAmount --;
            handleRequest(request);
            return true;
        }else{
            return false;
        }

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

    /**
     * 请求类，属性只包含一个名字字符串
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


    public static void main(String[] args) throws InterruptedException {
        TokenBucketLimiter tokenBucketLimiter = new TokenBucketLimiter(5,2);
        for(int i = 1;i <= 10;i ++){
            Request request = new Request(i,new Date());
            if(tokenBucketLimiter.tryAcquire(request)){
                System.out.println(i + "号请求被接受");
            }else{
                System.out.println(i + "号请求被拒绝");
            }
        }
    }
}










 class TokenLimiter {

    private ArrayBlockingQueue<String> blockingQueue;

    //容量大小
    private int limit;

    //令牌的产生间隔
    private int period;

    //令牌每次产生的个数
    private int amount;

    public TokenLimiter(int limit, int period, int amount) {
        this.limit = limit;
        this.period = period;
        this.amount = amount;

        blockingQueue = new ArrayBlockingQueue<>(limit);
    }

    private void init() {
        for(int i = 0; i < limit; i++) {
            blockingQueue.add("lp");
        }
    }

    private void addToken(int amount) {
        for(int i = 0; i < amount; i++) {
            //溢出返回false
            boolean lp = blockingQueue.offer("lp");
            System.out.println("offer result:" + lp);
        }
    }

    /**
     * 获取令牌
     * @return
     */
    public boolean tryAcquire() {
        //队首元素出队
        return blockingQueue.poll() != null ? true : false;
    }

    /**
     * 生产令牌
     */
    private void start(Object lock) {
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            synchronized (lock) {
                addToken(this.amount);
                lock.notify();
            }
        }, 500, this.period, TimeUnit.MILLISECONDS);
    }

    /**
     * 先生产2个令牌，减少4个令牌；再每500ms生产2个令牌，减少4个令牌
     */
    public static void main(String[] args) throws InterruptedException {
        int period = 500;
        TokenLimiter limiter = new TokenLimiter(8, period, 2);
        limiter.init();

        Object lock = new Object();
        limiter.start(lock);

        //让线程先产生2个令牌(溢出)
        synchronized (lock) {
            lock.wait();
        }
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 4; j++) {
                String s = i + "，" + j + "：";
                if(limiter.tryAcquire()) {
                    System.out.println(s + "拿到令牌");
                }
                else{
                    System.out.println(s + "拒绝");
                }
            }
            Thread.sleep(period);
        }
    }

}




















