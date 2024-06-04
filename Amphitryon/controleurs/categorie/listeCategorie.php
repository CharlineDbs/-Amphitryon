<?php 
require_once '../../modeles/dao/Param.php';
require_once '../../modeles/dao/DBConnex.php';
require_once '../../modeles/dao/CategorieDAO.php';


print(json_encode(CategorieDAO::listeCategorie()));

?>