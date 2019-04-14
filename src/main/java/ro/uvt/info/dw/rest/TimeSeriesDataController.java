package ro.uvt.info.dw.rest;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.uvt.info.dw.model.TimeSeriesDataDTO;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "/api/tsData", produces = MediaType.APPLICATION_JSON_VALUE)
public class TimeSeriesDataController {

    /**
     * Usage example
     *  curl "http://localhost:8080/tsData?assetId=WIKI/AAPL&tsDefinitionId=QUANDL.WIKI&startBusinessDate=2019-01-01&endBusinessDate=2019-01-20"
     *
     * Note: This implementation echos the request parameters as a JSON response
     *
     * @param assetId the id of the asset
     * @param tsDefinitionId the id of the time series definition
     * @param startBusinessDate business date start
     * @param endBusinessDate business date end (only time series records in (startBusinessDate, endBusinessDate] range will be returnes)
     * @param includeAttributes whether attributes definitions are returned (optional, default value = false)
     * @return an array of TimeSeries records
     */
    @GetMapping
    public List<TimeSeriesDataDTO> getTimeSeriesData(
        @RequestParam("assetId") String assetId,
        @RequestParam("tsDefinitionId") String tsDefinitionId,
        @RequestParam("startBusinessDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startBusinessDate,
        @RequestParam("endBusinessDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endBusinessDate,
        @RequestParam(name = "includeAttributes", defaultValue = "false") boolean includeAttributes
    ) {
        return Collections.singletonList(new TimeSeriesDataDTO(
            assetId,
            tsDefinitionId,
            startBusinessDate,
            endBusinessDate,
            includeAttributes));
    }
}
