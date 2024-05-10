import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Введите данные (Фамилия Имя Отчество дата_рождения номер_телефона пол): ");
            String input = reader.readLine();
            String[] data = input.split(" ");

            if (data.length != 6) {
                System.out.println("Введено неверное количество данных, должно быть Ф.И.О. дата рождения," +
                        " номер телефона и пол.");
                return;
            }

            String lastName = data[0];
            String firstName = data[1];
            String patronymic = data[2];

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date birthDate;
            try {
                birthDate = dateFormat.parse(data[3]);
            } catch (ParseException e) {
                throw new InvalidDataFormatException("Дата рождения введена не верно," +
                        " должна быть в формате: dd.mm.yyyy.");
            }

            long phoneNumber;
            try {
                phoneNumber = Long.parseLong(data[4]);
            } catch (NumberFormatException e) {
                throw new InvalidDataFormatException("Номер телефона должен быть прописан цифрами.");
            }

            char gender = data[5].toLowerCase().charAt(0);
            if (gender != 'f' && gender != 'm') {
                throw new InvalidDataFormatException("Пол введён не верно, верный формат буквой f или M.");
            }

            UserData userData = new UserData(lastName, firstName, patronymic, birthDate, phoneNumber, gender);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(lastName + ".txt", true))) {
                writer.write(userData.toString());
                writer.newLine();
                System.out.println("Данные успешно записаны в файл " + lastName + ".txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (InvalidDataFormatException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}