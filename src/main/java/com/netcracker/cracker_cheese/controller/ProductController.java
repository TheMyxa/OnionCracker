package com.netcracker.cracker_cheese.controller;

import com.netcracker.cracker_cheese.domain.entity.Product;
import com.netcracker.cracker_cheese.repository.ProductRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Map;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public ModelAndView getProducts() {
        //Iterable<Product> messages = productRepo.findAll();

        // model.put("messages", messages);
        return new ModelAndView("main", "product", productRepo.findAll());
    }


    @PostMapping("/add")
    //@RequestMapping(value = {"/add"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView add(@ModelAttribute Product product) {

        Product save = productRepo.save(product);
        return new ModelAndView("main", "product", productRepo.findAll());

    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") Product product) {
        productRepo.delete(product);
        return new ModelAndView("main", "product", productRepo.findAll());
    }

    @GetMapping("/products/{id}/edit")
    public ModelAndView editProduct(Model model, @PathVariable Integer id) {
        Optional<Product> byId = productRepo.findById(id);

        if (byId.isPresent()) {
            model.addAttribute("product", byId.get());
            return new ModelAndView("productEdit");
        }

        return new ModelAndView("productEdit", "product", productRepo.findById(id));
    }

    @PostMapping("/products/{id}/edit")
    public String editProductPost(@PathVariable Integer id, @ModelAttribute Product product) {
        product.setId(id);
        productRepo.save(product);
        return "redirect:/main";
        //return new ModelAndView("productEdit", "product", productRepo.findById(id));
    }


}
