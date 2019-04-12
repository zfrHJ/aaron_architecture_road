package com.zfr.aaron.redis.booleanfilter;

import io.rebloom.client.Client;

public class BooleanTest {

    public static void main(String[] args) {
        Client client = new Client("192.168.170.137",6379);

        client.delete("boolean:aaron:test");
        for (int i = 0; i < 100000; i++) {
            client.add("boolean:aaron:test", "user" + i);
            boolean ret = client.exists("boolean:aaron:test", "user" + (i + 1));
            if (ret) {
                System.out.println(i);
                break;
            }
        }

        //client.close();
    }

}
