package ro.uvt.info.dw.model.web;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
@AllArgsConstructor
public class TimeSeriesRecordResponse {
    private final LocalDate businessDate;
    private final Map<String, Object> values;
}
