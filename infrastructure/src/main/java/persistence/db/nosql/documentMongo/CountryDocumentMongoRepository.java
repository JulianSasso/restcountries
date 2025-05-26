package persistence.db.nosql.documentMongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryDocumentMongoRepository extends MongoRepository<CountryDocument, String> {

    // Exact match (case-insensitive)
    Optional<CountryDocument> findByAlpha2CodeIgnoreCase(String code);
    Optional<CountryDocument> findByAlpha3CodeIgnoreCase(String code);

    // Accent-insensitive, partial name match using normalized fields
    @Query("{ '$or': [ " +
           "{ 'normalizedName': { $regex: ?0, $options: 'i' } }, " +
           "{ 'normalizedAltSpellings': { $regex: ?0, $options: 'i' } } " +
           "] }")
    List<CountryDocument> findByNameOrAltSpellingsNormalized(String normalizedInput);

    // Calling code match in array
    @Query("{ 'callingCodes': { $in: [?0] } }")
    List<CountryDocument> findByCallingCode(String code);

    // Capital match using normalized field
    @Query("{ 'normalizedCapital': { $regex: ?0, $options: 'i' } }")
    List<CountryDocument> findByCapitalNormalized(String normalizedCapital);

    // Exact matches
    List<CountryDocument> findByRegion(String region);
    List<CountryDocument> findBySubregion(String subregion);

    @Query("{ 'normalizedDemonym': { $regex: ?0, $options: 'i' } }")
    List<CountryDocument> findByDemonymNormalized(String demonym);

    // Currency match (nested array of objects)
    @Query("{ 'currencies.code': ?0 }")
    List<CountryDocument> findByCurrency(String currencyCode);

    // Language matching
    @Query("{ 'languages.iso639_1': ?0 }")
    List<CountryDocument> findByLanguageTwoLetterIsoCode(String iso639_1);

    @Query("{ 'languages.iso639_2': ?0 }")
    List<CountryDocument> findByLanguageThreeLetterIsoCode(String iso639_2);

    // Match on any of several regionalBloc fields
    @Query("{ '$or': [ " +
           "{ 'regionalBlocs.acronym': { $regex: ?0, $options: 'i' } }, " +
           "{ 'regionalBlocs.otherAcronyms': { $regex: ?0, $options: 'i' } }, " +
           "{ 'regionalBlocs.name': { $regex: ?0, $options: 'i' } }, " +
           "{ 'regionalBlocs.otherNames': { $regex: ?0, $options: 'i' } } " +
           "] }")
    List<CountryDocument> findByRegionalBlocFlexible(String regionalBlocInput);

}
