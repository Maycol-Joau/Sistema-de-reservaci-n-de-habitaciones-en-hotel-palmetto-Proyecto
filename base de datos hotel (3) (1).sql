create database if not exists hotelP;
use hotelP;
create table if not exists servicio(
idServicio int not null,
precio double not null,
categoria varchar(45)not null,
primary key(idServicio)
);

create table if not exists habitacion(
numero_habitacion int not null,
precio double not null,
idServicio int not null,
primary key(numero_habitacion),
foreign key(idServicio)references servicio(idServicio)
);
create table if not exists reserva(
id_reserva int not null auto_increment,
sucursal varchar(45) not null,
habitacion int not null,
huesped int not null,
entrada date not null,
salida date not null,
primary key(id_reserva),
unique key unique_reserva (sucursal, id_reserva)
);
create table if not exists cliente(
idCliente int not null auto_increment,
nombre varchar(45) not null,
apellido varchar(45)not null,
telefono varchar(9)not null,
correo varchar(45)not null,
clave varchar(45)not null,
primary key(idCliente)
);
create table if not exists tarjeta(
idTarjeta int not null auto_increment,
tarjeta int not null,
vencimiento date not null,
codSeguridad int not null,
primary key(idTarjeta)
);
create table if not exists inventario(
id_Inventario int not null,
categoria_Producto varchar(45)not null,
primary key(id_Inventario)
);
create table if not exists empleado(
id_empleado int not null,
nombre varchar(45)not null,
apellido varchar(45)not null,
cargo varchar(45)not null,
telefono varchar(9)not null,
salario double not null,
primary key(id_empleado)
);
create table if not exists hotel(
id_hotel int not null,
nombre varchar(45)not null,
direccion varchar(45) not null,
telefono varchar(9)not null,
numero_habitacion int not null,
idCliente int not null,
id_Inventario int not null,
id_empleado int not null,
primary key(id_hotel),
foreign key(numero_habitacion)references habitacion(numero_habitacion),
foreign key(idCliente)references cliente(idCliente),
foreign key(id_Inventario)references inventario(id_Inventario),
foreign key(id_empleado)references empleado(id_empleado)
);
