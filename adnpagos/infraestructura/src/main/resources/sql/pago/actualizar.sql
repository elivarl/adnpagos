update pagos
	set porcentaje_descuento =  :porcentajeDescuento,
    subtotal = :subTotal,
    valor_descuento = :valorDescuento, 
    total = :total
where id = :id