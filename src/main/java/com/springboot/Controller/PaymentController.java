package com.springboot.Controller;

import com.springboot.Entity.Payment;
import com.springboot.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/generate/{invoiceId}") //"paymentMethod": "Credit Card"
    public ResponseEntity<Payment> addPayment(@PathVariable Long invoiceId,
                                               @RequestBody Payment paymentDetails) {
        Payment newPayment = paymentService.addPayment(invoiceId, paymentDetails);
        return new ResponseEntity<>(newPayment, HttpStatus.CREATED);
    }

    @GetMapping("/generate/{invoiceId}")
    public ResponseEntity<List<Payment>> getPayments(@PathVariable Long invoiceId) {
        List<Payment> payments = paymentService.getPaymentsByInvoiceId(invoiceId);
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @PutMapping("/generate/{invoiceId}")  //?newStatus=Pending
    public ResponseEntity<List<Payment>> updatePaymentStatus(@PathVariable Long invoiceId,
                                                              @RequestParam String newStatus) {
        List<Payment> updatedPayments = paymentService.updatePaymentStatusByInvoiceId(invoiceId, newStatus);
        return new ResponseEntity<>(updatedPayments, HttpStatus.OK);
    }

}
