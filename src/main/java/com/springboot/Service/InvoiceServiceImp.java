package com.springboot.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.Entity.Invoice;
import com.springboot.Entity.InvoiceSummary;
import com.springboot.Entity.Product;
import com.springboot.Error.RecordNotFoundException;
import com.springboot.Repository.InvoiceRepository;
import com.springboot.Repository.ProductRepository;
import com.springboot.Repository.InvoiceSummaryRepository;
import com.springboot.DTO.ProductRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImp implements InvoiceService {

    @Autowired
    private InvoiceRepository repoin;

    @Autowired
    private ProductRepository repop;

    @Autowired
    private InvoiceSummaryRepository summaryRepo;

    @Override
    public List<Invoice> generateInvoice(Long invocieId, List<ProductRequest> productRequests) {
        List<Invoice> invoices = new ArrayList<>();
        double grossTotal = 0.0;

        for (ProductRequest productRequest : productRequests) {
            String productName = productRequest.getName();
            int quantity = productRequest.getQuantity();  

            Optional<Product> productOpt = repop.findByName(productName);
            if (productOpt.isPresent()) {
                Product product = productOpt.get();

                Invoice invoice = new Invoice();
                invoice.setInvoiceId(invocieId);  
                invoice.setName(product.getName());
                invoice.setuPrice(product.getuPrice());
                invoice.setQuantity(quantity);

                double total = product.getuPrice() * quantity;
                invoice.setTotal(total);              
             
                repoin.save(invoice);

                invoices.add(invoice);

                grossTotal += total;
            } else {
                throw new RecordNotFoundException("Product not found: " + productName);
            }
        }

        Invoice grossTotalInvoice = new Invoice();             
        grossTotalInvoice.setInvoiceId(invocieId);  
        grossTotalInvoice.setName("Gross Total");
        grossTotalInvoice.setuPrice(0.0);  
        grossTotalInvoice.setQuantity(1);  
        grossTotalInvoice.setTotal(grossTotal);

        repoin.save(grossTotalInvoice);
        invoices.add(grossTotalInvoice);

        InvoiceSummary invoiceSummary = new InvoiceSummary();
        invoiceSummary.setInvoiceId(invocieId); 
        invoiceSummary.setGrossTotal(grossTotal);
        summaryRepo.save(invoiceSummary);

        return invoices;
    }
   
    @Override
    public List<Invoice> getInvByinvoiceId(Long invoiceId) {
        List<Invoice> invoices = repoin.findByinvoiceId(invoiceId); 
        if (invoices.isEmpty()) {
            throw new RecordNotFoundException("No invoices found for invoice ID: " + invoiceId);
        }
        return invoices;
    }

    @Override
    public void deleteInvByinvoiceId(Long invoiceId) {
        List<Invoice> invoices = repoin.findByinvoiceId(invoiceId);  
        if (invoices.isEmpty()) {
            throw new RecordNotFoundException("No invoices found for invoice ID: " + invoiceId);
        }

        repoin.deleteAll(invoices);

        InvoiceSummary invoiceSummary = summaryRepo.findByInvoiceId(invoiceId);
        if (invoiceSummary != null) {
            summaryRepo.delete(invoiceSummary);
        }
    }

}

