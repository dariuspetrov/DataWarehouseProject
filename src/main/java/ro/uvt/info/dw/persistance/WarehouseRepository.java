package ro.uvt.info.dw.persistance;

public interface WarehouseRepository<E,K> {

    E save(E entity);
    // Delete an E instance from Cassandra
    void delete(E entity);
    // Delete all rows matching the partitionKey
    void deleteAll(K partitionKey);
    // Returns the most recent instance matching the partitionKey
    // Out of multiple records with the same partitionKey, return the one
    // with most recent system_time
    E findLatest(K partitionKey);
    // Returns all the records matching the partitionKey
    Iterable<E> findAll(K partitionKey);
}
