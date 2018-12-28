package com.ligx.loadbalancer;

import lombok.*;

/**
 * Author: ligongxing.
 * Date: 2018/12/28.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Server {

    private String ip;

    private int weight;

    private int currentWeight = 0;
}
