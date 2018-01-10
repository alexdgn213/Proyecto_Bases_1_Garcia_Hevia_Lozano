select f1.fab_nombre as origen ,f2.fab_nombre as destino, AVG(s.sol_cantidad) as promedio
from fabrica f1, fabrica f2, solicitud s
WHERE s.fk_fab_codigo1= f1.fab_codigo AND s.fk_fab_codigo2=f2.fab_codigo
GROUP by origen,destino;