package com.biathlon.jeu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

		try {
			//Création du statement pour faire les requetes
			Statement statement = connection.createStatement();
			ResultSet output_query = statement.executeQuery(requete);
			return output_query;
		} catch (SQLException e1) {e1.printStackTrace();}
		return null;
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
