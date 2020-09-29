package uk.gorodny.jasperreportdemo;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@Service
public class JasperService {

  public InputStreamResource generateReport(Man man) throws JRException, FileNotFoundException {
    String filePath = ResourceUtils.getFile("classpath:jasper-demo.jrxml").getAbsolutePath();
    JasperReport jasperReport = JasperCompileManager.compileReport(filePath);
    Map<String, Object> params = new HashMap<>();
    params.put("demoName", man.getName());
    params.put("demoSurname", man.getSurname());
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
    return new InputStreamResource(new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jasperPrint)));
  }
}
