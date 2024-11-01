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
import java.util.stream.Collectors;

import static ru.Robert.NauJava.Entities.Report.Status.COMPLETED;
import static ru.Robert.NauJava.Entities.Report.Status.ERROR;

@Service
public class ReportService {
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

    public Report getReport(Long reportId) {
        Optional<Report> report = reportRepository.findById(reportId);
        if (report.isEmpty()) {
            throw new RuntimeException("Report not found");
        }

        return report.orElse(null);
    }

    @Async
    public CompletableFuture<Report> generateReport() {
        CompletableFuture<Report> future = CompletableFuture.supplyAsync(() -> {
            Report report = new Report();

            long startTotalTime = System.currentTimeMillis();

            Thread contactCountThread = new Thread(() -> {
                long startCountTime = System.currentTimeMillis();
                report.setContactCount(contactRepository.count());
                long endCountTime = System.currentTimeMillis();

                report.setContactCountTime(endCountTime - startCountTime);
            });

            Thread countriesThread = new Thread(() -> {
                long startCountriesTime = System.currentTimeMillis();
                //report.setCountryList(countryRepository.findAll());
                Set<Country> countries = new HashSet<>();
                for (Country country : countryRepository.findAll()) {
                    countries.add(country);
                }
                report.setCountryList(countries);
                long endCountriesTime = System.currentTimeMillis();
                report.setCountriesTime(endCountriesTime - startCountriesTime);
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
            report.setTotalTime(endTotalTime - startTotalTime);

            return report;
        });
        future.thenAccept(report -> {
            report.setStatus(COMPLETED);
            reportRepository.save(report);

            /*String reportContent = createStringReportContent(report.getContactCount(),
                    report.getContactCountTime(), report.getCountryList(),
                    report.getCountriesTime(), report.getTotalTime());
            report.setContent(reportContent);*/

        });
        return future;
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

}
