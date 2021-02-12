<?php

include 'conexion.php';
$id_producto=$_POST['id_producto'];
$nombre=$_POST['Nombre'];
$precio=$_POST['Precio'];


$consulta="insert into producto values('".$id_producto."','".$nombre."','".$precio."')";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);

?>