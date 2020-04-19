package com.Car_Rental.controller;

import com.Car_Rental.controller.requests.color.ColorCreateRequest;
import com.Car_Rental.controller.requests.color.ColorUpdateRequest;
import com.Car_Rental.entity.Color;
import com.Car_Rental.exceptions.EntityNotFoundException;
import com.Car_Rental.repository.springdata.ColorRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/rest/color")
public class ColorController {

    private final ColorRepository colorDao;

    @Qualifier(value = "mvcConversionService")
    private ConversionService conversionService;

    @ApiOperation(value = "Get all color from server")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting data"),
            @ApiResponse(code = 400, message = "Something wrong"),
            @ApiResponse(code = 401, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Users not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Color>> getColors() {
        return new ResponseEntity<>(colorDao.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get color from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting color"),
            @ApiResponse(code = 400, message = "Invalid Color ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "Color was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Color> getColorById(
            @ApiParam("Color Path Id") @PathVariable String id) {
        Color color = colorDao
                .findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException(Color.class, id));
        return new ResponseEntity<>(color, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Color> createColor(
            @ModelAttribute @Valid ColorCreateRequest request) {
        Color color = conversionService.convert(request, Color.class);
        return new ResponseEntity<>(colorDao.saveAndFlush(color), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update color by colorID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful color update"),
            @ApiResponse(code = 400, message = "Invalid User ID supplied"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Color> updateUser(
            @ModelAttribute @Valid ColorUpdateRequest request) {
        Color color = conversionService.convert(request, Color.class);
        return new ResponseEntity<>(colorDao.save(color), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteColor(@ApiParam("User Path Id")
                                            @PathVariable("id") String id) {
        colorDao.deleteById(Long.valueOf(id));
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}

