package junit5demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class StudentManagementTest {
   private StudentManager studentManager;

   @BeforeEach
   public void setup() {
      System.out.println("Instantiating Student Manager");
      studentManager = new StudentManager();
   }

   @AfterEach
   public void tearDown() {
      studentManager = null;
      System.out.println("Should Execute After Each Test");
   }

   @Test
   @DisplayName("it should create a student")
   public void shouldCreateStudent() {
      studentManager.createStudent("Ali", "Can", "05421234567");
      assertFalse(studentManager.getAllStudent().isEmpty());
      assertEquals(1, studentManager.getAllStudent().size());
   }

   @Test
   @DisplayName("it should throw runtime exception when First Name is null")
   public void shouldTrowRuntimeExceptionWhenFirstNameIsNull() {
      assertThrows(RuntimeException.class, () -> studentManager.createStudent(null, "Can", "05421234567"));
   }

   @Test
   @DisplayName("it should throw runtime exception when last Name is null")
   public void shouldTrowRuntimeExceptionWhenLastNameIsNull() {
      assertThrows(RuntimeException.class, () -> studentManager.createStudent("Ali", null, "05421234567"));
   }

   @Test
   @DisplayName("it should throw exception when phone number is null")
   public void shouldTrowRuntimeExceptionWhenPhoneNumberIsNull() {
      assertThrows(RuntimeException.class, () -> studentManager.createStudent("Ali", "Can", null));
   }
   @Nested
   class RepeatedTests {
      @DisplayName("Repeat Student Creation Test 5 Times")
      @RepeatedTest(value = 5)
        public void shouldTestStudentCreationWithRepetation() {
            studentManager.createStudent("Ali", "Can", "05422345678");
            assertFalse(studentManager.getAllStudent().isEmpty());
            assertEquals(1, studentManager.getAllStudent().size());
        }
    }

   @Nested
   class ParameterizedTests {

      @DisplayName("Phone Number should match the required Format")
      @ParameterizedTest
      @ValueSource(strings = { "05353333333", "05412345678", "05329876543" })
      public void shouldTestPhoneNumberWithValueSource(String phoneNumber) {
         studentManager.createStudent("Ali", "Can", phoneNumber);
         assertFalse(studentManager.getAllStudent().isEmpty());
         assertEquals(1, studentManager.getAllStudent().size());
      }

      @DisplayName("CSV Source Case - Phone Number should match the required Format")
      @ParameterizedTest
      @CsvSource({ "05353333333", "05412345678", "05329876543" })
      public void shouldTestPhoneNumberWithCSVSource(String phoneNumber) {
         studentManager.createStudent("Ali", "Can", phoneNumber);
         assertFalse(studentManager.getAllStudent().isEmpty());
         assertEquals(1, studentManager.getAllStudent().size());
      }

      @DisplayName("CSV File Source Case - Phone Number should match the required Format")
      @ParameterizedTest
      @CsvFileSource(resources = "/data.csv")

      public void shouldTestPhoneNumberWithCSVFileSource(String phoneNumber) {
         studentManager.createStudent("Ali", "Can", phoneNumber);
         assertFalse(studentManager.getAllStudent().isEmpty());
         assertEquals(1, studentManager.getAllStudent().size());
      }
   }

    @DisplayName("Method Source  - Phone Number should match the required Format")
    @ParameterizedTest
    @MethodSource("phoneNumberList")
    public void shouldTestPhoneNumberFormatWithMethodSource(String phoneNumber) {
        studentManager.createStudent("Ali", "Can", phoneNumber);
        assertFalse(studentManager.getAllStudent().isEmpty());
        assertEquals(1, studentManager.getAllStudent().size());
    }

    private static List<String> phoneNumberList() {
        return Arrays.asList("05422223344", "02122345678");
    }

  
}
