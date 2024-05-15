package by.aston.jdbc.controller;

import by.aston.jdbc.dto.RatesReq;
import by.aston.jdbc.dto.RatesResp;
import by.aston.jdbc.entity.Rates;
import by.aston.jdbc.service.imp.RatesServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/rates")
@AllArgsConstructor
public class RatesController {

    private  RatesServiceImp ratesService;

    @PostMapping
    public void saveRates(@RequestBody RatesReq rates) throws SQLException {
        ratesService.save(rates);
    }

    @DeleteMapping("/{id}")
    public void deleteRates(@PathVariable Long id) throws SQLException {
        ratesService.delete(id);
    }

    @GetMapping("/{id}")
    public RatesResp getRates(@PathVariable Long id) {
        return ratesService.findById(id);
    }

    @GetMapping
    public List<RatesResp> getAllRates() {
        return ratesService.findAll();
    }
}