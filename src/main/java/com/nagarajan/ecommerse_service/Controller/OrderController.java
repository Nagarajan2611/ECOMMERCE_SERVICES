package com.nagarajan.ecommerse_service.Controller;

import com.nagarajan.ecommerse_service.Model.Order.Order;
import com.nagarajan.ecommerse_service.Model.Order.OrderRequest;
import com.nagarajan.ecommerse_service.Model.Order.OrderResponse;
import com.nagarajan.ecommerse_service.Model.Order.StatusRequest;
import com.nagarajan.ecommerse_service.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService service;
    @PostMapping
    ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request){
        OrderResponse response=service.createOrder(request);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @PostMapping("/status/{id}")
    ResponseEntity<OrderResponse> updateStatus(@PathVariable long id, @RequestBody StatusRequest request){
        OrderResponse response= service.updateStatus(id,request);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @PutMapping("/id/{id}")
    ResponseEntity<OrderResponse> UpdateOrder(@PathVariable long id,@RequestBody OrderRequest request){
        OrderResponse response=service.UpdateOrder(id,request);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @DeleteMapping("/id/{id}")
    ResponseEntity<String> DeleteOrderById(@PathVariable long id){
        service.DeleteOrderById(id);
        return new ResponseEntity<>("Deleted successfully"+id,HttpStatus.OK);
    }
    @GetMapping
    ResponseEntity<List<OrderResponse>> GetAllOrders(@RequestParam(required = false,defaultValue = "1") int page,
                                                     @RequestParam(required = false,defaultValue = "20") int size,
                                                     @RequestParam(required = false,defaultValue = "id") String sortby,
                                                     @RequestParam(required = false,defaultValue = "ASC") String direction,
                                                     @RequestParam(required = false) Double totalprice,
                                                     @RequestParam(required = false)String status){
        List<OrderResponse> orderResponses=service.GetAllOrders(page,size,sortby,direction,totalprice,status);
        return new ResponseEntity<>(orderResponses,HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    ResponseEntity<OrderResponse> GetOrderById(@PathVariable long id){
        OrderResponse response=service.GetOrderById(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/status/{status}")
    ResponseEntity<OrderResponse> GetOrderByStatus(@PathVariable String status){
        OrderResponse response=service.GetOrderByStatus(status);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
