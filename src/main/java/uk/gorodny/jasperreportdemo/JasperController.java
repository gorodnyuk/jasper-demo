package uk.gorodny.jasperreportdemo;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@RestController
public class JasperController {

    @Autowired
    private JasperService jasperService;

    @GetMapping("/jasper")
    public void getReport(@RequestParam("name") String name,
                          @RequestParam("surname") String surname,
                          HttpServletResponse httpServletResponse) throws IOException, JRException {
        JasperPrint jasperPrint = jasperService.generateReport(new Man(name, surname));
        httpServletResponse.setContentType("application/x-pdf");
        httpServletResponse.setHeader("Content-disposition", "inline; filename=demoReport.pdf");
        OutputStream outputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
    }
}
