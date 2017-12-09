--pk = primary key
--fk= foreign key
 
create table lugar
(
	lug_codigo serial not null,
	lug_nombre varchar(80) not null,
	lug_tipo varchar(80) not null,
	fk_lug_codigo int,
	constraint pk_lugar primary key(lug_codigo),
	constraint fk_lugar foreign key(lug_codigo)
	references lugar(lug_codigo)
);


create table fabrica
(
	fab_codigo serial not null,
	fab_nombre varchar(80),
	fk_lug_codigo int not null, 
	constraint pk_fabrica primary key(fab_codigo),
	constraint fk_lugar foreign key(fk_lug_codigo)
	references lugar (lug_codigo)
);


create table zona
(
	zon_codigo serial not null,
	zon_nombre varchar(100) not null,
	zon_descripcion varchar(100),
	fk_fab_codigo int not null,
	constraint pk_zona primary key (zon_codigo,fk_fab_codigo),
	constraint fk_fab_codigo foreign key(fk_fab_codigo)
	references fabrica(fab_codigo)
);

create table personal
(
	per_ci int not null,
	per_nombre varchar(80) not null,
	per_apellido_1 varchar(30) not null,
	per_apellido_2 varchar(30) not null,
	per_fecha_inicio date not null,
	per_titulacion varchar(80) not null,
	per_experiencia varchar(100) not null,
	per_fnac date not null,
	fk_lug_codigo int not null,
	fk_zon_codigo int not null,
    fk_fab_codigo int not null,
    fk_zon_codigo_2 int,
    fk_fab_codigo_2 int,
	constraint pk_personal primary key(per_ci),
	constraint fk_lugar foreign key(fk_lug_codigo)
	references lugar(lug_codigo),
	constraint fk_zon_codigo foreign key(fk_zon_codigo,fk_fab_codigo)
	references zona(zon_codigo,fk_fab_codigo),
    constraint fk_zon_codigo_2 foreign key(fk_zon_codigo_2,fk_fab_codigo_2)
	references zona(zon_codigo,fk_fab_codigo)
);

create table beneficiario
(
	ben_ci int not null,
	ben_nombre varchar(30) not null,
	ben_apellido_1 varchar(30) not null,
	ben_apellido_2 varchar(30) not null,
	ben_parentesco varchar(30),
	fk_lug_codigo int not null,
	fk_per_ci int not null,
	constraint pk_beneficiario primary key(ben_ci),
	constraint fk_lugar foreign key(fk_lug_codigo)
	references lugar(lug_codigo),
	constraint fk_personal foreign key(fk_per_ci)
	references personal(per_ci)
);

create table proveedor
(
	pro_rif int not null,
	pro_nombre varchar(30) not null,
	pro_monto_acreditado int not null,
	pro_fecha_inicio date not null,
	fk_lug_codigo int not null,
	constraint pk_proveedor primary key(pro_rif),
	constraint fk_lugar foreign key(fk_lug_codigo)
	references lugar(lug_codigo)
);

create table cliente
(
	cli_rif int not null,
	cli_nombre varchar(30) not null,
	cli_monto_acreditado int not null,
	cli_fecha_inicio date not null,
	fk_lug_codigo int not null,
	constraint pk_cliente primary key(cli_rif),
	constraint fk_lugar foreign key(fk_lug_codigo)
	references lugar(lug_codigo)
);

create table informacion_contacto
(
	inf_codigo serial not null,
	inf_valor varchar(80) not null,
	inf_tipo varchar(30) not null,
	fk_pro_rif int,
	fk_per_ci int,
	fk_cli_rif int,
	fk_ben_ci int,
	constraint pk_informacion_contacto primary key(inf_codigo),
	constraint fk_pro_rif foreign key(fk_pro_rif)
	references proveedor (pro_rif),
	constraint fk_per_ci foreign key(fk_per_ci)
	references personal(per_ci),
	constraint fk_cli_rif foreign key(fk_cli_rif)
	references cliente(cli_rif),
	constraint fk_ben_ci foreign key(fk_ben_ci)
	references beneficiario(ben_ci)
);

create table modelo_aeronave
(
	mod_codigo serial not null,
	mod_nombre varchar(30) not null,
	constraint pk_modelo_aeronave primary key(mod_codigo)
);

create table caracteristica
(
	car_codigo serial not null,
	car_nombre varchar(80) not null,
	constraint pk_caracteristica primary key(car_codigo)
);

create table mod_car
(
	mod_car_codigo serial not null,
	mod_car_valor real not null, --Lo cambiamos porque las medidas puedes ser: 44,1 
	mod_car_descripcion varchar(100), --Se puso para determinar la medida ej: (cm, metros, etc)
	fk_mod_codigo int not null,
	fk_car_codigo int not null,
	constraint pk_mod_car primary key(mod_car_codigo,fk_mod_codigo,fk_car_codigo),
	constraint fk_modelo_aeronave foreign key(fk_mod_codigo)
	references modelo_aeronave(mod_codigo),
	constraint fk_caracteristica foreign key(fk_car_codigo)
	references caracteristica(car_codigo)	
);

create table aeronave
(
	aer_codigo serial not null,
	aer_fecha_compra date not null,
	fk_cli_rif int not null,
	fk_mod_codigo int not null,
	constraint pk_aeronave primary key(aer_codigo),
	constraint fk_cli_rif foreign key(fk_cli_rif)
	references cliente(cli_rif),
	constraint fk_tip_cod foreign key(fk_mod_codigo)
	references modelo_aeronave(mod_codigo)
);

create table material
(
	mat_codigo serial not null,
	mat_nombre varchar(80) not null,
	mat_tiempo_estimado int not null, --Numero de dias
	constraint pk_material primary key(mat_codigo)
);


create table mat_pro
(
	mat_pro_codigo serial not null,
	mat_pro_precio int not null,
	mat_pro_fecha_compra date not null,
	mat_pro_cantidad int not null,
    fk_mat_codigo int not null,
    fk_pro_rif int not null,
	constraint pk_mat_pro primary key(mat_pro_codigo, fk_mat_codigo,fk_pro_rif),
	constraint fk_mat_codigo foreign key(fk_mat_codigo)
	references material(mat_codigo),
	constraint fk_pro_rif foreign key(fk_pro_rif)
	references proveedor(pro_rif)
);



create table tipo_pieza
(
	tip_codigo serial not null,
    fk_tip_codigo int,
    tip_tiempo_fabricacion int not null,--Numero de dias u horas
	tip_nombre varchar(80) not null,
	constraint pk_tip_pieza primary key(tip_codigo),
	constraint fk_tip_cod foreign key(fk_tip_codigo)
	references tipo_pieza(tip_codigo)
);

create table inventario
(
	inv_codigo serial not null,
    fk_fab_codigo int not null,
    inv_descripcion varchar(80) not null,
	constraint pk_inventario primary key(inv_codigo),
	constraint fk_fabrica foreign key(fk_fab_codigo)
	references fabrica(fab_codigo)
);

create table pieza
(
	pie_codigo serial not null,
	pie_fecha_estimada date,
	pie_fecha_entregada date,
    fk_inv_codigo int,
    fk_aer_codigo int,
    fk_tip_codigo int not null,
    fk_pie_codigo int,
	constraint pk_pieza primary key(pie_codigo),
	constraint fk_inv_codigo foreign key(fk_inv_codigo)
	references inventario(inv_codigo),
	constraint fk_aeronave foreign key(fk_aer_codigo)
	references aeronave(aer_codigo),
	constraint fk_tip_codigo foreign key(fk_tip_codigo)
	references tipo_pieza(tip_codigo),
	constraint fk_pieza foreign key(fk_pie_codigo)
	references pieza(pie_codigo)
);

create table ensamblaje
(
	ens_codigo serial not null,
	ens_descripcion varchar(80) not null,
	fk_zon_codigo int not null,
    fk_fab_codigo int not null,
	fk_pie_codigo int,
    fk_tip_codigo int,
	constraint pk_ens_codigo primary key(ens_codigo),
	constraint fk_zon_codigo foreign key(fk_zon_codigo,fk_fab_codigo)
	references zona(zon_codigo,fk_fab_codigo),
	constraint fk_pie_codigo foreign key(fk_pie_codigo)
	references pieza(pie_codigo),
    constraint fk_tip_codigo foreign key(fk_tip_codigo)
    references tipo_pieza(tip_codigo)
);

create table solicitud
(
	sol_codigo serial not null,
	sol_cantidad int not null,
	sol_completada int not null, --estatus=1 =realizada, 0=pending
	sol_descripcion varchar(100) not null,
	fk_fab_codigo1 int not null,
	fk_fab_codigo2 int not null,
	fk_tip_codigo int,
    fk_mat_codigo int,
	constraint pk_solicitud primary key(sol_codigo,fk_fab_codigo1,fk_fab_codigo2),
	constraint fk_fab_codigo1 foreign key(fk_fab_codigo1)
	references fabrica (fab_codigo),
	constraint fk_fab_codigo2 foreign key(fk_fab_codigo2)
	references fabrica (fab_codigo),
	constraint fk_pie_codigo foreign key(fk_tip_codigo)
	references tipo_pieza(tip_codigo),
	constraint fk_inv_codigo foreign key(fk_mat_codigo)
	references material(mat_codigo)
);

create table tip_mod
(
	tip_mod_codigo serial not null,
	tip_mod_cantidad int not null,
    fk_mod_codigo int not null,
    fk_tip_codigo int not null,
	constraint pk_tip_mod primary key(tip_mod_codigo),
	constraint fk_mod_codigo foreign key(fk_mod_codigo)
	references modelo_aeronave(mod_codigo),
	constraint fk_tip_codigo foreign key(fk_tip_codigo)
	references tipo_pieza(tip_codigo)
);

create table prueba
(
	pru_codigo serial not null,
	pru_nombre varchar(80) not null,
	pru_descripcion varchar(90) not null,
	constraint pk_prueba primary key(pru_codigo)
);

create table tip_pru 
	(
		tip_pru_codigo serial not null,
		fk_tip_codigo int not null,
		fk_pru_codigo int not null,
		constraint pk_tip_pru_codigo primary key(tip_pru_codigo),
		constraint fk_pru_codigo foreign key(fk_pru_codigo)
		references prueba(pru_codigo),
		constraint fk_tip_codigo foreign key(fk_tip_codigo)
		references tipo_pieza(tip_codigo)
		);

create table pru_pie
(
	pru_pie_codigo serial not null,
	pru_pie_fecha_realizacion date,
    fk_pru_codigo int not null,
    fk_pie_codigo int not null,
	constraint pk_pru_pie primary key(pru_pie_codigo,fk_pru_codigo,fk_pie_codigo),
	constraint fk_pru_codigo foreign key(fk_pru_codigo)
	references prueba(pru_codigo),
	constraint fk_pie_codigo foreign key(fk_pie_codigo)
	references pieza(pie_codigo)
);

create table mat_inv
(
	mat_inv_codigo serial not null,
	mat_inv_cantidad int,
    fk_mat_codigo int not null,
    fk_inv_codigo int not null,
	constraint pk_mat_inv primary key(mat_inv_codigo,fk_mat_codigo,fk_inv_codigo),
	constraint fk_mat_codigo foreign key(fk_mat_codigo)
	references material(mat_codigo),
	constraint fk_inv_codigo foreign key(fk_inv_codigo)
	references inventario(inv_codigo)
);	

create table pru_mat_inv
(
		pru_mat_inv_codigo serial not null,
		pru_mat_inv_fecha_realizacion date,
    	fk_pru_codigo int not null,
    	fk_mat_codigo int not null,
    	fk_inv_codigo int not null,
    	fk_mat_inv_codigo int not null,
		constraint pk_pru_mat_inv primary key(pru_mat_inv_codigo,fk_pru_codigo,fk_mat_codigo,fk_inv_codigo,fk_mat_inv_codigo),
		constraint fk_pru_codigo foreign key(fk_pru_codigo)
		references prueba(pru_codigo),
		constraint fk_mat_inv_codigo foreign key(fk_mat_inv_codigo,fk_mat_codigo,fk_inv_codigo)
		references mat_inv(mat_inv_codigo,fk_mat_codigo,fk_inv_codigo)
);

create table per_pru_pie
(
	 per_pru_pie_codigo serial not null,
	 per_pru_pie_encargado int ,--1 es el encargado
     fk_per_ci int not null,
     fk_pru_codigo int not null,
     fk_pie_codigo int not null,
     fk_pru_pie_codigo int not null,
	 constraint pk_per_pru_pie primary key(per_pru_pie_codigo,fk_per_ci,fk_pru_codigo,fk_pie_codigo,fk_pru_pie_codigo),
	 constraint fk_personal foreign key(fk_per_ci)
	 references personal(per_ci),
	 constraint fk_pru_pie_codigo foreign key(fk_pru_pie_codigo,fk_pie_codigo,fk_pru_codigo)
	 references pru_pie(pru_pie_codigo,fk_pie_codigo,fk_pru_codigo)
);

create table rol
(
	 rol_codigo serial not null,
	 rol_nombre varchar(80) not null unique,
	 rol_descripcion varchar(80) not null,
	 constraint pk_rol_codigo primary key(rol_codigo)
);

create table usuario
(
	 usu_codigo serial not null,
	 usu_nombre varchar(30) not null unique,
	 usu_clave varchar(30) not null,
	 fk_rol_codigo int not null,
	 constraint pk_usu_codigo primary key(usu_codigo),
	 constraint fk_rol_codigo foreign key (fk_rol_codigo)
	 references Rol(rol_codigo)
);

create table privilegio
(
	pri_codigo serial not null,
	pri_accion varchar(40) not null,
	pri_descripcion varchar(100) not null,
	constraint pk_pri_codigo primary key(pri_codigo)
);

create table rol_pri
(
	rol_pri_codigo serial not null,
	fk_rol_codigo int not null,
	fk_pri_codigo int not null,
	constraint pk_rol_pri_codigo primary key(rol_pri_codigo, fk_rol_codigo, fk_pri_codigo),
	constraint fk_rol_codigo foreign key(fk_rol_codigo)
	references rol(rol_codigo),
	constraint fk_pri_codigo foreign key(fk_pri_codigo)
	references privilegio(pri_codigo)	
);

create table tip_mat
(
	tip_mat_codigo serial not null,
	tip_mat_cantidad int not null,
	fk_tip_codigo int not null,
	fk_mat_codigo int not null,
	constraint pk_tip_mat_codigo primary key(tip_mat_codigo, fk_tip_codigo, fk_mat_codigo),
	constraint fk_tip_codigo foreign key(fk_tip_codigo)
	references tipo_pieza(tip_codigo),
	constraint fk_mat_codigo foreign key(fk_mat_codigo)
	references material(mat_codigo)	
);

create table pru_aer
(
	pru_aer_codigo serial not null,
	pru_aer_fecha_realizacion date,
    fk_pru_codigo int not null,
    fk_aer_codigo int not null,
	constraint pk_pru_aer primary key(pru_aer_codigo,fk_pru_codigo,fk_aer_codigo),
	constraint fk_pru_codigo foreign key(fk_pru_codigo)
	references prueba(pru_codigo),
	constraint fk_aer_codigo foreign key(fk_aer_codigo)
	references aeronave(aer_codigo)
);

create table forma_pago
(
	for_codigo serial not null,
	for_monto int not null,
	for_efectivo int,
	for_numero bigint,
	for_banco varchar(30),
	for_tipo_tarjeta varchar(30),
	for_fecha_vencimiento date,
	for_tipo varchar(30),
	constraint pk_for_codigo primary key(for_codigo)
);

create table pago --Falto fk_cli
(
	pag_codigo serial not null,
	fk_for_codigo int not null,
	fk_mat_pro_codigo int,
	fk_aer_codigo int,
	fk_mat_codigo int,
	fk_pro_rif int,
	--fk_cli_rif int,
	constraint pk_pag_codigo primary key(pag_codigo,fk_for_codigo),
	constraint fk_for_codigo foreign key(fk_for_codigo)
	references forma_pago(for_codigo),
	constraint fk_mat_pro_codigo foreign key(fk_mat_pro_codigo,fk_mat_codigo,fk_pro_rif)
	references mat_pro(mat_pro_codigo,fk_mat_codigo,fk_pro_rif),
	constraint fk_aer_codigo foreign key(fk_aer_codigo)
	references aeronave(aer_codigo)
	--constraint fk_cli_rif foreign key(fk_cli_rif),
	--references cliente(cli_rif)
);

create table estatus
(	
	 est_codigo serial not null,
	 est_nombre varchar(30) not null,
	 fk_ens_codigo int,
	 fk_pru_pie_codigo int,
	 fk_pru_codigo int,
	 fk_pie_codigo int,
	 fk_pru_aer_codigo int,
	 fk_aer_codigo int,
	 fk_mat_codigo int,
	 fk_inv_codigo int,
	 fk_mat_inv_codigo int,
	 fk_pru_mat_inv_codigo int,
	 constraint pk_est_codigo primary key(est_codigo),
	 constraint fk_ens_codigo foreign key(fk_ens_codigo)
	 references ensamblaje(ens_codigo),
	 constraint fk_pru_pie_codigo foreign key(fk_pru_pie_codigo,fk_pru_codigo,fk_pie_codigo)
	 references pru_pie(pru_pie_codigo,fk_pru_codigo,fk_pie_codigo),
	 constraint fk_pru_aer_codigo foreign key(fk_pru_aer_codigo,fk_aer_codigo,fk_pru_codigo)
	 references pru_aer(pru_aer_codigo,fk_aer_codigo,fk_pru_codigo),
	 constraint fk_mat_codigo foreign key(fk_mat_codigo)
	 references material(mat_codigo),
	 constraint fk_inv_codigo foreign key(fk_inv_codigo)
	 references inventario(inv_codigo),
	 constraint fk_pru_mat_inv_codigo foreign key(fk_pru_mat_inv_codigo,fk_mat_inv_codigo,fk_pru_codigo,fk_inv_codigo,fk_mat_codigo)
	 references pru_mat_inv(pru_mat_inv_codigo,fk_mat_inv_codigo,fk_pru_codigo,fk_inv_codigo,fk_mat_codigo),
    constraint fk_aer_codigo foreign key(fk_aer_codigo)
    references aeronave(aer_codigo)
);

--

