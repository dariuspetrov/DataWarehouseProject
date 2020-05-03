package ro.uvt.info.dw.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.stereotype.Repository;
import ro.uvt.info.dw.model.cassandra.TimeSeriesDefinition;

@Repository
@EnableCassandraRepositories
public interface TimeSeriesDefinitionRepository extends CassandraRepository<TimeSeriesDefinition, String> {
}
