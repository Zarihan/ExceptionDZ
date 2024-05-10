import java.text.SimpleDateFormat;
import java.util.Date;

class UserData {
    String lastName;
    String firstName;
    String patronymic;
    Date birthDate;
    long phoneNumber;
    char gender;

    public UserData(String lastName, String firstName, String patronymic, Date birthDate, long phoneNumber, char gender) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return lastName + " " + firstName + " " + patronymic + " " + dateFormat.format(birthDate) + " " + phoneNumber + " " + gender;
    }
}

class InvalidDataFormatException extends Exception {
    public InvalidDataFormatException(String message) {
        super(message);
    }
}