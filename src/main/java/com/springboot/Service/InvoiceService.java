package com.springboot.Service;

import java.util.List;

import com.springboot.DTO.ProductRequest;
import com.springboot.Entity.Invoice;

public interface InvoiceService {
   
    List<Invoice> generateInvoice(Long invoiceId, List<ProductRequest> productRequests);
    List<Invoice> getInvByinvoiceId(Long invoiceId);
    void deleteInvByinvoiceId(Long invoiceId);
   
}
