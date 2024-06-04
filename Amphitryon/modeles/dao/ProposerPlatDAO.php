<?php
class ProposerPlatDAO{


    // on ajoute dans PROPOSERPLAT un plat pour le service sélectionné

    public static function proposerPlat(int $idService, String $dateService, int $idPlat, int $quantiteProposee, float $prixVente, int $qteDispo){
        try{
            $sql = "Insert into PROPOSERPLAT (IDSERVICE, DATESERVICE, IDPLAT, QUANTITEPROPOSEE, PRIXVENTE, QTEDISPO, QTEVENDUE) 
            values (:idService, :dateService, :idPlat, :quantiteProposee, :prixVente, :qteDispo, 0)";

            $requetePrepa = DBConnex::getInstance()->prepare($sql);

            $requetePrepa->bindParam("idService", $idService);
            $requetePrepa->bindParam("dateService", $dateService);
            $requetePrepa->bindParam("idPlat", $idPlat);
            $requetePrepa->bindParam("quantiteProposee", $quantiteProposee);
            $requetePrepa->bindParam("prixVente", $prixVente);
            $requetePrepa->bindParam("qteDispo", $qteDispo);

            $requetePrepa->execute();
            
        }catch(Exception $e){
            $reponse = "";
        }
    }

    // on modifie le prix et la quanité pour un plat proposé

    public static function modifieProposerPlat(int $idService, String $dateService, int $idPlat, float $prixVente, int $qteProposee){
        try{
            $sql = "Update PROPOSERPLAT set PRIXVENTE = :prixVente, QUANTITEPROPOSEE = :qteProposee, QTEDISPO = :qteProposee
            where IDSERVICE = :idService and DATESERVICE = :dateService and IDPLAT = :idPlat ";

            $requetePrepa = DBConnex::getInstance()->prepare($sql);
            $requetePrepa->bindParam("prixVente", $prixVente);
            $requetePrepa->bindParam("idService", $idService);
            $requetePrepa->bindParam("dateService", $dateService);
            $requetePrepa->bindParam("idPlat", $idPlat);
            $requetePrepa->bindParam("qteProposee", $qteProposee);

            $requetePrepa->execute();
        }catch(Exception $e){
            $reponse = "";
        }
    }

    // on supprime un plat proposé

    public static function nePlusProposer(int $idService, String $dateService, $idPlat){
        try{
            $sql = "delete from PROPOSERPLAT where IDSERVICE = :idService and DATESERVICE = :dateService and IDPLAT = :idPlat ";
            $requetePrepa = DBConnex::getInstance()->prepare($sql);
            $requetePrepa->bindParam("idService", $idService);
            $requetePrepa->bindParam("dateService", $dateService);
            $requetePrepa->bindParam("idPlat", $idPlat);
            $requetePrepa->execute();

        }catch(Exception $e){
            $reponse = "";
        }
    }


    // on affiche tous les plats qui sont proposés pour un service

    public static function afficheProposerPlat($idService, $dateService){
        try{
            $sql = "select PROPOSERPLAT.IDSERVICE, PROPOSERPLAT.DATESERVICE, PROPOSERPLAT.IDPLAT, PLAT.NOMPLAT, PROPOSERPLAT.QTEDISPO, PROPOSERPLAT.QTEVENDUE, 
                    PROPOSERPLAT.QUANTITEPROPOSEE, PROPOSERPLAT.PRIXVENTE FROM PROPOSERPLAT INNER JOIN PLAT ON PLAT.IDPLAT = PROPOSERPLAT.IDPLAT
                    WHERE PROPOSERPLAT.IDSERVICE = :idService AND DATESERVICE = :dateService";
            $requetePrepa = DBConnex::getInstance()->prepare($sql);
            $requetePrepa->bindParam("idService", $idService);
            $requetePrepa->bindParam("dateService", $dateService);
            $requetePrepa->execute();
            $liste = $requetePrepa->fetchAll(PDO::FETCH_ASSOC); 

        }catch(Exception $e){
            $liste = "";
        }
        return $liste;
    }
    

    // on affiche les services pour proposer un plat

    public static function afficheServiceProposerPlat(){
        try{
            $sql = "select IDSERVICE, LIBELLESERVICE, DATESERVICE from DATESERVICE, SERVICE";
            $requetePrepa = DBConnex::getInstance()->prepare($sql);
            $requetePrepa->execute();
            $liste = $requetePrepa->fetchAll(PDO::FETCH_ASSOC); 

        }catch(Exception $e){
            $liste = "";
        }
        return $liste;
    }

    // on affiche tous les plats qui ne sont pas proposés 

    public static function affichePlatAProposer($idService, $dateService){
        try{
            $sql = " Select PLAT.IDPLAT, NOMPLAT FROM PLAT WHERE PLAT.IDPLAT NOT IN (SELECT PROPOSERPLAT.IDPLAT FROM PROPOSERPLAT 
                     WHERE PROPOSERPLAT.IDSERVICE = :idService AND PROPOSERPLAT.DATESERVICE = :dateService) ";
            $requetePrepa = DBConnex::getInstance()->prepare($sql);
            $requetePrepa->bindParam("idService", $idService);
            $requetePrepa->bindParam("dateService", $dateService);
            $requetePrepa->execute();
            $liste = $requetePrepa->fetchAll(PDO::FETCH_ASSOC); 

        }catch(Exception $e){
            $liste = "";
        }
        return $liste;
    }

}

?>