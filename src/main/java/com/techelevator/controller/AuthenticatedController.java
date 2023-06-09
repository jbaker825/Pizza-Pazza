package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@PreAuthorize("isAuthenticated()")
@RestController
public class AuthenticatedController {

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


    @PostMapping("/specialty-pizza")
    @ResponseStatus(HttpStatus.CREATED)
    public int createSpecialtyPizza(@RequestBody SpecialtyPizza specialtyPizza) {
        return specialtyPizzaDao.createSpecialtyPizza(specialtyPizza);

    }

    @PostMapping("/crusts")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean createCrust(@RequestBody Crust crust) {
        return crustDao.createCrust(crust.getCrustName(), crust.getPrice());
    }

    @PostMapping("/sauce")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean createSauce(@RequestBody Sauce sauce) {
        return sauceDao.createSauce(sauce.getSauceName(), sauce.getPrice());
    }

    @PostMapping("/pizza-size")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean createPizzaSize(@RequestBody PizzaSize pizzaSize) {
        return pizzaSizeDao.createPizzaSize(pizzaSize.getSizeName(), pizzaSize.getPrice());
    }

    @PostMapping("/toppings")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean createTopping(@RequestBody Topping topping) {
        return toppingDao.createTopping(topping.getToppingName(), topping.isAvailable(), topping.isPremium());
    }

    @PutMapping("/specialty-pizza/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean updateSpecialtyPizza(@PathVariable int id, @RequestBody SpecialtyPizza specialtyPizza) {
        return specialtyPizzaDao.updateSpecialtyPizza(specialtyPizza);

    }

    @PutMapping("/crusts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean updateCrust(@PathVariable int id, @RequestBody Crust crust) {
        return crustDao.updateCrust(crust);
    }

    @PutMapping("/sauce/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean updateSauce(@PathVariable int id, @RequestBody Sauce sauce) {
        return sauceDao.updateSauce(sauce);
    }

    @PutMapping("/pizza-size/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean updatePizzaSize(@PathVariable int id, @RequestBody PizzaSize pizzaSize) {
        return pizzaSizeDao.updatePizzaSize(pizzaSize);
    }

    @PutMapping("/toppings/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean updateTopping(@PathVariable int id, @RequestBody Topping topping) {
        return toppingDao.updateTopping(topping);
    }

    @PutMapping("/sale/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean updateSale(@PathVariable int id, @RequestBody Sale sale) {
        return saleDao.updateSale(sale);
    }

//    @PutMapping("/pizza/{id}")
//    public boolean updatePizza(@PathVariable int id, @RequestBody Pizza pizza) {
//        return pizzaDao.updatePizza(pizza);
//    }

    @DeleteMapping("/specialty-pizza/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteSpecialtyPizza(@PathVariable int id) {
        return specialtyPizzaDao.deleteSpecialtyPizza(id);

    }

    @DeleteMapping("/crusts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteCrust(@PathVariable int id) {
        return crustDao.deleteCrust(id);
    }

    @DeleteMapping("/sauce/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteSauce(@PathVariable int id) {
        return sauceDao.deleteSauce(id);
    }

    @DeleteMapping("/pizza-size/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deletePizzaSize(@PathVariable int id) {
        return pizzaSizeDao.deletePizzaSize(id);
    }

    @DeleteMapping("/toppings/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteTopping(@PathVariable int id) {
        return toppingDao.deleteTopping(id);
    }



//    @DeleteMapping("/sale/{id}")
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public boolean deleteSale(@PathVariable int id) {
//        return saleDao.deleteSale(id);
//    }

//    @DeleteMapping("/pizza/{id}")
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public boolean deletePizza(@PathVariable int id) {
//        return pizzaDao.deletePizza(id);
//    }

    @GetMapping("/sale")
    public List<Sale> getAllSales(){
        return saleDao.getAllSales();
    }

    @GetMapping("/pizza")
    public List<Pizza> getAllPizzas() {
        return pizzaDao.getAllPizzas();
    }

    @GetMapping("/pizza-toppings")
    public List<Topping> getToppingsForPizzaOrder(@RequestBody Pizza pizza) {
        return toppingDao.getPizzaToppings(pizza);
    }

    // get specialtyPizzas
    // get toppings
    // get crusts
    // get sauce
    // get size
    // POST pizza
    // POST Sale

    // PUT specialtyPizzas
    // PUT toppings
    // PUT crusts
    // PUT sauce
    // PUT size



}
