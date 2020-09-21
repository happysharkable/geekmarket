package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.entities.Product;
import com.geekbrains.geek.market.exceptions.ResourceNotFoundException;
import com.geekbrains.geek.market.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping
    public String showAllProducts(Model model, @RequestParam(defaultValue = "1", name = "p") Integer page) {
        if (page < 1) {
            page = 1;
        }
        model.addAttribute("products", productService.findAll(page - 1, 5));
        return "products";
    }

    @GetMapping(params = {"min"})
    public String showProductsByMinPrice(Model model, @RequestParam Integer min) {
        model.addAttribute("products", productService.findByMinPrice(min));
        return "products";
    }

    @GetMapping(params = {"max"})
    public String showProductsByMaxPrice(Model model, @RequestParam Integer max) {
        model.addAttribute("products", productService.findByMaxPrice(max));
        return "products";
    }

    @GetMapping(params = {"min", "max"})
    public String showProductsByMinAndMaxPrice(Model model, @RequestParam Integer min, @RequestParam Integer max) {
        if (min != null && max != null) {
            model.addAttribute("products", productService.findByMinAndMaxPrice(min, max));
            return "products";
        } else if (min == null && max == null) {
            return "redirect:/products";
        } else if (min != null) {
            return String.format("redirect:/products?min=%d", min);
        } else {
            return String.format("redirect:/products?max=%d", max);
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Product getOneProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exists"));
    }
}
