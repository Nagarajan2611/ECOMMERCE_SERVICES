package com.nagarajan.ecommerse_service.ServicesImpl;

import com.nagarajan.ecommerse_service.Model.Order.Order;
import com.nagarajan.ecommerse_service.Model.Order.OrderRequest;
import com.nagarajan.ecommerse_service.Model.Order.OrderResponse;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface OrderServeImp {
    OrderResponse createOrder(OrderRequest request);
    OrderResponse UpdateOrder(long id,OrderRequest request);
    void DeleteOrderById(long id);
    List<OrderResponse> GetAllOrders(int page,int size,String sortby,String direction,Double totalprice,String status);
    OrderResponse GetOrderById(long id);
    OrderResponse GetOrderByStatus(String status);
}
