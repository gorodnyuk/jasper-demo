package uk.gorodny.jasperreportdemo;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class JasperService {

    public byte[] generateReport(Man man) throws JRException, FileNotFoundException {
        String filePath = ResourceUtils.getFile("classpath:jasper-demo.jrxml").getAbsolutePath();
        JasperReport jasperReport = JasperCompileManager.compileReport(filePath);
        Map<String, Object> params = new HashMap<>();
        params.put("demoName", man.getName());
        params.put("demoSurname", man.getSurname());
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(Collections.singletonList(""));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, jrBeanCollectionDataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
