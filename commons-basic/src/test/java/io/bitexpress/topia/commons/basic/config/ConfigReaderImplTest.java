package io.bitexpress.topia.commons.basic.config;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.core.io.PathResource;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigReaderImplTest {
	private ObjectMapper objectMapper = new ObjectMapper();

	private ConfigReader<Integer, Sample> configReader;

	@BeforeClass
	public void prepare() throws Exception {
		ConfigReaderImpl<Integer, Sample> configReader = new ConfigReaderImpl<Integer, Sample>();
		configReader.setObjectType(Sample.class);
		configReader.setKeyProperty("age");
		configReader.setObjectMapper(objectMapper);

		configReader.setJsonResource(new PathResource("src/test/resources/broker-sample.json"));
		configReader.afterPropertiesSet();
		this.configReader = configReader;

	}

	@Test
	public void test() {
		List<Sample> samples = configReader.getConfigList();
		System.out.println(samples);
		Sample config = configReader.getConfig(11);
		System.out.println(config);
	}

	@Test
	public void notfound() {
		try {
			Sample config = configReader.getConfig(21);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(e.getMessage(), "config not found,21");
		}
	}

	public static class Sample {
		private Integer age;

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("age", this.age).toString();
		}

	}
}
