<?php 
require_once '../../modeles/dao/Param.php';
require_once '../../modeles/dao/DBConnex.php';
require_once '../../modeles/dao/PlatsDAO.php';

//$_POST['NOMPLAT'] = "Crème brulée" ; 
//$_POST['DESCRIPTIF'] = "Crème brulée à la vanille de Madagascar";
//$_POST['IDCATEG']  = 3 ;

print(json_encode(PlatsDAO::ajoutePlat($_POST['NOMPLAT'], $_POST['DESCRIPTIF'], $_POST['IDCATEG'])));


?>