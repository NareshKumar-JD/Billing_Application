package com.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.Entity.InvoiceSummary;

public interface InvoiceSummaryRepository extends JpaRepository<InvoiceSummary, Long> {
 
	  InvoiceSummary findByInvoiceId(Long invoiceId);
	
}
