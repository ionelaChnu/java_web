
public class Student {
	String name;
	String surname;
	int age;
	int markSubject1;
	int markSubject2;
	int markSubject3;
	
	public Student() {
		super();
	}
	
	public Student(String name, String surname, int age, int markSubject1, int markSubject2,
			int markSubject3) {
		super();
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.markSubject1 = markSubject1;
		this.markSubject2 = markSubject2;
		this.markSubject3 = markSubject3;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getMarkSubject1() {
		return markSubject1;
	}
	public void setMarkSubject1(int markSubject1) {
		this.markSubject1 = markSubject1;
	}
	public int getMarkSubject2() {
		return markSubject2;
	}
	public void setMarkSubject2(int markSubject2) {
		this.markSubject2 = markSubject2;
	}
	public int getMarkSubject3() {
		return markSubject3;
	}
	public void setMarkSubject3(int markSubject3) {
		this.markSubject3 = markSubject3;
	}
	
	@Override
	public String toString() {
		return "Student [name=" + name + ", surname=" + surname + ", age=" + age + ", markSubject1="
				+ markSubject1 + ", markSubject2=" + markSubject2 + ", markSubject3=" + markSubject3 + "]";
	}
	
}
