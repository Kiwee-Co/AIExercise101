package exercise.coding.kiwee.ai.superheros;

public abstract class Superhero {
	private String name;
	private String superpower;
	
	public Superhero(String name, String superpower) {
        this.name = name;
        this.superpower = superpower;
    }
	
	public String getSuperpower() {
		return name + " with superpower: " + superpower;
	}
}
