Select AVG(sc.Aeronaves) as aeronaves , AVG(sc.Piezas) as piezas
from
(select to_char(a1.aer_fecha_compra,'YYYY') as año, to_char(a1.aer_fecha_compra,'MM') as mes,
(Select count(*) from aeronave a2 where to_char(a2.aer_fecha_compra,'YYYY')=to_char(a1.aer_fecha_compra,'YYYY')
AND to_char(a2.aer_fecha_compra,'MM')=to_char(a1.aer_fecha_compra,'MM')) as Aeronaves,
(Select count(*) from pieza p where to_char(p.pie_fecha_entregada,'YYYY')=to_char(a1.aer_fecha_compra,'YYYY')
AND to_char(p.pie_fecha_entregada,'MM')=to_char(a1.aer_fecha_compra,'MM')) as Piezas
FROM aeronave a1
UNION
select to_char(p1.pie_fecha_entregada,'YYYY') as año, to_char(p1.pie_fecha_entregada,'MM') as mes,
(Select count(*) from aeronave a2 where to_char(a2.aer_fecha_compra,'YYYY')=to_char(p1.pie_fecha_entregada,'YYYY')) as Aeronaves,
(Select count(*) from pieza p2 where to_char(p2.pie_fecha_entregada,'YYYY')=to_char(p1.pie_fecha_entregada,'YYYY')) as Piezas
FROM pieza p1
Order by año) as sc