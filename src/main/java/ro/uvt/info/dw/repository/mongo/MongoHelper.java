package ro.uvt.info.dw.repository.mongo;

import org.apache.commons.lang.StringUtils;
import org.bson.Document;

import java.lang.reflect.Field;
import java.util.Map;

class MongoHelper {

    static <E> Document entityToDocument(E entity, Class<?> cls) {
        if (entity == null) {
            return null;
        }

        if (cls == null) {
            throw new IllegalArgumentException("Class cannot be null.");
        }

        Document doc = new Document();
        Field[] allFields = cls.getDeclaredFields();

        for(Field field : allFields) {
            String name  = getFieldName(field);

            try {
                field.setAccessible(true);
                Object value = (field.isAnnotationPresent(javax.persistence.EmbeddedId.class) ||
                                field.isAnnotationPresent(javax.persistence.Embedded.class)) ?
                    entityToDocument(field.get(entity), field.getType())
                    : field.get(entity);

                // In MongoDB field names cannot contain the dot (.) character as it is part of dot-notation syntax
                if (value instanceof Map && !(value instanceof Document)) {
                    Document acc = new Document();
                    ((Map<Object, Object>)value)
                        .entrySet()
                        .stream()
                        //.collect(Collectors.toMap(k -> k.toString().replace('.', '_'), v -> v)));
                        .forEach(entry -> acc.append(entry.getKey().toString().replace('.', '_'), entry.getValue()));
                    value = acc;
                }

                doc.append(name, value);

            } catch (IllegalAccessException | ClassCastException e) {
                // ignore unaccessible fields
                e.printStackTrace();
            }
        }
        return doc;
    }

    static <E> E documentToEntity(Document doc, Class<E> cls) {
        if (doc == null) {
            return null;
        }

        if (cls == null) {
            throw new IllegalArgumentException("Class cannot be null.");
        }

        // TODO: Deserialization from Mongo's Document representation is not
        // fully tested, nor supported; it is limited to primitive data types fields only
        try {
            E e = cls.newInstance();

            doc.forEach((name, value1) -> {

                try {
                    Field field = getDeclaredField(cls, name);

                    Object value = (field.isAnnotationPresent(javax.persistence.EmbeddedId.class) ||
                                    field.isAnnotationPresent(javax.persistence.Embedded.class)) ?
                        documentToEntity((Document) value1, field.getType())
                        : value1;

                    field.setAccessible(true);
                    field.set(e, value);
                } catch (NoSuchFieldException ex) {
                    // silently ignore attributes missing from entity
                    // (maybe an older version of the Code is run against a newer database)
                    //ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            });

            return e;

        } catch (IllegalAccessException | InstantiationException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    static String getPartitionKeyField(Class<?> cls) {
        Field[] allFields = cls.getDeclaredFields();

        for(Field field : allFields) {
            if (field.isAnnotationPresent(javax.persistence.Id.class)) {
                return getFieldName(field);
            }

            if (field.isAnnotationPresent(javax.persistence.EmbeddedId.class)) {
                String prefix = getFieldName(field);
                String suffix = getPartitionKeyField(field.getType());
                return prefix + "." + suffix;
            }

            if (StringUtils.equals("id", field.getName().toLowerCase())) {
                return getFieldName(field);
            }
        }

        return "id";
    }

    private static String getFieldName(Field field) {
        String name = field.getName();
        if (field.isAnnotationPresent(javax.persistence.Column.class)) {
            javax.persistence.Column annotation = field.getAnnotation(javax.persistence.Column.class);
            name = (annotation != null) && StringUtils.isNotBlank(annotation.name()) ? annotation.name() : name;
        }

        // In MongoDB field names cannot contain the dot (.) character as it is part of dot-notation syntax
        return name.replace('.', '_');
    }

    private static Field getDeclaredField(Class<?> cls, String name) throws NoSuchFieldException {

        try {
            return cls.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            // Field could not be found by name,
            // Search for field by @Column name
            for(Field field : cls.getDeclaredFields()) {
                if (field.isAnnotationPresent(javax.persistence.Column.class)) {
                    javax.persistence.Column annotation = field.getAnnotation(javax.persistence.Column.class);
                    String colName = (annotation != null) && StringUtils.isNotBlank(annotation.name()) ? annotation.name() : field.getName();
                    if (StringUtils.equals(name, colName))
                        return field;
                }
            }
            throw e;
        }

    }
}
