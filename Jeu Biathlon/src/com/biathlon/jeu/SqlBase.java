package com.biathlon.jeu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.biathlon.action.Biathlete;

public class SqlBase {

	public static Connection connection;

	/***** CONSTRUCTEUR *****/
	public SqlBase() {
		//On fait la connection avec la base de donnée MariaDB
		try {
			connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/jeu_biathlon_test?user=root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet requete(String requete) {
		System.out.println(requete);
		try {
			//Création du statement pour faire les requetes
			Statement statement = connection.createStatement();
			ResultSet output_query = statement.executeQuery(requete);
			return output_query;
		} catch (SQLException e1) {e1.printStackTrace();}
		return null;
	}

	public void initDataBase() {

		//Créationde toutes les tables
		ResultSet drop_table_date = requete("DROP TABLE IF EXISTS dates");
		ResultSet create_table_date = requete(""
				+ "CREATE TABLE dates ("
				+ "id_date INT PRIMARY KEY NOT NULL AUTO_INCREMENT,"
				+ "jour_semaine varchar(50),"
				+ "jour varchar(4),"
				+ "mois varchar(4));");

		ResultSet drop_table_pays = requete("DROP TABLE IF EXISTS pays");
		ResultSet create_table_pays = requete(""
				+ "CREATE TABLE pays ("
				+ "id_pays INT PRIMARY KEY NOT NULL,"
				+ "libelle_pays Varchar(50),"
				+ "drapeau_grand_file varchar(200),"
				+ "drapeau_icone_file varchar(200)"
				+ "	);"
				);	

		ResultSet drop_table_type_course = requete("DROP TABLE IF EXISTS typecourses");
		ResultSet create_table_type_course = requete(""
				+ "CREATE TABLE typecourses ("
				+ "id_type_course INT PRIMARY KEY,"
				+ "libelle_type_course Varchar(50),"
				+ "dist_type_course varchar(50),"
				+ "type_tir varchar(50)"
				+ "	);"
				);	

		ResultSet drop_table_participants = requete("DROP TABLE IF EXISTS participants");
		ResultSet create_table_participants = requete(""
				+ "CREATE TABLE participants ("
				+ "id_participant INT PRIMARY KEY NOT NULL AUTO_INCREMENT,"
				+ "id_course INT,"
				+ "classement INT,"
				+ "temps INT,"//a stocker en miliseconde
				+ "id_bareme INT"
				+ "	);"
				);	

		ResultSet drop_table_membres = requete("DROP TABLE IF EXISTS membres");
		ResultSet create_table_membres = requete(""
				+ "CREATE TABLE membres ("
				+ "id_biathlete_carriere INT,"
				+ "id_participant INT,"
				+ "nb_pioche INT,"
				+ "nb_fautes INT"
				+ ");"
				);	

		ResultSet drop_table_joueur = requete("DROP TABLE IF EXISTS joueurs");
		ResultSet create_table_joueur = requete(""
				+ "CREATE TABLE joueurs ("
				+ "id_joueur INT PRIMARY KEY NOT NULL AUTO_INCREMENT,"
				+ "nom_joueur Varchar(50),"
				+ "id_equipe INT,"
				+ "id_biathlete_homme INT,"
				+ "id_biathlete_femme INT,"
				+ "id_date INT,"
				+ "annee INT"
				+ "	);"
				);	

		ResultSet drop_table_equipes = requete("DROP TABLE IF EXISTS equipes");
		ResultSet create_table_equipes = requete(""
				+ "CREATE TABLE equipes ("
				+ "id_equipe INT PRIMARY KEY NOT NULL,"
				+ "libelle_equipe Varchar(50),"
				+ "id_pays INT"
				+ "	);"
				);	

		ResultSet drop_table_courses = requete("DROP TABLE IF EXISTS courses");
		ResultSet create_table_courses = requete(""
				+ "CREATE TABLE courses ("
				+ "id_course INT PRIMARY KEY NOT NULL AUTO_INCREMENT,"
				+ "id_joueur INT,"
				+ "annee INT,"
				+ "id_type_course INT,"
				+ "id_compet INT,"
				+ "id_date_course INT,"
				+ "rang_saison_compet INT,"
				+ "statut_course Varchar(50)"
				+ "	);"
				);	

		ResultSet drop_table_competitions = requete("DROP TABLE IF EXISTS competitions");
		ResultSet create_table_competitions = requete(""
				+ "CREATE TABLE competitions ("
				+ "id_compet INT PRIMARY KEY NOT NULL AUTO_INCREMENT,"
				+ "lieu_compet Varchar(50),"
				+ "id_pays INT,"
				+ "background_file varchar(200)"
				+ "	);"
				);	

		ResultSet drop_table_carriere_biathlete = requete("DROP TABLE IF EXISTS biathletescarriere");
		ResultSet create_table_carriere_biathlete = requete(
				"CREATE TABLE biathletescarriere ("
						+ "id_biathlete_carriere INT PRIMARY KEY NOT NULL AUTO_INCREMENT ,"
						+ "id_joueur INT," 
						+ "id_biathlete INT," 
						+ "statut_biathlete VARCHAR(50),"
						+ "END INT,"
						+ "ACC INT,"
						+ "COU INT,"
						+ "DEB INT,"
						+ "VIT INT,"
						+ "SKI INT,"
						+ "REC INT,"
						+ "REG INT,"
						+ "POT INT,"
						+ "REN INT"
						+ "	);"
				);

		ResultSet drop_table_biathlete = requete("DROP TABLE IF EXISTS biathletes");
		ResultSet create_table_biathlete = requete(
				"CREATE TABLE biathletes ("
						+ "id_biathlete INT PRIMARY KEY NOT NULL AUTO_INCREMENT,"
						+ "nom_biathlete VARCHAR(50)," 
						+ "prenom_biathlete VARCHAR(50)," 
						+ "libelle_biathlete VARCHAR(50)," 
						+ "id_equipe INT,"
						+ "annee_biathlete INT,"
						+ "sexe_biathlete VARCHAR(1),"
						+ "statut_biathlete VARCHAR(50),"
						+ "END INT,"
						+ "ACC INT,"
						+ "COU INT,"
						+ "DEB INT,"
						+ "VIT INT,"
						+ "SKI INT,"
						+ "REC INT,"
						+ "REG INT,"
						+ "POT INT,"
						+ "REN INT"
						+ "	);"
				);


		ResultSet drop_table_bareme = requete("DROP TABLE IF EXISTS bareme");
		ResultSet create_table_bareme = requete(""
				+ "CREATE TABLE bareme ("
				+ "id_bareme INT PRIMARY KEY NOT NULL,"
				+ "point INT"
				+ "	);"
				);	



		/**************** INSERT ****************/		
		ResultSet insert_dates = requete(""
				+ "INSERT INTO dates (jour_semaine , jour, mois) "
				+ "VALUES "
				+ " ('Jeudi', '12' , '11'),"

				+ " ('Samedi', '30' , '11'),"
				+ " ('Dimanche', '01' , '12'),"
				+ " ('Mercredi', '04' , '12'),"
				+ " ('Jeudi', '05' , '12'),"
				+ " ('Samedi', '07' , '12'),"
				+ " ('Dimanche', '08' , '12'),"
				//2
				+ " ('Vendredi', '13' , '12'),"
				+ " ('Samedi', '14' , '12'),"
				+ " ('Dimanche', '15' , '12'),"
				//3
				+ " ('Jeudi', '19' , '12'),"
				+ " ('Vendredi', '20' , '12'),"
				+ " ('Samedi', '21' , '12'),"
				+ " ('Dimanche', '22' , '12'),"
				//4
				+ " ('Jeudi', '09' , '01'),"
				+ " ('Vendredi', '10' , '01'),"
				+ " ('Samedi', '11' , '01'),"
				+ " ('Dimanche', '12' , '01'),"
				//5
				+ " ('Mercredi', '15' , '01'),"
				+ " ('Jeudi', '16' , '01'),"
				+ " ('Vendredi', '17' , '01'),"
				+ " ('Samedi', '18' , '01'),"
				+ " ('Dimanche', '19' , '01'),"
				//6
				+ " ('Jeudi', '23' , '01'),"
				+ " ('Vendredi', '24' , '01'),"
				+ " ('Samedi', '25' , '01'),"
				+ " ('Dimanche', '26' , '01'),"
				//7 WC
				+ " ('Jeudi', '13' , '02'),"
				+ " ('Vendredi', '14' , '02'),"
				+ " ('Samedi', '15' , '02'),"
				+ " ('Dimanche', '16' , '02'),"
				+ " ('Mardi', '18' , '02'),"
				+ " ('Mercredi', '19' , '02'),"
				+ " ('Jeudi', '20' , '02'),"
				+ " ('Samedi', '22' , '02'),"
				+ " ('Dimanche', '23' , '02'),"
				//8
				+ " ('Jeudi', '05' , '03'),"
				+ " ('Vendredi', '06' , '03'),"
				+ " ('Samedi', '07' , '03'),"
				+ " ('Dimanche', '08' , '03'),"
				//9
				+ " ('Jeudi', '12' , '03'),"
				+ " ('Vendredi', '13' , '03'),"
				+ " ('Samedi', '14' , '03'),"
				+ " ('Dimanche', '15' , '03'),"
				//10
				+ " ('Vendredi', '20' , '03'),"
				+ " ('Samedi', '21' , '03'),"
				+ " ('Dimanche', '22' , '03');"
				);


		ResultSet insert_type_course = requete(""
				+ "INSERT INTO typecourses "
				+ "(id_type_course, libelle_type_course, dist_type_course, type_tir) "
				+ "VALUES "
				+ " (1,'Relais mixte', '6/6/7,5/7,5', 'relais'),"
				+ " (2,'Relais mixte simple', '6/7,5/6/7,6', 'relais'),"
				+ " (3,'Relais homme', '7,5/7,5/7,5/7,5', 'relais'),"
				+ " (4,'Relais femme', '6/6/6/6', 'relais'),"
				+ " (5,'Sprint homme', '10', 'classique'),"
				+ " (6,'Sprint femme', '7,5', 'classique'),"
				+ " (7,'Poursuite homme', '12,5', 'classique'),"
				+ " (8,'Poursuite femme', '10', 'classique'),"
				+ " (9,'Mass Start homme', '15', 'classique'),"
				+ " (10,'Mass Start femme', '12,5', 'classique'),"
				+ " (11,'Individuel homme', '20', 'classique'),"
				+ " (12,'Individuel femme', '15', 'classique')"
				+ ";"
				);

		ResultSet insert_pays = requete(""
				+ "INSERT INTO pays (id_pays, libelle_pays, drapeau_grand_file, drapeau_icone_file) "
				+ "VALUES (1, 'USA', '/images/drapeau/grand/usa.png', '/images/drapeau/icone/usa.png'),"
				+ "(2, 'Ukraine', '/images/drapeau/grand/ukraine.png', '/images/drapeau/icone/ukraine.png'),"
				+ "(3, 'Switzerland', '/images/drapeau/grand/suisse.png', '/images/drapeau/icone/suisse.png'),"
				+ "(4, 'Sweden', '/images/drapeau/grand/suede.png', '/images/drapeau/icone/suede.png'),"
				+ "(5, 'Slovenia', '/images/drapeau/grand/slovenie.png', '/images/drapeau/icone/slovenie.png'),"
				+ "(6, 'Slovakia', '/images/drapeau/grand/slovaquie.png', '/images/drapeau/icone/slovaquie.png'),"
				+ "(7, 'Russia', '/images/drapeau/grand/russie.png', '/images/drapeau/icone/russie.png'),"
				+ "(8, 'Romania', '/images/drapeau/grand/roumanie.png', '/images/drapeau/icone/roumanie.png'),"
				+ "(9, 'Poland', '/images/drapeau/grand/pologne.png', '/images/drapeau/icone/pologne.png'),"
				+ "(10, 'Norway', '/images/drapeau/grand/norvege.png', '/images/drapeau/icone/norvege.png'),"
				+ "(11, 'Lithuania', '/images/drapeau/grand/lituanie.png', '/images/drapeau/icone/lituanie.png'),"
				+ "(12, 'Latvia', '/images/drapeau/grand/lettonie.png', '/images/drapeau/icone/lettonie.png'),"
				+ "(13, 'Kazakhstan', '/images/drapeau/grand/kazakhstan.png', '/images/drapeau/icone/kazakhstan.png'),"
				+ "(14, 'Japan', '/images/drapeau/grand/japon.png', '/images/drapeau/icone/japon.png'),"
				+ "(15, 'Italy', '/images/drapeau/grand/italie.png', '/images/drapeau/icone/italie.png'),"
				+ "(16, 'Germany', '/images/drapeau/grand/allemagne.png', '/images/drapeau/icone/allemagne.png'),"
				+ "(17, 'France', '/images/drapeau/grand/france.png', '/images/drapeau/icone/france.png'),"
				+ "(18, 'Finland', '/images/drapeau/grand/finlande.png', '/images/drapeau/icone/finlande.png'),"
				+ "(19, 'Czech Republic', '/images/drapeau/grand/reptcheque.png', '/images/drapeau/icone/reptcheque.png'),"
				+ "(20, 'Canada', '/images/drapeau/grand/canada.png', '/images/drapeau/icone/canada.png'),"
				+ "(21, 'Bulgaria', '/images/drapeau/grand/bulgarie.png', '/images/drapeau/icone/bulgarie.png'),"
				+ "(22, 'Belarus', '/images/drapeau/grand/belarus.png', '/images/drapeau/icone/belarus.png'),"
				+ "(23, 'Austria', '/images/drapeau/grand/autriche.png', '/images/drapeau/icone/autriche.png'),"
				+ "(24, 'Spain', '/images/drapeau/grand/espagne.png', '/images/drapeau/icone/espagne.png'),"
				+ "(25, 'Serbia', '/images/drapeau/grand/serbie.png', '/images/drapeau/icone/serbie.png'),"
				+ "(26, 'Moldova', '/images/drapeau/grand/moldavie.png', '/images/drapeau/icone/moldavie.png'),"
				+ "(27, 'Korea Republic', '/images/drapeau/grand/correedusud.png', '/images/drapeau/icone/correedusud.png'),"
				+ "(28, 'Hungary', '/images/drapeau/grand/hongrie.png', '/images/drapeau/icone/hongrie.png'),"
				+ "(29, 'Greece', '/images/drapeau/grand/grece.png', '/images/drapeau/icone/grece.png'),"
				+ "(30, 'FYR Macedonia', '/images/drapeau/grand/macedoine.png', '/images/drapeau/icone/macedoine.png'),"
				+ "(31, 'Estonia', '/images/drapeau/grand/estonie.png', '/images/drapeau/icone/estonie.png'),"
				+ "(32, 'China PR', '/images/drapeau/grand/chine.png', '/images/drapeau/icone/chine.png'),"
				+ "(33, 'Belgium', '/images/drapeau/grand/belgique.png', '/images/drapeau/icone/belgique.png');"
				);

		ResultSet insert_bareme = requete(
				"INSERT INTO bareme (id_bareme, point) VALUES (1, 60), (2, 54), (3, 48), (4, 43), (5, 40), (6, 38), (7, 36), (8, 34), (9, 32), (10, 31), (11, 30), (12, 29), (13, 28), (14, 27), (15, 26), (16, 25), (17, 24), (18, 23), (19, 22), (20, 21), (21, 20), (22, 19), (23, 18), (24, 17), (25, 16), (26, 15), (27, 14), (28, 13), (29, 12), (30, 11), (31, 10), (32, 9), (33, 8), (34, 7), (35, 6), (36, 5), (37, 4), (38, 3), (39, 2), (40, 1) ,(100, 0);"
				);

		ResultSet insert_equipe = requete("INSERT INTO equipes (id_equipe, libelle_equipe, id_pays) "
				+ "VALUES (1, 'USA', 1),"
				+" (2, 'Ukraine', 2),"
				+" (3, 'Switzerland', 3),"
				+"(4, 'Sweden', 4),"
				+"(5, 'Slovenia', 5),"
				+"(6, 'Slovakia', 6),"
				+"(7, 'Russia', 7),"
				+"(8, 'Romania', 8),"
				+"(9, 'Poland', 9),"
				+"(10, 'Norway', 10),"
				+"(11, 'Lithuania', 11),"
				+"(12, 'Latvia', 12),"
				+"(13, 'Kazakhstan', 13),"
				+"(14, 'Japan', 14),"
				+"(15, 'Italy', 15),"
				+" (16, 'Germany', 16),"
				+"(17, 'France', 17),"
				+"(18, 'Finland', 18),"
				+"(19, 'Czech Republic', 19),"
				+"(20, 'Canada', 20),"
				+"(21, 'Bulgaria', 21),"
				+"(22, 'Belarus', 22),"
				+"(23, 'Austria', 23),"
				+"(24, 'Spain', 24),"
				+"(25, 'Serbia', 25),"
				+"(26, 'Moldova', 26),"
				+"(27, 'Korea Republic', 27),"
				+"(28, 'Hungary', 28),"
				+"(29, 'Greece', 29),"
				+"(30, 'FYR Macedonia', 30),"
				+"(31, 'Estonia', 31),"
				+"(32, 'China PR', 32),"
				+"(33, 'Belgium', 33);"
				);

		ResultSet insert_compet = requete("INSERT INTO competitions (id_compet, lieu_compet, id_pays, background_file) VALUES (1, 'Oestersund', 4, '/images/background/oestersund.png'), (2, 'Hochfilzen', 23, '/images/background/hochfilzen.png'), (3, 'Le Grand Bornand', 17, '/images/background/legrandbornand.png'), (4, 'Oberhof', 16, '/images/background/oberhof.png'), (5, 'Ruhpolding', 16, '/images/background/ruhpolding.png'), (6, 'Pokljuka', 5, '/images/background/pokljuka.png'), (7, 'Antholz', 15, '/images/background/antholz.png'), (8, 'Nove Mesto', 19, '/images/background/novemesto.png'), (9, 'Kontiolathi', 18, '/images/background/kontiolathi.png'), (10, 'Oslo', 10, '/images/background/oslo.png');"
				);


		ResultSet insert_biathletes = requete("INSERT INTO biathletes "
				+"(id_biathlete, nom_biathlete, prenom_biathlete, libelle_biathlete, id_equipe, annee_biathlete, sexe_biathlete, statut_biathlete, END, ACC, COU, DEB, VIT, SKI, REC, REG, POT, REN) "
				+ " VALUES (1, 'ANDERSEN', 'Aleksander Fjeld', 'A.ANDERSEN', 10, 1990, 'H', 'Actif', 80, 80, 80, 82, 80, 60, 80, 80, 7, 2),"
				+"(2, 'ANEV', 'Krasimir', 'K.ANEV', 21, 1990, 'H', 'Actif', 80, 80, 91, 84, 80, 73, 80, 80, 5, 4),"
				+"(3, 'ANGELIS', 'Apostolos', 'A.ANGELIS', 29, 1990, 'H', 'Actif', 80, 80, 73, 50, 80, 61, 80, 80, 6, 1),"
				+"(4, 'BABIKOV', 'Anton', 'A.BABIKOV', 7, 1990, 'H', 'Actif', 80, 80, 85, 83, 80, 70, 80, 80, 2, 4),"
				+"(5, 'BARTKO', 'Simon', 'S.BARTKO', 6, 1990, 'H', 'Actif', 80, 80, 78, 57, 80, 61, 80, 80, 6, 1),"
				+"(6, 'BAUER', 'Klemen', 'K.BAUER', 5, 1990, 'H', 'Actif', 80, 80, 78, 76, 80, 79, 80, 80, 5, 3),"
				+"(7, 'BIRKELAND', 'Lars Helge', 'L.BIRKELAND', 10, 1990, 'H', 'Actif', 80, 80, 85, 90, 80, 72, 80, 80, 3, 4),"
				+"(8, 'BJOENTEGAARD', 'Erlend', 'E.BJOENTEGAARD', 10, 1990, 'H', 'Actif', 80, 80, 87, 80, 80, 82, 80, 80, 6, 6),"
				+"(9, 'BOCHARNIKOV', 'Sergey', 'S.BOCHARNIKOV', 22, 1990, 'H', 'Actif', 80, 80, 86, 82, 80, 66, 80, 80, 7, 2),"
				+"(10, 'BOE', 'Johannes Thingnes', 'J.T.BOE', 10, 1990, 'H', 'Actif', 80, 80, 89, 89, 80, 97, 80, 80, 7, 8),"
				+"(11, 'BOE', 'Tarjei', 'T.BOE', 10, 1990, 'H', 'Actif', 80, 80, 94, 82, 80, 90, 80, 80, 7, 8),"
				+"(12, 'BORMOLINI', 'Thomas', 'T.BORMOLINI', 15, 1990, 'H', 'Actif', 80, 80, 85, 79, 80, 67, 80, 80, 4, 3),"
				+"(13, 'BROWN', 'Jake', 'J.BROWN', 1, 1990, 'H', 'Actif', 80, 80, 70, 79, 80, 65, 80, 80, 6, 1),"
				+"(14, 'BURKHALTER', 'Joscha', 'J.BURKHALTER', 3, 1990, 'H', 'Actif', 80, 80, 82, 80, 80, 59, 80, 80, 5, 1),"
				+"(15, 'BURNOTTE', 'Jules', 'J.BURNOTTE', 20, 1990, 'H', 'Actif', 80, 80, 75, 72, 80, 68, 80, 80, 5, 2),"
				+"(16, 'BUTA', 'George', 'G.BUTA', 8, 1990, 'H', 'Actif', 80, 80, 87, 88, 80, 74, 80, 80, 4, 1),"
				+"(17, 'CHENG', 'Fangming', 'F.CHENG', 32, 1990, 'H', 'Actif', 80, 80, 83, 74, 80, 66, 80, 80, 4, 1),"
				+"(18, 'CHRISTIANSEN', 'Vetle Sjaastad', 'V.CHRISTIANSEN', 10, 1990, 'H', 'Actif', 80, 80, 89, 79, 80, 88, 80, 80, 7, 7),"
				+"(19, 'CLAUDE', 'Fabien', 'F.CLAUDE', 17, 1990, 'H', 'Actif', 80, 80, 80, 79, 80, 80, 80, 80, 6, 5),"
				+"(20, 'CLAUDE', 'Florent', 'F.CLAUDE', 33, 1990, 'H', 'Actif', 80, 80, 83, 86, 80, 72, 80, 80, 5, 5),"
				+"(21, 'DALE', 'Johannes', 'J.DALE', 10, 1990, 'H', 'Actif', 80, 80, 93, 76, 80, 89, 80, 80, 8, 7),"
				+"(22, 'DESTHIEUX', 'Simon', 'S.DESTHIEUX', 17, 1990, 'H', 'Actif', 80, 80, 88, 81, 80, 85, 80, 80, 7, 7),"
				+"(23, 'DOHERTY', 'Sean', 'S.DOHERTY', 1, 1990, 'H', 'Actif', 80, 80, 80, 79, 80, 73, 80, 80, 7, 5),"
				+"(24, 'DOLDER', 'Mario', 'M.DOLDER', 3, 1990, 'H', 'Actif', 80, 80, 82, 70, 80, 68, 80, 80, 4, 3),"
				+"(25, 'DOLL', 'Benedikt', 'B.DOLL', 16, 1990, 'H', 'Actif', 80, 80, 81, 76, 80, 90, 80, 80, 7, 7),"
				+"(26, 'DOMBROVSKI', 'Karol', 'K.DOMBROVSKI', 11, 1990, 'H', 'Actif', 80, 80, 82, 81, 80, 64, 80, 80, 5, 2),"
				+"(27, 'DOVZAN', 'Miha', 'M.DOVZAN', 5, 1990, 'H', 'Actif', 80, 80, 85, 82, 80, 74, 80, 80, 4, 2),"
				+"(28, 'DUDCHENKO', 'Anton', 'A.DUDCHENKO', 2, 1990, 'H', 'Actif', 80, 80, 90, 82, 80, 75, 80, 80, 4, 1),"
				+"(29, 'EBERHARD', 'Julian', 'J.EBERHARD', 23, 1990, 'H', 'Actif', 80, 80, 70, 78, 80, 91, 80, 80, 7, 7),"
				+"(30, 'EBERHARD', 'Tobias', 'T.EBERHARD', 23, 1990, 'H', 'Actif', 80, 80, 78, 65, 80, 73, 80, 80, 2, 4),"
				+"(31, 'EDER', 'Simon', 'S.EDER', 23, 1990, 'H', 'Actif', 80, 80, 90, 82, 80, 70, 80, 80, 7, 7),"
				+"(32, 'ELISEEV', 'Matvey', 'M.ELISEEV', 7, 1990, 'H', 'Actif', 80, 80, 82, 86, 80, 76, 80, 80, 6, 6),"
				+"(33, 'ERMITS', 'Kalev', 'K.ERMITS', 31, 1990, 'H', 'Actif', 80, 80, 76, 69, 80, 68, 80, 80, 5, 2),"
				+"(34, 'FAK', 'Jakov', 'J.FAK', 5, 1990, 'H', 'Actif', 80, 80, 91, 83, 80, 68, 80, 80, 5, 6),"
				+"(35, 'FEMLING', 'Peppe', 'P.FEMLING', 4, 1990, 'H', 'Actif', 80, 80, 75, 83, 80, 65, 80, 80, 4, 5),"
				+"(36, 'FILLON MAILLET', 'Quentin', 'Q.FILLON MAILLET', 17, 1990, 'H', 'Actif', 80, 80, 87, 85, 80, 92, 80, 80, 7, 8),"
				+"(37, 'FINELLO', 'Jeremy', 'J.FINELLO', 3, 1990, 'H', 'Actif', 80, 80, 80, 73, 80, 60, 80, 80, 4, 4),"
				+"(38, 'FOURCADE', 'Martin', 'M.FOURCADE', 17, 1990, 'H', 'Actif', 80, 80, 95, 88, 80, 88, 80, 80, 7, 8),"
				+"(39, 'GARANICHEV', 'Evgeniy', 'E.GARANICHEV', 7, 1990, 'H', 'Actif', 80, 80, 91, 82, 80, 72, 80, 80, 4, 6),"
				+"(40, 'GIACOMEL', 'Tommaso', 'T.GIACOMEL', 15, 1990, 'H', 'Actif', 80, 80, 84, 88, 80, 65, 80, 80, 6, 2),"
				+"(41, 'GJESBAKK', 'Fredrik', 'F.GJESBAKK', 10, 1990, 'H', 'Actif', 80, 80, 82, 77, 80, 68, 80, 80, 4, 1),"
				+"(42, 'GOW', 'Christian', 'C.GOW', 20, 1990, 'H', 'Actif', 80, 80, 81, 79, 80, 70, 80, 80, 5, 3),"
				+"(43, 'GOW', 'Scott', 'S.GOW', 20, 1990, 'H', 'Actif', 80, 80, 86, 72, 80, 68, 80, 80, 5, 3),"
				+"(44, 'GUIGONNAT', 'Antonin', 'A.GUIGONNAT', 17, 1990, 'H', 'Actif', 80, 80, 85, 81, 80, 73, 80, 80, 6, 5),"
				+"(45, 'GUZIK', 'Grzegorz', 'G.GUZIK', 9, 1990, 'H', 'Actif', 80, 80, 79, 73, 80, 64, 80, 80, 4, 1),"
				+"(46, 'HARJULA', 'Tuomas', 'T.HARJULA', 18, 1990, 'H', 'Actif', 80, 80, 91, 71, 80, 60, 80, 80, 4, 1),"
				+"(47, 'HASILLA', 'Tomas', 'T.HASILLA', 6, 1990, 'H', 'Actif', 80, 80, 77, 59, 80, 70, 80, 80, 4, 1),"
				+"(48, 'HELDNA', 'Robert', 'R.HELDNA', 31, 1990, 'H', 'Actif', 80, 80, 81, 84, 80, 69, 80, 80, 4, 1),"
				+"(49, 'HIIDENSALO', 'Olli', 'O.HIIDENSALO', 18, 1990, 'H', 'Actif', 80, 80, 79, 62, 80, 64, 80, 80, 4, 1),"
				+"(50, 'HOFER', 'Lukas', 'L.HOFER', 15, 1990, 'H', 'Actif', 80, 80, 82, 73, 80, 84, 80, 80, 6, 7),"
				+"(51, 'HORN', 'Philipp', 'P.HORN', 16, 1990, 'H', 'Actif', 80, 80, 71, 81, 80, 80, 80, 80, 6, 6),"
				+"(52, 'HOWE', 'Alex', 'A.HOWE', 1, 1990, 'H', 'Actif', 80, 80, 68, 72, 80, 73, 80, 80, 4, 1),"
				+"(53, 'ILIEV', 'Vladimir', 'V.ILIEV', 21, 1990, 'H', 'Actif', 80, 80, 78, 69, 80, 77, 80, 80, 4, 4),"
				+"(54, 'JACQUELIN', 'Emilien', 'E.JACQUELIN', 17, 1990, 'H', 'Actif', 80, 80, 90, 81, 80, 84, 80, 80, 7, 7),"
				+"(55, 'KAUKENAS', 'Tomas', 'T.KAUKENAS', 11, 1990, 'H', 'Actif', 80, 80, 79, 72, 80, 70, 80, 80, 4, 1),"
				+"(56, 'KHALILI', 'Said Karimulla', 'S.KHALILI', 7, 1990, 'H', 'Actif', 80, 80, 82, 77, 80, 60, 80, 80, 4, 1),"
				+"(57, 'KIM', 'Yonggyu', 'Y.KIM', 27, 1990, 'H', 'Actif', 80, 80, 86, 53, 80, 67, 80, 80, 4, 1),"
				+"(58, 'KODAMA', 'Shohei', 'S.KODAMA', 14, 1990, 'H', 'Actif', 80, 80, 64, 63, 80, 72, 80, 80, 4, 1),"
				+"(59, 'KRCMAR', 'Michal', 'M.KRCMAR', 19, 1990, 'H', 'Actif', 80, 80, 81, 79, 80, 78, 80, 80, 6, 5),"
				+"(60, 'KRUPCIK', 'Tomas', 'T.KRUPCIK', 19, 1990, 'H', 'Actif', 80, 80, 78, 80, 80, 71, 80, 80, 4, 1),"
				+"(61, 'KUEHN', 'Johannes', 'J.KUEHN', 16, 1990, 'H', 'Actif', 80, 80, 94, 64, 80, 87, 80, 80, 7, 5),"
				+"(62, 'LABASTAU', 'Mikita', 'M.LABASTAU', 22, 1990, 'H', 'Actif', 80, 80, 89, 85, 80, 72, 80, 80, 7, 4),"
				+"(63, 'LAEGREID', 'Sturla Holm', 'S.LAEGREID', 10, 1990, 'H', 'Actif', 80, 80, 85, 83, 80, 76, 80, 80, 8, 4),"
				+"(64, 'LANDERTINGER', 'Dominik', 'D.LANDERTINGER', 23, 1990, 'H', 'Actif', 80, 80, 89, 80, 80, 77, 80, 80, 6, 7),"
				+"(65, 'LANGER', 'Thierry', 'T.LANGER', 33, 1990, 'H', 'Actif', 80, 80, 79, 77, 80, 60, 80, 80, 4, 1),"
				+"(66, 'LAPSHIN', 'Timofei', 'T.LAPSHIN', 27, 1990, 'H', 'Actif', 80, 80, 76, 81, 80, 64, 80, 80, 1, 5),"
				+"(67, 'LATYPOV', 'Eduard', 'E.LATYPOV', 7, 1990, 'H', 'Actif', 80, 80, 80, 79, 80, 69, 80, 80, 2, 5),"
				+"(68, 'LEITNER', 'Felix', 'F.LEITNER', 23, 1990, 'H', 'Actif', 80, 80, 84, 79, 80, 76, 80, 80, 5, 6),"
				+"(69, 'LESSER', 'Erik', 'E.LESSER', 16, 1990, 'H', 'Actif', 80, 80, 88, 81, 80, 71, 80, 80, 6, 7),"
				+"(70, 'LOGINOV', 'Alexander', 'A.LOGINOV', 7, 1990, 'H', 'Actif', 80, 80, 92, 83, 80, 86, 80, 80, 7, 8),"
				+"(71, 'MALYSHKO', 'Dmitry', 'D.MALYSHKO', 7, 1990, 'H', 'Actif', 80, 80, 79, 74, 80, 76, 80, 80, 2, 4),"
				+"(72, 'MORAVEC', 'Ondrej', 'O.MORAVEC', 19, 1990, 'H', 'Actif', 80, 80, 89, 85, 80, 74, 80, 80, 5, 6),"
				+"(73, 'NAWRATH', 'Philipp', 'P.NAWRATH', 16, 1990, 'H', 'Actif', 80, 80, 88, 81, 80, 71, 80, 80, 6, 5),"
				+"(74, 'NEDZA-KUBINIEC', 'Andrzej', 'A.NEDZA-KUBINIEC', 9, 1990, 'H', 'Actif', 80, 80, 77, 73, 80, 74, 80, 80, 4, 1),"
				+"(75, 'NELIN', 'Jesper', 'J.NELIN', 4, 1990, 'H', 'Actif', 80, 80, 85, 66, 80, 82, 80, 80, 6, 6),"
				+"(76, 'NORDGREN', 'Leif', 'L.NORDGREN', 1, 1990, 'H', 'Actif', 80, 80, 79, 82, 80, 69, 80, 80, 7, 3),"
				+"(77, 'OTCENAS', 'Martin', 'M.OTCENAS', 6, 1990, 'H', 'Actif', 80, 80, 76, 80, 80, 73, 80, 80, 6, 3),"
				+"(78, 'OZAKI', 'Kosuke', 'K.OZAKI', 14, 1990, 'H', 'Actif', 80, 80, 86, 69, 80, 66, 80, 80, 4, 1),"
				+"(79, 'PANTOV', 'Anton', 'A.PANTOV', 13, 1990, 'H', 'Actif', 80, 80, 94, 85, 80, 71, 80, 80, 4, 1),"
				+"(80, 'PATRIJUKS', 'Aleksandrs', 'A.PATRIJUKS', 12, 1990, 'H', 'Actif', 80, 80, 76, 73, 80, 59, 80, 80, 4, 1),"
				+"(81, 'PEIFFER', 'Arnd', 'A.PEIFFER', 16, 1990, 'H', 'Actif', 80, 80, 93, 82, 80, 82, 80, 80, 7, 8),"
				+"(82, 'PIDRUCHNYI', 'Dmytro', 'D.PIDRUCHNYI', 2, 1990, 'H', 'Actif', 80, 80, 86, 81, 80, 81, 80, 80, 8, 6),"
				+"(83, 'PONSILUOMA', 'Martin', 'M.PONSILUOMA', 4, 1990, 'H', 'Actif', 80, 80, 68, 79, 80, 82, 80, 80, 4, 5),"
				+"(84, 'PORSHNEV', 'Nikita', 'N.PORSHNEV', 7, 1990, 'H', 'Actif', 80, 80, 85, 79, 80, 70, 80, 80, 1, 4),"
				+"(85, 'PRYMA', 'Artem', 'A.PRYMA', 2, 1990, 'H', 'Actif', 80, 80, 84, 80, 80, 78, 80, 80, 5, 5),"
				+"(86, 'PUCHIANU', 'Cornel', 'C.PUCHIANU', 8, 1990, 'H', 'Actif', 80, 80, 87, 59, 80, 64, 80, 80, 6, 2),"
				+"(87, 'RAENKEL', 'Raido', 'R.RAENKEL', 31, 1990, 'H', 'Actif', 80, 80, 61, 50, 80, 74, 80, 80, 4, 1),"
				+"(88, 'RASTORGUJEVS', 'Andrejs', 'A.RASTORGUJEVS', 12, 1990, 'H', 'Actif', 80, 80, 76, 78, 80, 82, 80, 80, 3, 6),"
				+"(89, 'REES', 'Roman', 'R.REES', 16, 1990, 'H', 'Actif', 80, 80, 89, 88, 80, 71, 80, 80, 4, 5),"
				+"(90, 'SAMUELSSON', 'Sebastian', 'S.SAMUELSSON', 4, 1990, 'H', 'Actif', 80, 80, 84, 77, 80, 80, 80, 80, 5, 6),"
				+"(91, 'SCHEMPP', 'Simon', 'S.SCHEMPP', 16, 1990, 'H', 'Actif', 80, 80, 89, 78, 80, 70, 80, 80, 6, 7),"
				+"(92, 'SEMENOV', 'Sergii', 'S.SEMENOV', 2, 1990, 'H', 'Actif', 80, 80, 79, 81, 80, 66, 80, 80, 5, 3),"
				+"(93, 'SEPPALA', 'Tero', 'T.SEPPALA', 18, 1990, 'H', 'Actif', 80, 80, 78, 72, 80, 72, 80, 80, 6, 4),"
				+"(94, 'SINAPOV', 'Anton', 'A.SINAPOV', 21, 1990, 'H', 'Actif', 80, 80, 77, 61, 80, 71, 80, 80, 4, 1),"
				+"(95, 'SLESINGR', 'Michal', 'M.SLESINGR', 19, 1990, 'H', 'Actif', 80, 80, 85, 77, 80, 73, 80, 80, 5, 4),"
				+"(96, 'SLOTINS', 'Roberts', 'R.SLOTINS', 12, 1990, 'H', 'Actif', 80, 80, 64, 63, 80, 62, 80, 80, 4, 1),"
				+"(97, 'SMOLSKI', 'Anton', 'A.SMOLSKI', 22, 1990, 'H', 'Actif', 80, 80, 81, 83, 80, 76, 80, 80, 6, 4),"
				+"(98, 'STENERSEN', 'Torstein', 'T.STENERSEN', 4, 1990, 'H', 'Actif', 80, 80, 73, 76, 80, 69, 80, 80, 4, 1),"
				+"(99, 'STROLIA', 'Vytautas', 'V.STROLIA', 11, 1990, 'H', 'Actif', 80, 80, 79, 76, 80, 74, 80, 80, 4, 1),"
				+"(100, 'STVRTECKY', 'Jakub', 'J.STVRTECKY', 19, 1990, 'H', 'Actif', 80, 80, 84, 59, 80, 70, 80, 80, 6, 2),"
				+"(101, 'SZCZUREK', 'Lukasz', 'L.SZCZUREK', 9, 1990, 'H', 'Actif', 80, 80, 83, 74, 80, 61, 80, 80, 4, 1),"
				+"(102, 'TACHIZAKI', 'Mikito', 'M.TACHIZAKI', 14, 1990, 'H', 'Actif', 80, 80, 86, 79, 80, 71, 80, 80, 4, 1),"
				+"(103, 'TKALENKO', 'Ruslan', 'R.TKALENKO', 2, 1990, 'H', 'Actif', 80, 80, 81, 75, 80, 70, 80, 80, 4, 1),"
				+"(104, 'TRSAN', 'Rok', 'R.TRSAN', 5, 1990, 'H', 'Actif', 80, 80, 90, 87, 80, 71, 80, 80, 5, 3),"
				+"(105, 'TYSHCHENKO', 'Artem', 'A.TYSHCHENKO', 2, 1990, 'H', 'Actif', 80, 80, 92, 81, 80, 60, 80, 80, 4, 1),"
				+"(106, 'VACLAVIK', 'Adam', 'A.VACLAVIK', 19, 1990, 'H', 'Actif', 80, 80, 78, 70, 80, 72, 80, 80, 4, 1),"
				+"(107, 'VARABEI', 'Maksim', 'M.VARABEI', 22, 1990, 'H', 'Actif', 80, 80, 90, 58, 80, 63, 80, 80, 4, 1),"
				+"(108, 'VITENKO', 'Vladislav', 'V.VITENKO', 13, 1990, 'H', 'Actif', 80, 80, 62, 61, 80, 60, 80, 80, 4, 1),"
				+"(109, 'WEGER', 'Benjamin', 'B.WEGER', 3, 1990, 'H', 'Actif', 80, 80, 79, 79, 80, 85, 80, 80, 6, 5),"
				+"(110, 'WIESTNER', 'Serafin', 'S.WIESTNER', 3, 1990, 'H', 'Actif', 80, 80, 81, 68, 80, 71, 80, 80, 6, 3),"
				+"(111, 'WINDISCH', 'Dominik', 'D.WINDISCH', 15, 1990, 'H', 'Actif', 80, 80, 78, 75, 80, 83, 80, 80, 2, 6),"
				+"(112, 'YALIOTNAU', 'Raman', 'R.YALIOTNAU', 22, 1990, 'H', 'Actif', 80, 80, 76, 72, 80, 72, 80, 80, 7, 5),"
				+"(113, 'YAN', 'Xingyuan', 'X.YAN', 32, 1990, 'H', 'Actif', 80, 80, 69, 76, 80, 66, 80, 80, 4, 1),"
				+"(114, 'YEREMIN', 'Roman', 'R.YEREMIN', 13, 1990, 'H', 'Actif', 80, 80, 66, 71, 80, 75, 80, 80, 7, 3),"
				+"(115, 'ZAHKNA', 'Rene', 'R.ZAHKNA', 31, 1990, 'H', 'Actif', 80, 80, 86, 84, 80, 67, 80, 80, 6, 3),"
				+"(116, 'COOPER', 'Travis', 'T.COOPER', 1, 1990, 'H', 'Actif', 80, 80, 65, 73, 80, 65, 80, 80, 1, 1),"
				+"(117, 'SIMA', 'Michal', 'M.SIMA', 6, 1990, 'H', 'Actif', 80, 80, 80, 76, 80, 72, 80, 80, 1, 1),"
				+"(118, 'COLTEA', 'George', 'G.COLTEA', 8, 1990, 'H', 'Actif', 80, 80, 60, 60, 80, 70, 80, 80, 1, 1),"
				+"(119, 'SERBAN', 'Denis', 'D.SERBAN', 8, 1990, 'H', 'Actif', 80, 80, 80, 65, 80, 58, 80, 80, 1, 1),"
				+"(120, 'JANIK', 'Mateusz', 'M.JANIK', 9, 1990, 'H', 'Actif', 80, 80, 61, 68, 80, 62, 80, 80, 1, 1),"
				+"(121, 'BANYS', 'Linas', 'L.BANYS', 11, 1990, 'H', 'Actif', 80, 80, 69, 70, 80, 66, 80, 80, 1, 1),"
				+"(122, 'BRAUN', 'Maxime', 'M.BRAUN', 13, 1990, 'H', 'Actif', 80, 80, 80, 79, 80, 59, 80, 80, 1, 1),"
				+"(123, 'BAISHO', 'Kazuki', 'K.BAISHO', 14, 1990, 'H', 'Actif', 80, 80, 68, 50, 80, 69, 80, 80, 1, 1),"
				+"(124, 'MISE', 'Edgars', 'E.MISE', 12, 1990, 'H', 'Actif', 80, 80, 75, 70, 80, 68, 80, 80, 1, 1),"
				+"(125, 'ZINI', 'Saverio', 'S.ZINI', 15, 1990, 'H', 'Actif', 80, 80, 83, 60, 80, 64, 80, 80, 1, 1),"
				+"(126, 'CAPPELLARI', 'Daniele', 'D.CAPPELLARI', 15, 1990, 'H', 'Actif', 80, 80, 86, 85, 80, 62, 80, 80, 1, 1),"
				+"(127, 'RANTA', 'Jaakko', 'J.RANTA', 18, 1990, 'H', 'Actif', 80, 80, 66, 75, 80, 59, 80, 80, 1, 1),"
				+"(128, 'MILLAR', 'Aidan', 'A.MILLAR', 20, 1990, 'H', 'Actif', 80, 80, 62, 60, 80, 60, 80, 80, 1, 1),"
				+"(129, 'GERDZHIKOV', 'Dimitar', 'D.GERDZHIKOV', 21, 1990, 'H', 'Actif', 80, 80, 80, 85, 80, 63, 80, 80, 1, 1),"
				+"(130, 'LEE', 'Suyoung', 'S.LEE', 27, 1990, 'H', 'Actif', 80, 80, 65, 65, 80, 65, 80, 80, 1, 1),"
				+"(131, 'CHOI', 'Dujin', 'D.CHOI', 27, 1990, 'H', 'Actif', 80, 80, 76, 76, 80, 61, 80, 80, 1, 1),"
				+"(132, 'WANG', 'Wenqiang', 'W.WANG', 32, 1990, 'H', 'Actif', 80, 80, 60, 60, 80, 70, 80, 80, 1, 1),"
				+"(133, 'LI', 'Xuezhi', 'X.LI', 32, 1990, 'H', 'Actif', 80, 80, 81, 76, 80, 61, 80, 80, 1, 1),"
				+"(134, 'ZHU', 'Zhenyu', 'Z.ZHU', 32, 1990, 'H', 'Actif', 80, 80, 67, 62, 80, 70, 80, 80, 1, 1),"
				+"(135, 'LAHAYE-GOFFART', 'Tom', 'T.LAHAYE-GOFFART', 33, 1990, 'H', 'Actif', 80, 80, 75, 75, 80, 59, 80, 80, 1, 1),"
				+"(136, 'DIELEN', 'Pjotr', 'P.DIELEN', 33, 1990, 'H', 'Actif', 80, 80, 68, 62, 80, 60, 80, 80, 1, 1),"
				+"(137, 'AKHATOVA', 'Lyudmila', 'L.AKHATOVA', 13, 1990, 'F', 'Actif', 80, 80, 93, 66, 80, 61, 80, 80, 1, 1),"
				+"(138, 'ALIMBEKAVA', 'Dzinara', 'D.ALIMBEKAVA', 22, 1990, 'F', 'Actif', 80, 80, 76, 76, 80, 56, 80, 80, 1, 1),"
				+"(139, 'ANDERSSON', 'Ingela', 'I.ANDERSSON', 4, 1990, 'F', 'Actif', 80, 80, 80, 73, 80, 69, 80, 80, 1, 1),"
				+"(140, 'AYMONIER', 'Celia', 'C.AYMONIER', 17, 1990, 'F', 'Actif', 80, 80, 75, 71, 80, 88, 80, 80, 1, 5),"
				+"(141, 'BANKES', 'Megan', 'M.BANKES', 20, 1990, 'F', 'Actif', 80, 80, 75, 63, 80, 67, 80, 80, 1, 1),"
				+"(142, 'BEAUDRY', 'Sarah', 'S.BEAUDRY', 20, 1990, 'F', 'Actif', 80, 80, 75, 89, 80, 55, 80, 80, 1, 1),"
				+"(143, 'BELCHENKO', 'Yelizaveta', 'Y.BELCHENKO', 13, 1990, 'F', 'Actif', 80, 80, 81, 79, 80, 59, 80, 80, 1, 1),"
				+"(144, 'BENDIKA', 'Baiba', 'B.BENDIKA', 12, 1990, 'F', 'Actif', 80, 80, 78, 81, 80, 60, 80, 80, 1, 3),"
				+"(145, 'BESCOND', 'Anais', 'A.BESCOND', 17, 1990, 'F', 'Actif', 80, 80, 84, 78, 80, 82, 80, 80, 1, 6),"
				+"(146, 'BLASHKO', 'Darya', 'D.BLASHKO', 2, 1990, 'F', 'Actif', 80, 80, 91, 88, 80, 64, 80, 80, 1, 2),"
				+"(147, 'BRAISAZ', 'Justine', 'J.BRAISAZ', 17, 1990, 'F', 'Actif', 80, 80, 76, 73, 80, 82, 80, 80, 1, 7),"
				+"(148, 'BRORSSON', 'Mona', 'M.BRORSSON', 4, 1990, 'F', 'Actif', 80, 80, 91, 76, 80, 75, 80, 80, 1, 6),"
				+"(149, 'BRUN-LIE', 'Thekla', 'T.BRUN-LIE', 10, 1990, 'F', 'Actif', 80, 80, 93, 84, 80, 60, 80, 80, 1, 3),"
				+"(150, 'CHARVATOVA', 'Lucie', 'L.CHARVATOVA', 19, 1990, 'F', 'Actif', 80, 80, 69, 65, 80, 73, 80, 80, 1, 4),"
				+"(151, 'CHEVALIER', 'Chloe', 'C.CHEVALIER', 17, 1990, 'F', 'Actif', 80, 80, 83, 88, 80, 73, 80, 80, 1, 4),"
				+"(152, 'CHU', 'Yuanmeng', 'Y.CHU', 32, 1990, 'F', 'Actif', 80, 80, 79, 85, 80, 60, 80, 80, 1, 2),"
				+"(153, 'COLOMBO', 'Caroline', 'C.COLOMBO', 17, 1990, 'F', 'Actif', 80, 80, 78, 69, 80, 63, 80, 80, 1, 2),"
				+"(154, 'DAVIDOVA', 'Marketa', 'M.DAVIDOVA', 19, 1990, 'F', 'Actif', 80, 80, 86, 73, 80, 89, 80, 80, 1, 7),"
				+"(155, 'DREISSIGACKER', 'Emily', 'E.DREISSIGACKER', 1, 1990, 'F', 'Actif', 80, 80, 80, 76, 80, 65, 80, 80, 1, 1),"
				+"(156, 'DUNKLEE', 'Susan', 'S.DUNKLEE', 1, 1990, 'F', 'Actif', 80, 80, 85, 69, 80, 70, 80, 80, 1, 6),"
				+"(157, 'DZHIMA', 'Yuliia', 'Y.DZHIMA', 2, 1990, 'F', 'Actif', 80, 80, 87, 82, 80, 72, 80, 80, 1, 6),"
				+"(158, 'ECKHOFF', 'Tiril', 'T.ECKHOFF', 10, 1990, 'F', 'Actif', 80, 80, 86, 76, 80, 94, 80, 80, 1, 8),"
				+"(159, 'EDER', 'Mari', 'M.EDER', 18, 1990, 'F', 'Actif', 80, 80, 78, 72, 80, 85, 80, 80, 1, 5),"
				+"(160, 'EGAN', 'Clare', 'C.EGAN', 1, 1990, 'F', 'Actif', 80, 80, 91, 75, 80, 70, 80, 80, 1, 5),"
				+"(161, 'EINFALT', 'Lea', 'L.EINFALT', 5, 1990, 'F', 'Actif', 80, 80, 69, 78, 80, 56, 80, 80, 1, 1),"
				+"(162, 'FIALKOVA', 'Paulina', 'P.FIALKOVA', 6, 1990, 'F', 'Actif', 80, 80, 88, 77, 80, 80, 80, 80, 1, 7),"
				+"(163, 'FIALKOVA', 'Ivona', 'I.FIALKOVA', 6, 1990, 'F', 'Actif', 80, 80, 77, 73, 80, 60, 80, 80, 1, 3),"
				+"(164, 'FROLINA', 'Anna', 'A.FROLINA', 27, 1990, 'F', 'Actif', 80, 80, 66, 72, 80, 64, 80, 80, 1, 4),"
				+"(165, 'GAIM', 'Grete', 'G.GAIM', 31, 1990, 'F', 'Actif', 80, 80, 80, 76, 80, 64, 80, 80, 1, 1),"
				+"(166, 'GASPARIN', 'Selina', 'S.GASPARIN', 3, 1990, 'F', 'Actif', 80, 80, 76, 76, 80, 70, 80, 80, 1, 6),"
				+"(167, 'GASPARIN', 'Aita', 'A.GASPARIN', 3, 1990, 'F', 'Actif', 80, 80, 85, 82, 80, 66, 80, 80, 1, 5),"
				+"(168, 'GASPARIN', 'Elisa', 'E.GASPARIN', 3, 1990, 'F', 'Actif', 80, 80, 84, 76, 80, 64, 80, 80, 1, 5),"
				+"(169, 'GONTIER', 'Nicole', 'N.GONTIER', 15, 1990, 'F', 'Actif', 80, 80, 68, 63, 80, 81, 80, 80, 1, 4),"
				+"(170, 'GWIZDON', 'Magdalena', 'M.GWIZDON', 9, 1990, 'F', 'Actif', 80, 80, 78, 71, 80, 60, 80, 80, 1, 3),"
				+"(171, 'HAECKI', 'Lena', 'L.HAECKI', 3, 1990, 'F', 'Actif', 80, 80, 73, 71, 80, 87, 80, 80, 1, 6),"
				+"(172, 'HAMMERSCHMIDT', 'Maren', 'M.HAMMERSCHMIDT', 16, 1990, 'F', 'Actif', 80, 80, 80, 76, 80, 76, 80, 80, 1, 6),"
				+"(173, 'HAUSER', 'Lisa Theresa', 'L.T.HAUSER', 23, 1990, 'F', 'Actif', 80, 80, 85, 84, 80, 72, 80, 80, 1, 7),"
				+"(174, 'HERRMANN', 'Denise', 'D.HERRMANN', 16, 1990, 'F', 'Actif', 80, 80, 82, 72, 80, 98, 80, 80, 1, 8),"
				+"(175, 'HETTICH', 'Janina', 'J.HETTICH', 16, 1990, 'F', 'Actif', 80, 80, 76, 69, 80, 57, 80, 80, 1, 1),"
				+"(176, 'HILDEBRAND', 'Franziska', 'F.HILDEBRAND', 16, 1990, 'F', 'Actif', 80, 80, 82, 77, 80, 66, 80, 80, 1, 5),"
				+"(177, 'HINZ', 'Vanessa', 'V.HINZ', 16, 1990, 'F', 'Actif', 80, 80, 87, 76, 80, 77, 80, 80, 1, 6),"
				+"(178, 'HOEGBERG', 'Elisabeth', 'E.HOEGBERG', 4, 1990, 'F', 'Actif', 80, 80, 77, 81, 80, 63, 80, 80, 1, 4),"
				+"(179, 'HOJNISZ', 'Monika', 'M.HOJNISZ', 9, 1990, 'F', 'Actif', 80, 80, 90, 81, 80, 85, 80, 80, 1, 7),"
				+"(180, 'HORCHLER', 'Karolin', 'K.HORCHLER', 16, 1990, 'F', 'Actif', 80, 80, 92, 77, 80, 75, 80, 80, 1, 6),"
				+"(181, 'INNERHOFER', 'Katharina', 'K.INNERHOFER', 23, 1990, 'F', 'Actif', 80, 80, 84, 55, 80, 86, 80, 80, 1, 5),"
				+"(182, 'JAENKAE', 'Erika', 'E.JAENKAE', 18, 1990, 'F', 'Actif', 80, 80, 72, 52, 80, 67, 80, 80, 1, 1),"
				+"(183, 'JISLOVA', 'Jessica', 'J.JISLOVA', 19, 1990, 'F', 'Actif', 80, 80, 86, 79, 80, 60, 80, 80, 1, 1),"
				+"(184, 'KADEVA', 'Daniela', 'D.KADEVA', 21, 1990, 'F', 'Actif', 80, 80, 80, 75, 80, 63, 80, 80, 1, 1),"
				+"(185, 'KALKENBERG', 'Emilie Aagheim', 'E.KALKENBERG', 10, 1990, 'F', 'Actif', 80, 80, 93, 66, 80, 57, 80, 80, 1, 1),"
				+"(186, 'KIM', 'Seonsu', 'S.KIM', 27, 1990, 'F', 'Actif', 80, 80, 60, 68, 80, 67, 80, 80, 1, 1),"
				+"(187, 'KLEMENCIC', 'Polona', 'P.KLEMENCIC', 5, 1990, 'F', 'Actif', 80, 80, 73, 64, 80, 63, 80, 80, 1, 1),"
				+"(188, 'KNOTTEN', 'Karoline Offigstad', 'K.KNOTTEN', 10, 1990, 'F', 'Actif', 80, 80, 83, 81, 80, 68, 80, 80, 1, 4),"
				+"(189, 'KO', 'Eunjung', 'E.KO', 27, 1990, 'F', 'Actif', 80, 80, 85, 76, 80, 58, 80, 80, 1, 1),"
				+"(190, 'KOCERGINA', 'Natalja', 'N.KOCERGINA', 11, 1990, 'F', 'Actif', 80, 80, 78, 63, 80, 69, 80, 80, 1, 1),"
				+"(191, 'KONDRATYEVA', 'Anastassiya', 'A.KONDRATYEVA', 13, 1990, 'F', 'Actif', 80, 80, 73, 72, 80, 59, 80, 80, 1, 1),"
				+"(192, 'KRUCHINKINA', 'Elena', 'E.KRUCHINKINA', 22, 1990, 'F', 'Actif', 80, 80, 78, 69, 80, 60, 80, 80, 1, 3),"
				+"(193, 'KRYUKO', 'Iryna', 'I.KRYUKO', 22, 1990, 'F', 'Actif', 80, 80, 87, 81, 80, 70, 80, 80, 1, 6),"
				+"(194, 'KUKLINA', 'Larisa', 'L.KUKLINA', 7, 1990, 'F', 'Actif', 80, 80, 84, 82, 80, 71, 80, 80, 1, 6),"
				+"(195, 'LUNDER', 'Emma', 'E.LUNDER', 20, 1990, 'F', 'Actif', 80, 80, 76, 87, 80, 59, 80, 80, 1, 2),"
				+"(196, 'MACHYNIAKOVA', 'Veronika', 'V.MACHYNIAKOVA', 6, 1990, 'F', 'Actif', 80, 80, 88, 73, 80, 62, 80, 80, 1, 2),"
				+"(197, 'MAGNUSSON', 'Anna', 'A.MAGNUSSON', 4, 1990, 'F', 'Actif', 80, 80, 75, 82, 80, 60, 80, 80, 1, 3),"
				+"(198, 'MAKARAINEN', 'Kaisa', 'K.MAKARAINEN', 18, 1990, 'F', 'Actif', 80, 80, 84, 73, 80, 85, 80, 80, 1, 8),"
				+"(199, 'MARKKANEN', 'Sanna', 'S.MARKKANEN', 18, 1990, 'F', 'Actif', 80, 80, 90, 80, 80, 66, 80, 80, 1, 4),"
				+"(200, 'MEINEN', 'Susanna', 'S.MEINEN', 3, 1990, 'F', 'Actif', 80, 80, 73, 60, 80, 56, 80, 80, 1, 1),"
				+"(201, 'MENG', 'Fanqi', 'F.MENG', 32, 1990, 'F', 'Actif', 80, 80, 91, 88, 80, 63, 80, 80, 1, 1),"
				+"(202, 'MERKUSHYNA', 'Anastasiya', 'A.MERKUSHYNA', 2, 1990, 'F', 'Actif', 80, 80, 86, 79, 80, 73, 80, 80, 1, 6),"
				+"(203, 'MINKKINEN', 'Suvi', 'S.MINKKINEN', 18, 1990, 'F', 'Actif', 80, 80, 77, 73, 80, 60, 80, 80, 1, 3),"
				+"(204, 'MIRONOVA', 'Svetlana', 'S.MIRONOVA', 7, 1990, 'F', 'Actif', 80, 80, 73, 72, 80, 86, 80, 80, 1, 3),"
				+"(205, 'MOSER', 'Nadia', 'N.MOSER', 20, 1990, 'F', 'Actif', 80, 80, 76, 64, 80, 66, 80, 80, 1, 1),"
				+"(206, 'NILSSON', 'Emma', 'E.NILSSON', 4, 1990, 'F', 'Actif', 80, 80, 77, 77, 80, 61, 80, 80, 1, 1),"
				+"(207, 'OEBERG', 'Hanna', 'H.OEBERG', 4, 1990, 'F', 'Actif', 80, 80, 86, 79, 80, 86, 80, 80, 1, 8),"
				+"(208, 'OEBERG', 'Elvira', 'E.OEBERG', 4, 1990, 'F', 'Actif', 80, 80, 73, 82, 80, 69, 80, 80, 1, 4),"
				+"(209, 'OJA', 'Regina', 'R.OJA', 31, 1990, 'F', 'Actif', 80, 80, 73, 86, 80, 58, 80, 80, 1, 3),"
				+"(210, 'PARK', 'Jiae', 'J.PARK', 27, 1990, 'F', 'Actif', 80, 80, 61, 77, 80, 59, 80, 80, 1, 1),"
				+"(211, 'PEHLIVANSKA', 'Lyubomira', 'L.PEHLIVANSKA', 21, 1990, 'F', 'Actif', 80, 80, 83, 73, 80, 56, 80, 80, 1, 1),"
				+"(212, 'PERSSON', 'Linn', 'L.PERSSON', 4, 1990, 'F', 'Actif', 80, 80, 88, 79, 80, 83, 80, 80, 1, 5),"
				+"(213, 'PIDHRUSHNA', 'Olena', 'O.PIDHRUSHNA', 2, 1990, 'F', 'Actif', 80, 80, 87, 79, 80, 72, 80, 80, 1, 4),"
				+"(214, 'POLIAKOVA', 'Terezia', 'T.POLIAKOVA', 6, 1990, 'F', 'Actif', 80, 80, 76, 77, 80, 63, 80, 80, 1, 3),"
				+"(215, 'PORSHNEVA', 'Anastasiia', 'A.PORSHNEVA', 7, 1990, 'F', 'Actif', 80, 80, 83, 75, 80, 69, 80, 80, 1, 1),"
				+"(216, 'PREUSS', 'Franziska', 'F.PREUSS', 16, 1990, 'F', 'Actif', 80, 80, 89, 85, 80, 78, 80, 80, 1, 7),"
				+"(217, 'PUSKARCIKOVA', 'Eva', 'E.PUSKARCIKOVA', 19, 1990, 'F', 'Actif', 80, 80, 75, 75, 80, 62, 80, 80, 1, 5),"
				+"(218, 'REID', 'Joanne', 'J.REID', 1, 1990, 'F', 'Actif', 80, 80, 82, 71, 80, 66, 80, 80, 1, 4),"
				+"(219, 'REZTSOVA', 'Kristina', 'K.REZTSOVA', 7, 1990, 'F', 'Actif', 80, 80, 74, 83, 80, 65, 80, 80, 1, 1),"
				+"(220, 'RIEDER', 'Christina', 'C.RIEDER', 23, 1990, 'F', 'Actif', 80, 80, 87, 87, 80, 69, 80, 80, 1, 1),"
				+"(221, 'ROEISELAND', 'Marte Olsbu', 'M.ROEISELAND', 10, 1990, 'F', 'Actif', 80, 80, 86, 82, 80, 93, 80, 80, 1, 8),"
				+"(222, 'SANFILIPPO', 'Federica', 'F.SANFILIPPO', 15, 1990, 'F', 'Actif', 80, 80, 80, 68, 80, 65, 80, 80, 1, 5),"
				+"(223, 'SCHWAIGER', 'Julia', 'J.SCHWAIGER', 23, 1990, 'F', 'Actif', 80, 80, 86, 74, 80, 56, 80, 80, 1, 1),"
				+"(224, 'SEMERENKO', 'Valj', 'Va.SEMERENKO', 2, 1990, 'F', 'Actif', 80, 80, 83, 79, 80, 75, 80, 80, 1, 6),"
				+"(225, 'SEMERENKO', 'Vita', 'Vi.SEMERENKO', 2, 1990, 'F', 'Actif', 80, 80, 92, 80, 80, 74, 80, 80, 1, 6),"
				+"(226, 'SIMON', 'Julia', 'J.SIMON', 17, 1990, 'F', 'Actif', 80, 80, 78, 75, 80, 80, 80, 80, 1, 7),"
				+"(227, 'SKOTTHEIM', 'Johanna', 'J.SKOTTHEIM', 4, 1990, 'F', 'Actif', 80, 80, 87, 80, 80, 71, 80, 80, 1, 5),"
				+"(228, 'SOLA', 'Hanna', 'H.SOLA', 22, 1990, 'F', 'Actif', 80, 80, 71, 67, 80, 69, 80, 80, 1, 2),"
				+"(229, 'SOLEMDAL', 'Synnoeve', 'S.SOLEMDAL', 10, 1990, 'F', 'Actif', 80, 80, 94, 77, 80, 60, 80, 80, 1, 5),"
				+"(230, 'STARYKH', 'Irina', 'I.STARYKH', 7, 1990, 'F', 'Actif', 80, 80, 87, 75, 80, 70, 80, 80, 1, 5),"
				+"(231, 'TACHIZAKI', 'Fuyuko', 'F.TACHIZAKI', 14, 1990, 'F', 'Actif', 80, 80, 88, 77, 80, 67, 80, 80, 1, 3),"
				+"(232, 'TALIHAERM', 'Johanna', 'J.TALIHAERM', 31, 1990, 'F', 'Actif', 80, 80, 78, 84, 80, 66, 80, 80, 1, 1),"
				+"(233, 'TANDREVOLD', 'Ingrid Landmark', 'I.TANDREVOLD', 10, 1990, 'F', 'Actif', 80, 80, 90, 73, 80, 81, 80, 80, 1, 8),"
				+"(234, 'TANG', 'Jialin', 'J.TANG', 32, 1990, 'F', 'Actif', 80, 80, 89, 82, 80, 63, 80, 80, 1, 1),"
				+"(235, 'TODOROVA', 'Milena', 'M.TODOROVA', 21, 1990, 'F', 'Actif', 80, 80, 85, 74, 80, 63, 80, 80, 1, 1),"
				+"(236, 'TOMINGAS', 'Tuuli', 'T.TOMINGAS', 31, 1990, 'F', 'Actif', 80, 80, 85, 68, 80, 56, 80, 80, 1, 1),"
				+"(237, 'VISHNEVSKAYA', 'Galina', 'G.VISHNEVSKAYA', 13, 1990, 'F', 'Actif', 80, 80, 83, 76, 80, 61, 80, 80, 1, 1),"
				+"(238, 'VITTOZZI', 'Lisa', 'L.VITTOZZI', 15, 1990, 'F', 'Actif', 80, 80, 79, 81, 80, 78, 80, 80, 1, 8),"
				+"(239, 'VORONINA', 'Tamara', 'T.VORONINA', 7, 1990, 'F', 'Actif', 80, 80, 80, 73, 80, 68, 80, 80, 1, 1),"
				+"(240, 'VOZELJ', 'Tais', 'T.VOZELJ', 5, 1990, 'F', 'Actif', 80, 80, 62, 64, 80, 65, 80, 80, 1, 1),"
				+"(241, 'WIERER', 'Dorothea', 'D.WIERER', 15, 1990, 'F', 'Actif', 80, 80, 87, 78, 80, 88, 80, 80, 1, 8),"
				+"(242, 'YURLOVA-PERCHT', 'Ekaterina', 'E.YURLOVA', 7, 1990, 'F', 'Actif', 80, 80, 92, 74, 80, 77, 80, 80, 1, 7),"
				+"(243, 'ZADRAVEC', 'Nina', 'N.ZADRAVEC', 5, 1990, 'F', 'Actif', 80, 80, 76, 56, 80, 62, 80, 80, 1, 1),"
				+"(244, 'ZBYLUT', 'Kinga', 'K.ZBYLUT', 9, 1990, 'F', 'Actif', 80, 80, 81, 70, 80, 60, 80, 80, 1, 4),"
				+"(245, 'ZDOUC', 'Dunja', 'D.ZDOUC', 23, 1990, 'F', 'Actif', 80, 80, 86, 79, 80, 59, 80, 80, 1, 4),"
				+"(246, 'ZDRAVKOVA', 'Maria', 'M.ZDRAVKOVA', 21, 1990, 'F', 'Actif', 80, 80, 82, 72, 80, 64, 80, 80, 1, 1),"
				+"(247, 'ZHANG', 'Yan', 'Y.ZHANG', 32, 1990, 'F', 'Actif', 80, 80, 83, 73, 80, 65, 80, 80, 1, 3),"
				+"(248, 'ZUK', 'Kamila', 'K.ZUK', 9, 1990, 'F', 'Actif', 80, 80, 68, 76, 80, 79, 80, 80, 1, 5),"
				+"(249, 'EDVINSEN', 'Bjorg', 'B.EDVINSEN', 10, 1990, 'H', 'jeune', 80, 80, 68, 76, 80, 79, 80, 80, 1, 5),"
				+"(250, 'OLSEN', 'Lansen', 'L.OLSEN', 10, 1990, 'H', 'jeune', 80, 80, 68, 76, 80, 79, 80, 80, 1, 5),"
				+"(251, 'OLEN FESSEN', 'Erland', 'E.OLEN FESSEN', 10, 1990, 'H', 'jeune', 80, 80, 68, 76, 80, 79, 80, 80, 1, 5),"
				+"(252, 'OEBECK', 'Ingemund', 'I.OEBECK', 10, 1990, 'H', 'jeune', 80, 80, 68, 76, 80, 79, 80, 80, 1, 5),"
				+"(253, 'ELLISSON', 'Knut', 'K.ELLISSON', 10, 1990, 'H', 'jeune', 80, 80, 68, 76, 80, 79, 80, 80, 1, 5),"
				+"(254, 'ELIPSEN', 'Oystein', 'O.ELIPSEN', 10, 1990, 'H', 'jeune', 80, 80, 68, 76, 80, 79, 80, 80, 1, 5),"
				+"(255, 'PHILSEN', 'Terje', 'T.PHILSEN', 10, 1990, 'H', 'jeune', 80, 80, 68, 76, 80, 79, 80, 80, 1, 5),"
				+"(256, 'JOE', 'Emilson', 'E.JOE', 10, 1990, 'H', 'jeune', 80, 80, 68, 76, 80, 79, 80, 80, 1, 5),"
				+"(257, 'BOE', 'Gunder', 'G.BOE', 10, 1990, 'H', 'jeune', 80, 80, 68, 76, 80, 79, 80, 80, 1, 5),"
				+"(258, 'BORTERGEN', 'Emile', 'E.BORTERGEN', 10, 1990, 'H', 'jeune', 80, 80, 68, 76, 80, 79, 80, 80, 1, 5),"
				+"(259, 'BHOGTOREGARD', 'Ole', 'O.BHOGTOREGARD', 10, 1990, 'H', 'jeune', 80, 80, 68, 76, 80, 79, 80, 80, 1, 5),"
				+"(260, 'BJISTINEN', 'Eihri', 'E.BJISTINEN', 10, 1990, 'H', 'jeune', 80, 80, 68, 76, 80, 79, 80, 80, 1, 5),"
				+"(261, 'ERIKSEN', 'Andreas', 'A.ERIKSEN', 10, 1990, 'H', 'jeune', 80, 80, 68, 76, 80, 79, 80, 80, 1, 5),"
				+"(262, 'EMILIGEN', 'Isak', 'I.EMILIGEN', 10, 1990, 'H', 'jeune', 80, 80, 68, 76, 80, 79, 80, 80, 1, 5),"
				+"(263, 'ARMSUND LINKED', 'Marius', 'M.ARMSUND LINKED', 10, 1990, 'H', 'jeune', 80, 80, 68, 76, 80, 79, 80, 80, 1, 5),"
				+"(264, 'RIJK', 'Vladimir', 'V.RIJK', 10, 1990, 'H', 'jeune', 80, 80, 68, 76, 80, 79, 80, 80, 1, 5),"
				+"(265, 'OLISTAND', 'Markus', 'M.OLISTAND', 10, 1990, 'H', 'jeune', 80, 80, 68, 76, 80, 79, 80, 80, 1, 5),"
				+"(266, 'POJKARD LUND', 'Lukka', 'L.POJKARD LUND', 10, 1990, 'H', 'jeune', 80, 80, 68, 76, 80, 79, 80, 80, 1, 5),"
				+"(267, 'MOWJILWEN', 'Jakob', 'J.MOWJILWEN', 10, 1990, 'H', 'jeune', 80, 80, 68, 76, 80, 79, 80, 80, 1, 5),"
				+"(268, 'OJHERG', 'Kristoff', 'K.OJHERG', 10, 1990, 'H', 'jeune', 80, 80, 68, 76, 80, 79, 80, 80, 1, 5),"
				+"(269, 'HERLIENAN', 'Alexander', 'A.HERLIENAN', 10, 1990, 'H', 'jeune', 80, 80, 68, 76, 80, 79, 80, 80, 1, 5),"
				+"(270, 'EGH', 'Jason', 'J.EGH', 10, 1990, 'H', 'jeune', 80, 80, 68, 76, 80, 79, 80, 80, 1, 5);"
				);
	}



	public void creerJoueur() {
		//Mise a jour de la table joueur
		ResultSet insert_joueur = Main.database.requete(
				"INSERT INTO joueurs "
						+ "(nom_joueur, id_equipe, id_biathlete_homme, "
						+ "id_biathlete_femme, id_date, annee) "
						+ "VALUES ('LeGrosCocoDu73', 10, 10, 158, 1, "+ Main.init_annee+");"
				);

		//Mise a jour de la table Biathlete carrière des biathletes actifs
	}

	public void initCarrierre(Biathlete biathlete_homme,Biathlete biathlete_femme, Joueur joueur ) {

		ResultSet select_biathlete = Main.database.requete("Select * from biathletes where statut_biathlete = 'actif'");

		try {while(select_biathlete.next()) {
			ResultSet insert_biathlete_carriere = Main.database.requete(""
					+ "INSERT INTO biathletescarriere"
					+ "("
					+ " id_joueur, "
					+ "id_biathlete,"
					+ " statut_biathlete, END, ACC, COU, DEB, VIT, SKI, REC, REG, POT, REN) "
					+ "VALUES ("
					+ joueur.getId_joueur() +","
					+ select_biathlete.getInt("id_biathlete") + ",'"
					+ select_biathlete.getString("statut_biathlete") + "',"
					+ select_biathlete.getInt("END") + ","
					+ select_biathlete.getInt("ACC") + ","
					+ select_biathlete.getInt("COU") + ","
					+ select_biathlete.getInt("DEB") + ","
					+ select_biathlete.getInt("VIT") + ","
					+ select_biathlete.getInt("SKI") + ","
					+ select_biathlete.getInt("REC") + ","
					+ select_biathlete.getInt("REG") + ","
					+ select_biathlete.getInt("POT") + ","
					+ select_biathlete.getInt("REN") + ");"

					);
		}
		} catch (SQLException e) {e.printStackTrace();}

		//Ajouter les deux biathletes créé !!!!

		//inserer homme dans biathlete
		ResultSet insert_homme_biathlete = Main.database.requete("INSERT INTO biathletes"
				+ "("
				+ "nom_biathlete,"
				+ "prenom_biathlete,"
				+ "libelle_biathlete,"
				+ "id_equipe,"
				+ "annee_biathlete,"
				+ "sexe_biathlete,"
				+ "statut_biathlete, END, ACC, COU, DEB, VIT, SKI, REC, REG, POT, REN) "
				+ "VALUES ('"
				+ biathlete_homme.getNom() +"','"
				+ biathlete_homme.getPrenom() +"','"
				+ biathlete_homme.getLibelle() + "',"
				+ biathlete_homme.getId_equipe() + ","
				+ biathlete_homme.getAge() + ",'"
				+ biathlete_homme.getSexe() + "','"
				+ biathlete_homme.getStatut() + "',"
				+ biathlete_homme.getEnd() + ","
				+ biathlete_homme.getAcc() + ","
				+ biathlete_homme.getCou() + ","
				+ biathlete_homme.getDeb() + ","
				+ biathlete_homme.getVit() + ","
				+ biathlete_homme.getSki() + ","
				+ biathlete_homme.getRec() + ","
				+ biathlete_homme.getReg() + ","
				+ biathlete_homme.getPot() + ","
				+ biathlete_homme.getRen() + ");"
				);

		//inserer femme dan biathlete
		ResultSet insert_femme_biathlete = Main.database.requete("INSERT INTO biathletes"
				+ "("
				+ "nom_biathlete,"
				+ "prenom_biathlete,"
				+ "libelle_biathlete,"
				+ "id_equipe,"
				+ "annee_biathlete,"
				+ "sexe_biathlete,"
				+ "statut_biathlete, END, ACC, COU, DEB, VIT, SKI, REC, REG, POT, REN) "
				+ "VALUES ('"
				+ biathlete_femme.getNom() +"','"
				+ biathlete_femme.getPrenom() +"','"
				+ biathlete_femme.getLibelle() + "',"
				+ biathlete_femme.getId_equipe() + ","
				+ biathlete_femme.getAge() + ",'"
				+ biathlete_femme.getSexe() + "','"
				+ biathlete_femme.getStatut() + "',"
				+ biathlete_femme.getEnd() + ","
				+ biathlete_femme.getAcc() + ","
				+ biathlete_femme.getCou() + ","
				+ biathlete_femme.getDeb() + ","
				+ biathlete_femme.getVit() + ","
				+ biathlete_femme.getSki() + ","
				+ biathlete_femme.getRec() + ","
				+ biathlete_femme.getReg() + ","
				+ biathlete_femme.getPot() + ","
				+ biathlete_femme.getRen() + ");"
				);


		//Mise a jour de la table Biathlete carrière des biathletes de joueurs
		ResultSet select_biathlete_joueur = Main.database.requete("Select * from biathletes where statut_biathlete = 'creation'");
		try {while(select_biathlete_joueur.next()) {
			//si homme
			if(select_biathlete_joueur.getString("sexe_biathlete") == "H") {
				ResultSet insert_homme_biathlete_carriere = Main.database.requete(""
						+ "INSERT INTO biathletescarriere"
						+ "("
						+ " id_joueur, "
						+ "id_biathlete,"
						+ " statut_biathlete, END, ACC, COU, DEB, VIT, SKI, REC, REG, POT, REN) "
						+ "VALUES ("
						+ joueur.getId_joueur() +","
						+ select_biathlete_joueur.getInt("id_biathlete") + ",'"
						+ select_biathlete_joueur.getString("statut_biathlete") + "',"
						+ select_biathlete_joueur.getInt("END") + ","
						+ select_biathlete_joueur.getInt("ACC") + ","
						+ select_biathlete_joueur.getInt("COU") + ","
						+ select_biathlete_joueur.getInt("DEB") + ","
						+ select_biathlete_joueur.getInt("VIT") + ","
						+ select_biathlete_joueur.getInt("SKI") + ","
						+ select_biathlete_joueur.getInt("REC") + ","
						+ select_biathlete_joueur.getInt("REG") + ","
						+ select_biathlete_joueur.getInt("POT") + ","
						+ select_biathlete_joueur.getInt("REN") + ");"

						);
				//update du statut des joueurs
				ResultSet update_biathlete_joueur = Main.database.requete("Update biathletes set statut_biathlete = 'joueur' where id_biathlete = " + select_biathlete_joueur.getInt("id_biathlete"));

			}else {
				//si femme
				ResultSet insert_femme_biathlete_carriere = Main.database.requete(""
						+ "INSERT INTO biathletescarriere"
						+ "("
						+ " id_joueur, "
						+ "id_biathlete,"
						+ " statut_biathlete, END, ACC, COU, DEB, VIT, SKI, REC, REG, POT, REN) "
						+ "VALUES ("
						+ joueur.getId_joueur() +","
						+ select_biathlete_joueur.getInt("id_biathlete") + ",'"
						+ select_biathlete_joueur.getString("statut_biathlete") + "',"
						+ select_biathlete_joueur.getInt("END") + ","
						+ select_biathlete_joueur.getInt("ACC") + ","
						+ select_biathlete_joueur.getInt("COU") + ","
						+ select_biathlete_joueur.getInt("DEB") + ","
						+ select_biathlete_joueur.getInt("VIT") + ","
						+ select_biathlete_joueur.getInt("SKI") + ","
						+ select_biathlete_joueur.getInt("REC") + ","
						+ select_biathlete_joueur.getInt("REG") + ","
						+ select_biathlete_joueur.getInt("POT") + ","
						+ select_biathlete_joueur.getInt("REN") + ");"

						);

				//update du statut des joueurs
				ResultSet update_biathlete_joueur = Main.database.requete("Update biathletes set statut_biathlete = 'joueur' where id_biathlete = " + select_biathlete_joueur.getInt("id_biathlete"));
			}



		}
		} catch (SQLException e) {e.printStackTrace();}
	}

	public void initPlanningCourse() {

		//Mise a jour de la table Course

		Object[][][] liste_type_course_default = new Object[][][]
				{
			{//Ostersund
				{2,2},
				{2,1},
				{3,5},
				{3,6},
				{4,11},
				{5,12},
				{6,3},
				{7,4}
			},
			{//Hochfilzen
				{8,6},
				{8,5},
				{9,4},
				{9,7},
				{10,8},
				{10,3}
			},
			{//Le grand Bornand
				{11,5},
				{12,6},
				{13,7},
				{13,8},
				{14,9},
				{14,10}
			},
			{//Oberhof
				{15,6},
				{16,5},
				{17,4},
				{17,3},
				{18,10},
				{18,9}
			},
			{//Rupolding
				{19,6},
				{20,5},
				{21,4},
				{22,3},
				{23,8},
				{23,7}
			},
			{//Polkljiuka
				{24,11},
				{25,12},
				{26,2},
				{26,1},
				{27,9},
				{27,10}
			},
			{//Mondiaux
				{28,1},
				{29,6},
				{30,5},
				{31,7},
				{31,8},
				{32,12},
				{33,11},
				{34,2},
				{35,4},
				{35,3},
				{36,9},
				{36,10}
			},
			{//Nove mesto
				{37,6},
				{38,5},
				{39,3},
				{39,4},
				{40,9},
				{41,10}
			},
			{//Kontiolahti
				{42,6},
				{42,5},
				{43,8},
				{43,7},
				{44,2},
				{44,1}
			},
			{//Kontiolahti
				{45,5},
				{45,6},
				{46,8},
				{46,7},
				{47,10},
				{47,9}
			}};

			//List des compet_id et ceux deja tiré
			ArrayList<Integer> list_compete_deja_tire = new ArrayList<>();
			ArrayList<Integer> list_compete = new ArrayList<>();
			//Rempli la liste des id compet
			ResultSet compet = Main.database.requete("Select id_compet from competitions;");
			//On rempli la liste d'ID
			try {while(compet.next()) {list_compete.add(compet.getInt("id_compet"));}
			} catch (SQLException e) {e.printStackTrace();}

			int indice_compet;

			//Boucle sur les 10 sites de la saison
			for(int i = 0; i<10;i++) {

				do {
					//Aléa sur les compet
					indice_compet = (int) Math.round((Math.random()*((float)list_compete.size()-1)));

					//jusqu'a ce quil en trouve un nouveau
				}while(list_compete_deja_tire.contains(list_compete.get(indice_compet)));
				//On met a jour la liste des site déja tié
				list_compete_deja_tire.add(list_compete.get(indice_compet));

				//boucle sur la liste de type course prédéfinie
				for(int j = 0 ; j < liste_type_course_default[i].length; j++) {

					int rang = i+1;
					ResultSet insert_course = Main.database.requete(								
							"INSERT INTO courses "
									+ "(id_joueur, annee, id_type_course, id_compet, id_date_course, rang_saison_compet, statut_course) "
									+ "VALUES ("
									+ Main.joueur.getId_joueur() + ","
									+ Main.joueur.getAnnee() +","
									+ liste_type_course_default[i][j][1]+" , " 
									+ list_compete.get(indice_compet) + " , '"
									+ liste_type_course_default[i][j][0]+ "' , "
									+ rang + ","
									+ " 'a venir');"
							);
				}
			}
	}

	public int getBareme(int classement) {
		if(classement < 41) {
			return classement;
		}else {
			return 100;
		}
	}

	//Inserrer une ligne dans participant
	public void insertLigneParticipant(int id_course, int nb_lignes) {
		for(int i = 0 ; i < nb_lignes; i++) {
			ResultSet insert_participants = Main.database.requete(								
					"INSERT INTO participants "
							+ "(id_course, classement, temps, id_bareme) "
							+ "VALUES ("
							+ id_course +","
							+ 0 + " , " 
							+ 0 + " , "
							+ 100 + ");"
					);
		}
	}

	//Inserer une ligne dans membre
	public void insertLigneMembreIndividuel(int id_course,ArrayList<Integer> liste_participants) {
		//liste des participants de la course en question
		ResultSet select_participant_course = Main.database.requete("SELECT * from participants where id_course = " + id_course);
		//Boucle sur les participants
		int cpt = 0;
		try {while(select_participant_course.next()) {
			//insert un membre
			ResultSet insert_membre = Main.database.requete(								
					"INSERT INTO membres "
							+ "(id_biathlete_carriere, id_participant, nb_pioche, nb_fautes) "
							+ "VALUES ("
							+ liste_participants.get(cpt) + ","
							+ select_participant_course.getInt("id_participant") + " , " 
							+ 0 + " , "
							+ 0 + ");"
					);

			//on incrémente le compteur
			cpt +=1;

		}} catch (SQLException ex) {ex.printStackTrace();}
	}

	public void insertLigneMembreEquipe(int id_course,ArrayList<ArrayList<Integer>> liste_participants) {
		//liste des participants de la course en question
		ResultSet select_participant_course = Main.database.requete("SELECT * from participants where id_course = " + id_course);

		//Boucle sur les equipes
		int cpt = 0;
		try {while(select_participant_course.next()) {

			//boucle sur les membres de l'équipe
			for(int i = 0 ; i < liste_participants.get(cpt).size() ; i++) {
				//insert un membre
				ResultSet insert_membre = Main.database.requete(								
						"INSERT INTO membres "
								+ "(id_biathlete_carriere, id_participant, nb_pioche, nb_fautes) "
								+ "VALUES ("
								+ liste_participants.get(cpt).get(i) + ","
								+ select_participant_course.getInt("id_participant") + " , " 
								+ 0 + " , "
								+ 0 + ");"
						);
			}

			//on incrémente le compteur
			cpt +=1;

		}} catch (SQLException ex) {ex.printStackTrace();}
	}

	public ArrayList<Integer> listParticipantsSprintIndiv(String sexe){
		ArrayList<Integer> liste_participants_individuel = new ArrayList<>();
		//Select les 110 meilleur biathletes 
		//AMELIORER EN RESPECTANT LES QUOTAS PAR NATIONS !!!
		ResultSet select_biathletes = Main.database.requete(			
				"SELECT biathletescarriere.* "
						+ "FROM biathletescarriere join biathletes on biathletes.id_biathlete = biathletescarriere.id_biathlete "
						+ "where biathletes.sexe_biathlete = '"+ sexe +"' "
						+ "and biathletescarriere.statut_biathlete = 'actif' "
						+ "and biathletescarriere.id_joueur = " + Main.joueur.getId_joueur() + " "
						+ "ORDER BY biathletescarriere.REN DESC LIMIT 110"
				);

		//Parcour les biathletes pour réccupérer l'ID
		try {while(select_biathletes.next()) {
			liste_participants_individuel.add(select_biathletes.getInt("id_biathlete_carriere"));
		}} catch (SQLException ex) {ex.printStackTrace();}

		return liste_participants_individuel;
	}

	public ArrayList<Integer> listParticipantsPoursuite(String sexe){
		ArrayList<Integer> liste_participants_individuel = new ArrayList<>();
		//Select les 60 meilleur biathletes de la poursuite
		//Classe les biathlete selon leur temps d'arrivé
		ResultSet select_participants = Main.database.requete("SELECT biathletescarriere.* , participants.temps "
				+" FROM biathletescarriere join biathletes on biathletes.id_biathlete = biathletescarriere.id_biathlete "
				+" join membres on biathletescarriere.id_biathlete_carriere = membres.id_biathlete_carriere "
				+" join participants on membres.id_participant = participants.id_participant "
				+" where biathletes.sexe_biathlete = '" + sexe +"' "
				//Recupere l'ID du sprint
				+" and id_course IN ("
				+" SELECT id_course "
				+" FROM courses "
				//Por le joueur courrant
				+" WHERE id_joueur = " + Main.joueur.getId_joueur()
				//Pour la competition courrante
				+" and id_compet IN ( "
				+ "SELECT id_compet From courses where id_course = " + Main.joueur.getId_course_courrante() + " ) "
				//Pour l'année courrante
				+" and annee = " + Main.joueur.getAnnee()
				//Pour le sprint
				+" and ( id_type_course = 5 OR id_type_course = 6)"
				+"  )"
				//Tri par temps et prend les 60 premiers
				+" ORDER BY participants.temps ASC LIMIT 60"
				);

		//Parcour les biathletes pour réccupérer l'ID
		try {while(select_participants.next()) {
			liste_participants_individuel.add(select_participants.getInt("id_biathlete_carriere"));
		}} catch (SQLException ex) {ex.printStackTrace();}

		return liste_participants_individuel;
	}

	public String selectClassement(int annee , int id_type_course ,String sexe, int id_course, int limit, boolean equipe) {
		//Id compet sera utile pour les 5 meileur pour les mass start
		String requete = "";

		requete = requete + "SELECT biathletes.nom_biathlete, biathletes.prenom_biathlete, biathletescarriere.id_biathlete_carriere , SUM(bareme.point) as pts FROM " + 
				"biathletes join biathletescarriere on biathletescarriere.id_biathlete = biathletes.id_biathlete " + 
				"join membres on membres.id_biathlete_carriere = biathletescarriere.id_biathlete_carriere " + 
				"join participants on membres.id_participant = participants.id_participant " + 
				"join bareme on bareme.id_bareme = participants.id_bareme " + 
				"join courses on courses.id_course = participants.id_course " + 
				" Where biathletescarriere.id_joueur = " + Main.joueur.getId_joueur();

		//Si on tri par sexe
		if(sexe != "") {
			requete = requete + " and biathletes.sexe_biathlete = '"+sexe+"' ";
		}
		
		//Si on tri par type de course
		if(id_type_course != 0) {
			requete = requete + " and courses.id_type_course = " + id_type_course;
		}

		//Si on tri par sexe
		if(annee != 0) {
			requete = requete + " and courses.annee = "+annee+" ";
		}

		//Si il ne s(agit pas d'une compet en particulier
		if(id_course !=0) {
			requete = requete + " and courses.id_compet IN (SELECT courses.id_compet FROM courses Where id_course = "+id_course+") " ;
		}
		
		//Si on tri par equipe (A MODIFIER C EST PAS AUSSI SIMPLE)
		if(equipe == true) {
			requete = requete + " group by biathletescarriere.id_equipe" ;
		}else {
			requete = requete + " group by biathletescarriere.id_biathlete_carriere" ;
		}
		
		//on ordonne par points 
		requete = requete + " order By pts DESC ";
		
		//Si on doit ajouter une limite
		if(limit!=0) {
			requete = requete + " LIMIT " + limit;
		}
		System.out.println("--- CLASSEMENT ---");
		System.out.println(requete);
		return requete;
	}

	public ArrayList<Integer> listParticipantsMassStart(String sexe){
		ArrayList<Integer> liste_participants_individuel = new ArrayList<>();
		//Select les 110 meilleur biathletes 
		//AMELIORER EN RESPECTANT LES QUOTAS PAR NATIONS !!!
		ResultSet select_biathletes_25 = Main.database.requete(			
				
				"SELECT biathletescarriere.id_biathlete_carriere , SUM(bareme.point) as pts FROM " + 
						"biathletes join biathletescarriere on biathletescarriere.id_biathlete = biathletes.id_biathlete " + 
						"join membres on membres.id_biathlete_carriere = biathletescarriere.id_biathlete_carriere " + 
						"join participants on membres.id_participant = participants.id_participant " + 
						"join bareme on bareme.id_bareme = participants.id_bareme " + 
						"join courses on courses.id_course = participants.id_course " + 
						" Where biathletes.sexe_biathlete = '"+sexe+"' " +
						" and biathletescarriere.id_joueur = " + Main.joueur.getId_joueur() +
						" group by biathletescarriere.id_biathlete_carriere" + 
						" order By pts DESC LIMIT 25"
				);

		//Parcour les biathletes pour réccupérer l'ID
		try {while(select_biathletes_25.next()) {
			liste_participants_individuel.add(select_biathletes_25.getInt("id_biathlete_carriere"));
		}} catch (SQLException ex) {ex.printStackTrace();}

		ResultSet select_biathletes_5 = Main.database.requete(	
				selectClassement(Main.joueur.getAnnee(), 0, sexe, Main.joueur.getId_course_courrante(), 0, false)
				);
		

		//Parcour les biathletes pour réccupérer l'ID
		try {while(select_biathletes_5.next()) {

			//Si on a pas nos 30 participants
			if(liste_participants_individuel.size() < 30) {

				//si il n'y est pas deja 
				if(liste_participants_individuel.contains(select_biathletes_5.getInt("id_biathlete_carriere")) == false) {
					//on l'ajoute
					liste_participants_individuel.add(select_biathletes_5.getInt("id_biathlete_carriere"));
				}
			}else {
				break;
			}

		}} catch (SQLException ex) {ex.printStackTrace();}



		return liste_participants_individuel;
	}

	public ArrayList<ArrayList<Integer>> listParticipantsRelais(String sexe , int nb_relayeur){
		ArrayList<ArrayList<Integer>> liste_participants_equipes = new ArrayList<>();
		ArrayList<Integer> liste_membres;

		//Si c'est mixte
		if(sexe == "M") {

			//Femme et Hommes
			ResultSet select_equipes = Main.database.requete(""
					+" SELECT id_equipe,count(*) as nb_membre"
					+" FROM biathletes join biathletescarriere on biathletescarriere.id_biathlete = biathletes.id_biathlete"
					+" WHERE biathletescarriere.statut_biathlete = 'actif'"
					+" and biathletescarriere.id_joueur = " + Main.joueur.getId_joueur()
					+" group by id_equipe, sexe_biathlete"
					+" HAVING nb_membre >= " + nb_relayeur
					);

			//ON FAUT UNE METHODE DE BAISE MAIS FLEMME DE REFLECHIR

			ArrayList list_equipe = new ArrayList<>();
			ArrayList list_equipe_1 = new ArrayList<>();
			System.out.println(list_equipe_1);
			//on parcour les équipes pour ajouter les membres
			try {while(select_equipes.next()) {
				//Si l'id aparait déja une fois
				if(list_equipe_1.contains(select_equipes.getInt("id_equipe"))) {
					//on l'ajoute pour valider la participation
					list_equipe.add(select_equipes.getInt("id_equipe"));
				}
				//On ajoue dans la liste id_equipe_1
				list_equipe_1.add(select_equipes.getInt("id_equipe"));
			}} catch (SQLException ex) {ex.printStackTrace();}

			System.out.println(list_equipe);

			//On boule sur les equipe selectionnées
			for (int i = 0 ; i<list_equipe.size();i++) {

				//liste des mambres de l'équipe
				liste_membres = new ArrayList<>();

				//On itere en fonction du nombre de memrbe dans l'équipe
				for(int j=3 ; j > nb_relayeur;j--) {
					//Les deux femmes
					ResultSet select_participants_f = Main.database.requete(""
							+ "select biathletes.libelle_biathlete ,biathletescarriere.* ,"
							//on définit un score
							+ " (biathletescarriere.COU + biathletescarriere.SKI + biathletescarriere.REN) AS score"
							+" from biathletescarriere join biathletes on biathletescarriere.id_biathlete = biathletes.id_biathlete"
							+" where biathletes.id_equipe = " + list_equipe.get(i)
							+" and biathletescarriere.id_joueur = " + Main.joueur.getId_joueur()
							+" and biathletescarriere.statut_biathlete = 'actif'"
							+" and biathletes.sexe_biathlete = 'F' "
							//On tri en décroissant par rapport au score calculé
							+" order by score DESC LIMIT " + nb_relayeur);

					//on parcour les équipes pour ajouter les membres
					try {while(select_participants_f.next()) {
						//On rempli la liste des biathletes
						liste_membres.add(select_participants_f.getInt("id_biathlete_carriere"));

					}} catch (SQLException ex) {ex.printStackTrace();}

					//Les deux femmes
					ResultSet select_participants_h = Main.database.requete(""
							+ "select biathletes.libelle_biathlete ,biathletescarriere.* ,"
							//on définit un score
							+ " (biathletescarriere.COU + biathletescarriere.SKI + biathletescarriere.REN) AS score"
							+" from biathletescarriere join biathletes on biathletescarriere.id_biathlete = biathletes.id_biathlete"
							+" where biathletes.id_equipe = " + list_equipe.get(i)
							+" and biathletescarriere.id_joueur = " + Main.joueur.getId_joueur()
							+" and biathletescarriere.statut_biathlete = 'actif'"
							+" and biathletes.sexe_biathlete = 'H' "
							//On tri en décroissant par rapport au score calculé
							+" order by score DESC LIMIT " + nb_relayeur);

					//on parcour les équipes pour ajouter les membres
					try {while(select_participants_h.next()) {

						//On rempli la liste des biathletes
						liste_membres.add(select_participants_h.getInt("id_biathlete_carriere"));

					}} catch (SQLException ex) {ex.printStackTrace();}

				}
				liste_participants_equipes.add(liste_membres);
			}



		}else {
			//sinon
			ResultSet select_equipes = Main.database.requete(""
					+" SELECT id_equipe,count(*) as nb_membre"
					+" FROM biathletes join biathletescarriere on biathletescarriere.id_biathlete = biathletes.id_biathlete"
					+" WHERE biathletescarriere.statut_biathlete = 'actif'"
					+" and biathletescarriere.id_joueur = " + Main.joueur.getId_joueur()
					+" and biathletes.sexe_biathlete = '"+ sexe +"' "
					+" group by id_equipe"
					+" HAVING nb_membre >=" + nb_relayeur
					);

			//on parcour les équipes pour ajouter les membres
			try {while(select_equipes.next()) {

				//liste des mambres de l'équipe
				liste_membres = new ArrayList<>();

				//Les deux femmes
				ResultSet select_participants = Main.database.requete(""
						+ "select biathletes.libelle_biathlete ,biathletescarriere.* ,"
						//on définit un score
						+ " (biathletescarriere.COU + biathletescarriere.SKI + biathletescarriere.REN) AS score"
						+" from biathletescarriere join biathletes on biathletescarriere.id_biathlete = biathletes.id_biathlete"
						+" where biathletes.id_equipe = " + select_equipes.getInt("id_equipe")
						+" and biathletescarriere.id_joueur = " + Main.joueur.getId_joueur()
						+" and biathletescarriere.statut_biathlete = 'actif'"
						+" and biathletes.sexe_biathlete = '"+sexe+"' "
						//On tri en décroissant par rapport au score calculé
						+" order by score DESC LIMIT " + nb_relayeur);

				//on parcour les équipes pour ajouter les membres
				try {while(select_participants.next()) {
					//On rempli la liste des biathletes
					liste_membres.add(select_participants.getInt("id_biathlete_carriere"));

				}} catch (SQLException ex) {ex.printStackTrace();}


				liste_participants_equipes.add(liste_membres);
			}} catch (SQLException ex) {ex.printStackTrace();}

		}

		return liste_participants_equipes;
	}



	public void selectResultat() {

	}


	//Initialise la table membre et participants avant la course
	public void initCourse(ResultSet course) {
		ArrayList<ArrayList<Integer>> liste_participants_equipes = new ArrayList<>();
		ArrayList<Integer> liste_participants_individuel = new ArrayList<>();


		try {
			switch(course.getInt("id_type_course")) {
			case 1://Relai mixte

				//Liste des participants
				liste_participants_equipes = listParticipantsRelais("M",2);

				//On créé les lignes dans participants
				insertLigneParticipant(course.getInt("id_course"),liste_participants_equipes.size());

				//Remplire la liste des biathletes 
				insertLigneMembreEquipe(course.getInt("id_course"),liste_participants_equipes);

				break;
			case 2://relai mixte simple

				//Liste des participants
				liste_participants_equipes = listParticipantsRelais("M",1);

				//On créé les lignes dans participants
				insertLigneParticipant(course.getInt("id_course"),liste_participants_equipes.size());

				//Remplire la liste des biathletes 
				insertLigneMembreEquipe(course.getInt("id_course"),liste_participants_equipes);
				break;
			case 3://relain homme

				//Liste des participants
				liste_participants_equipes = listParticipantsRelais("H",4);

				//On créé les lignes dans participants
				insertLigneParticipant(course.getInt("id_course"),liste_participants_equipes.size());

				//Remplire la liste des biathletes 
				insertLigneMembreEquipe(course.getInt("id_course"),liste_participants_equipes);
				break;
			case 4://relai femme

				//Liste des participants
				liste_participants_equipes = listParticipantsRelais("F",4);

				//On créé les lignes dans participants
				insertLigneParticipant(course.getInt("id_course"),liste_participants_equipes.size());

				//Remplire la liste des biathletes 
				insertLigneMembreEquipe(course.getInt("id_course"),liste_participants_equipes);

				break;
			case 5://sprint homme
				//Liste des participants
				liste_participants_individuel = listParticipantsSprintIndiv("H");

				//On créé les lignes dans participants
				insertLigneParticipant(course.getInt("id_course"),liste_participants_individuel.size());

				//Remplire la liste des biathletes 
				insertLigneMembreIndividuel(course.getInt("id_course"),liste_participants_individuel);
				break;
			case 6://sprint femme
				//Liste des participants
				liste_participants_individuel = listParticipantsSprintIndiv("F");

				//On créé les lignes dans participants
				insertLigneParticipant(course.getInt("id_course"),liste_participants_individuel.size());

				//Remplire la liste des biathletes 
				insertLigneMembreIndividuel(course.getInt("id_course"),liste_participants_individuel);
				break;
			case 7://poursuite homme

				//Liste des participants
				liste_participants_individuel = listParticipantsPoursuite("H");

				//On créé les lignes dans participants
				insertLigneParticipant(course.getInt("id_course"),liste_participants_individuel.size());

				//Remplire la liste des biathletes 
				insertLigneMembreIndividuel(course.getInt("id_course"),liste_participants_individuel);
				break;
			case 8://poursuite femme

				//Liste des participants
				liste_participants_individuel = listParticipantsPoursuite("F");

				//On créé les lignes dans participants
				insertLigneParticipant(course.getInt("id_course"),liste_participants_individuel.size());

				//Remplire la liste des biathletes 
				insertLigneMembreIndividuel(course.getInt("id_course"),liste_participants_individuel);
				break;
			case 9://masss start homme

				//Liste des participants
				liste_participants_individuel = listParticipantsMassStart("H");

				//On créé les lignes dans participants
				insertLigneParticipant(course.getInt("id_course"),liste_participants_individuel.size());

				//Remplire la liste des biathletes 
				insertLigneMembreIndividuel(course.getInt("id_course"),liste_participants_individuel);
				break;
			case 10://mass start femme

				//Liste des participants
				liste_participants_individuel = listParticipantsMassStart("F");

				//On créé les lignes dans participants
				insertLigneParticipant(course.getInt("id_course"),liste_participants_individuel.size());

				//Remplire la liste des biathletes 
				insertLigneMembreIndividuel(course.getInt("id_course"),liste_participants_individuel);
				break;
			case 11://indiv homme
				//Liste des participants
				liste_participants_individuel = listParticipantsSprintIndiv("H");

				//On créé les lignes dans participants
				insertLigneParticipant(course.getInt("id_course"),liste_participants_individuel.size());

				//Remplire la liste des biathletes 
				insertLigneMembreIndividuel(course.getInt("id_course"),liste_participants_individuel);
				break;
			case 12://indiv femme

				//Liste des participants
				liste_participants_individuel = listParticipantsSprintIndiv("F");

				//On créé les lignes dans participants
				insertLigneParticipant(course.getInt("id_course"),liste_participants_individuel.size());

				//Remplire la liste des biathletes 
				insertLigneMembreIndividuel(course.getInt("id_course"),liste_participants_individuel);
				break;
			}
		} catch (SQLException e) {e.printStackTrace();}
	}

	//Met a jour la table participant lorsqu'un biathlete a fini sa course
	//Met a jour la table membre aussi
	public void courseTermineBiathlete(int id_participant, int id_biathlete, int temps, int nb_pioches, int nb_fautes) {
		//Met a jour la table membre
		ResultSet update_membre = Main.database.requete(
				"UPDATE membres "
						+ "SET nb_fautes = " + nb_fautes + " , "
						+ " nb_pioche = " + nb_pioches
						+ " WHERE id_participant = " + id_participant
						+ " AND id_biathlete_carriere = " + id_biathlete
				);
		//Met a jour la table participant
		ResultSet update_participant = Main.database.requete(
				"UPDATE participants "
						+ "SET temps = " + temps
						+ " WHERE id_participant = " + id_participant
				);
	}

	//Met a jour la table de participants quand la course est terminé
	public void updateParticipationCourseTermine(int id_course){

		//Classe les biathlete selon leur temps d'arrivé
		ResultSet select_participants = Main.database.requete(
				"SELECT * FROM participants "
						+ "	WHERE id_course = " + id_course
						+ "	ORDER by temps ASC"
				);
		int classement = 1;

		//mise a jour de la table participant
		try {while(select_participants.next()) {
			//met a jour le classement
			ResultSet update_classement_participant = Main.database.requete(
					"UPDATE participants "
							+ "SET classement = " + classement
							+ " WHERE id_participant = "  + select_participants.getInt("id_participant")
					);

			//met a jour le bareme
			if(classement <= 40) {
				ResultSet update_bareme_participant = Main.database.requete(
						"UPDATE participants "
								+ "SET id_bareme = " + classement
								+ " WHERE id_participant = " + select_participants.getInt("id_participant")
						);
			}

			//on incrémente le classement
			classement +=1;
		}} catch (SQLException ex) {ex.printStackTrace();}

		//COnsidere que la course est terminé
		ResultSet update_course = Main.database.requete(
				"UPDATE courses "
						+ "SET statut_course = 'termine'"
						+ " WHERE id_course = " + id_course
				);

	}


	public void updateDateJoueur(int id_joueur) {
		//fait l'update sur la base
		ResultSet update_date_joueur = Main.database.requete(""
				+ "UPDATE joueurs "
				+ "Set id_date = id_date + 1 "
				+ "WHERE id_joueur = " + id_joueur
				);

		Main.joueur.setId_date_courrante(Main.joueur.getId_date_courrante()+1);
	}

	public void updateAnneeJoueur() {
		//fait l'update sur la base
		ResultSet update_annee_joueur = Main.database.requete(""
				+ "UPDATE joueurs "
				+ "Set annee = annee + 1 "
				+ "WHERE id_joueur = " + Main.joueur.getId_joueur()
				);
		Main.joueur.setAnnee(Main.joueur.getAnnee()+1);
	}

	//initialise les potentiels
	public void initPotentielCarriere(int id_joueur) {
		//prend tout les biathletes de la carriere
		ResultSet select_biathlete_joueur = Main.database.requete("Select * from biathletescarriere where id_joueur = " + id_joueur);
		try {while(select_biathlete_joueur.next()) {

			//Mise a jour du potentiel du biathlete courrant
			this.updatePotentiel(select_biathlete_joueur.getInt("id_biathlete_carriere"));

		}} catch (SQLException e) {e.printStackTrace();}

	}

	//Mise a jour du potentiel
	public void updatePotentiel(int id_biathlete) {
		//Genere un numéro de potentiel aléatoire
		int potentiel = (int) Math.round((Math.random() * 7) + 1);
		//fait l'update sur la base
		ResultSet insert_course = Main.database.requete(""
				+ "UPDATE biathletescarriere "
				+ "Set POT = " + potentiel + " "
				+ "WHERE id_biathlete_carriere = " + id_biathlete
				);
	}


	/*
	//Collection du classement
	ListeEnregistrement list = new ListeEnregistrement();

	try {

		//On stock le resultat de la requete dans un objet resultset
		ResultSet resulttest = statement.executeQuery("select * from pays");
		//On parcour le resultat de la requete
		while (resulttest.next()) {
			//On affiche la requete

			//System.out.println("Id : " + resulttest.getInt("Id_Nationalite") + " Nationalite : " + resulttest.getString("Nationalite") );
			//On créé un objet record a partir de la requete
			//System.out.println(chaine);
			Enregistrement record;
			record = new Enregistrement(resulttest.getInt("id_pays"),resulttest.getString("libelle_pays"));
			//record.toString();
			//On ajoue l'objet a la liste
			list.ajouteDonnee(record);
		}

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;*/

}
