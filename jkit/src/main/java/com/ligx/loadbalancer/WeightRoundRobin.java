package com.ligx.loadbalancer;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Author: ligongxing.
 * Date: 2018/12/28.
 * Description: 负载均衡之加权轮询算法
 */
public class WeightRoundRobin {

    private List<Server> serverList;

    public WeightRoundRobin(List<Server> serverList) {
        this.serverList = serverList;
    }

    public Server next() {
        int totalWeight = 0;
        for (int i = 0; i < serverList.size(); i++) {
            Server server = serverList.get(i);
            totalWeight += server.getWeight();
            server.setCurrentWeight(server.getCurrentWeight() + server.getWeight());
        }

        Collections.sort(serverList, new Comparator<Server>() {
            @Override
            public int compare(Server o1, Server o2) {
                return o2.getCurrentWeight() - o1.getCurrentWeight();
            }
        });
        Server selected = serverList.get(0);

        selected.setCurrentWeight(selected.getCurrentWeight() - totalWeight);

        return selected;
    }


}
