<?php

include 'conexion.php';
$id_detalle=$_POST['Id_Detalle'];
$id_Pedido_p=$_POST['Id_Pedido_p'];
$id_Producto_p=$_POST['id_Producto_p'];
$cantidad=$_POST['Cantidad'];

$consulta="delete from producto where Id_detalle= '".$id_detalle."'";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);

?>