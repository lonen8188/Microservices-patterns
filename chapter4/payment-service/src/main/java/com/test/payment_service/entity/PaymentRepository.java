package com.test.payment_service.entity;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

	public List<Payment> findByOrderId(long orderId);
}
