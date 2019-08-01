package com.zfr.aaron.spring.jvm;

/**
 * @author zfr
 *
 */
public class Kafka {

    public static void main() {

        ReplicaManager replicaManager = new ReplicaManager();

        replicaManager.loadReplicasFromDisk();
    }

}
