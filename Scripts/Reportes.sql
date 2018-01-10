--Produccion anual
select 
	to_char(a1.aer_fecha_compra,'YYYY') as año,
	(Select count(*) 
		from aeronave a2 
		where to_char(a2.aer_fecha_compra,'YYYY')=to_char(a1.aer_fecha_compra,'YYYY')) as Aeronaves,
	(Select count(*) 
		from pieza p 
		where to_char(p.pie_fecha_entregada,'YYYY')=to_char(a1.aer_fecha_compra,'YYYY')) as Piezas
FROM aeronave a1
UNION
select 
	to_char(p1.pie_fecha_entregada,'YYYY') as año,
	(Select count(*) 
		from aeronave a2 
		where to_char(a2.aer_fecha_compra,'YYYY')=to_char(p1.pie_fecha_entregada,'YYYY')) as Aeronaves,
	(Select count(*) 
		from pieza p2 
		where to_char(p2.pie_fecha_entregada,'YYYY')=to_char(p1.pie_fecha_entregada,'YYYY')) as Piezas
FROM pieza p1
Order by año;

--Promedio de produccion mensual
Select 
	AVG(sc.Aeronaves) as aeronaves , 
	AVG(sc.Piezas) as piezas
from
	(select 
		sc2.año as año, 
		sc2.mes as mes,
	    SUM(sc2.aeronaves) as aeronaves,
	    SUM(sc2.piezas) as piezas
	    from
		(select 
			to_char(a1.aer_fecha_compra,'YYYY') as año, 
			to_char(a1.aer_fecha_compra,'MM') as mes,
			(Select 
				count(*) 
			from aeronave a2 
			where to_char(a2.aer_fecha_compra,'YYYY')=to_char(a1.aer_fecha_compra,'YYYY')
			AND to_char(a2.aer_fecha_compra,'MM')=to_char(a1.aer_fecha_compra,'MM')) as Aeronaves,
			0 as Piezas
		FROM aeronave a1
		UNION
		select 
			to_char(p1.pie_fecha_entregada,'YYYY') as año, 
			to_char(p1.pie_fecha_entregada,'MM') as mes,
			0 as Aeronaves,
			(Select 
				count(*) 
			from pieza p2 
			where to_char(p2.pie_fecha_entregada,'YYYY')=to_char(p1.pie_fecha_entregada,'YYYY')
	        AND to_char(p2.pie_fecha_entregada,'MM')=to_char(p1.pie_fecha_entregada,'MM')) as Piezas
		FROM pieza p1
		Order by año,mes) as sc2
    GROUP BY año, mes) as sc;

-- Top 10 clientes
select 
	SB.cli_nombre,
	AVG (SB.NUMERO) 
from (select 
		cli_nombre,
		EXTRACT (year from aer_fecha_compra) AS AÑO,
		COUNT (*) AS NUMERO 
	from aeronave,cliente
	where fk_cli_rif=cli_rif
	group by cli_nombre, AÑO) as SB
group by SB.cli_nombre
order by AVG(SB.NUMERO) desc
limit 1

--Modelos de aviones
SELECT
     modelo_aeronave."mod_codigo" AS modelo_aeronave_mod_codigo,
     modelo_aeronave."mod_nombre" AS modelo_aeronave_mod_nombre
FROM "public"."modelo_aeronave" modelo_aeronave;

--Media mensual de aviones por modelo
Select 
	mod_nombre as nombre, 
	floor(avg(sc.cantidad))
from modelo_aeronave,
	(Select fk_mod_codigo as codigoModelo, to_char(aer_fecha_compra,'YYYY') as año,  to_char(aer_fecha_compra,'MM') as mes, count(*) as cantidad from aeronave
	group by año,mes,fk_mod_codigo
	order by fk_mod_codigo,año,mes) as sc
where mod_codigo=sc.codigoModelo
Group by mod_nombre;

--Modelo mas vendido
SELECT
     COUNT	(*) AS Vendidos,
     aeronave."fk_mod_codigo" AS aeronave_fk_mod_codigo,
     modelo_aeronave."mod_nombre" AS modelo_aeronave_mod_nombre
FROM
     "public"."modelo_aeronave" modelo_aeronave INNER JOIN "public"."aeronave" aeronave ON modelo_aeronave."mod_codigo" = aeronave."fk_mod_codigo"
GROUP BY aeronave."fk_mod_codigo",modelo_aeronave."mod_nombre"
ORDER BY COUNT (modelo_aeronave."mod_nombre") desc
LIMIT 1

-- Equipo mas eficiente
select 
	per_ci,per_nombre,
	per_apellido_1,
	per_apellido_2
from personal,pru_pie,per_pru_pie AS PPP
where per_ci=PPP.fk_per_ci AND pru_pie_codigo=fk_pru_pie_codigo 
AND (pru_pie_fecha_realizacion-pru_pie_fecha_estimada)=(select MAX((pru_pie_fecha_realizacion-pru_pie_fecha_estimada))
                                                        from pru_pie)
limit 1;

--Inventario Mensual
Select 
	to_char(lot_fecha_compra, 'YYYY') As año,  
	to_char(lot_fecha_compra, 'MM') As mes, 
	mat_nombre as material,
	Sum(lot_cantidad) as cantidad
FROM Material, lote_material
WHERE mat_codigo=fk_mat_codigo
AND lot_codigo in(
				 SELECT l.lot_codigo
				 FROM lote_material l, material m, proveedor p
				 WHERE l.fk_pro_rif=p.pro_rif AND l.fk_mat_codigo=m.mat_codigo
				 AND not exists(Select pru_lot_codigo from pru_lot where fk_lot_codigo= l.lot_codigo AND fk_est_codigo!=4)
				)
GROUP BY año, mes, material
Order by año,mes,material;

--Producto mas pedido
SELECT
     material."mat_codigo" AS material_mat_codigo,
     material."mat_nombre" AS material_mat_nombre,
     SUM(lote_material."lot_cantidad") AS cantidadSOlicitada
FROM
     "public"."material" material INNER JOIN "public"."mat_pro" mat_pro ON material."mat_codigo" = mat_pro."fk_mat_codigo"
     INNER JOIN "public"."lote_material" lote_material ON mat_pro."mat_pro_codigo" = lote_material."fk_mat_pro_codigo"
     AND mat_pro."fk_mat_codigo" = lote_material."fk_mat_codigo"
     AND mat_pro."fk_pro_rif" = lote_material."fk_pro_rif"
GROUP BY material."mat_codigo", material."mat_nombre"
ORDER BY SUM(lote_material."lot_cantidad") DESC
LIMIT 1;

--Tipo de Ala Mas utilizado
SELECT 
	tipo_pieza."tip_nombre" AS tipo_pieza_tip_nombre, 
	COUNT(tip_nombre) AS Cantidad
FROM tipo_pieza
WHERE fk_tip_codigo= 1
GROUP BY tip_nombre
ORDER BY COUNT(tip_nombre) desc
LIMIT 1;

--Aviones mas rentables
Select 
	sc.codigo,sc.modelo, 
	AVG(sc.retraso) as promedio
FROM
    (select a.aer_codigo as codigo, mod_nombre as modelo, pp.pru_pie_fecha_realizacion - pp.pru_pie_fecha_estimada as retraso
    from aeronave a, modelo_aeronave m, pieza p, pru_pie pp
    WHERE a.fk_mod_codigo=m.mod_codigo AND p.fk_aer_codigo=a.aer_codigo AND pp.fk_pie_codigo = p.pie_codigo
    UNION ALL
    select a.aer_codigo as codigo, mod_nombre as modelo, e.ens_fecha_finalizada - e.ens_fecha_estimada as retraso
    from aeronave a, modelo_aeronave m, pieza p, ensamblaje e
    WHERE a.fk_mod_codigo=m.mod_codigo AND p.fk_aer_codigo=a.aer_codigo AND e.fk_pie_codigo = p.pie_codigo
    UNION ALL
    select a.aer_codigo as codigo, mod_nombre as modelo, pa.pru_aer_fecha_realizacion - pa.pru_aer_fecha_estimada as retraso
    from aeronave a, modelo_aeronave m, pru_aer pa
    WHERE a.fk_mod_codigo=m.mod_codigo AND pa.fk_aer_codigo=a.aer_codigo) as sc
GROUP BY sc.codigo,sc.modelo
ORDER BY promedio
limit 10;

--Especificaciones modelo
SELECT
     caracteristica."car_nombre" AS caracteristica_car_nombre,
     mod_car."mod_car_valor" AS mod_car_mod_car_valor,
     mod_car."mod_car_descripcion" AS mod_car_mod_car_descripcion
FROM
     "public"."caracteristica" caracteristica INNER JOIN "public"."mod_car" mod_car ON caracteristica."car_codigo" = mod_car."fk_car_codigo"
     INNER JOIN "public"."modelo_aeronave" modelo_aeronave ON mod_car."fk_mod_codigo" = modelo_aeronave."mod_codigo"
where mod_nombre=$P{nombre};

--Productos no calificados
SELECT  
     material."mat_nombre" AS material_mat_nombre,
SUM( lote_material."lot_cantidad") AS lote_material_lot_cantidad
FROM
     "public"."mat_pro" mat_pro INNER JOIN "public"."lote_material" lote_material ON mat_pro."mat_pro_codigo" = lote_material."fk_mat_pro_codigo"
     AND mat_pro."fk_mat_codigo" = lote_material."fk_mat_codigo"
     AND mat_pro."fk_pro_rif" = lote_material."fk_pro_rif"
     INNER JOIN "public"."material" material ON mat_pro."fk_mat_codigo" = material."mat_codigo"
     INNER JOIN "public"."pru_lot" pru_lot ON lote_material."lot_codigo" = pru_lot."fk_lot_codigo"
     INNER JOIN "public"."estatus" estatus ON pru_lot."fk_est_codigo" = estatus."est_codigo"
WHERE estatus."est_nombre"='Cancelado' AND pru_lot."fk_pru_codigo"='1'
GROUP BY  material."mat_nombre"

-- Promedio de traslados
select 
	f1.fab_nombre as origen ,
	f2.fab_nombre as destino, 
	AVG(s.sol_cantidad) as promedio
from fabrica f1, fabrica f2, solicitud s
WHERE s.fk_fab_codigo1= f1.fab_codigo AND s.fk_fab_codigo2=f2.fab_codigo
GROUP by origen,destino;

--Lista de proveedores
 SELECT
     proveedor."pro_rif" AS proveedor_pro_rif,
     proveedor."pro_nombre" AS proveedor_pro_nombre,
     proveedor."pro_monto_acreditado" AS proveedor_pro_monto_acreditado,
     to_char(date(proveedor."pro_fecha_inicio"),'DD-MM-YYYY') AS proveedor_pro_fecha_inicio,
     lugar."lug_nombre" AS lugar_lug_nombre
FROM
     "public"."lugar" lugar INNER JOIN "public"."proveedor" proveedor ON lugar."lug_codigo" = proveedor."fk_lug_codigo"

-- Fabrica mas eficiente
Select 
	sc.codigo, 
	sc.fabrica, 
	AVG(sc.promedio) as promedio 
from 	(select f.fab_codigo as codigo, 
		f.fab_nombre as fabrica, 
		z.zon_nombre as zona, 
		AVG(e.ens_fecha_finalizada-e.ens_fecha_estimada) as promedio 
		from ensamblaje e, fabrica f, zona z
		where f.fab_codigo = e.fk_fab_codigo AND z.zon_codigo=e.fk_zon_codigo
		GROUP BY f.fab_codigo, f.fab_nombre, z.zon_nombre) as sc
Group by sc.codigo, sc.fabrica
Order by promedio
limit 1;

-- Descripcion de piezas
Select 
	tip_nombre as Nombre, 
	tip_descripcion as descripcion 
from tipo_pieza 
Where tip_descripcion is not null;















