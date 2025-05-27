package eu.fayder.restcountries.infrastructure.persistence.db.nosql.documentMongo;

import eu.fayder.restcountries.infrastructure.persistence.db.nosql.documentMongo.country.CountryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryDocumentMongoRepository extends MongoRepository<CountryDocument, String> {

    Optional<CountryDocument> findByAlpha2CodeIgnoreCase(String code);
    Optional<CountryDocument> findByAlpha3CodeIgnoreCase(String code);

    @Query("{ '$or': [ " +
           "{ 'normalizedName': { $regex: ?0, $options: 'i' } }, " +
           "{ 'normalizedAltSpellings': { $regex: ?0, $options: 'i' } } " +
           "] }")
    List<CountryDocument> findByNameOrAltSpellingsNormalized(String normalizedInput);

    @Query("{ 'callingCodes': { $in: [?0] } }")
    List<CountryDocument> findByCallingCode(String code);

    @Query("{ 'normalizedCapital': { $regex: ?0, $options: 'i' } }")
    List<CountryDocument> findByCapitalNormalized(String normalizedCapital);

    @Query("{ 'region': { $regex: '^?0$', $options: 'i' } }")
    List<CountryDocument> findByRegion(String region);

    @Query("{ 'region': { $regex: '^?0$', $options: 'i' } }")
    List<CountryDocument> findBySubregion(String subregion);

    @Query("{ 'normalizedDemonym': { $regex: ?0, $options: 'i' } }")
    List<CountryDocument> findByDemonymNormalized(String demonym);

    @Query("{ 'currencies.code': { $regex: '^?0$', $options: 'i' } }")
    List<CountryDocument> findByCurrency(String currencyCode);

    @Query("{ 'languages.iso639_1': ?0 }")
    List<CountryDocument> findByLanguageTwoLetterIsoCode(String iso639_1);

    @Query("{ 'languages.iso639_2': ?0 }")
    List<CountryDocument> findByLanguageThreeLetterIsoCode(String iso639_2);

    // Match on any of several regionalBloc fields
    @Query("{ '$or': [ " +
           "{ 'regionalBlocs.acronym': { $regex: '^?0$', $options: 'i' } }, " +
           "{ 'regionalBlocs.otherAcronyms': { $regex: '^?0$', $options: 'i' } }, " +
           "{ 'regionalBlocs.name': { $regex: '^?0$', $options: 'i' } }, " +
           "{ 'regionalBlocs.otherNames': { $regex: '^?0$', $options: 'i' } } " +
           "] }")
    List<CountryDocument> findByRegionalBlocFlexible(String regionalBlocInput);

}
