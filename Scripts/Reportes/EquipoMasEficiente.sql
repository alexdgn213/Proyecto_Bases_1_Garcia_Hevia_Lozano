select per_ci,per_nombre,per_apellido_1,per_apellido_2
from personal,pru_pie,per_pru_pie AS PPP
where per_ci=PPP.fk_per_ci AND pru_pie_codigo=fk_pru_pie_codigo AND 
(pru_pie_fecha_realizacion-pru_pie_fecha_estimada)=(select MAX((pru_pie_fecha_realizacion-pru_pie_fecha_estimada))
                                                   from pru_pie
                                                   )