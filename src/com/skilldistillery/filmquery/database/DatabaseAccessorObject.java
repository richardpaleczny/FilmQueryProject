//	All JDBC code will be in methods of the DatabaseAccessorObject class.
//	
//	Implement the getFilmById method that takes an int film ID,
//	and returns a Film object (or null, if the film ID returns no data.)

//	Implement getActorById method that takes an int actor ID, and returns
//	an Actor object (or null, if the actor ID returns no data.)

//	Implement getActorsByFilmId with an appropriate List implementation
//	that will be populated using a ResultSet and returned.

//	Make sure your JDBC code uses PreparedStatement with bind variables
//	instead of concatenating values into SQL strings.

package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private String user = "student";
	private String pass = "student";
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
	}

	@Override
	public Film getFilmById(int filmId) {

		String sql = "SELECT id, title, description, release_year, "
				+ "language_id, rental_duration, rental_rate, length, "
				+ "replacement_cost, rating, special_features FROM film WHERE film.id = ?";

		Film film = null;
		try (Connection conn = DriverManager.getConnection(URL, user, pass);) {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next() == true) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String desc = rs.getString(3);
				short releaseYear = rs.getShort(4);
				int langId = rs.getInt(5);
				int rentDur = rs.getInt(6);
				double rate = rs.getDouble(7);
				int length = rs.getInt(8);
				double repCost = rs.getDouble(9);
				String rating = rs.getString(10);
				String features = rs.getString(11);
				film = new Film(filmId, title, desc, releaseYear, langId, rentDur,
						rate, length, repCost, rating,
						features);
			}
			
			// Get language string
			sql = "SELECT language.name FROM language JOIN film "
					+ "ON film.language_id = language.id "
					+ "WHERE film.id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,  filmId);
			rs = stmt.executeQuery();
			if (rs.next() == true) {
				String language = rs.getString(1);
				film.setLanguage(language);
			}

			// Get all actors of film id given and put them in Film object's inherent list field
			// Do this by calling the other method in this class *getActorsByFilmId()*
			film.setActorList(getActorsByFilmId(filmId));

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			System.err.println(e);
		} catch (NullPointerException e) {
			return null;
		}

		if (film == null) {
			return null;
		} else {
			return film;
		}
	}

	@Override
	public Actor getActorById(int actorId) {

		String sql = "SELECT actor.id, actor.first_name, actor.last_name " 
		+ "FROM actor WHERE actor.id = ?";

		Actor actor = null;
		try (Connection conn = DriverManager.getConnection(URL, user, pass);) {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next() == true) {
				int id = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				actor = new Actor(id, firstName, lastName);
			}

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			System.err.println(e);
		} catch (NullPointerException e) {
			return null;
		}

		if (actor == null) {
			return null;
		} else {
			return actor;
		}
	}

	@Override
	public List<Actor> getActorsByFilmId(int filmId) {

		List<Actor> actorList = new ArrayList<>();

		String sql = "SELECT actor.id, actor.first_name, actor.last_name "
				+ "FROM actor JOIN film_actor ON film_actor.actor_id = actor.id "
				+ "JOIN film ON film.id = film_actor.film_id WHERE film.id = ?";

		Actor actor = null;
		try (Connection conn = DriverManager.getConnection(URL, user, pass);) {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				actor = new Actor(id, firstName, lastName);
				actorList.add(actor);
			}

		} catch (SQLException e) {
			System.err.println(e);
		} catch (NullPointerException e) {
			return null;
		}

		return actorList;
	}

}
