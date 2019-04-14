package ro.uvt.info.dw.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TimeSeriesDataDTO {
    private final String assetId;
    private final String tsDefinitionId;
    private final LocalDate startBusinessDate;
    private final LocalDate endBusinessDate;
    private final boolean includeAttributes;
}
