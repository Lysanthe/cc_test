package bc.util;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;

import bc.util.mine.SHA256Miner;
import bc.util.mine.ThreadMiner;

public class Start {

	public static void main(String[] args) {
		Block block = new Block();
			
		ThreadMiner threadMiner = new ThreadMiner(block);
		
		threadMiner.start();
		
		threadMiner.writeStatus();
		
		
			
		StringWriter stringWriter = new StringWriter();
		final Map<String, String> generatorConfig = new HashMap<>();
		generatorConfig.put(JsonGenerator.PRETTY_PRINTING, "true");
		JsonWriterFactory writerFactory = Json.createWriterFactory(generatorConfig);
		JsonWriter writer = writerFactory.createWriter(stringWriter);
		writer.writeObject(block.json());
		writer.close();
		System.out.println(stringWriter.getBuffer().toString());
	}
}
