package com.nagarajan.ecommerse_service.ServicesImpl;

import com.nagarajan.ecommerse_service.Model.Cart.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface CartServeImp {
    Cart CreateCart(long userId);
    void DeleteCartById(long id);
    CartResponse GetCartById(long id);
    List<CartResponse> GetAllCart();
    CartItems addproduct(CartItemsRequest request);
    CartItems UpdateCartitems(long id, CartItemsRequest request);
    public void DeleteCartItemsById(long id);
    List<CartItems> GetAllCartItems();
}
