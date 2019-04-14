package ro.uvt.info.dw.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ro.uvt.info.dw.model.TimeSeriesDataDTO;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TimeSeriesDataController.class)
public class TimeSeriesDataControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void givenTimeSeriesParameters_whenGetTimeSeriesData_thenReturnJsonArray()
            throws Exception {

        TimeSeriesDataDTO dto = new TimeSeriesDataDTO(
            "asset",
            "definition",
            LocalDate.of(2019, 4, 1),
            LocalDate.of(2019, 4, 15),
            false);

        mvc.perform(get("/api/tsData")
            .param("assetId", dto.getAssetId())
            .param("tsDefinitionId", dto.getTsDefinitionId())
            .param("startBusinessDate", dto.getStartBusinessDate().toString())
            .param("endBusinessDate", dto.getEndBusinessDate().toString())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].assetId", is(dto.getAssetId())))
            .andExpect(jsonPath("$[0].tsDefinitionId", is(dto.getTsDefinitionId())))
            .andExpect(jsonPath("$[0].startBusinessDate", is(dto.getStartBusinessDate().toString())))
            .andExpect(jsonPath("$[0].endBusinessDate", is(dto.getEndBusinessDate().toString())))
            .andExpect(jsonPath("$[0].includeAttributes", is(dto.isIncludeAttributes())));
    }
}
