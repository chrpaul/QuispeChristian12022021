	<?php

include 'conexion.php';
$id_cliente=$_POST['Id_Cliente'];
$nombre_cliente=$_POST['Nombre_Cliente'];
$direccion=$_POST['Direccion_Cliente'];

$consulta="insert into clientes values('".$id_cliente."','".$nombre_cliente."','".$direccion."')";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);

?>