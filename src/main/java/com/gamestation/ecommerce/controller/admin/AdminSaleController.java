package com.gamestation.ecommerce.controller.admin;

import com.gamestation.ecommerce.model.Product;
import com.gamestation.ecommerce.model.Sale;
import com.gamestation.ecommerce.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/admin/sale")
public class AdminSaleController {

    @Autowired
    private SalesService salesService;

    @GetMapping
    public ModelAndView getAdminSalePage() {
        ModelAndView mav = new ModelAndView("admin/sale/sale");
        List<Sale> sales = salesService.getAllSales();
        mav.addObject("sales", sales);
        return mav;
    }

    @GetMapping("/{id}")
    public Sale getSaleById(@PathVariable Integer id) {
        return salesService.getSaleById(id);
    }

    @PostMapping("")
    public Sale createSale(@RequestBody Sale sale) {
        return salesService.createSale(sale);
    }

    @PutMapping("/{id}")
    public Sale updateSale(@PathVariable Integer id, @RequestBody Sale sale) {
        return salesService.updateSale(id, sale);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSale(@PathVariable Integer id) {
        salesService.deleteSale(id);
        return ResponseEntity.ok().build();
    }
}

