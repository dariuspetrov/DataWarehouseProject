package ro.uvt.info.dw.model.persistance.cassandra;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import ro.uvt.info.dw.model.persistance.WarehouseRepository;

@RequiredArgsConstructor
public class SpringDataWarehouseRepositoryImpl<E, K>
        implements WarehouseRepository<E, K> {
    private CrudRepository<E, K> springDataRepository;

    @Override
    public E save(E entity) {
        return springDataRepository.save(entity);
    }

    @Override
    public void delete(E entity) {
// TODO
    }

    @Override
    public void deleteAll(K partitionKey) {
// TODO
    }

    @Override
    public E findLatest(K partitionKey) {
// TODO
        return null;
    }

    @Override
    public Iterable<E> findAll(K partitionKey) {
// TODO
        return null;
    }
}