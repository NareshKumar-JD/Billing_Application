package com.springboot.Service;

import com.springboot.Entity.Payment;
import java.util.List;

public interface PaymentService {

    Payment addPayment(Long invoiceId, Payment paymentDetails);

    List<Payment> getPaymentsByInvoiceId(Long invoiceId);

    List<Payment> updatePaymentStatusByInvoiceId(Long invoiceId, String newStatus);
}
