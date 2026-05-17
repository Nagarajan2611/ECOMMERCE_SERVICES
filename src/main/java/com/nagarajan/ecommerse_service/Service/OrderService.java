package com.nagarajan.ecommerse_service.Service;

import com.nagarajan.ecommerse_service.Model.Order.Order;
import com.nagarajan.ecommerse_service.Model.Order.OrderRequest;
import com.nagarajan.ecommerse_service.Model.Order.OrderResponse;
import com.nagarajan.ecommerse_service.Repo.OrderRepo;
import com.nagarajan.ecommerse_service.ServicesImpl.OrderServeImp;
import com.nagarajan.ecommerse_service.Specification.OrderSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderServeImp {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private OrderRepo repo;
    public OrderResponse createOrder(OrderRequest request) {
        Order order=mapper.map(request,Order.class);
        order.setDate(LocalDate.now());
        repo.save(order);
        return mapper.map(order,OrderResponse.class);
    }
    public OrderResponse UpdateOrder(long id, OrderRequest request) {
        Order order=repo.findById(id).orElseThrow();
        order.setTotalPrice(request.getTotalPrice());
        order.setUser(request.getUser());
        order.setItems(request.getItems());
        order.setStatus(request.getStatus());
        order.setDate(LocalDate.now());
        repo.save(order);
        return mapper.map(order,OrderResponse.class);
    }
    public void DeleteOrderById(long id) {
        repo.deleteById(id);
    }
    public List<OrderResponse> GetAllOrders(int page, int size, String sortby, String direction,
                                            Double totalprice,String status) {
        Sort sort=null;
        if(!direction.equalsIgnoreCase("DESC")){
            sort=Sort.by(sortby).descending();
        }
        if(!direction.equalsIgnoreCase("ASC")){
            sort=Sort.by(sortby).ascending();
        }
        else{
            sort=Sort.by(sortby).ascending();
        }
        PageRequest pageRequest=PageRequest.of(page-1,size,sort);
        Specification<Order> specification=new OrderSpecification(totalprice,status);
        Page<Order> page1=repo.findAll(specification,pageRequest);
        return page1.getContent()
                .stream()
                .map(page2->mapper.map(page2,OrderResponse.class))
                .toList();
    }
    public OrderResponse GetOrderById(long id) {
        Order order=repo.findById(id).orElseThrow();
        return mapper.map(order,OrderResponse.class);
    }
    public OrderResponse GetOrderByStatus(String status) {
        Optional<Order> order=repo.findByStatus(status);
        return null;
    }
}
