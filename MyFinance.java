import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/***
 * A sample project using the following concepts POJO , ArrayList , OOP and
 * other basic concept
 * 
 * @author Eganathan R
 *
 */
public class MyFinance {
	static MyFinance current;
	private int counter = 0;
	private ArrayList<People> peoplesList = new ArrayList<>();

	// Initial Loading of data from text file
	static {
		current = new MyFinance();
		current.importPeople();
	}	
	
	// Load- deals the first step
	void start() {
		try (Scanner sc = new Scanner(System.in)) {

			while (true) {
				menu();
				int option = sc.nextInt();
				switch (option) {
				case 1:
					System.out.println("Please enter the name : ");
					searchPeople(sc.nextLine());
				case 2:
					printEveryone();
					break;
				case 3:
					registration();
					break;
				case 4:
					importPeople();
					break;
				case 5:
					exportPeople();
					break;
				case 6:
					System.out.println(" Bye ....");
					System.exit(0);
					break;
				default:
					System.out.println("Sorry Invalid Input!, Please try again. ");
				}

			}
		}
	}

	// prints the Main Menu
	void menu() {
		System.out.println("1. Search People");
		System.out.println("2. View All People");
		System.out.println("3. Register new people");
		System.out.println("4. Import peoples (.txt)");
		System.out.println("5. Export peoples (.txt)");
		System.out.println("6. Exit");
	}

	// Registration Process
	boolean registration() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Hi Whats your name ?");
		String name = sc.nextLine();

		// validating name
		if (name.equals("") || name.length() <= 0 || name == null) {
			System.out.println("Please input a valid name! ");
			return false;
		}
		System.out.println("whats your age " + name + " ?");
		int age = sc.nextInt();
		// validating age
		if (age <= 0 || age >= 100) {
			System.out.println("Please input a valid age! ");
			return false;
		}
		System.out.print(" Please mentione your gender \n 1 for Male and 2 for female \n");
		int g = sc.nextInt();
		Gender gender = g == 1 ? Gender.MALE : Gender.FEMALE;

		// validating name
		if (gender == null) {
			System.out.println("No Valid Gender input! ");
			return false;
		}

		int ID = counter++;
		if (registerPeople(name, age, gender, ID))
			System.out.println("Succesfully Created!");

		return true;
	}

	// Creation of People
	boolean registerPeople(String name, int age, Gender gender, int ID) {
		peoplesList.add(new People(name, age, gender, ID));
		return true;
	}

	// Search People
	void searchPeople(String s) {
		for (People val : peoplesList) {
			if (val.equals(s)) {
				printPeople(val);
			}
		}
	}

	// Print Everyone
	void printEveryone() {
		for (People val : peoplesList) {
			printPeople(val);
		}
	}

	// Print People
	void printPeople(People v) {
		System.out.println("==================================");
		System.out.println("\t Name \t: " + v.getName());
		System.out.println("\t Age \t: " + v.getAge());
		System.out.println("\t Gender \t: " + v.getGender());
		System.out.println(" ");
	}

	// Exporting People to a file
	boolean exportPeople() {

		File f = new File("peopleExported.ppl");

		try {
			if (!f.exists())
				f.createNewFile();

			// if there is any content i will override it
			FileWriter w = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(w);

			for (People val : peoplesList) {

				// preparing the string
				String values = "" + val.getName() + "%" + val.getAge() + "%" + val.getGender() + "%" + "%"
						+ val.getID() + "\n";

				bw.write(values);
				bw.flush();
			}
			bw.close();

		} catch (IOException e) {
			System.out.println("Sorry we couldnt process it now!");
		}

		System.out.println(" Succesfully Exported! ");

		return true;
	}

	// Import People to ArrayList
	boolean importPeople() {
		File f = new File("peopleExported.ppl");
		try {
			// checking if file exists
			if (!f.exists())
				System.out.println("Sorry file does not exist!");

			// if there is any content i will override it
			FileReader r = new FileReader(f);
			BufferedReader br = new BufferedReader(r);
			String value = br.readLine();

			// runs till the value is null
			while (value != null) {
				String[] splits = value.split("%");
				int age = Integer.valueOf(splits[1]);
				Gender gen = splits[3].equals("MALE") ? Gender.MALE : Gender.FEMALE;

				peoplesList.add(new People(splits[0], age, gen, Integer.valueOf(splits[4])));
				value = br.readLine();
			}

			br.close();

		} catch (IOException e) {
			System.out.println("Sorry we couldnt process it now! ");
		}

		System.out.println(" Succesfully Exported! ");

		return true;
	}
	
	
	//Main Method
	public static void main(String[] args) {

		// Just to call the methods
		current.start();
	}


}
