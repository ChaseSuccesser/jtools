package com.ligx.util;

import org.junit.Test;

/**
 * Author: ligongxing.
 * Date: 2018/09/28.
 */
public class DbUtilTest {

    @Test
    public void write() {
        DbUtil.write("jtools_demo,ClassName=ProfilingTestService,Method=ProfilingTestService.profilingTestV2 QPS=1i,Avg=577i,Min=186i,Max=917i,Count=9i,TP50=752i,TP90=917i,TP95=917i,TP99=917i,TP999=917i,TP9999=917i,TP99999=917i,TP100=917i 1538118444679000000");
    }
}