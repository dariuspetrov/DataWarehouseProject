package ro.uvt.info.dw.model.cassandra;


import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import ro.uvt.info.dw.model.DataType;

import java.util.Date;
import java.util.Set;

@Table("ts_definition")
@Data
@Accessors(fluent = true)
public class TimeSeriesDefinition {
    @PrimaryKeyColumn(name="id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String id;
    @PrimaryKeyColumn(name="system_time", ordinal = 1, type = PrimaryKeyType.CLUSTERED,
            ordering = Ordering.DESCENDING)
    private Date systemTime;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String publisher;
    @Column("type")
    private int type;
    @Column("attributes")
    private Set<String> attributes;

}
