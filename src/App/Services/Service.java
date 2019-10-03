package App.Services;

public abstract class Service {
	public static final String toDBString(String string) {
		return "\"" + string + "\"";
	}
}
