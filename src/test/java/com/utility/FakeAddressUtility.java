package com.utility;

import java.util.Locale;

import com.github.javafaker.Faker;
import com.ui.pojos.AddressPOJO;

public class FakeAddressUtility {

	public static AddressPOJO getFakeAddress() {
		Faker faker = new Faker(new Locale("en-IND"));
		AddressPOJO addressPOJO=new AddressPOJO(faker.company().name(),
				faker.address().buildingNumber(),
				faker.address().streetAddress(), 
				faker.numerify("######"), 
				faker.address().city(),
				faker.phoneNumber().cellPhone(),
				faker.phoneNumber().phoneNumber(),
				"test",
				"Office", 
				faker.number().digit(),
				String.valueOf(
				        faker.number().numberBetween(0, 36)
				)
		);
		return addressPOJO;
	}
}
