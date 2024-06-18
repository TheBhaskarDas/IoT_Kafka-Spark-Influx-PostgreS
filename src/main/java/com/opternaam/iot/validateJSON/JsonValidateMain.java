package com.opternaam.iot.validateJSON;

import java.io.File;
import java.io.IOException;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;

public class JsonValidateMain {

	public static void main(String[] args) {
		File schemaFile = new File("Schema.json");
		//File jsonFile = new File("SampleJson.json");
		String bean = "[{\"JsonBean\":[{\"devicesDetails\":\"esp\",\"custId\":\"cust1\",\"topic\":\"day28\",\"valueTemp\":\"24.5\",\"deviceId\":\"001\",\"valueHum\":34.4}]}]";
		try {
			if (ValidationUtils.isValidJson(schemaFile,bean)){
				System.out.println("Valid!");
			}else{
				System.out.println("NOT valid!");
			}
		} catch (ProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
