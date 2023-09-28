package com.vermeg.app.controller;

import java.util.List;


import com.vermeg.app.entity.Cart;
import com.vermeg.app.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {
	
	@Autowired
    CartService cartService;
	
	@GetMapping("/all")
    public ResponseEntity<List<Cart>> getAllCarts () {
        List<Cart> employees = cartService.findAllCarts();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Cart> getCartById (@PathVariable("id") long id) {
        Cart cart = cartService.findCartById(id);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/add/{id}/{idCourse}")
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart, @PathVariable("id") long id, @PathVariable("idCourse") String idCourse) throws Exception {
    	Cart newCart = cartService.addCart(cart, id, idCourse);
        return new ResponseEntity<>(newCart, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Cart> updateCart(@RequestBody Cart cart) {
    	Cart updateEmployee = cartService.updateCart(cart);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable("id") long id) {
    	cartService.deleteCart(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping("/findCart/{id}")
    public ResponseEntity<List<Cart>> getCartByUser (@PathVariable("id") long id) {
        List<Cart> cart = cartService.findCartByUser(id);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
	
    /*@PostMapping("/addcourse")
    public ResponseEntity<Course> addCart(@RequestBody Course course) {
    	Course newCart = cartService.addCourse(course);
        return new ResponseEntity<>(newCart, HttpStatus.CREATED);
    }*/
    
    @DeleteMapping("/deleteCarts/{id}")
    public ResponseEntity<?> deleteCarts(@PathVariable("id") long id) {
    	cartService.deleteAllUserCart(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    

}
