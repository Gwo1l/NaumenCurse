package ru.Robert.NauJava;

import org.springframework.beans.factory.annotation.Autowired;
import ru.Robert.NauJava.BusinessLogic.ContactService;

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

        Long id = Long.valueOf(cmd[1]);
        String login = cmd[2];
        String number = cmd[3];
        String country = cmd[4];
        String gender = cmd[5];

        switch (cmd[0])
        {
            case "create" -> {
                contactService.createContact(id, login, number, country, gender);
                System.out.println("Пользователь успешно добавлен...");
            }

            case "read" -> {
                System.out.println("Пользователь: ");
                System.out.println(contactService.findById(Long.valueOf(cmd[1])));
            }

            case "update" -> {
                if (!login.isEmpty()) {
                    contactService.updateLogin(id, login);
                }
                if (!number.isEmpty()) {
                    contactService.updateNumber(id, number);
                }
                System.out.println("Пользователь успешно обновлен...");
            }

            case "delete" -> {
                contactService.deleteById(id);
                System.out.println("Пользователь успешно удален...");
            }

            default -> System.out.println("Введена неизвестная команда...");
        }
    }
}
