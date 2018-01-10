Select sc.codigo, sc.fabrica, AVG(sc.promedio) as promedio from 
(select f.fab_codigo as codigo, f.fab_nombre as fabrica, z.zon_nombre as zona, AVG(e.ens_fecha_estimada - e.ens_fecha_finalizada) as promedio from ensamblaje e, fabrica f, zona z
where
f.fab_codigo = e.fk_fab_codigo AND z.zon_codigo=e.fk_zon_codigo
GROUP BY f.fab_codigo, f.fab_nombre, z.zon_nombre) as sc
Group by sc.codigo, sc.fabrica
Order by promedio
limit 1;