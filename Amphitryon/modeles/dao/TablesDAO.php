<?php
class TablesDAO{

	public static function listeTables($uneDate, $unService){
    try{
        $sql = "select * from TABLES where DATESERVICE = :dateService and SERVICE = :idService";
        $requetePrepa = DBConnex::getInstance()->prepare($sql);
        $requetePrepa->bindParam("dateService", $uneDate);
        $requetePrepa->bindParam("idService", $unService);
        $requetePrepa->execute();
        $liste = $requetePrepa->fetchAll(PDO::FETCH_ASSOC); 
    }catch(Exception $e){
        $liste = "";
    }
    return $liste;
    }

    public static function ajouteTable($uneDate, $unService, $nbPlace){
        try{
            $sql = "insert into TABLES (DATESERVICE, IDSERVICE, NBPLACES) values (:dateService, :idService, :nbPlaces)";
            $requetePrepa = DBConnex::getInstance()->prepare($sql);
            $requetePrepa->bindParam("dateService", $uneDate);
            $requetePrepa->bindParam("idService", $unService);
            $requetePrepa->bindParam("nbPlaces", $nbPlace);
            $requetePrepa->execute();
        }catch(Exception $e){
            return false;
        }
        return true;
    }

    public static function modifieNbPlaces($uneDate, $unService, $numTable, $nbPlace){
        try{
            $sql = "update TABLES set NBPLACES = :nbPlaces where DATESERVICE = :dateService and IDSERVICE = :idService and NUMTABLE = :numTable";
            $requetePrepa = DBConnex::getInstance()->prepare($sql);
            $requetePrepa->bindParam("dateService", $uneDate);
            $requetePrepa->bindParam("idService", $unService);
            $requetePrepa->bindParam("numTable", $numTable);
            $requetePrepa->bindParam("nbPlaces", $nbPlace);
            $requetePrepa->execute();
        }catch(Exception $e){
            return false;
        }
        return true;
    }

    public static function supprimeTable($uneDate, $unService, $numTable){
        try{
            $sql = "delete from TABLES where DATESERVICE = :dateService and IDSERVICE = :idService and NUMTABLE = :numTable";
            $requetePrepa = DBConnex::getInstance()->prepare($sql);
            $requetePrepa->bindParam("dateService", $uneDate);
            $requetePrepa->bindParam("idService", $unService);
            $requetePrepa->bindParam("numTable", $numTable);
            $requetePrepa->execute();
        }catch(Exception $e){
            return false;
        }
        return true;
    }

    public static function affecteTableServeur($uneDate, $unService, $numTable, $idPerso){
        try{
            $sql = "update TABLES set IDPERSO = :idPerso where DATESERVICE = :dateService and IDSERVICE = :idService and NUMTABLE = :numTable";
            $requetePrepa = DBConnex::getInstance()->prepare($sql);
            $requetePrepa->bindParam("dateService", $uneDate);
            $requetePrepa->bindParam("idService", $unService);
            $requetePrepa->bindParam("numTable", $numTable);
            $requetePrepa->bindParam("idPerso", $idPerso);
            $requetePrepa->execute();
        }catch(Exception $e){
            return false;
        }
        return true;
    }

    public static function supprimeAffectation($uneDate, $unService, $numTable){
        try{
            $sql = "update TABLES set IDPERSO = null where DATESERVICE = :dateService and IDSERVICE = :idService and NUMTABLE = :numTable";
            $requetePrepa = DBConnex::getInstance()->prepare($sql);
            $requetePrepa->bindParam("dateService", $uneDate);
            $requetePrepa->bindParam("idService", $unService);
            $requetePrepa->bindParam("numTable", $numTable);
            $requetePrepa->execute();
        }catch(Exception $e){
            return false;
        }
        return true;
    }

    public static afficheAffectation($uneDate, $unService, $numTable){
        try{
            $sql = "select IDPERSO from TABLES where DATESERVICE = :dateService and IDSERVICE = :idService and NUMTABLE = :numTable";
            $requetePrepa = DBConnex::getInstance()->prepare($sql);
            $requetePrepa->bindParam("dateService", $uneDate);
            $requetePrepa->bindParam("idService", $unService);
            $requetePrepa->bindParam("numTable", $numTable);
            $requetePrepa->execute();
        }catch(Exception $e){
            return false;
        }
        return true;
    }


}

?>