<?php

include 'conexion.php';
$codigo=$_GET['Id_Producto'];

$consulta ="select * from productos where codigo = '$Id_Producto'";
$resultado = $conexion -> query($consulta);

while($fila=$resultado -> fetch_array()){
	$producto[] = array_map('utf8_encode',$fila);
}

echo json_encode($producto);
$resultado -> close();

?>