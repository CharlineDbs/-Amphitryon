-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : ven. 09 fév. 2024 à 08:07
-- Version du serveur : 10.5.15-MariaDB-0+deb11u1
-- Version de PHP : 8.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `nolwenn_amphitryon`
--

-- --------------------------------------------------------

--
-- Structure de la table `CATEGORIEPLAT`
--

CREATE TABLE `CATEGORIEPLAT` (
  `IDCATEG` int(11) NOT NULL,
  `LIBELLECATEG` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `CATEGORIEPLAT`
--

INSERT INTO `CATEGORIEPLAT` (`IDCATEG`, `LIBELLECATEG`) VALUES
(1, 'Entrée'),
(2, 'Plat principal'),
(3, 'Dessert');

-- --------------------------------------------------------

--
-- Structure de la table `COMMANDE`
--

CREATE TABLE `COMMANDE` (
  `IDCOMMANDE` int(11) NOT NULL,
  `DATESERVICE` date NOT NULL,
  `IDSERVICE` int(11) NOT NULL,
  `NUMTABLE` int(11) NOT NULL,
  `HEURECOMMANDE` time DEFAULT NULL,
  `ETATCOMMANDE` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `COMMANDE`
--

INSERT INTO `COMMANDE` (`IDCOMMANDE`, `DATESERVICE`, `IDSERVICE`, `NUMTABLE`, `HEURECOMMANDE`, `ETATCOMMANDE`) VALUES
(1, '2024-01-19', 1, 1, '10:47:59', 'Envoyer'),
(2, '2024-02-02', 2, 1, '12:00:00', 'Envoyer');

-- --------------------------------------------------------

--
-- Structure de la table `COMMANDER`
--

CREATE TABLE `COMMANDER` (
  `IDPLAT` int(11) NOT NULL,
  `IDCOMMANDE` int(11) NOT NULL,
  `QTEPLAT` int(11) DEFAULT NULL,
  `INFOCOMPLEMENTAIRE` varchar(50) DEFAULT NULL,
  `ETATPLAT` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `DATESERVICE`
--

CREATE TABLE `DATESERVICE` (
  `DATESERVICE` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `DATESERVICE`
--

INSERT INTO `DATESERVICE` (`DATESERVICE`) VALUES
('2024-01-19'),
('2024-01-20'),
('2024-02-02');

-- --------------------------------------------------------

--
-- Structure de la table `PERSONNEL`
--

CREATE TABLE `PERSONNEL` (
  `IDPERSO` int(11) NOT NULL,
  `IDSTATUT` int(11) NOT NULL,
  `NOM` varchar(32) DEFAULT NULL,
  `PRENOM` varchar(32) DEFAULT NULL,
  `LOGIN` varchar(32) DEFAULT NULL,
  `MDP` varchar(128) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `PERSONNEL`
--

INSERT INTO `PERSONNEL` (`IDPERSO`, `IDSTATUT`, `NOM`, `PRENOM`, `LOGIN`, `MDP`) VALUES
(1, 1, 'Mocaer', 'Nolwenn', 'nmocaer', '81dc9bdb52d04dc20036dbd8313ed055'),
(2, 2, 'Dubos', 'Charline', 'cdubos', '81dc9bdb52d04dc20036dbd8313ed055'),
(3, 3, 'Duprat', 'Hugo', 'hduprat', '81dc9bdb52d04dc20036dbd8313ed055'),
(4, 3, 'Arthur', 'Gaillard', 'agaillard', '81dc9bdb52d04dc20036dbd8313ed055');

-- --------------------------------------------------------

--
-- Structure de la table `PLAT`
--

CREATE TABLE `PLAT` (
  `IDPLAT` int(11) NOT NULL,
  `IDCATEG` int(11) NOT NULL,
  `NOMPLAT` varchar(32) DEFAULT NULL,
  `DESCRIPTIF` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `PLAT`
--

INSERT INTO `PLAT` (`IDPLAT`, `IDCATEG`, `NOMPLAT`, `DESCRIPTIF`) VALUES
(1, 1, 'Salade italienne', 'Tomate, mozzarella, olive, jambon parme'),
(2, 2, 'Entrecôte boeuf', 'Entrecôte de 200g accompagnée de frites'),
(3, 3, 'Mousse au chocolat', 'Mousse au chocolat du chef'),
(4, 1, 'Plateau de charcuterie', 'Jambon Pays, Coppa, Saucisson, Chorizo'),
(5, 1, 'Tartare de saumon et avocat', 'Saumon pêché le matin sur un lit d\'avocat'),
(6, 2, 'Tagliatelle Pesto', 'Tagliatelle Pesto (Basilic, huile d\'olive, pignon)'),
(7, 2, 'Blanquette de veau', 'Veau français accompagné de pommes de terre'),
(8, 3, 'Panacotta', 'Coulis au choix (fruits rouge, caramel, mangue)'),
(9, 3, 'Crème brulée', 'Crème brulée à la vanille de Madagascar');

-- --------------------------------------------------------

--
-- Structure de la table `PROPOSERPLAT`
--

CREATE TABLE `PROPOSERPLAT` (
  `IDSERVICE` int(11) NOT NULL,
  `DATESERVICE` date NOT NULL,
  `IDPLAT` int(11) NOT NULL,
  `QUANTITEPROPOSEE` int(11) DEFAULT NULL,
  `PRIXVENTE` decimal(10,2) DEFAULT NULL,
  `QTEDISPO` int(11) DEFAULT NULL,
  `QTEVENDUE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `SERVICE`
--

CREATE TABLE `SERVICE` (
  `IDSERVICE` int(11) NOT NULL,
  `LIBELLESERVICE` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `SERVICE`
--

INSERT INTO `SERVICE` (`IDSERVICE`, `LIBELLESERVICE`) VALUES
(1, 'Midi'),
(2, 'Soir');

-- --------------------------------------------------------

--
-- Structure de la table `STATUT`
--

CREATE TABLE `STATUT` (
  `IDSTATUT` int(11) NOT NULL,
  `LIBELLESTATUT` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `STATUT`
--

INSERT INTO `STATUT` (`IDSTATUT`, `LIBELLESTATUT`) VALUES
(1, 'Chef de salle'),
(2, 'Chef cuisinier'),
(3, 'Serveur');

-- --------------------------------------------------------

--
-- Structure de la table `TABLES`
--

CREATE TABLE `TABLES` (
  `DATESERVICE` date NOT NULL,
  `IDSERVICE` int(11) NOT NULL,
  `NUMTABLE` int(11) NOT NULL,
  `IDPERSO` int(11) DEFAULT NULL,
  `NBPLACES` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `TABLES`
--

INSERT INTO `TABLES` (`DATESERVICE`, `IDSERVICE`, `NUMTABLE`, `IDPERSO`, `NBPLACES`) VALUES
('2024-01-19', 1, 1, 4, 2),
('2024-01-20', 1, 1, 4, 2),
('2024-02-02', 2, 1, 3, 4);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `CATEGORIEPLAT`
--
ALTER TABLE `CATEGORIEPLAT`
  ADD PRIMARY KEY (`IDCATEG`);

--
-- Index pour la table `COMMANDE`
--
ALTER TABLE `COMMANDE`
  ADD PRIMARY KEY (`IDCOMMANDE`),
  ADD KEY `I_FK_COMMANDE_TABLES` (`DATESERVICE`,`IDSERVICE`,`NUMTABLE`),
  ADD KEY `NUMTABLE` (`NUMTABLE`),
  ADD KEY `IDSERVICE` (`IDSERVICE`);

--
-- Index pour la table `COMMANDER`
--
ALTER TABLE `COMMANDER`
  ADD PRIMARY KEY (`IDPLAT`,`IDCOMMANDE`),
  ADD KEY `I_FK_COMMANDER_PLAT` (`IDPLAT`),
  ADD KEY `I_FK_COMMANDER_COMMANDE` (`IDCOMMANDE`);

--
-- Index pour la table `DATESERVICE`
--
ALTER TABLE `DATESERVICE`
  ADD PRIMARY KEY (`DATESERVICE`);

--
-- Index pour la table `PERSONNEL`
--
ALTER TABLE `PERSONNEL`
  ADD PRIMARY KEY (`IDPERSO`),
  ADD KEY `I_FK_PERSONNEL_STATUT` (`IDSTATUT`);

--
-- Index pour la table `PLAT`
--
ALTER TABLE `PLAT`
  ADD PRIMARY KEY (`IDPLAT`),
  ADD KEY `I_FK_PLAT_CATEGORIEPLAT` (`IDCATEG`);

--
-- Index pour la table `PROPOSERPLAT`
--
ALTER TABLE `PROPOSERPLAT`
  ADD PRIMARY KEY (`IDSERVICE`,`DATESERVICE`,`IDPLAT`),
  ADD KEY `I_FK_PROPOSERPLAT_SERVICE` (`IDSERVICE`),
  ADD KEY `I_FK_PROPOSERPLAT_DATESERVICE` (`DATESERVICE`),
  ADD KEY `I_FK_PROPOSERPLAT_PLAT` (`IDPLAT`);

--
-- Index pour la table `SERVICE`
--
ALTER TABLE `SERVICE`
  ADD PRIMARY KEY (`IDSERVICE`);

--
-- Index pour la table `STATUT`
--
ALTER TABLE `STATUT`
  ADD PRIMARY KEY (`IDSTATUT`);

--
-- Index pour la table `TABLES`
--
ALTER TABLE `TABLES`
  ADD PRIMARY KEY (`DATESERVICE`,`IDSERVICE`,`NUMTABLE`),
  ADD KEY `I_FK_TABLES_SERVICE` (`IDSERVICE`),
  ADD KEY `I_FK_TABLES_DATESERVICE` (`DATESERVICE`),
  ADD KEY `I_FK_TABLES_PERSONNEL` (`IDPERSO`),
  ADD KEY `INDEX NumTable` (`NUMTABLE`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `CATEGORIEPLAT`
--
ALTER TABLE `CATEGORIEPLAT`
  MODIFY `IDCATEG` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `COMMANDE`
--
ALTER TABLE `COMMANDE`
  MODIFY `IDCOMMANDE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `PERSONNEL`
--
ALTER TABLE `PERSONNEL`
  MODIFY `IDPERSO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `PLAT`
--
ALTER TABLE `PLAT`
  MODIFY `IDPLAT` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `SERVICE`
--
ALTER TABLE `SERVICE`
  MODIFY `IDSERVICE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `STATUT`
--
ALTER TABLE `STATUT`
  MODIFY `IDSTATUT` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `TABLES`
--
ALTER TABLE `TABLES`
  MODIFY `NUMTABLE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `COMMANDE`
--
ALTER TABLE `COMMANDE`
  ADD CONSTRAINT `COMMANDE_ibfk_1` FOREIGN KEY (`NUMTABLE`) REFERENCES `TABLES` (`NUMTABLE`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `COMMANDE_ibfk_2` FOREIGN KEY (`DATESERVICE`) REFERENCES `TABLES` (`DATESERVICE`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `COMMANDE_ibfk_3` FOREIGN KEY (`IDSERVICE`) REFERENCES `TABLES` (`IDSERVICE`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `COMMANDER`
--
ALTER TABLE `COMMANDER`
  ADD CONSTRAINT `FK_COMMANDER_COMMANDE` FOREIGN KEY (`IDCOMMANDE`) REFERENCES `COMMANDE` (`IDCOMMANDE`),
  ADD CONSTRAINT `FK_COMMANDER_PLAT` FOREIGN KEY (`IDPLAT`) REFERENCES `PLAT` (`IDPLAT`);

--
-- Contraintes pour la table `PERSONNEL`
--
ALTER TABLE `PERSONNEL`
  ADD CONSTRAINT `FK_PERSONNEL_STATUT` FOREIGN KEY (`IDSTATUT`) REFERENCES `STATUT` (`IDSTATUT`);

--
-- Contraintes pour la table `PLAT`
--
ALTER TABLE `PLAT`
  ADD CONSTRAINT `FK_PLAT_CATEGORIEPLAT` FOREIGN KEY (`IDCATEG`) REFERENCES `CATEGORIEPLAT` (`IDCATEG`);

--
-- Contraintes pour la table `PROPOSERPLAT`
--
ALTER TABLE `PROPOSERPLAT`
  ADD CONSTRAINT `FK_PROPOSERPLAT_DATESERVICE` FOREIGN KEY (`DATESERVICE`) REFERENCES `DATESERVICE` (`DATESERVICE`),
  ADD CONSTRAINT `FK_PROPOSERPLAT_PLAT` FOREIGN KEY (`IDPLAT`) REFERENCES `PLAT` (`IDPLAT`),
  ADD CONSTRAINT `FK_PROPOSERPLAT_SERVICE` FOREIGN KEY (`IDSERVICE`) REFERENCES `SERVICE` (`IDSERVICE`);

--
-- Contraintes pour la table `TABLES`
--
ALTER TABLE `TABLES`
  ADD CONSTRAINT `FK_TABLES_DATESERVICE` FOREIGN KEY (`DATESERVICE`) REFERENCES `DATESERVICE` (`DATESERVICE`),
  ADD CONSTRAINT `FK_TABLES_PERSONNEL` FOREIGN KEY (`IDPERSO`) REFERENCES `PERSONNEL` (`IDPERSO`),
  ADD CONSTRAINT `FK_TABLES_SERVICE` FOREIGN KEY (`IDSERVICE`) REFERENCES `SERVICE` (`IDSERVICE`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
