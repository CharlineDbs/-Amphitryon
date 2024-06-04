<?php 
require_once '../../modeles/dao/Param.php';
require_once '../../modeles/dao/DBConnex.php';
require_once '../../modeles/dao/PlatsDAO.php';



print(json_encode(PlatsDAO::listePlats($_POST['IDCATEG'])));
?>