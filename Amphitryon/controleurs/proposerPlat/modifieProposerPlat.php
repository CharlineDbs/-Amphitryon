<?php 
require_once '../../modeles/dao/Param.php';
require_once '../../modeles/dao/DBConnex.php';
require_once '../../modeles/dao/ProposerPlatDAO.php';

//$_POST["IDSERVICE"] = 1;
//$_POST["IDPLAT"] = 13;
//$_POST["DATESERVICE"] = "2024-02-02";
//$_POST["QUANTITEPROPOSEE"] = 30;
//$_POST["PRIXVENTE"] = 11.3;
//$_POST["QTEDISPO"] = 2;

print(json_encode(ProposerPlatDAO::modifieProposerPlat($_POST["IDSERVICE"], $_POST["DATESERVICE"], $_POST["IDPLAT"], $_POST["PRIXVENTE"], $_POST["QUANTITEPROPOSEE"])));


?>