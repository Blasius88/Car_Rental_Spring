package com.Car_Rental_Spring.controller;

import com.Car_Rental_Spring.controller.requests.ColorCreateRequest;
import com.Car_Rental_Spring.controller.requests.SearchCriteria;
import com.Car_Rental_Spring.domain.Color;
import com.Car_Rental_Spring.repository.ColorDao;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
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
            @ApiResponse(code = 200, message = "Successful getting user"),
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

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Color> createColor(@RequestBody @Valid ColorCreateRequest request) {
        Color color = new Color();
        data(request, color);

        Color savedColor = colorDao.save(color);

        return new ResponseEntity<>(savedColor, HttpStatus.OK);
    }

    @ApiOperation(value = "Update user by userID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful user update"),
            @ApiResponse(code = 400, message = "Invalid User ID supplied"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Color> updateUser(@PathVariable("id") Long userId,
                                            @RequestBody ColorCreateRequest request) {
        Color color = colorDao.findById(userId);
        data(request, color);

        return new ResponseEntity<>(color, HttpStatus.OK);
    }

    private void data(@RequestBody ColorCreateRequest request, Color color) {
        color.setColor(request.getColorName());
    }

    @ApiOperation(value = "Search user by query")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful user update"), //OK
            @ApiResponse(code = 400, message = "Invalid query supplied"), //Invalid request
            @ApiResponse(code = 404, message = "User was not found"), //Resourse not found
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit", value = "limit of users", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "offset", value = "start node of users", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "query", value = "search query", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Color>> searchUsers(@ApiIgnore @ModelAttribute SearchCriteria search) {
        List<Color> searchResult =
                colorDao.search(
                        search.getQuery(),
                        search.getLimit(),
                        search.getOffset()
                );
        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteUser(@PathVariable("id") Long colorId) {
        colorDao.delete(colorId);
        return new ResponseEntity<>(colorId, HttpStatus.OK);
    }
}

