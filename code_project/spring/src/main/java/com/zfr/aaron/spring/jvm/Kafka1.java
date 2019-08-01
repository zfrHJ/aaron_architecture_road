package com.zfr.aaron.spring.jvm;

/**
 * @author zfr
 *
 *kafka1也是放在方法区，存放的是类对象信息
 *
 */
public class Kafka1 {

    /**
     *
     * 堆内存长期存活对象，放在方法区
     */
    private static ReplicaFetcher replicaFetcher = new ReplicaFetcher();

    public static void main(String[] args) {

        loadReplicasFromDisk();

        while (true){
            fetchReplicasFromRemote();
        }
    }


    /**
     * 存放在虚拟机栈
     */
    private static void fetchReplicasFromRemote() {

        replicaFetcher.fetch();
    }

    /**
     * 存放在虚拟机栈
     */
    public static void loadReplicasFromDisk() {
        ReplicaManager replicaManager = new ReplicaManager();
        replicaManager.load();
    }




}
