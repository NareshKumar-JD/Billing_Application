package com.springboot.Controller;

import com.springboot.Entity.Invoice;
import com.springboot.Error.RecordNotFoundException;
import com.springboot.Service.InvoiceService;
import com.springboot.DTO.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/generate/{invoiceId}")
    public ResponseEntity<List<Invoice>> generateInvoice(@PathVariable Long invoiceId, @RequestBody List<ProductRequest> productRequests) {
        List<Invoice> invoices = invoiceService.generateInvoice(invoiceId, productRequests);
        return ResponseEntity.ok(invoices);
    }
    
    @GetMapping("/generate/{invoiceId}")
    public ResponseEntity<List<Invoice>> getInvoicesByIId(@PathVariable("invoiceId") Long invoiceId) {
        try {
            List<Invoice> invoices = invoiceService.getInvByinvoiceId(invoiceId);
            return new ResponseEntity<>(invoices, HttpStatus.OK);
        } catch (RecordNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/generate/{invoiceId}")
    public ResponseEntity<String> deleteInvoicesByIId(@PathVariable("invoiceId") Long invoiceId) {
        try {
            invoiceService.deleteInvByinvoiceId(invoiceId);
            return new ResponseEntity<>("Invoices with iId " + invoiceId + " deleted successfully.", HttpStatus.OK);
        } catch (RecordNotFoundException e) {
            return new ResponseEntity<>("Invoices with iId " + invoiceId + " not found.", HttpStatus.NOT_FOUND);
        }
    }

}

       