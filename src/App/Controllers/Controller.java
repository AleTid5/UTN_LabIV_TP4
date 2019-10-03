package App.Controllers;

public abstract class Controller {	
	protected static final void assertOrFail(Boolean comparison, Exception ex) throws Exception {
		if (! comparison) throw ex;
	}
}