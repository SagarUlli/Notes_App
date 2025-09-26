package com.notesapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class NotesApp {
	private static final String FILE_NAME = "notes.txt";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int choice;

		do {
			System.out.println("\n--- Notes App ---");
			System.out.println("1. Add Note");
			System.out.println("2. View Notes");
			System.out.println("3. Exit");
			System.out.print("Choose an option: ");
			choice = scanner.nextInt();
			scanner.nextLine(); // consume newline

			switch (choice) {
			case 1:
				addNote(scanner);
				break;
			case 2:
				viewNotes();
				break;
			case 3:
				System.out.println("Exiting Notes App.");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		} while (choice != 3);

		scanner.close();
	}

	private static void addNote(Scanner scanner) {
		System.out.print("Enter your note: ");
		String note = scanner.nextLine();

		try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
			writer.write(note + System.lineSeparator());
			System.out.println("Note saved.");
		} catch (IOException e) {
			System.out.println("Error writing to file: " + e.getMessage());
		}
	}

	private static void viewNotes() {
		System.out.println("\n--- Your Notes ---");
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
			String line;
			boolean empty = true;
			while ((line = reader.readLine()) != null) {
				System.out.println("- " + line);
				empty = false;
			}
			if (empty) {
				System.out.println("[No notes found.]");
			}
		} catch (FileNotFoundException e) {
			System.out.println("[Notes file not found. Add a note to create it.]");
		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}
	}
}
