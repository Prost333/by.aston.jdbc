package by.aston.jdbc.controller;

import by.aston.jdbc.entity.NameRates;
import by.aston.jdbc.service.imp.NameRatesServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/namerates")
@AllArgsConstructor
public class NameRatesController {
    private  NameRatesServiceImp nameRatesService;

    @PostMapping
    public void saveNameRates(@RequestBody NameRates nameRates) throws SQLException {
        nameRatesService.save(nameRates);
    }

    @DeleteMapping("/{id}")
    public void deleteNameRates(@PathVariable Long id) throws SQLException {
        NameRates nameRates = nameRatesService.findById(id);
        if (nameRates != null) {
            nameRatesService.delete(nameRates);
        }
    }

    @GetMapping("/{id}")
    public NameRates getNameRates(@PathVariable Long id) {
        return nameRatesService.findById(id);
    }

    @GetMapping
    public List<NameRates> getAllNameRates() {
        return nameRatesService.findAll();
    }
}
