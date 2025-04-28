package com.puenteblanco.pb.services.report;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.itextpdf.html2pdf.HtmlConverter;

public abstract class AbstractBaseReportService implements ReportService {

    private final Configuration freemarkerConfig;

    public AbstractBaseReportService() {
        freemarkerConfig = new Configuration(Configuration.VERSION_2_3_31);
        try {
            freemarkerConfig.setDirectoryForTemplateLoading(new ClassPathResource("templates").getFile());
        } catch (Exception e) {
            throw new RuntimeException("No se pudo cargar la carpeta de templates", e);
        }
        freemarkerConfig.setDefaultEncoding("UTF-8");
        freemarkerConfig.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    @Override
    public byte[] generateReport(Map<String, Object> dataModel, String templateName) {
        try (StringWriter stringWriter = new StringWriter()) {
            Template template = freemarkerConfig.getTemplate(templateName + ".ftl");
            template.process(dataModel, stringWriter);

            String htmlContent = stringWriter.toString();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            HtmlConverter.convertToPdf(htmlContent, outputStream);
            return outputStream.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error al generar el PDF", e);
        }
    }
}