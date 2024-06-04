<?php 
require_once '../modeles/dao/Param.php';
require_once '../modeles/dao/DBConnex.php';
require_once '../modeles/dao/PersonnelDAO.php';



print(json_encode(PersonnelDAO::authentification($_POST['LOGIN'], $_POST['MDP'])));
?>

