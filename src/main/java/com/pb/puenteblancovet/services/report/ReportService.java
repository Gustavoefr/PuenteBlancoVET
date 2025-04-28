package com.puenteblanco.pb.services.report;
import java.util.Map;

public interface ReportService {
    byte[] generateReport(Map<String, Object> dataModel, String templateName);
}