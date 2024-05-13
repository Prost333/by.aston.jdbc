package by.aston.jdbc.controller;

import by.aston.jdbc.entity.City;
import by.aston.jdbc.service.imp.CityServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {
    private final CityServiceImp cityService;

    @Autowired
    public CityController(CityServiceImp cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    public ResponseEntity<Void> saveCity(@RequestBody City city) throws SQLException {
        cityService.save(city);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) throws SQLException {
        City city = cityService.findById(id);
        if (city != null) {
            cityService.delete(city);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCity(@PathVariable Long id) {

        return ResponseEntity.ok(cityService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<City>> getAllCities() {
        return ResponseEntity.ok(cityService.findAll());
    }
}
