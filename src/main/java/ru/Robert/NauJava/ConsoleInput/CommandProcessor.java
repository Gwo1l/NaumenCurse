package ru.Robert.NauJava.ConsoleInput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.Robert.NauJava.BusinessLogic.ContactService;
import ru.Robert.NauJava.Entities.Contact;

@Component
public class CommandProcessor {
    private final ContactService contactService;
    @Autowired
    public CommandProcessor(ContactService contactService)
    {
        this.contactService = contactService;
    }
    public void processCommand(String input)
    {
        String[] cmd = input.split(" ");

        switch (cmd[0])
        {
            case "create" -> {
                String login = cmd[1];
                String number = cmd[2];
                String country = cmd[3];
                String gender = cmd[4];

                contactService.createContact(login, number, country, gender);
                System.out.println("Пользователь успешно добавлен...");
            }

            case "read" -> {
                Long id = Long.valueOf(cmd[1]);
                Contact contact = contactService.findById(id);

                if (contact == null) {
                    System.out.println("Такого контакта нет...");
                    break;
                }

                String login = contact.getName();
                String number = contact.getPhoneNumber();
                String country = contact.getCountry();
                String gender = contact.getGender();

                System.out.println(String.format("Пользователь: %s (%s, %s, %s)",
                        login, number, country, gender));
            }

            case "updateLogin" -> {
                Long id = Long.valueOf(cmd[1]);
                String login = cmd[2];

                contactService.updateLogin(id, login);
                System.out.println("Имя пользователя успешно обновлено...");
            }

            case "updateNumber" -> {
                Long id = Long.valueOf(cmd[1]);
                String number = cmd[2];

                contactService.updateNumber(id, number);
                System.out.println("Номер пользователя успешно обновлен...");
            }

            case "delete" -> {
                Long id = Long.valueOf(cmd[1]);

                contactService.deleteById(id);
                System.out.println("Пользователь успешно удален...");
            }

            case "help" -> {
                System.out.println("create <login, number, country, gender> - создание контакта");
                System.out.println("read <id> - получение контакта");
                System.out.println("updateLogin <id, login> - изменение логина контакта");
                System.out.println("updateNumber <id, number> - изменение номера контакта");
                System.out.println("delete <id> - удаление контакта");
            }

            default -> System.out.println("Введена неизвестная команда...");
        }
    }
}
