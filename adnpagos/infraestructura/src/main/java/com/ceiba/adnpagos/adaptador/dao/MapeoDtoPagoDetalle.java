package com.ceiba.adnpagos.adaptador.dao;

import com.ceiba.adnpagos.modelo.dto.DtoPagoDetalle;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoDtoPagoDetalle implements RowMapper<DtoPagoDetalle>, MapperResult {
    @Override
    public DtoPagoDetalle mapRow(ResultSet rs, int rowNum) throws SQLException {

        String descripcion= rs.getString("descripcion");
        Double valor= rs.getDouble("valor");
        return new DtoPagoDetalle(descripcion,valor);
    }
}
