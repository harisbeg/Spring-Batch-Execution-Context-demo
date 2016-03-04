package hello;

public class Person {
	private String lastName;
	private String firstName;

	public Person() {

	}

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "firstName: " + firstName + ", lastName: " + lastName;
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}

		if (!Person.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		
		final Person other = (Person) obj;
		
		if ((null == this.firstName) || (null == this.lastName)) {
			return false;
		}
		
		if ((null == other.firstName) || (null == other.lastName)) {
			return false;
		}
		
		if (!this.firstName.equalsIgnoreCase(other.firstName) || !this.lastName.equalsIgnoreCase(other.lastName)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int hash = 19;
	    hash = 23 * hash + (this.firstName != null ? this.firstName.hashCode() : 0);
	    hash = 23 * hash + (this.lastName != null ? this.lastName.hashCode() : 0);
	    return hash;
	}

}