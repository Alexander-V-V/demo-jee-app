public class Employee {

    private final String firstName;
    private final String lastName;
    private final int age;
    private final String gender;

  public Employee(String firstName, String lastName, int age, String gender) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.gender = gender;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public int getAge() {
    return age;
  }

  public String getGender() {
    return gender;
  }

  @Override
  public String toString() {
    // name, last name, age, gender
    return String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>", firstName, lastName, age, gender);
  }
}
