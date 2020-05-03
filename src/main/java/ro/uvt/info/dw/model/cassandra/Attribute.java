package ro.uvt.info.dw.model.cassandra;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import ro.uvt.info.dw.model.DataType;
import ro.uvt.info.dw.persistance.TemporalEntity;

import java.util.Date;

@Table("attribute")
@Data
@Accessors(fluent = true)
public class Attribute implements TemporalEntity<Attribute> {
    @PrimaryKeyColumn(name="id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String id;
    @PrimaryKeyColumn(name="system_time", ordinal = 1, type = PrimaryKeyType.CLUSTERED,
            ordering = Ordering.DESCENDING)
    private Date systemTime;
    @Column
    private String name;
    @Column
    private String description;
    @Column("type")
    private int dataType;



    public Attribute setDataType(DataType dt) {
        this.dataType = dt.ordinal();
        return this;
    }
    public DataType getDataType() {
        return DataType.valueOf(this.dataType);
    }
}