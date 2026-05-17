package com.nagarajan.ecommerse_service.Service;

import com.nagarajan.ecommerse_service.Model.Cart.*;
import com.nagarajan.ecommerse_service.Model.Product.Product;
import com.nagarajan.ecommerse_service.Model.User.User;
import com.nagarajan.ecommerse_service.Repo.CartItemsRepo;
import com.nagarajan.ecommerse_service.Repo.CartRepo;
import com.nagarajan.ecommerse_service.Repo.ProductRepo;
import com.nagarajan.ecommerse_service.Repo.UserRepo;
import com.nagarajan.ecommerse_service.ServicesImpl.CartServeImp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class CartService implements CartServeImp {
    @Autowired
    private CartItemsRepo cartItemsRepo;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProductRepo productRepo;
    public Cart CreateCart(long userId) {
        User user=userRepo.findById(userId).orElseThrow(()->new RuntimeException("user Not Found"));
        Cart cart=new Cart();
        cart.setDate(LocalDate.now());
        cart.setUser(user);
        System.out.println(userId);
        return cartRepo.save(cart);
    }
    public CartItems addproduct(CartItemsRequest request){
        Cart cart=cartRepo.findById(request.getCartId()).orElseThrow();
        Product product=productRepo.findById(request.getProductId()).orElseThrow();
        CartItems cartItems=new CartItems();
        cartItems.setCart(cart);
        cartItems.setProduct(product);
        cartItems.setQuantity(request.getQuantity());
        return cartItemsRepo.save(cartItems);
    }
    public CartItems UpdateCartitems(long id, CartItemsRequest request) {
        CartItems cartItems=cartItemsRepo.findById(id).orElseThrow();
        Cart cart=cartRepo.findById(request.getCartId()).orElseThrow();
        Product product=productRepo.findById(request.getProductId()).orElseThrow();
        cartItems.setCart(cart);
        cartItems.setProduct(product);
        cartItems.setQuantity(request.getQuantity());
        return cartItemsRepo.save(cartItems);

    }
        public void DeleteCartById(long id) {
  cartRepo.deleteById(id);
    }
    public void DeleteCartItemsById(long id){
        cartItemsRepo.deleteById(id);
    }
    public List<CartItems> GetAllCartItems(){
        List<CartItems> cartItems=cartItemsRepo.findAll();
        return cartItems;
            }
    public List<CartResponse> GetAllCart() {
        List<Cart> cart1=cartRepo.findAll();
        return cart1.stream().map(page1->mapper.map(page1,CartResponse.class)).toList();
    }
    public CartResponse GetCartById(long id) {
        Cart cart=cartRepo.findById(id).orElseThrow();
        return mapper.map(cart,CartResponse.class);
    }
}
