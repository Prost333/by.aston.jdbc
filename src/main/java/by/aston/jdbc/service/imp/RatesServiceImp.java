package by.aston.jdbc.service.imp;


import by.aston.jdbc.dto.RatesResp;
import by.aston.jdbc.entity.Rates;
import by.aston.jdbc.mapper.RatesMapper;
import by.aston.jdbc.repository.RatesDao;
import by.aston.jdbc.service.RatesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RatesServiceImp implements RatesService {
    private RatesDao ratesDao;
    private RatesMapper ratesMapper;

    @Override
    public void save(Rates r) throws SQLException {
        ratesDao.addRates(r);
    }

    @Override
    public void delete(Long id) throws SQLException {
        ratesDao.deleteRates(id);
    }

    @Override
    public RatesResp findById(Long id) {
        return ratesMapper.toResponse(ratesDao.findById(id));
    }

    @Override
    public List<RatesResp> findAll() {
        return ratesDao.findAll()
                .stream()
                .map(ratesMapper::toResponse)
                .collect(Collectors.toList());
    }
}

