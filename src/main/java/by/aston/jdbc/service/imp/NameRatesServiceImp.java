package by.aston.jdbc.service.imp;

import by.aston.jdbc.entity.NameRates;
import by.aston.jdbc.repository.NameRatesDao;
import by.aston.jdbc.service.NameRatesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class NameRatesServiceImp implements NameRatesService {
    private NameRatesDao nameRatesDao;

    @Override
    public void save(NameRates n) throws SQLException {
        nameRatesDao.addNameRates(n);
    }

    @Override
    public void delete(NameRates n) throws SQLException {
        nameRatesDao.deleteNameRates(Math.toIntExact(n.getId()));
    }

    @Override
    public NameRates findById(Long id) {
        return nameRatesDao.findById(id);
    }

    @Override
    public List<NameRates> findAll() {
        return nameRatesDao.findAll();
    }
}
