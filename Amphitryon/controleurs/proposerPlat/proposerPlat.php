<?php 
require_once '../../modeles/dao/Param.php';
require_once '../../modeles/dao/DBConnex.php';
require_once '../../modeles/dao/ProposerPlatDAO.php';

//$_POST["IDSERVICE"] = 1;
//$_POST["IDPLAT"] = 6;
//$_POST["DATESERVICE"] = "2024-03-08";
//$_POST["QUANTITEPROPOSEE"] = 16;
//$_POST["PRIXVENTE"] = 13.85 ;
//$_POST["QTEDISPO"] = 16;


print(json_encode(ProposerPlatDAO::proposerPlat($_POST["IDSERVICE"],$_POST["DATESERVICE"], $_POST["IDPLAT"], $_POST["QUANTITEPROPOSEE"], $_POST["PRIXVENTE"], $_POST["QTEDISPO"])));




?>