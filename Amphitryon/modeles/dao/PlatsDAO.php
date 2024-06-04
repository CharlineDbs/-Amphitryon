<?php
class PlatsDAO{

    // on affiche tous les plats pour une catégorie passée en param

	public static function listePlats($unIdCateg){
		try{
			$sql = "select * from PLAT WHERE IDCATEG = :idCateg" ;
			$requetePrepa = DBConnex::getInstance()->prepare($sql);
            $requetePrepa->bindParam("idCateg", $unIdCateg);
			$requetePrepa->execute();
            $liste = $requetePrepa->fetchAll(PDO::FETCH_ASSOC); 
		}catch(Exception $e){
			$liste = "";
		}
		return $liste;
	}


    // On ajoute un plat

    public static function ajoutePlat(string $nomPlat, String $descriptif, int $idCateg ){
        try{
            $sql = "insert into PLAT (NOMPLAT, DESCRIPTIF, IDCATEG) values (:nomPlat, :descriptif, :idCateg) ";
            $requetePrepa = DBConnex::getInstance()->prepare($sql);
            $requetePrepa->bindParam("nomPlat", $nomPlat);
            $requetePrepa->bindParam("descriptif", $descriptif);
            $requetePrepa->bindParam("idCateg", $idCateg);
            $requetePrepa->execute();
        }catch(Exception $e){
            $reponse = "";
        }
    }

    // on supprime un plat en fonction de son id

    public static function supprimerPlat(int $idPlat){
        try{
            $sql = "delete from PLAT where IDPLAT = :idPlat";
            $requetePrepa = DBConnex::getInstance()->prepare($sql);
            $requetePrepa->bindParam("idPlat", $idPlat);
            $requetePrepa->execute();
        }catch(Exception $e){
            $reponse = "";
        }
    }


    //  on modifie le nom et le descriptif du plat 

    public static function modifierPlat(int $idPlat, string $nomPlat, String $descriptif){
        try{
            $sql = "update PLAT set NOMPLAT = :nomPlat, DESCRIPTIF = :descriptif where IDPLAT = :idPlat" ;
            $requetePrepa = DBConnex::getInstance()->prepare($sql);
            $requetePrepa->bindParam("nomPlat", $nomPlat);
            $requetePrepa->bindParam("descriptif", $descriptif);
            //$requetePrepa->bindParam("idCateg", $idCateg);
            $requetePrepa->bindParam("idPlat", $idPlat);
            $requetePrepa->execute();
        }catch(Exception $e){
            $reponse = "";
        }
    }

}

?>