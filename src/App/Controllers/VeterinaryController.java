package App.Controllers;

import java.util.List;

import App.Models.Pet;
import App.Services.VeterinaryService;

public class VeterinaryController extends Controller {
	public static final List<Pet> getList() {
		if (VeterinaryService.getPets() == null)
			VeterinaryService.initializeList();

		return VeterinaryService.getPets();
	}

	public static final void add(String name, Integer age, Boolean isMale) {
		if (name.length() == 0 || age == null) return;
			
		VeterinaryService.addPet(new Pet(name, age, isMale ? "Macho" : "Hembra"));
	}

	public static final void remove(Integer petIndex) {
		VeterinaryService.removePet(VeterinaryService.getPets().get(petIndex));
	}
}
