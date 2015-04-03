package backendEntities;

import java.util.Comparator;

public class ContactComparator implements Comparator<Contact>{

	public int compare(Contact arg0, Contact arg1) {
		return arg0.getPropertyName().compareTo(arg1.getPropertyName());
	}

}
