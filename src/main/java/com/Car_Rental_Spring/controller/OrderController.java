package com.Car_Rental_Spring.controller;

import com.Car_Rental_Spring.controller.requests.OrderCreateRequest;
import com.Car_Rental_Spring.domain.Order;
import com.Car_Rental_Spring.repository.OrderDao;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping(value = "/rest/order")
public class OrderController {

    @Autowired
    private OrderDao orderDao;

    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        return new ResponseEntity<>(orderDao.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get Order from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting Order"),
            @ApiResponse(code = 400, message = "Invalid Order ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "Order was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Order> getOrderById(@ApiParam("Order Path Id") @PathVariable Long id) {
        Order order = orderDao.findById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    @ApiOperation(value = "Get Order from server by Order")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting Order"),
            @ApiResponse(code = 400, message = "Invalid Order supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "Order was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Order> getOrderName(String str) {
        Order order = orderDao.findOrder(str);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Order> createOrder(@RequestBody @Valid OrderCreateRequest request) {
        Order order = new Order();
        data(request, order);

        Order savedOrder = orderDao.save(order);

        return new ResponseEntity<>(savedOrder, HttpStatus.OK);
    }

    private void data(@RequestBody OrderCreateRequest request, Order order) {
        order.setOrderUserId(request.getIdUser());
        order.setOrderCarId(request.getIdCar());
        order.setOrderWorkerId(request.getIdWorker());
        order.setRentalStart(request.getRentalStart());
        order.setRentalEnd(request.getRentalEnd());
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteOrder(@PathVariable("id") Long orderId) {
        orderDao.delete(orderId);
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }
}
