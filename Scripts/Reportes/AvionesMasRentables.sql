Select sc.codigo,sc.modelo, AVG(sc.retraso) as promedio
FROM
    (select a.aer_codigo as codigo, mod_nombre as modelo, pp.pru_pie_fecha_realizacion - pp.pru_pie_fecha_estimada as retraso
    from aeronave a, modelo_aeronave m, pieza p, tipo_pieza tp, pru_pie pp
    WHERE a.fk_mod_codigo=m.mod_codigo AND p.fk_aer_codigo=a.aer_codigo AND p.fk_tip_codigo=tp.tip_codigo
    UNION ALL
    select a.aer_codigo as codigo, mod_nombre as modelo, pa.pru_aer_fecha_realizacion - pa.pru_aer_fecha_estimada as retraso
    from aeronave a, modelo_aeronave m, pru_aer pa
    WHERE a.fk_mod_codigo=m.mod_codigo AND pa.fk_aer_codigo=a.aer_codigo) as sc
GROUP BY sc.codigo,sc.modelo
ORDER BY promedio
limit 10;