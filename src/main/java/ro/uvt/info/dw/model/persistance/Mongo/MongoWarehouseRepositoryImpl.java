package ro.uvt.info.dw.model.persistance.Mongo;


import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import ro.uvt.info.dw.model.persistance.WarehouseRepository;
import ro.uvt.info.dw.model.web.AssetResponse;
import ro.uvt.info.dw.repository.mongo.MongoHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

//@AllArgsConstructor
public class MongoWarehouseRepositoryImpl<E, K> implements WarehouseRepository<E, K> {
    private final MongoDatabase em;
    private final Class<E> clsE;

    public MongoWarehouseRepositoryImpl(MongoDatabase em, Class<E> clsE) {

        this.em = em;
        this.clsE = clsE;
    }

    @Override
    public E save(E entity) {
        Document doc = entityToDocument(entity);
        MongoCollection<Document> collection = getCollection();
        collection
                .withWriteConcern(WriteConcern.ACKNOWLEDGED)
                .insertOne(doc);
        return entity;
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
    public AssetResponse findLatest(K partitionKey) {
        Iterator<E> i = findAll(partitionKey).iterator();
        return i.hasNext() ? (AssetResponse) i.next() : null;
    }
    @Override
    public Iterable<E> findAll(K partitionKey) {
        //TODO Check this code-------------------------------------

        Iterator<E> i = findAll(partitionKey).iterator();
        ArrayList<E> list = new ArrayList<E>();
        while (i.hasNext()) {
            list.add((E) i);
        }

        return list;
    }
    private MongoCollection<Document> getCollection() {
        String tableName = clsE.getSimpleName().toLowerCase();
        if (clsE.isAnnotationPresent(javax.persistence.Table.class)) {
            javax.persistence.Table annotation =
                    clsE.getAnnotation(javax.persistence.Table.class);
            tableName = (annotation != null) &&
                    StringUtils.isNotBlank(annotation.name()) ? annotation.name() : tableName;
        }
        return em.getCollection(tableName);
    }
    private Document entityToDocument(E entity) {
        return MongoHelper.entityToDocument(entity, clsE);
    }
    private E documentToEntity(Document doc) {
        return MongoHelper.documentToEntity(doc, clsE);

    }

    static MongoDatabase connect() {
        final List<ServerAddress> seedList = Arrays.asList(
                new ServerAddress("dwcluster-shard-00-00-e74rs.mongodb.net", 27017),
                new ServerAddress("dwcluster-shard-00-01-e74rs.mongodb.net", 27017),
                new ServerAddress("dwcluster-shard-00-02-e74rs.mongodb.net", 27017));
        final MongoCredential credential = MongoCredential.createScramSha1Credential(
                "admin",
                "admin",
                "***".toCharArray());
        final MongoClientOptions options = MongoClientOptions.builder()
                .socketTimeout(30000)
                .connectionsPerHost(1)
                .sslEnabled(true)
                .build();
        final String databaseName = "pop1";
        return new MongoClient(seedList, credential, options)
                .getDatabase(databaseName);
    }
}