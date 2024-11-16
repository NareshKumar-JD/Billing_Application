package com.springboot.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.Entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	 List<Invoice> findByinvoiceId(Long invoiceId);
	
}
