package com.magnakod.service.mq;

import com.magnakod.entity.Tasks;
import org.springframework.stereotype.Service;

public interface RabbitMQService {
    boolean send(Tasks blog);
}