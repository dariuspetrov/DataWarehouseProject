package ro.uvt.info.dw.model.web;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TimeSeriesResponse {
    private String assetId;
    private String tsDefinitionId;
    private List<TimeSeriesRecordResponse> records;



}
