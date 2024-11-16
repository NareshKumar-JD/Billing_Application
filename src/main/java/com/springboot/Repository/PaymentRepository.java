package com.springboot.Repository;

import com.springboot.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	List<Payment> findByInvoiceId(Long invoiceId);
}
