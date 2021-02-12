<?php

include 'conexion.php';
$consulta ="select pedidos.Id_Pedido,clientes.Nombre_Cliente,pedidos.Total_Iva.pedidos.Total_Ice,pedidos.total from pedidos INNER JOIN clientes ON pedidos.Id_Cliente = clientes.Id_Cliente";

$resultado = $conexion -> query($consulta);

while($fila=$resultado -> fetch_array()){
	$producto[] = array_map('utf8_encode',$fila);
}

echo json_encode($producto);
$resultado -> close();

?>