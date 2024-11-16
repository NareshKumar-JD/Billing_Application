package com.springboot.Service;

import com.springboot.Entity.Payment;
import com.springboot.Entity.InvoiceSummary;
import com.springboot.Repository.PaymentRepository;
import com.springboot.Repository.InvoiceSummaryRepository;
import com.springboot.Error.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImp implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private InvoiceSummaryRepository invoiceSummaryRepository;

    @Override
    public Payment addPayment(Long invoiceId, Payment paymentDetails) {
        InvoiceSummary invoiceSummary = invoiceSummaryRepository.findByInvoiceId(invoiceId);

        if (invoiceSummary == null) {
            throw new RecordNotFoundException("Invoice Summary not found for invoice ID: " + invoiceId);
        }

        Payment payment = new Payment();
        payment.setInvoiceId(invoiceId);
        payment.setAmountPaid(invoiceSummary.getGrossTotal()); // Set grossTotal as amountPaid
        payment.setPaymentMethod(paymentDetails.getPaymentMethod());
        payment.setPaymentDate(paymentDetails.getPaymentDate());
        payment.setPaymentStatus("Pending"); // Default status to Pending

        if (payment.getAmountPaid() >= invoiceSummary.getGrossTotal()) {
            payment.setPaymentStatus("Paid");
        }

        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getPaymentsByInvoiceId(Long invoiceId) {
        List<Payment> payments = paymentRepository.findByInvoiceId(invoiceId);
        if (payments.isEmpty()) {
            throw new RecordNotFoundException("No payments found for invoice ID: " + invoiceId);
        }
        return payments;
    }

    @Override
    public List<Payment> updatePaymentStatusByInvoiceId(Long invoiceId, String newStatus) {
        List<Payment> payments = paymentRepository.findByInvoiceId(invoiceId);

        if (payments.isEmpty()) {
            throw new RecordNotFoundException("No payments found for invoice ID: " + invoiceId);
        }

        for (Payment payment : payments) {
            payment.setPaymentStatus(newStatus);
            paymentRepository.save(payment); 
        }

        return payments;
    }
}
