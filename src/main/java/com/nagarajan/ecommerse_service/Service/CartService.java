package com.nagarajan.ecommerse_service.Service;

import com.nagarajan.ecommerse_service.Model.Cart.*;
import com.nagarajan.ecommerse_service.Model.Product.Product;
import com.nagarajan.ecommerse_service.Model.User.User;
import com.nagarajan.ecommerse_service.Repo.CartItemsRepo;
import com.nagarajan.ecommerse_service.Repo.CartRepo;
import com.nagarajan.ecommerse_service.Repo.ProductRepo;
import com.nagarajan.ecommerse_service.Repo.UserRepo;
import com.nagarajan.ecommerse_service.ServicesImpl.CartServeImp;
import org.apache.el.stream.Stream;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    private logeddUser logeddUser;

    public Cart CreateCart() {
        String username= logeddUser.loggedInUser();
        User user=userRepo.findByName(username).orElseThrow(()->
                                 new RuntimeException("user Not Found"));
        Cart cart=new Cart();
        cart.setDate(LocalDate.now());
        cart.setUser(user);
        return cartRepo.save(cart);
    }
    public CartItems addproduct(CartItemsRequest request){
        String username= logeddUser.loggedInUser();
        Cart cart=cartRepo.findById(request.getCartId()).orElseThrow(()->
                                  new RuntimeException("cart Not Found"));
        if(!logeddUser.Admin()&&!cart.getUser().getName().equals(username)){
            throw new RuntimeException("Access Denied - Not your cart");
        }
        Product product=productRepo.findById(request.getProductId()).orElseThrow(()->
                                   new RuntimeException("Product Not Found"));
        CartItems cartItems=new CartItems();
        cartItems.setCart(cart);
        cartItems.setProduct(product);
        cartItems.setQuantity(request.getQuantity());
        return cartItemsRepo.save(cartItems);
    }
    public CartItems UpdateCartitems(long id, CartItemsRequest request) {
        CartItems cartItems=cartItemsRepo.findById(id).orElseThrow(()->
                                  new RuntimeException("CartItems Not Found"));
        String username= logeddUser.loggedInUser();
        if(!logeddUser.Admin()&&!cartItems.getCart().getUser().getName().equals(username)){
            throw new RuntimeException("Access Denied");
        }
        Cart cart=cartRepo.findById(request.getCartId()).orElseThrow(()->
                                 new RuntimeException("Cart Not Found"));
        Product product=productRepo.findById(request.getProductId()).orElseThrow(()->
                                 new RuntimeException("Product Not Found"));
        cartItems.setCart(cart);
        cartItems.setProduct(product);
        cartItems.setQuantity(request.getQuantity());
        return cartItemsRepo.save(cartItems);

    }
        public void DeleteCartById(long id) {

         Cart cart= cartRepo.findById(id).orElseThrow(()->new RuntimeException("Cart Not Found"));
         String username= logeddUser.loggedInUser();
         if(!logeddUser.Admin()&&!cart.getUser().getName().equals(username)){
             throw new RuntimeException("Access Denied");
         }
         cartRepo.deleteById(cart.getId());
    }
    public void DeleteCartItemsById(long id){
        CartItems cartItems=cartItemsRepo.findById(id).orElseThrow(()->
                             new RuntimeException("CartItems Not Found"));
        String username= logeddUser.loggedInUser();
        if(!logeddUser.Admin()&&!cartItems.getCart().getUser().getName().equals(username)){
            throw new RuntimeException("Access Denied");
        }
        cartItemsRepo.deleteById(cartItems.getId());
    }
    public List<CartItems> GetAllCartItems(){
        List<CartItems> cartItems=cartItemsRepo.findAll();
        return cartItems;
            }
    public List<CartResponse> GetAllCart() {
        List<Cart> cart1=cartRepo.findAll();
        return cart1.stream().map(page1->mapper.map(page1,CartResponse.class)).toList();
    }
    public List<CartResponse> GetCartById() {
        String username= logeddUser.loggedInUser();
        User user=userRepo.findByName(username).orElseThrow(()->new RuntimeException(""));

        List<Cart> carts=cartRepo.findByUserId(user.getId());
        return carts.stream().map(cart->mapper.map(cart,CartResponse.class)).toList();
    }
}
