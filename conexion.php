<?php

$hostname='localhost';
$database='lacigarra';
$username='root';
$password='';
		
$conexion=new mysqli($hostname,$username,$password,$database);
if($conexion->connect_errno){
		echo "lo sentimos, el sitio web está experimentado problemas";
}

?>