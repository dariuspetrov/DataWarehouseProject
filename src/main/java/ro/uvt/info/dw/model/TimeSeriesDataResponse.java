package ro.uvt.info.dw.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TimeSeriesDataResponse {
    private final TimeSeriesResponse tsData;
    private final AttributesResponse attributes;
}
