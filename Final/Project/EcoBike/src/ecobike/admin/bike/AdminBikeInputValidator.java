package ecobike.admin.bike;

import ecobike.database.SQLDatabase;

public class AdminBikeInputValidator {

    public AdminBikeInputValidator() {

    }

    public Boolean ifBikeDeletionInvalid( int bikeID) {
        return SQLDatabase.GetInstance().ifBikeIsRented(bikeID);
    }

    public Boolean ifNameNotUniqueAfterEdit( String originalBikeName, String updatedBikeName) {
        return (!updatedBikeName.equals(originalBikeName)) && (SQLDatabase.GetInstance().ifBikeNameExisted(updatedBikeName));
    }

    public Boolean ifLicenseNotUniqueAfterEdit(String originalBikeLicense, String updatedBikeLicense) {
        return (!updatedBikeLicense.equals(originalBikeLicense)) && (SQLDatabase.GetInstance().ifBikeLicenseExisted(updatedBikeLicense));
    }

    public Boolean ifNewNameUnique( String name) {
        return SQLDatabase.GetInstance().ifBikeNameExisted(name);
    }

    public Boolean ifNewLicenseUnique(String license) {
        return SQLDatabase.GetInstance().ifBikeLicenseExisted(license);
    }
}
