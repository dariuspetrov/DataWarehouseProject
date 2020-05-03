package ro.uvt.info.dw.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.stereotype.Repository;
import ro.uvt.info.dw.model.cassandra.Asset;

@Repository
@EnableCassandraRepositories()
public interface AssetRepository extends CassandraRepository<Asset, String>{
}
