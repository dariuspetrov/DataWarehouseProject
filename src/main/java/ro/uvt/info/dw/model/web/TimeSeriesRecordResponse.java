package ro.uvt.info.dw.model.web;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class TimeSeriesRecordResponse {
    private LocalDate businessDate;
    private Map<Object, Object> values;

    public TimeSeriesRecordResponse(LocalDate date, HashMap<Object, Object> objectObjectHashMap) {
        businessDate = date;
        values = objectObjectHashMap;
    }
}
