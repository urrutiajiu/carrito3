alter table CARRITO drop foreign key FK5jca13mye8xblgtuo43npmhw7
alter table CARRITO_ITEM drop foreign key FKapy41sst137rte4a77valvn9j
alter table CARRITO_ITEM drop foreign key FK6mhskjwrrfrcjmqjvqo0tokjv
drop table if exists CARRITO
drop table if exists CARRITO_ITEM
drop table if exists CLIENTE
drop table if exists PRODUCTO
create table CARRITO (DTYPE varchar(31) not null, ID_CARRITO bigint not null auto_increment, ESTADO varchar(255) not null, FECHA datetime, TIPO varchar(255) not null, ID_CLIENTE bigint, primary key (ID_CARRITO)) engine=InnoDB
create table CARRITO_ITEM (ID_CARRITO_DETALLE bigint not null auto_increment, CANTIDAD integer, PRECIO_UNITARIO decimal(19,2), ID_CARRITO bigint not null, ID_PRODUCTO bigint not null, primary key (ID_CARRITO_DETALLE)) engine=InnoDB
create table CLIENTE (ID_CLIENTE bigint not null auto_increment, DNI bigint not null, ES_VIP bit not null, NOMBRE varchar(50) not null, primary key (ID_CLIENTE)) engine=InnoDB
create table PRODUCTO (ID_PRODUCTO bigint not null auto_increment, NOMBRE varchar(50), PRECIO_UNITARIO decimal(10,2), primary key (ID_PRODUCTO)) engine=InnoDB
alter table CARRITO add constraint FK5jca13mye8xblgtuo43npmhw7 foreign key (ID_CLIENTE) references CLIENTE (ID_CLIENTE)
alter table CARRITO_ITEM add constraint FKapy41sst137rte4a77valvn9j foreign key (ID_CARRITO) references CARRITO (ID_CARRITO)
alter table CARRITO_ITEM add constraint FK6mhskjwrrfrcjmqjvqo0tokjv foreign key (ID_PRODUCTO) references PRODUCTO (ID_PRODUCTO)
