package com.inventory.kafka.config;

import org.springframework.context.SmartLifecycle;
import org.springframework.integration.kafka.util.TopicUtils;

/**
 * Created by eko.j.manurung on 6/6/2016.
 */
public class TopicCreator implements SmartLifecycle {

    private final String topic;

    private final String zkConnect;

    private volatile boolean running;

    public TopicCreator(String topic, String zkConnect) {
        this.topic = topic;
        this.zkConnect = zkConnect;
    }

    @Override
    public void start() {
        TopicUtils.ensureTopicCreated(this.zkConnect, this.topic, 1, 1);
        this.running = true;
    }

    @Override
    public void stop() {
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }

    @Override
    public int getPhase() {
        return Integer.MIN_VALUE;
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        callback.run();
    }
}
