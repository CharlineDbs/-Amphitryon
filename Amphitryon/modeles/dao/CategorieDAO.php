<?php

class CategorieDAO{


	// On affiche la liste des catégories

    public static function listeCategorie(){
		try{
			$sql = "select * from CATEGORIEPLAT " ;
			$requetePrepa = DBConnex::getInstance()->prepare($sql);
			$requetePrepa->execute();
            $liste = $requetePrepa->fetchAll(PDO::FETCH_ASSOC); 
		}catch(Exception $e){
			$liste = "";
		}
		return $liste;
	}

}