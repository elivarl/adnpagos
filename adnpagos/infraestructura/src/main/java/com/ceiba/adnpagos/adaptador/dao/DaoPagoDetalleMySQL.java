package com.ceiba.adnpagos.adaptador.dao;

import com.ceiba.adnpagos.modelo.dto.DtoPagoDetalle;
import com.ceiba.adnpagos.puerto.dao.DaoPagoDetalle;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoPagoDetalleMySQL implements DaoPagoDetalle {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "pagodetalle", value = "listarPorIdPago")
    private static String sqlListarPorIdPago;

    public DaoPagoDetalleMySQL(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoPagoDetalle> listarPagoDetallePorIdPago(Long idPago) {
        MapSqlParameterSource paramSource= new MapSqlParameterSource();
        paramSource.addValue("idPago", idPago);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarPorIdPago,paramSource,new MapeoDtoPagoDetalle());


    }
}
