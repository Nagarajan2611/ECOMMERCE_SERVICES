package com.nagarajan.ecommerse_service.Service;

import com.nagarajan.ecommerse_service.Model.Order.Order;
import com.nagarajan.ecommerse_service.Model.Order.OrderRequest;
import com.nagarajan.ecommerse_service.Model.Order.OrderResponse;
import com.nagarajan.ecommerse_service.Model.Order.StatusRequest;
import com.nagarajan.ecommerse_service.Model.User.User;
import com.nagarajan.ecommerse_service.Repo.OrderRepo;
import com.nagarajan.ecommerse_service.Repo.UserRepo;
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

@Service
public class OrderService implements OrderServeImp {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private logeddUser logeddUser;
    public OrderResponse createOrder(OrderRequest request) {
        String username= logeddUser.loggedInUser();
        User user=userRepo.findByName(username).orElseThrow(()->
                          new RuntimeException("User Not Found"));
        Order order=mapper.map(request,Order.class);
        double totalprice=0;
        for(var item:order.getItems()){
            double subtotal=item.getPrice()*item.getQuantity();
            totalprice=totalprice+subtotal;
        }
        order.setStatus("PLACED");
        order.setTotalPrice(totalprice);
        order.setDate(LocalDate.now());
        order.setUser(user);
        orderRepo.save(order);
        return mapper.map(order,OrderResponse.class);
    }
    public OrderResponse UpdateOrder(long id, OrderRequest request) {
        if(!logeddUser.Admin()){
            throw new RuntimeException("Only Admin can Update order");
        }
        Order order=orderRepo.findById(id).orElseThrow(()->
                      new RuntimeException("Order Not found"));
        double totalprice=0;
        for(var item:order.getItems()){
            double subtotal=item.getPrice()*item.getQuantity();
            totalprice=totalprice+subtotal;
        }
        order.setTotalPrice(totalprice);
        order.setItems(request.getItems());
        order.setStatus("PLACED");
        order.setDate(LocalDate.now());
        orderRepo.save(order);
        return mapper.map(order,OrderResponse.class);
    }
    public void DeleteOrderById(long id) {
        if (!logeddUser.Admin()) {
            throw new RuntimeException("Only Admin can Delete Orders");
        }
        orderRepo.deleteById(id);
    }
    public List<OrderResponse> GetAllOrders(int page, int size, String sortby, String direction,
                                            Double totalprice,String status) {
        if(!logeddUser.Admin()){
            throw new RuntimeException("Only Admin can view Orders");

        }
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
        Page<Order> page1=orderRepo.findAll(specification,pageRequest);
        return page1.getContent()
                .stream()
                .map(page2->mapper.map(page2,OrderResponse.class))
                .toList();
    }
    public OrderResponse GetOrderById(long id) {
        String username= logeddUser.loggedInUser();
        Order order=orderRepo.findById(id).orElseThrow(()->
                        new RuntimeException("Order Not Found"));
        if (!logeddUser.Admin()&&!order.getUser().getName().equals(username)){
            throw new RuntimeException("Access Denied ");
        }
        return mapper.map(order,OrderResponse.class);
    }
    public OrderResponse GetOrderByStatus(String status) {
        if (!logeddUser.Admin()){
            throw new RuntimeException("Only Admin Can Search Orders");
        }
        Order order=orderRepo.findByStatus(status).orElseThrow(()->
                new RuntimeException("Order Not Found"));
        return mapper.map(order,OrderResponse.class);
    }

    public OrderResponse updateStatus(long id, StatusRequest request){
        Order order=orderRepo.findById(id).orElseThrow(()->
                new RuntimeException("order Not Found"));
        order.setStatus(request.getStatus());
        orderRepo.save(order);
        return mapper.map(order,OrderResponse.class);
    }
}


