package com.ligx.loadbalancer;

import java.util.List;
import java.util.Random;

/**
 * Author: ligongxing.
 * Date: 2019/04/10.
 * Description: 负载均衡之加权随机算法
 */
public class WeightRandom {

    private final Random random = new Random();

    private List<Server> serverList;

    public WeightRandom(List<Server> serverList) {
        this.serverList = serverList;
    }

    public Server next() {
        int totalWeight = 0;
        for (int i = 0; i < serverList.size(); i++) {
            totalWeight += serverList.get(i).getWeight();
        }

        int offset = random.nextInt(totalWeight);
        for (int i = 0; i < serverList.size(); i++) {
            Server server = serverList.get(i);
            offset -= server.getWeight();
            if (offset < 0) {
                return server;
            }
        }

        return serverList.get(random.nextInt(serverList.size()));
    }
}
