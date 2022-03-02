update pagos
	set fecha_pago = :fechaPago,
    identificacion_cliente = :identificacionCliente,
    valor_total = :valorTotal
where id = :id