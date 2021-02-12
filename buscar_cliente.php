<?php

include 'conexion.php';
$codigo=$_GET['Id_Cliente'];

$consulta ="select * from clientes where Id_Cliente = '$Id_Cliente'";
$resultado = $conexion -> query($consulta);

while($fila=$resultado -> fetch_array()){
	$producto[] = array_map('utf8_encode',$fila);
}

echo json_encode($producto);
$resultado -> close();

?>