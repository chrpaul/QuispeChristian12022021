<?php

include 'conexion.php';
$consulta ="select detallepedidos.Id_Pedido,productos.Nombre,detallepedidos.cantidad,detallepedidos.Subtotal_Iva_Real,detallepedidos.Subtotal_Iva_Ice,detallepedidos.SubtotalProductos from detallepedidos INNER JOIN productos ON detallepedidos.Id_Producto_p = productos.Id_Producto";

$resultado = $conexion -> query($consulta);

while($fila=$resultado -> fetch_array()){
	$producto[] = array_map('utf8_encode',$fila);
}

echo json_encode($producto);
$resultado -> close();

?>