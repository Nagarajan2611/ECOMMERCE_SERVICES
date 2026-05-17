package com.nagarajan.ecommerse_service.Controller;

import com.nagarajan.ecommerse_service.Model.Cart.Cart;
import com.nagarajan.ecommerse_service.Model.Cart.CartItems;
import com.nagarajan.ecommerse_service.Model.Cart.CartItemsRequest;
import com.nagarajan.ecommerse_service.Model.Cart.CartResponse;
import com.nagarajan.ecommerse_service.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService service;
    @PostMapping("/{userId}")
    ResponseEntity<Cart> CreateCart(@PathVariable long userId){
        Cart response=service.CreateCart(userId);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @DeleteMapping("/id/{id}")
    ResponseEntity<String> DeleteCartById(@PathVariable long id){
        service.DeleteCartById(id);
        return new ResponseEntity<>("Deleted Cart id "+id+" successfully!"+id,HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    ResponseEntity<CartResponse> GetCartById(@PathVariable long id){
        CartResponse response=service.GetCartById(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping
    ResponseEntity<List<CartResponse>> GetAllCart(){
        List<CartResponse> response=service.GetAllCart();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PostMapping("/cartitems")
    ResponseEntity<CartItems> addproduct(@RequestBody CartItemsRequest request){
        CartItems response= service.addproduct(request);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @PutMapping("/cartitems/id/{id}")
    ResponseEntity<CartItems> UpdateCartitems(@PathVariable long id,@RequestBody CartItemsRequest request){
        CartItems response=service.UpdateCartitems(id,request);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }
    @DeleteMapping("/cartitems/id/{id}")
    ResponseEntity<String> DeleteCartItemsById(long id){
        service.DeleteCartItemsById(id);
        return  new ResponseEntity<>("Deleted CartItem id "+id+" successfully!",HttpStatus.OK);
    }
    @GetMapping("/cartitems")
    ResponseEntity<List<CartItems>> GetAllCartItems(){
     List<CartItems> cartItems=service.GetAllCartItems();
     return  new ResponseEntity<>(cartItems,HttpStatus.OK);
    }
}
