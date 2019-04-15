package ro.uvt.info.dw.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TimeSeriesResponse {
    private final String assetId;
    private final String tsDefinitionId;
    private final List<TimeSeriesRecordResponse> records;
}
