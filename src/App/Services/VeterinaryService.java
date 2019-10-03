package App.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import App.Models.Pet;

public class VeterinaryService extends Service {
	private static List<Pet> pets = null;

	public static List<Pet> getPets() {
		return pets;
	}

	public static void addPet(Pet pet) {
		pets.add(pet);
		ConnectionService.insert("mascotas", Arrays.asList("Nombre", "Edad", "Sexo"),
				Arrays.asList(toDBString(pet.getName()), Integer.toString(pet.getAge()), toDBString(pet.getGender())));
		VeterinaryService.hydrateList();
	}

	public static void removePet(Pet pet) {
		pets.remove(pet);
		ConnectionService.delete("mascotas", pet.getId());
		VeterinaryService.hydrateList();
	}

	public static void initializeList() {
		VeterinaryService.pets = new ArrayList<Pet>();
		VeterinaryService.hydrateList();
	}

	public static void hydrateList() {
		pets = ConnectionService.findAll("mascotas");
	}
}
