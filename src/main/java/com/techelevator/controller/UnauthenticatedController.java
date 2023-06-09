package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class UnauthenticatedController {
    @Autowired
    ToppingDao toppingDao;
    @Autowired
    CrustDao crustDao;
    @Autowired
    SauceDao sauceDao;
    @Autowired
    PizzaSizeDao pizzaSizeDao;
    @Autowired
    SpecialtyPizzaDao specialtyPizzaDao;
    @Autowired
    SaleDao saleDao;
    @Autowired
    PizzaDao pizzaDao;

    @GetMapping("/toppings")
    public List<Topping> getAllToppings() {
        return toppingDao.getAllToppings();
    }

    @GetMapping("/crusts")
    public List<Crust> getAllCrusts() {
        return crustDao.getAllCrusts();
    }

    @GetMapping("/sauce")
    public List<Sauce> getAllSauces() {
        return sauceDao.getAllSauces();
    }

    @GetMapping("/pizza-size")
    public List<PizzaSize> getAllPizzaSizes() {
        return pizzaSizeDao.getAllPizzaSizes();
    }

    @GetMapping("/specialty-pizza")
    public List<SpecialtyPizza> getAllSpecialtyPizzas() {
        return specialtyPizzaDao.getAllSpecialtyPizzas();
    }

    @PostMapping("/order")
    @ResponseStatus(HttpStatus.CREATED)
    public int createSaleAndPizza(@RequestBody PayloadWrapper payloadWrapper) {
        Sale sale = payloadWrapper.getSale();
        List<Pizza> pizzas = payloadWrapper.getPizzas();

        int saleId = saleDao.createSale(sale);
        sale.setSaleId(saleId);

        BigDecimal totalSale = new BigDecimal("0");
        for (Pizza pizza : pizzas) {
            pizza.setSaleId(saleId);
            pizzaDao.createPizza(pizza);
            BigDecimal pizzaPrice = pizza.getPrice();
            totalSale = totalSale.add(pizzaPrice);
        }

        sale.setSaleTotal(totalSale);
        saleDao.updateSale(sale);

        return saleId;
    }

    @GetMapping("/toppings/{id}")
    public Topping getToppingById(@PathVariable int id) {
        return toppingDao.getToppingById(id);
    }

    @GetMapping("/crusts/{id}")
    public Crust getCrustById(@PathVariable int id) {
        return crustDao.getCrustById(id);
    }

    @GetMapping("/sauce/{id}")
    public Sauce getSauceById(@PathVariable int id) {
        return sauceDao.getSauceById(id);
    }

    @GetMapping("/pizza-size/{id}")
    public PizzaSize getPizzaSizeById(@PathVariable int id) {
        return pizzaSizeDao.getPizzaSizeById(id);
    }

    @GetMapping("/specialty-pizza/{id}")
    public SpecialtyPizza getSpecialtyPizzaById(@PathVariable int id) {
        return specialtyPizzaDao.getSpecialtyPizzaById(id);
    }

    @GetMapping("/sale/{id}")
    public Sale getSaleById(@PathVariable int id) {
        return saleDao.getSaleById(id);
    }

    @PostMapping("/pizza")
    public boolean createPizza(@RequestBody Pizza pizza) {
        return pizzaDao.createPizza(pizza);
    }



  //  @PostMapping("/sale")
  //  @ResponseStatus(HttpStatus.CREATED)
  //  public int createSaleTest(@RequestBody Sale sale) {
  //     return saleDao.createSale(sale);

   // }

   // @PutMapping("/sale/{saleId}")
   // public Sale setDelivery(@PathVariable int saleId, @RequestBody Sale sale){
   //     Sale newSale = saleDao.getSaleById(saleId);
   //     newSale.setDelivery(sale.isDelivery());
   //     newSale.setCcLastFour(sale.getCcLastFour());
   //     newSale.setCustomerAddress(sale.getCustomerAddress());
   //     newSale.setSaleStatus("Pending");
   //     newSale.setSaleTotal(sale.getSaleTotal()); //A better way?
   //     saleDao.updateSale(newSale);
   //     return saleDao.getSaleById(saleId);
   // }

    //@PostMapping("/create-pizza")
    //@ResponseStatus(HttpStatus.CREATED)
    //public boolean createSaleTest(@RequestBody Pizza pizza) {
    //    return pizzaDao.createPizza(pizza);
    //}









    // get specialtyPizzas
    // get toppings
    // get crusts
    // get sauce
    // get size
    // POST pizza
    // POST Sale
    //

}
