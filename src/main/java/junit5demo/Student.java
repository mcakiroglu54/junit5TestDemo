package junit5demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Student {
  private String firstName;
  private String lastName;
  private String phoneNumber;

  public void checkFirstName() {
    if (this.firstName.isBlank()) {
      throw new RuntimeException("The first name can not be blank.");
    }
  }

  public void checkLastName() {
    if (this.lastName.isBlank()) {
      throw new RuntimeException("The last name can not be blank.");
    }
  }

  public void checkPhoneNumber() {
    if (this.phoneNumber.isBlank()) {
      throw new RuntimeException("The phone nubmer can not be blank.");
    }
    if (this.phoneNumber.length() != 11) {
      throw new RuntimeException("The phone nubmer should be eleven digit.");
    }
  }
}
