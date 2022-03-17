
/***
 * People POJO CLASS
 * @author Eganathan.R
 *
 */
public class People {
	private String name;
	private int age;
	private Gender gender;
	private final int ID;

	People(String name, int age, Gender gender, int ID) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.ID = ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getID() {
		return ID;
	}

	
}
