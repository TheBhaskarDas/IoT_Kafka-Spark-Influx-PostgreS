package com.opternaam.iot.sparkintegrator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import org.joda.time.DateTime;
import org.json.JSONArray;

import com.opternaam.iot.avroschemavalidation.AvroSchemTemp;
import com.opternaam.iot.resources.*;
import com.pygmalios.reactiveinflux.jawa.JavaPoint;
import com.pygmalios.reactiveinflux.jawa.Point;
import com.pygmalios.reactiveinflux.spark.jawa.SparkInflux;
import	com.opternaam.iot.sparkintegrator.*;


@SuppressWarnings("unused")
public class MqttInflux {

	static	Logger	log	=	Logger.getLogger(Validators.class);

	static	Float	tempVal	;
	static	Float	humiVal;

	private static String device_id = null, cust_id = null, devices_details = null;

	private static String value_hum = null, value_temp = null;
	private static String date = null;

	public static String generateJSON(ArrayList<AvroSchemTemp> listOfJsonBean) {
		org.json.JSONObject object = new org.json.JSONObject();
		JSONArray array = new JSONArray();
		object.put("temperaturejson", listOfJsonBean);
		array.put(object);
		System.out.println(array.toString());
		return array.toString();
	}

	public static void main(String[] args) {

		//PropertyConfigurator.configure("log4j.properties"); // CHANGE PATH TO LOG4J's PROPERTIES FILE

		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("Study Spark");

		JavaStreamingContext streamingContext = new JavaStreamingContext(conf, Durations.seconds(3));
		SparkInflux sparkInflux = new SparkInflux("opterna", 4000);

		SparkSession spark = SparkSession.builder().appName("Spark SQL examples").master("local").getOrCreate();
		spark.sparkContext().setLogLevel("INFO");

		Map<String, Object> kafkaParams = new HashMap<String, Object>();
		kafkaParams.put("bootstrap.servers", "localhost:9092");
		kafkaParams.put("key.deserializer", StringDeserializer.class);
		kafkaParams.put("value.deserializer", StringDeserializer.class);
		kafkaParams.put("group.id", "use_a_separate_group_id_for_each_stream");
		//kafkaParams.put("auto.offset.reset", "earliest");
		kafkaParams.put("enable.auto.commit", false);

		Collection<String> topics = Arrays.asList("influx_mqtt");

		JavaInputDStream<ConsumerRecord<String, String>> stream = KafkaUtils.createDirectStream(streamingContext,
				LocationStrategies.PreferConsistent(),
				ConsumerStrategies.<String, String>Subscribe(topics, kafkaParams));



		JavaDStream<Point> jdstreamPoint = stream.map(new Function<ConsumerRecord<String, String>, Point>() {

			// DeviceMsgSamplePattern-"msg,001,24.5,34.4,20171127192300,ESPcust1,esp"
			//STM DeviceMsgSamplePattern-"msg,002,24.5,34.4,20171127192300,STMcust1,STM,0122(Vibration)"
			public Point call(ConsumerRecord<String, String> data) throws Exception {
				Map<String, String> tags = new HashMap<>();
				tags.put("mytag", "1");
				Map<String, Object> fields = new HashMap<String, Object>();

				String[] splitedString = data.value().split(",");

				DeviceData	schema	=	new	DeviceData(); // CREATING A REFERNCE VARIABLE TO THE POJO GENERATED FROM THE SCHEMA....
				
				//SCHEMA FILE IS LOCATED IN "com.opternaam.iot.resources" NAMED AS DeviceData
				
				
				String	temp	=	splitedString[2];
				String	humi	=	splitedString[3];

				JsonBean beanConsEsp = null;
				Point pointTemp = null;

				if (splitedString.length > 1) {
					beanConsEsp = new JsonBean(data.topic(), splitedString[1].toString(), splitedString[5].toString(),
							splitedString[6].toString(), splitedString[4].toString(), splitedString[2].toString(),
							splitedString[3].toString());
					//(String topic, String deviceId, String custId, String devicesDetails, String date, String valueTemp, String valueHum)

				}

				if(Validators.tempValidator(temp)&&Validators.humiValidator(humi)) {
					schema.setDeviceID(splitedString[1]);
					schema.setTemperature(tempVal);
					schema.setHumidity(humiVal);
					schema.setDate(splitedString[4]);
					schema.setCutomerID(splitedString[5]);
					schema.setDeviceID(splitedString[6]);



					fields.put("Customer-Id", beanConsEsp.getCustId());
					fields.put("Device-Id", beanConsEsp.getDeviceId());
					fields.put("Value-Humidity", beanConsEsp.getValueHum());
					fields.put("Value-Temperature", beanConsEsp.getValueTemp());
					fields.put("Date-From-Device", beanConsEsp.getDate());
					fields.put("Device-Details", beanConsEsp.getDevicesDetails());
					//fields.put("ValueVibration", beanConsEsp.getValueVibration());
					pointTemp = new JavaPoint(DateTime.now(), "ESPTable", tags, fields);
				}
				else {
					System.out.println("CAUGHT EXCEPTION IN TRY OF SCHEMA");
					log.error("ERROR CAUGHT IN SETTING SCHEMA");

					fields.put("Customer-Id", beanConsEsp.getCustId());
					fields.put("Device-Id", beanConsEsp.getDeviceId());
					fields.put("Value-Humidity", beanConsEsp.getValueHum());
					fields.put("Value-Temperature", beanConsEsp.getValueTemp());
					fields.put("Date-From-Device", beanConsEsp.getDate());
					fields.put("Device-Details", beanConsEsp.getDevicesDetails());
					//fields.put("ValueVibration", beanConsEsp.getValueVibration());
					
					
					//STORING THE WRONG VALUES IN ANOTHER MEASUREMENT.

					pointTemp = new JavaPoint(DateTime.now(), "ESPTableNotApplicable", tags, fields);

				}


				return pointTemp;
			}

		});
		jdstreamPoint.print();
		if (jdstreamPoint != null && jdstreamPoint.dstream().countByValue$default$1() > 0) {
			jdstreamPoint.print();
			sparkInflux.saveToInflux(jdstreamPoint);
		} else {
			System.out.println("kafka topic message is empty");
		}
		streamingContext.start();
		try {
			streamingContext.awaitTermination();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
