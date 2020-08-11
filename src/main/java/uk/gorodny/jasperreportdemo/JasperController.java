package uk.gorodny.jasperreportdemo;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class JasperController {

    @Autowired
    private JasperService jasperService;

    @GetMapping("/jasper")
    public ResponseEntity<byte[]> getReport(@RequestParam("name") String name,
                                            @RequestParam("surname") String surname) throws IOException, JRException {
        byte[] reportBytes = jasperService.generateReport(new Man(name, surname));
        return ResponseEntity
                .ok()
                // Specify content type as PDF
                .header("Content-Type", "application/pdf; charset=UTF-8")
                // Tell browser to display PDF if it can
                .header("Content-Disposition", "attachment; filename=report.pdf")
                .body(reportBytes);
    }
}
