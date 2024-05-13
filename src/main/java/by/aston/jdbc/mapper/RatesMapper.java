package by.aston.jdbc.mapper;

import by.aston.jdbc.dto.RatesReq;
import by.aston.jdbc.dto.RatesResp;
import by.aston.jdbc.entity.Rates;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RatesMapper {

    RatesResp toResponse(Rates rates);
    @Mapping(target = "id", ignore = true)
    Rates toRequest(RatesReq ratesReq);
}
