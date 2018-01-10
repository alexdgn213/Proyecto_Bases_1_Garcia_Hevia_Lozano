SELECT
     caracteristica."car_nombre" AS caracteristica_car_nombre,
     mod_car."mod_car_valor" AS mod_car_mod_car_valor,
     mod_car."mod_car_descripcion" AS mod_car_mod_car_descripcion
FROM
     "public"."caracteristica" caracteristica INNER JOIN "public"."mod_car" mod_car ON caracteristica."car_codigo" = mod_car."fk_car_codigo"
     INNER JOIN "public"."modelo_aeronave" modelo_aeronave ON mod_car."fk_mod_codigo" = modelo_aeronave."mod_codigo"
where mod_nombre='AU80'