package App.Models;

public class Pet {
	private Integer id = null;
	private String name = null;
	private Integer age = null;
	private String gender = null;
	
	public Pet(Integer id, String name, Integer age, String gender) {
		this(name, age, gender);
		this.id = id;
	}
	
	public Pet(String name, Integer age, String gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Integer getAge() {
		return this.age;
	}
	
	public String getGender() {
		return this.gender;
	}
	
	@Override
	public String toString() {
		return String.format("%d | %s | %d | %s",
				this.id,
				this.name,
				this.age,
				this.gender);
	}
	
	@Override
	public boolean equals(Object pet) {
		return this.getId() == ((Pet) pet).getId();
	}
}
