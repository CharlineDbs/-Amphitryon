<?php 
require_once '../modeles/dao/Param.php';
require_once '../modeles/dao/DBConnex.php';
require_once '../modeles/dao/TablesDAO.php';

print(json_encode(TablesDAO::listeTables($_POST['dateService'], $_POST['idService'])));
?>