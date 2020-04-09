package com.Car_Rental_Spring.controller;

import com.Car_Rental_Spring.controller.requests.order.OrderCreateRequest;
import com.Car_Rental_Spring.controller.requests.order.OrderUpdateRequest;
import com.Car_Rental_Spring.entity.Order;
import com.Car_Rental_Spring.exceptions.EntityNotFoundException;
import com.Car_Rental_Spring.repository.springdata.OrderRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/rest/order")
public class OrderController {

    private final OrderRepository orderDao;

    @Autowired
    @Qualifier(value = "mvcConversionService")
    private ConversionService conversionService;

    @ApiOperation(value = "Get all orders from server")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting data"),
            @ApiResponse(code = 400, message = "Something wrong"),
            @ApiResponse(code = 401, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Users not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Order>> getOrders(){
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
    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> getOrderById(
            @ApiParam("Order Path Id") @PathVariable String id) {
        Order order = orderDao
                .findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException(Order.class, id));
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Order> createOrder(
            @ModelAttribute @Valid OrderCreateRequest request) {
        Order order = conversionService.convert(request, Order.class);
        return new ResponseEntity<>(orderDao.saveAndFlush(order), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteOrder(
            @PathVariable("id") Long orderId) {
        orderDao.deleteById(orderId);
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }

    @PostMapping("update/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> updateOrderById (
            @ModelAttribute @Valid OrderUpdateRequest request) {
        Order convertedOrder = conversionService.convert(request, Order.class);
        return new ResponseEntity(orderDao.save(convertedOrder), HttpStatus.OK);
    }
}
