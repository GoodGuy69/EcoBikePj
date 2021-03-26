package ecobike.admin.bike;

import ecobike.database.SQLDatabase;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ValidateInputTest {
	private AdminBikeInputValidator validator;
	private SQLDatabase db =SQLDatabase.GetInstance();
	
	public ValidateInputTest() {
		validator = new AdminBikeInputValidator();
	}
	
	@Test
	public void testBikeDeletion() {
		ArrayList<String> rentedBikesID = db.getRentedBikeIDs();
		for (String bikeID : rentedBikesID) {
			Assert.assertEquals(true, validator.ifBikeDeletionInvalid( Integer.parseInt(bikeID)));
		}
		ArrayList<String> unrentedBikesID = db.getUnrentedBikeIDs();
		for (String bikeID : unrentedBikesID) {
			Assert.assertEquals(false, validator.ifBikeDeletionInvalid( Integer.parseInt(bikeID)));
		}
	}
	
	@Test 
	public void testUniqueNameAfterEdit() {
		ArrayList<String> existedNames = db.getAllBikeNames();
		String uniqueName = "!@#%$!@#%^@#$@#!#@$";
		for (int i = 0; i < existedNames.size(); i++) {
			Assert.assertEquals(false, validator.ifNameNotUniqueAfterEdit( existedNames.get(i), uniqueName));
		}
	}
	
	@Test 
	public void testUniqueLicenseAfterEdit() {
		ArrayList<String> existedLicenses = db.getAllBikeLicenses();
		String uniqueLicense = "!@#%$!@#%^@#$@#!#@$";
		for (int i = 0; i < existedLicenses.size(); i++) {
			Assert.assertEquals(false, validator.ifNameNotUniqueAfterEdit( existedLicenses.get(i), uniqueLicense));
		}
	}
	
	@Test 
	public void testNewNameUnique() {
		ArrayList<String> existedNames = db.getAllBikeNames();
		for (String name : existedNames) {
			Assert.assertEquals(true, validator.ifNewNameUnique( name));
		}
	}
	
	@Test 
	public void testNewLicenseUnique() {
		ArrayList<String> existedLicenses = db.getAllBikeLicenses();
		for (String license : existedLicenses) {
			Assert.assertEquals(true, validator.ifNewLicenseUnique( license));
		}
	}

}
