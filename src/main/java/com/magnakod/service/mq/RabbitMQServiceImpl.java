package com.magnakod.service.mq;


import com.magnakod.config.RabbitMQConfig;
import com.magnakod.entity.Tasks;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQServiceImpl implements RabbitMQService {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private RabbitMQConfig config;

    @Override
    public boolean send(Tasks tasks) {
        try{
            rabbitTemplate.convertAndSend(config.getExchange(), config.getRoutingkey(), tasks);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
