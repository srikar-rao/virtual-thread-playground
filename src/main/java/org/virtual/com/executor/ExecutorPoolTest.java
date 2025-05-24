package org.virtual.com.executor;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.virtual.com.CommonUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorPoolTest {

    private static final Logger log = LoggerFactory.getLogger(ExecutorPoolTest.class);

    @Test
    public void fixedThreadPool(){
        execute(Executors.newFixedThreadPool(5), 10);
    }

    @Test
    public void singleThreadPool(){
        execute(Executors.newSingleThreadExecutor(), 200);
    }

    @Test
    public void cachedThreadPool(){
        execute(Executors.newCachedThreadPool(), 200);
    }

    @Test
    public void virtualThreadPool(){
        execute(Executors.newVirtualThreadPerTaskExecutor(), 200);
    }

    public static void execute(ExecutorService executorService, int taskCount) {
        try (executorService) {
            for (int i = 1; i <= taskCount; i++) {
                int finalI = i;
                executorService.execute(() -> ioTask(finalI));
            }
            log.info("Task submitted");
        }
    }


    private static void ioTask(int i) {
        log.info("IO Task started: {}, Thread info : {}", i, Thread.currentThread());
        CommonUtil.sleep(3000);
        log.info("IO Task Ended: {}, Thread info : {}", i, Thread.currentThread());
    }
}
