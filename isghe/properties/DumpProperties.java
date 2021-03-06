package isghe.properties;
import java.util.List;
import java.util.Properties;
import java.util.ArrayList;

class DumpProperties{
	private static String wrap (Object object){
		final String wrap = "\"";
		return wrap + object + wrap;
	}
	public static void main(String args[]){
		final Properties properties = System.getProperties();
		List <String> fields = new ArrayList <String> ();
		for (Object key: properties.keySet()) {
			if (!"line.separator".equals (key)){
				fields.add (DumpProperties.wrap (key) + ": " + DumpProperties.wrap (System.getProperty (key.toString ())));
			}
		}
		System.out.println ("{"+String.join (",", fields)+"}");
	}
}
