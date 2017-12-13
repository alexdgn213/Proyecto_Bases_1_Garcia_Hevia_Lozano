--Deben ir al reves de los creates
--Deben incluir las secuencias

alter sequence aeronave_aer_codigo_seq restart 1;
alter sequence car_mot_car_mot_codigo_seq restart 1;
alter sequence caracteristica_car_codigo_seq restart 1;
alter sequence ensamblaje_ens_codigo_seq restart 1;
alter sequence estatus_est_codigo_seq restart 1;
alter sequence fabrica_fab_codigo_seq restart 1;
alter sequence factura_fac_codigo_seq restart 1;
alter sequence forma_pago_for_codigo_seq restart 1;
alter sequence informacion_contacto_inf_codigo_seq restart 1;
alter sequence inventario_inv_codigo_seq restart 1;
alter sequence lote_material_lot_codigo_seq restart 1;
alter sequence lugar_lug_codigo_seq restart 1;
alter sequence mat_inv_mat_inv_codigo_seq restart 1;
alter sequence mat_pro_mat_pro_codigo_seq restart 1;
alter sequence material_mat_codigo_seq restart 1;
alter sequence mod_car_mod_car_codigo_seq restart 1;
alter sequence modelo_aeronave_mod_codigo_seq restart 1;
alter sequence mot_mod_mot_mod_codigo_seq restart 1;
alter sequence motor_mot_codigo_seq restart 1;
alter sequence per_pru_pie_per_pru_pie_codigo_seq restart 1;
alter sequence pieza_pie_codigo_seq restart 1;
alter sequence privilegio_pri_codigo_seq restart 1;
alter sequence pru_aer_pru_aer_codigo_seq restart 1;
alter sequence pru_lot_pru_lot_codigo_seq restart 1;
alter sequence pru_pie_pru_pie_codigo_seq restart 1;
alter sequence prueba_pru_codigo_seq restart 1;
alter sequence rol_pri_rol_pri_codigo_seq restart 1;
alter sequence rol_rol_codigo_seq restart 1;
alter sequence solicitud_sol_codigo_seq restart 1;
alter sequence tip_mat_tip_mat_codigo_seq restart 1;
alter sequence tip_mod_tip_mod_codigo_seq restart 1;
alter sequence tip_pru_tip_pru_codigo_seq restart 1;
alter sequence tipo_pieza_tip_codigo_seq restart 1;
alter sequence usuario_usu_codigo_seq restart 1;
alter sequence zona_zon_codigo_seq restart 1;

delete from estatus;
delete from forma_pago;
delete from pru_aer;
delete from tip_mat;
delete from rol_pri;
delete from privilegio;
delete from usuario;
delete from rol;
delete from per_pru_pie;
delete from pru_lot;
delete from mat_inv;
delete from pru_pie;
delete from tip_pru;
delete from prueba;
delete from tip_mod;
delete from solicitud;
delete from ensamblaje;
delete from pieza;
delete from inventario;
delete from tipo_pieza;
delete from lote_material;
delete from mat_pro;
delete from material;
delete from aeronave;
delete from factura;
delete from mod_car;
delete from car_mot;
delete from caracteristica;
delete from mot_mod;
delete from motor;
delete from modelo_aeronave;
delete from informacion_contacto;
delete from cliente;
delete from proveedor;
delete from beneficiario;
delete from personal;
delete from zona;
delete from fabrica;
delete from lugar;
--




