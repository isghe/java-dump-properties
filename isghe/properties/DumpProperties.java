package isghe.properties;
import java.util.Map;
import java.util.List;
import java.util.Properties;
import java.util.HashMap;
import java.util.ArrayList;
import org.apache.logging.log4j.Logger;

class DumpProperties{
	private static String wrap (final Object object){
		final String wrap = "\"";
		return wrap + object + wrap;
	}

	private static <Key, Value> String mapToJson (final Map <Key, Value> map){
		final List<String> list = new ArrayList<>();
		map.forEach((key, value)->{
			list.add(key+":"+value);
		});
		return "{"+String.join (",", list)+"}";
	}

	public static void main(String args[]){
		final Properties properties = System.getProperties();
		final Map <String, Object> fields = new HashMap <> ();
		for (Object key: properties.keySet()) {
			if (!"line.separator".equals (key)){
				assert false == fields.containsKey(key): key;
				fields.put (DumpProperties.wrap (key), DumpProperties.wrap (System.getProperty (key.toString ())));
			}
		}
		final String log4j2Key = "log4j2";
		assert false == fields.containsKey(log4j2Key): log4j2Key;
		fields.put (DumpProperties.wrap (log4j2Key), DumpProperties.wrap (org.apache.logging.log4j.Logger.class.getPackage().getSpecificationVersion()));
		System.out.println (DumpProperties.mapToJson(fields));
	}
}
