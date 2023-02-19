package LoginSystem.com.controller;

import LoginSystem.com.model.Product;
import LoginSystem.com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private ProductService productService;

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model) {
        List<Product> products = productService.search(query);
        model.addAttribute("products", products);
        return "search_results";
    }
}
