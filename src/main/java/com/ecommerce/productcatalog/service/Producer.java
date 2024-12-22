package com.ecommerce.productcatalog.service;

import com.ecommerce.productcatalog.entity.Analytics;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "product-events";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void publishProductEvent(Integer productid) throws JsonProcessingException {

        Analytics productDatum = new Analytics();
        productDatum.setProductid(productid);
        productDatum.setEvent("Product-Catalog-Service");
        productDatum.setMessage("New Product Created");

        ObjectMapper mapper = new ObjectMapper();
        String datum = mapper.writeValueAsString(productDatum);

        logger.info("Publishing inventory event: -> %s " + datum);
        kafkaTemplate.send(TOPIC, datum);

    }
}
