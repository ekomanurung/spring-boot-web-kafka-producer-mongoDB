package com.inventory.kafka.config;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.kafka.core.BrokerAddress;
import org.springframework.integration.kafka.core.BrokerAddressListConfiguration;
import org.springframework.integration.kafka.core.ConnectionFactory;
import org.springframework.integration.kafka.core.DefaultConnectionFactory;
import org.springframework.integration.kafka.core.Partition;
import org.springframework.integration.kafka.inbound.KafkaMessageDrivenChannelAdapter;
import org.springframework.integration.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.integration.kafka.outbound.KafkaProducerMessageHandler;
import org.springframework.integration.kafka.serializer.common.StringDecoder;
import org.springframework.integration.kafka.support.KafkaProducerContext;
import org.springframework.integration.kafka.support.ProducerConfiguration;
import org.springframework.integration.kafka.support.ProducerFactoryBean;
import org.springframework.integration.kafka.support.ProducerMetadata;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.PollableChannel;
/**
 * Created by eko.j.manurung on 6/6/2016.
 */
@Configuration
public class KafkaConfig {
    @Value("${kafka.topic:inventoryTest}")
    private String topic;

    @Value("${kafka.broker.address:localhost:9092}")
    private String brokerAddress;

    @Value("${kafka.zookeeper.connect:localhost:2181}")
    private String zookeeperConnect;

    @ServiceActivator(inputChannel="toKafka")
    @Bean
    public MessageHandler handler() throws Exception {
        KafkaProducerMessageHandler handler = new KafkaProducerMessageHandler(producerContext());
        handler.setTopicExpression(new LiteralExpression(this.topic));
        return handler;
    }

    @Bean
    public ConnectionFactory kafkaBrokerConnectionFactory() throws Exception {
        return new DefaultConnectionFactory(kafkaConfiguration());
    }

    @Bean
    public org.springframework.integration.kafka.core.Configuration kafkaConfiguration() {
        BrokerAddressListConfiguration configuration = new BrokerAddressListConfiguration(
                BrokerAddress.fromAddress(this.brokerAddress));
        configuration.setSocketTimeout(500);
        return configuration;
    }

    @Bean
    public KafkaProducerContext producerContext() throws Exception {
        KafkaProducerContext kafkaProducerContext = new KafkaProducerContext();
        ProducerMetadata<String, String> producerMetadata = new ProducerMetadata<>(this.topic, String.class,
                String.class, new StringSerializer(), new StringSerializer());
        Properties props = new Properties();
        props.put("linger.ms", "1000");
        ProducerFactoryBean<String, String> producer =
                new ProducerFactoryBean<>(producerMetadata, this.brokerAddress, props);
        ProducerConfiguration<String, String> config =
                new ProducerConfiguration<>(producerMetadata, producer.getObject());
        Map<String, ProducerConfiguration<?, ?>> producerConfigurationMap =
                Collections.<String, ProducerConfiguration<?, ?>>singletonMap(this.topic, config);
        kafkaProducerContext.setProducerConfigurations(producerConfigurationMap);
        return kafkaProducerContext;
    }

    @Bean
    public KafkaMessageListenerContainer container() throws Exception {
        final KafkaMessageListenerContainer kafkaMessageListenerContainer = new KafkaMessageListenerContainer(
                kafkaBrokerConnectionFactory(), new Partition(this.topic, 0));
        kafkaMessageListenerContainer.setMaxFetch(100);
        kafkaMessageListenerContainer.setConcurrency(1);
        return kafkaMessageListenerContainer;
    }

    @Bean
    public KafkaMessageDrivenChannelAdapter adapter(KafkaMessageListenerContainer container) {
        KafkaMessageDrivenChannelAdapter kafkaMessageDrivenChannelAdapter =
                new KafkaMessageDrivenChannelAdapter(container);
        StringDecoder decoder = new StringDecoder();
        kafkaMessageDrivenChannelAdapter.setKeyDecoder(decoder);
        kafkaMessageDrivenChannelAdapter.setPayloadDecoder(decoder);
        kafkaMessageDrivenChannelAdapter.setOutputChannel(fromKafka());
        return kafkaMessageDrivenChannelAdapter;
    }

    @Bean
    public PollableChannel fromKafka() {
        return new QueueChannel();
    }

    @Bean
    public TopicCreator topicCreator() {
        return new TopicCreator(this.topic, this.zookeeperConnect);
    }
}
