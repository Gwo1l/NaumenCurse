package ru.Robert.NauJava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import ru.Robert.NauJava.Entities.Contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Config {
    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public HashMap<Long, Contact> contactContainer() {
        return new HashMap<>();
    }

    @Autowired
    private CommandProcessor commandProcessor;
    @Bean
    public CommandLineRunner commandScanner() {
        return args ->
        {
            try (Scanner scanner = new Scanner(System.in))
            {
                System.out.println("Введите команду. 'exit' для выхода.");
                while (true)
                {
// Показать приглашение для ввода
                    System.out.print("> ");
                    String input = scanner.nextLine();
// Выход из цикла, если введена команда "exit"
                    if ("exit".equalsIgnoreCase(input.trim()))
                    {
                        System.out.println("Выход из программы...");
                        break;
                    }
// Обработка команды
                    commandProcessor.processCommand(input);
                }
            }
        };
    }
}
