package com.test.payment_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.payment_service.dto.CustomerOrder;
import com.test.payment_service.dto.OrderEvent;
import com.test.payment_service.dto.PaymentEvent;
import com.test.payment_service.entity.Payment;
import com.test.payment_service.entity.PaymentRepository;

@Component
public class ReversePayment {

	@Autowired
	private PaymentRepository repository;

	@Autowired
	private KafkaTemplate<String, OrderEvent> kafkaTemplate;

	@KafkaListener(topics = "reversed-payments", groupId = "payments-group")
	public void reversePayment(String event) {
		System.out.println("Inside reverse payment for order "+event);
		
		try {
			PaymentEvent paymentEvent = new ObjectMapper().readValue(event, PaymentEvent.class);

			CustomerOrder order = paymentEvent.getOrder();

			Iterable<Payment> payments = this.repository.findByOrderId(order.getOrderId());

			payments.forEach(p -> {
				p.setStatus("FAILED");
				repository.save(p);
			});

			OrderEvent orderEvent = new OrderEvent();
			orderEvent.setOrder(paymentEvent.getOrder());
			orderEvent.setType("ORDER_REVERSED");
			kafkaTemplate.send("reversed-orders", orderEvent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
