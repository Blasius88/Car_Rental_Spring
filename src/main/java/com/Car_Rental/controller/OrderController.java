package com.Car_Rental.controller;

import com.Car_Rental.controller.requests.order.OrderCreateRequest;
import com.Car_Rental.controller.requests.order.OrderUpdateRequest;
import com.Car_Rental.entity.Order;
import com.Car_Rental.exceptions.DateOrTimeEnteredIncorrectly;
import com.Car_Rental.exceptions.EntityNotFoundException;
import com.Car_Rental.repository.springdata.OrderRepository;
import com.Car_Rental.service.OrderForm;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/rest/order")
public class OrderController {

    private final OrderRepository orderDao;

    private final OrderForm orderForm;

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
    public ResponseEntity<List<Order>> getOrders() {
        try {
            return new ResponseEntity<>(orderDao.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
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
        try {

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteOrder(
            @PathVariable("id") Long orderId) {
        orderDao.deleteById(orderId);
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    @Transactional
    public ResponseEntity<Long> updateOrderById(@PathVariable("id") String id,
                                                @RequestBody @Valid OrderUpdateRequest request) {
        try {
            return new ResponseEntity(orderForm.update(request, Long.valueOf(id)), HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return new ResponseEntity(orderForm.update(request, Long.valueOf(id)), HttpStatus.OK);
    }

    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Order> createOrderUser(
            @ModelAttribute @Valid OrderCreateRequest request) throws DateOrTimeEnteredIncorrectly {
            return new ResponseEntity<>(orderForm.save(request), HttpStatus.CREATED);
    }
}
