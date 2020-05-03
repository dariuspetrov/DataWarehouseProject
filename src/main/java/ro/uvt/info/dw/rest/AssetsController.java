package ro.uvt.info.dw.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.uvt.info.dw.model.cassandra.Asset;
import ro.uvt.info.dw.model.persistance.WarehouseRepository;

import java.util.ArrayList;

@RequiredArgsConstructor


@RestController
@RequestMapping(path = "/api/assets", produces = MediaType.APPLICATION_JSON_VALUE)
public class AssetController{
    private final WarehouseRepository<Asset, String> assetsRepo;


    /**
     * Usage example
     *  curl "http://localhost:8080/api/tsData?assetId=WIKI/AAPL&tsDefinitionId=QUANDL.WIKI&startBusinessDate=2019-01-01&endBusinessDate=2019-01-20"
     *
     * Note: This implementation echos the request parameters as a JSON response
     *
     * @param offset the id of the asset
     * @param limit the id of the time series definition
     * @return an array of TimeSeries records
     */
    @GetMapping
    public ArrayList<String> getAll(
            @RequestParam( value = "offset", defaultValue = "0") Integer offset,
            @RequestParam(value = "limit", defaultValue = "20") Integer limit

    ) {
        return assetsRepo.findAll(id);
    }

    @GetMapping("/{assetId")
    public AssetResponse getAssetById(
            @PathVariable String path
    ){
        return this.assetsRepo.findLatest(path);
    }
}