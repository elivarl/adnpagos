update servicios
set numero_servicio = :numeroServicio,
	identificacion_cliente = :identificacionCliente,
	nombre_cliente = :nombreCliente,
    mes_pago= :mes,
    fecha_maxima_pago = :fechaMaximaPago,
    valor = :valor,
    estado = :estado,
	fecha_creacion = :fechaCreacion
where id = :id