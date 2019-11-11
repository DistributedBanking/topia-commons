package io.bitexpress.topia.commons.basic.enums;

import java.util.Locale;

import org.testng.annotations.Test;

public class EnumResourceBundleUtilsTest {

	@Test
	public void getDescription() {
		String description = EnumResourceBundleUtils.getDescription(E1.V1, Locale.getDefault());
		System.out.println(description);
	}

	@Test
	public void getEnumDescription() {
		String description = EnumResourceBundleUtils.getEnumDescription(E1.class, Locale.getDefault());
		System.out.println(description);
	}
}
