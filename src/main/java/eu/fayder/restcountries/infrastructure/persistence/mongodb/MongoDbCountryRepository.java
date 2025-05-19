package eu.fayder.restcountries.infrastructure.persistence.mongodb;

import eu.fayder.restcountries.boot.config.MongoProperties;
import eu.fayder.restcountries.domain.CountryRepository;
import eu.fayder.restcountries.domain.country.Country;
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
public class MongoDbCountryRepository implements CountryRepository {

    private final MongoTemplate mongoTemplate;
    private final String collectionName;

    @Autowired
    public MongoDbCountryRepository(MongoTemplate mongoTemplate, MongoProperties mongoProperties) {
        this.mongoTemplate = mongoTemplate;
        this.collectionName = mongoProperties.getCollection();
    }

    @Override
    public List<Country> findAll() {
        return mongoTemplate.findAll(Country.class, collectionName);
    }

    @Override
    public Optional<Country> findByAlpha2Code(String code) {
        Query query = new Query(Criteria.where("alpha2Code").regex(code, "i"));
        Country country = mongoTemplate.findOne(query, Country.class, collectionName);
        return Optional.ofNullable(country);
    }

    @Override
    public Optional<Country> findByAlpha3Code(String code) {
        Query query = new Query(Criteria.where("alpha3Code").regex(code, "i"));
        Country country = mongoTemplate.findOne(query, Country.class, collectionName);
        return Optional.ofNullable(country);
    }

    @Override
    public List<Country> findByNameContaining(String countryName) {
        String normalizedCountryName = normalize(countryName);

        Criteria criteria = new Criteria().orOperator(
                Criteria.where("name").regex(normalizedCountryName, "i"),
                Criteria.where("altSpellings").regex(normalizedCountryName, "i")
        );

        Query query = new Query(criteria);

        return mongoTemplate.find(query, Country.class, collectionName);
    }

    @Override
    public List<Country> findByCallingCode(String code) {
        Query query = new Query(Criteria.where("callingCodes").in(code));
        return mongoTemplate.find(query, Country.class, collectionName);
    }

    @Override
    public List<Country> findByCapitalContaining(String partialCapital) {
        String normalizedPartialCapital = normalize(partialCapital);
        Query query = new Query(Criteria.where("capital").regex(normalizedPartialCapital, "i"));
        return mongoTemplate.find(query, Country.class, collectionName);
    }

    @Override
    public List<Country> findByRegion(String region) {
        Query query = new Query(Criteria.where("region").regex("^" + region + "$", "i"));
        return mongoTemplate.find(query, Country.class, collectionName);
    }

    @Override
    public List<Country> findBySubregion(String subregion) {
        Query query = new Query(Criteria.where("subregion").regex("^" + subregion + "$", "i"));
        return mongoTemplate.find(query, Country.class, collectionName);
    }

    @Override
    public List<Country> findByCurrency(String currency) {
        Query query = new Query(Criteria.where("currencies.code").regex("^" + currency + "$", "i"));
        return mongoTemplate.find(query, Country.class, collectionName);
    }

    @Override
    public List<Country> findByLanguageTwoLetterIsoCode(String language) {
        Query query = new Query(Criteria.where("languages.iso639_1").regex("^" + language + "$", "i"));
        return mongoTemplate.find(query, Country.class, collectionName);
    }

    @Override
    public List<Country> findByLanguageThreeLetterIsoCode(String language) {
        Query query = new Query(Criteria.where("languages.iso639_2").regex("^" + language + "$", "i"));
        return mongoTemplate.find(query, Country.class, collectionName);
    }

    @Override
    public List<Country> findByDemonym(String demonym) {
        String normalizedDemonym = normalize(demonym);
        Query query = new Query(Criteria.where("demonym").regex("^" + normalizedDemonym + "$", "i"));
        return mongoTemplate.find(query, Country.class, collectionName);
    }

    @Override
    public List<Country> findByRegionalBloc(String regionalBloc) {
        Criteria criteria = new Criteria().orOperator(
                Criteria.where("regionalBlocs.acronym").regex("^" + regionalBloc + "$", "i"),
                Criteria.where("regionalBlocs.otherAcronyms").regex("^" + regionalBloc + "$", "i"),
                Criteria.where("regionalBlocs.name").regex("^" + regionalBloc + "$", "i"),
                Criteria.where("regionalBlocs.otherNames").regex("^" + regionalBloc + "$", "i")
        );

        Query query = new Query(criteria);

        return mongoTemplate.find(query, Country.class, collectionName);
    }

    private String normalize(String string) {
        return Normalizer.normalize(string, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}