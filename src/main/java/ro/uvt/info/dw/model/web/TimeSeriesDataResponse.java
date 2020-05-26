package ro.uvt.info.dw.model.web;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TimeSeriesDataResponse {
    private TimeSeriesResponse tsData;
    private AttributesResponse attributes;

    /*public TimeSeriesDataResponse(TimeSeriesResponse timeSeriesResponse, AttributesResponse attributesResponse) {
        tsData = timeSeriesResponse;
        attributes = attributesResponse;
    }*/
}
