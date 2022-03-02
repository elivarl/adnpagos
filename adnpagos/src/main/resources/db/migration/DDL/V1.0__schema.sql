create table usuario (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 clave varchar(45) not null,
 fecha_creacion datetime null,
 primary key (id)
);

create table servicios (
 id int(11) not null auto_increment,
 numero_servicio varchar(25) not null,
 identificacion_cliente varchar(15) not null,
 nombre_cliente varchar(100) not null,
 mes_pago varchar(25) not null,
 fecha_maxima_pago datetime null,
 valor decimal (8,3) not null,
 estado BOOLEAN,
 fecha_creacion datetime null,
 primary key (id)
);

/*create table servicios_pagos (
 id int(11) not null auto_increment,
 mes_pago varchar(25) not null,
 fecha_maxima_pago datetime null,
 valor decimal (8,3) not null,
 estado varchar(10) not null,
 fecha_creacion datetime null,
 id_servicio int (11),
 primary key (id),
 FOREIGN KEY(id_servicio)  REFERENCES  servicios(id) 
 ON DELETE CASCADE ON UPDATE CASCADE
);*/

create table pagos(
id int (11) not null auto_increment,
fecha_pago datetime null,
identificacion_cliente varchar(50) not null,
valor_total decimal(8,3) not null,
PRIMARY KEY(id)
);

create table pagos_detalle(
id int (11) not null auto_increment,
descripcion varchar(150) not null,
valor decimal(8,3) not null,
id_servicio int(11),
id_pago int (11),
PRIMARY KEY (id),
FOREIGN KEY (id_pago) REFERENCES pagos (id)
ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (id_servicio) REFERENCES servicios (id)
ON DELETE CASCADE ON UPDATE CASCADE
);


