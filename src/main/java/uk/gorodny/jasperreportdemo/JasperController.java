package uk.gorodny.jasperreportdemo;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
  public ResponseEntity<InputStreamResource> getReport(@RequestParam("name") String name,
                                                       @RequestParam("surname") String surname) throws IOException, JRException {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentDisposition(ContentDisposition.builder("attachment")
        .filename("report.pdf")
        .build());
    return ResponseEntity
        .ok()
        .headers(headers)
        .contentType(MediaType.APPLICATION_PDF)
        .body(jasperService.generateReport(new Man(name, surname)));
  }
}
