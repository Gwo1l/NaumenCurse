package ru.Robert.NauJava.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.Robert.NauJava.Entities.Report;
import ru.Robert.NauJava.Services.ReportService;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Controller
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/report")
    public String getReport(Model model) {
        CompletableFuture<Report> future = reportService.generateReport();
        try {
            Report reportFromFuture = future.get();
            model.addAttribute(reportFromFuture);
            return "report";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "exception";
    }

    @GetMapping("/report/{id}")
    public String getReportById(@PathVariable Long id, Model model) {
        Report report = reportService.getReport(id);

        if (report != null) {
            if (report.getStatus() == Report.Status.COMPLETED) {
                model.addAttribute("report", report);
                return "report";
            } else if (report.getStatus() == Report.Status.CREATED) {
                return "report_not_ready";
            } else {
                return "report_error";
            }
        } else {
            return "report_not_found";
        }
    }
}
