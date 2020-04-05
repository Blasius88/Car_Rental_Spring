package com.Car_Rental_Spring.controller;

import com.Car_Rental_Spring.controller.requests.color.ColorCreateRequest;
import com.Car_Rental_Spring.domain.Color;
import com.Car_Rental_Spring.repository.ColorDao;
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
@RequestMapping(value = "/rest/color")
public class ColorController {

    @Autowired
    private ColorDao colorDao;

    @GetMapping
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
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Color> getColorById(@ApiParam("Color Path Id") @PathVariable Long id) {
        Color color = colorDao.findById(id);
        return new ResponseEntity<>(color, HttpStatus.OK);
    }


    @ApiOperation(value = "Get color from server by color")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting color"),
            @ApiResponse(code = 400, message = "Invalid Color supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "Color was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Color> getColorName(String str) {
        Color color = colorDao.findColor(str);
        return new ResponseEntity<>(color, HttpStatus.OK);
    }
    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Color> createColor(@RequestBody @Valid ColorCreateRequest request) {
        Color color = new Color();
        data(request, color);

        Color savedColor = colorDao.save(color);

        return new ResponseEntity<>(savedColor, HttpStatus.OK);
    }

    @ApiOperation(value = "Update color by colorID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful color update"),
            @ApiResponse(code = 400, message = "Invalid User ID supplied"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Color> updateUser(@PathVariable("id") Long colorId,
                                            @RequestBody ColorCreateRequest request) {
        Color color = colorDao.findById(colorId);
        data(request, color);

        return new ResponseEntity<>(color, HttpStatus.OK);
    }

    private void data(@RequestBody ColorCreateRequest request, Color color) {
        color.setColorName(request.getColorName());
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteColor(@PathVariable("id") Long colorId) {
        colorDao.delete(colorId);
        return new ResponseEntity<>(colorId, HttpStatus.OK);
    }
}

