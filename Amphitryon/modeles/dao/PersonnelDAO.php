<?php
class PersonnelDAO{

	// recupère les informations de l'utilisateur

	public static function authentification($login , $mdp){
		try{
			$sql = "select * from PERSONNEL where LOGIN= :login and MDP = :mdp " ;
			$requetePrepa = DBConnex::getInstance()->prepare($sql);
			$mdp =  md5($mdp);
			$requetePrepa->bindParam("login", $login);
			$requetePrepa->bindParam("mdp", $mdp);
			$requetePrepa->execute();
			$reponse = $requetePrepa->fetch(PDO::FETCH_ASSOC);
		}catch(Exception $e){
			$reponse = "";
		}
		return $reponse;
	}


}

?>