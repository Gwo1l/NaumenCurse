package ru.Robert.NauJava.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.Robert.NauJava.CRUDRepositories.ContactRepository;
import ru.Robert.NauJava.CRUDRepositories.CountryRepository;
import ru.Robert.NauJava.CRUDRepositories.ReportRepository;
import ru.Robert.NauJava.Entities.Country;
import ru.Robert.NauJava.Entities.Report;

import java.util.*;
import java.util.concurrent.CompletableFuture;

import static ru.Robert.NauJava.Entities.Report.Status.COMPLETED;
import static ru.Robert.NauJava.Entities.Report.Status.ERROR;

@Service
public class ReportService {
    private long contactCount;
    private long contactCountTime;
    private long countriesTime;
    private long totalTime;
    private List<Country> countryList = new ArrayList<>();
    @Autowired
    ReportRepository reportRepository;
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    CountryRepository countryRepository;
    public Long createReport(String content) {
        Report report = new Report(content);
        return reportRepository.save(report).getId();
    }
    public String getContentById(Long id) {
        Report report = reportRepository.findReportById(id);
        return report.getContent();
    }

    @Async
    @Bean
    public void generateReport() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            Report report = new Report();
            reportRepository.save(report);

            long startTotalTime = System.currentTimeMillis();

            Thread contactCountThread = new Thread(() -> {
                long startCountTime = System.currentTimeMillis();
                contactCount = contactRepository.count();
                long endCountTime = System.currentTimeMillis();

                contactCountTime = endCountTime - startCountTime;
            });

            Thread countriesThread = new Thread(() -> {
                long startCountriesTime = System.currentTimeMillis();
                countryList = (List<Country>) countryRepository.findAll();
                long endCountriesTime = System.currentTimeMillis();
                countriesTime = endCountriesTime - startCountriesTime;
            });

            contactCountThread.start();
            countriesThread.start();

            try {
                contactCountThread.join();
                countriesThread.join();
            } catch (InterruptedException e) {
                report.setStatus(ERROR);
                e.printStackTrace();
            }

            long endTotalTime = System.currentTimeMillis();
            totalTime = endTotalTime - startTotalTime;

            report.setStatus(COMPLETED);
            reportRepository.save(report);

            String reportContent = createStringReportContent(contactCount, contactCountTime, countryList,
                    countriesTime, totalTime);
            report.setContent(reportContent);
            return reportContent;
        });
        future.thenAccept(result -> System.out.println(result));
    }

    private String createStringReportContent(long contactCount, long contactCountTime,
                                             List<Country> countryList, long countriesTime, long totalTime) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Количество зарегистрированных в системе пользователей:").append(contactCount).append('\n');
        stringBuilder.append("Затраченное время: ").append(contactCountTime).append('\n').append('\n');

        stringBuilder.append("Список стран в системе: ").append('\n');
        for (Country country : countryList) stringBuilder.append(" - ").append(country.getName()).append('\n');
        stringBuilder.append("Затраченное время: ").append(countriesTime).append('\n').append('\n');

        stringBuilder.append("Общее затраченное время: ").append(totalTime);

        return stringBuilder.toString();
    }

    private String createHtmlReport(String reportContent) {
        String htmlTemplate = "<html><body><h1>Отчет</h1><p>%s</p></body></html>";
        return String.format(htmlTemplate, reportContent);
    }
}
