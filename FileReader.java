import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

public class FileReader {
	
	public static void main(String... args) {
		Charset cs = Charset.defaultCharset();
		String fileName = args[0];	
		try {
			System.out.print(readFile(fileName, cs));
		} catch (IOException e) {
			System.out.printf("Could not read file %s", fileName);
		}
	}
	
	public static String readFile(String file, String csName) throws IOException {
		Charset cs = Charset.forName(csName);
		return readFile(file, cs);
	}
	
	public static String readFile(String file) throws IOException
	{
		Charset cs = Charset.defaultCharset();
		return readFile(file, cs);
	}

	public static String readFile(String file, Charset cs) throws IOException {
		// No real need to close the BufferedReader/InputStreamReader
	    // as they're only wrapping the stream
	    FileInputStream stream = new FileInputStream(file);
	    try {
	        Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
	        StringBuilder builder = new StringBuilder();
	        char[] buffer = new char[8192];
	        int read;
	        while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
	            builder.append(buffer, 0, read);
	        }
	        return builder.toString();
	    } finally {
	        // Potential issue here: if this throws an IOException,
	        // it will mask any others. Normally I'd use a utility
	        // method which would log exceptions and swallow them
	        stream.close();
	    }        
	}
}
