<?php 
require_once '../../modeles/dao/Param.php';
require_once '../../modeles/dao/DBConnex.php';
require_once '../../modeles/dao/ProposerPlatDAO.php';

//$_POST["IDSERVICE"] = 1;
//$_POST["DATESERVICE"] = "2024-01-19" ;


print(json_encode(ProposerPlatDAO::affichePlatAProposer($_POST["IDSERVICE"], $_POST["DATESERVICE"])));


?>