package ro.uvt.info.dw.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.stereotype.Repository;
import ro.uvt.info.dw.model.cassandra.TimeSeriesData;

@Repository
@EnableCassandraRepositories
public interface TimeSeriesDataRepository extends CassandraRepository<TimeSeriesData, String> {
}
