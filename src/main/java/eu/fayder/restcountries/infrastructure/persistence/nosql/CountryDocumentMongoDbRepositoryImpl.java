package eu.fayder.restcountries.infrastructure.persistence.nosql;

import eu.fayder.restcountries.boot.config.MongoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;

@Repository
@ConditionalOnProperty(name = "repository.type", havingValue = "mongodb")
public class CountryDocumentMongoDbRepositoryImpl implements CountryDocumentMongoDbRepository {

    private final MongoTemplate mongoTemplate;
    private final String collectionName;

    @Autowired
    public CountryDocumentMongoDbRepositoryImpl(MongoTemplate mongoTemplate, MongoProperties mongoProperties) {
        this.mongoTemplate = mongoTemplate;
        this.collectionName = mongoProperties.getCollection();
    }

    @Override
    public List<CountryDocument> findAll() {
        return mongoTemplate.findAll(CountryDocument.class, collectionName);
    }

    @Override
    public Optional<CountryDocument> findByAlpha2Code(String code) {
        Query query = new Query(Criteria.where("alpha2Code").regex(code, "i"));
        CountryDocument country = mongoTemplate.findOne(query, CountryDocument.class, collectionName);
        return Optional.ofNullable(country);
    }

    @Override
    public Optional<CountryDocument> findByAlpha3Code(String code) {
        Query query = new Query(Criteria.where("alpha3Code").regex(code, "i"));
        CountryDocument country = mongoTemplate.findOne(query, CountryDocument.class, collectionName);
        return Optional.ofNullable(country);
    }

    @Override
    public List<CountryDocument> findByNameContaining(String countryName) {
        String normalizedCountryName = normalize(countryName);

        Criteria criteria = new Criteria().orOperator(
                Criteria.where("name").regex(normalizedCountryName, "i"),
                Criteria.where("altSpellings").regex(normalizedCountryName, "i")
        );

        Query query = new Query(criteria);

        return mongoTemplate.find(query, CountryDocument.class, collectionName);
    }

    @Override
    public List<CountryDocument> findByCallingCode(String code) {
        Query query = new Query(Criteria.where("callingCodes").in(code));
        return mongoTemplate.find(query, CountryDocument.class, collectionName);
    }

    @Override
    public List<CountryDocument> findByCapitalContaining(String partialCapital) {
        String normalizedPartialCapital = normalize(partialCapital);
        Query query = new Query(Criteria.where("capital").regex(normalizedPartialCapital, "i"));
        return mongoTemplate.find(query, CountryDocument.class, collectionName);
    }

    @Override
    public List<CountryDocument> findByRegion(String region) {
        Query query = new Query(Criteria.where("region").regex("^" + region + "$", "i"));
        return mongoTemplate.find(query, CountryDocument.class, collectionName);
    }

    @Override
    public List<CountryDocument> findBySubregion(String subregion) {
        Query query = new Query(Criteria.where("subregion").regex("^" + subregion + "$", "i"));
        return mongoTemplate.find(query, CountryDocument.class, collectionName);
    }

    @Override
    public List<CountryDocument> findByCurrency(String currency) {
        Query query = new Query(Criteria.where("currencies.code").regex("^" + currency + "$", "i"));
        return mongoTemplate.find(query, CountryDocument.class, collectionName);
    }

    @Override
    public List<CountryDocument> findByLanguageTwoLetterIsoCode(String language) {
        Query query = new Query(Criteria.where("languages.iso639_1").regex("^" + language + "$", "i"));
        return mongoTemplate.find(query, CountryDocument.class, collectionName);
    }

    @Override
    public List<CountryDocument> findByLanguageThreeLetterIsoCode(String language) {
        Query query = new Query(Criteria.where("languages.iso639_2").regex("^" + language + "$", "i"));
        return mongoTemplate.find(query, CountryDocument.class, collectionName);
    }

    @Override
    public List<CountryDocument> findByDemonym(String demonym) {
        String normalizedDemonym = normalize(demonym);
        Query query = new Query(Criteria.where("demonym").regex("^" + normalizedDemonym + "$", "i"));
        return mongoTemplate.find(query, CountryDocument.class, collectionName);
    }

    @Override
    public List<CountryDocument> findByRegionalBloc(String regionalBloc) {
        Criteria criteria = new Criteria().orOperator(
                Criteria.where("regionalBlocs.acronym").regex("^" + regionalBloc + "$", "i"),
                Criteria.where("regionalBlocs.otherAcronyms").regex("^" + regionalBloc + "$", "i"),
                Criteria.where("regionalBlocs.name").regex("^" + regionalBloc + "$", "i"),
                Criteria.where("regionalBlocs.otherNames").regex("^" + regionalBloc + "$", "i")
        );

        Query query = new Query(criteria);

        return mongoTemplate.find(query, CountryDocument.class, collectionName);
    }

    private String normalize(String string) {
        return Normalizer.normalize(string, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}