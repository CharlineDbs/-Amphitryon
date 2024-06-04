<?php 
require_once '../../modeles/dao/Param.php';
require_once '../../modeles/dao/DBConnex.php';
require_once '../../modeles/dao/ProposerPlatDAO.php';

print(json_encode(ProposerPlatDAO::afficheServiceProposerPlat()));

?>