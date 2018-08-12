package com.skilldistillery.filmquery.app;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		app.launch();
//		app.test();
	}

//	private void test() {
//
//		Film film = db.getFilmById(-42);
//		System.out.println(film);
//
//		Actor actor = db.getActorById(1);
//		System.out.println(actor);
//
//		List<Actor> test = db.getActorsByFilmId(1);
//		System.out.println(test);
//
//	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {

		boolean condition = true;
		String userInput = null;

		do {

			System.out.println("=== MENU ===");
			System.out.println("What would you like to do?");
			System.out.println("1. Get Film");
			System.out.println("2. Get Actor");
			System.out.println("3. Get List of Actors In Film");
			System.out.println("4. Exit");
			System.out.print("Input: ");
			userInput = input.next();

			if (!(userInput.equals("1")) && !(userInput.equals("2")) && !(userInput.equals("3"))
					&& !(userInput.equals("4"))) {
				System.out.println("\nIncorrect input.\n");
			} else if (userInput.equals("4")) {
				System.out.println("Exiting program...");
				System.exit(0);
			} else {
				condition = false;
			}

		} while (condition);

		System.out.println();

		int idInput;
		switch (userInput) {
		case "1":
			boolean innerCondition = true;
			try {

				do {
					System.out.println("=== OPTIONS ===");
					System.out.println("1. Search film by id");
					System.out.println("2. Search film by keyword");
					System.out.println("3. Exit");
					System.out.print("Input: ");
					int innerMenuInput = input.nextInt();

					switch (innerMenuInput) {
					case 1:
						System.out.print("Enter the film id: ");
						idInput = input.nextInt();
						if (db.getFilmById(idInput) != null) {
							System.out.println(db.getFilmById(idInput));
						} else {
							System.out.println("\nReturn Query: Film not found.\n");
						}
						break;
					case 2:
						System.out.print("Enter film keyword(s): ");
						break;
					default:
						break;
					}

					if (innerMenuInput != 1 && innerMenuInput != 2 && innerMenuInput != 3) {
						System.out.println("\nIncorrect input.\n");
					} else if (innerMenuInput == 3) {
						System.out.println("Exiting program...");
						System.exit(0);
					} else {
						innerCondition = false;
					}

				} while (innerCondition);

			} catch (InputMismatchException e) {
				System.out.println(e);
			}

			break;
		case "2":
			System.out.print("Enter the actor id: ");
			idInput = input.nextInt();
			System.out.println("\nReturn Query: " + db.getActorById(idInput));
			break;
		case "3":
			System.out.println("Enter the film id: ");
			idInput = input.nextInt();
			System.out.println("\nReturn Query: " + db.getActorsByFilmId(idInput));
			break;
		default:
			break;
		}

	}

}
