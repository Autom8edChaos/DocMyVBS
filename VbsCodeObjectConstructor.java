import java.io.*;

public class VbsCodeObjectConstructor {
	
	public static void main(String... args) throws IOException
	{
		String fileName = args[0];
		
		VbsCodeObjectConstructor test = new VbsCodeObjectConstructor(fileName);
		System.out.print(test.get_codeString());
	}
	
	
	public VbsCodeObjectConstructor(String aFileName) throws IOException {
		set_fileName(aFileName);
		set_codeString(FileReader.readFile(aFileName));
		
		// now that we have the code in string format, we can extract the classes etc.
		VBScriptCodeStructure vbscriptCodeStructure = new VBScriptCodeStructure();
		
		ClassExtractor classExtractor = new ClassExtractor();
		classExtractor.code = get_codeString();
		
		vbscriptCodeStructure.Add("classes", classExtractor.Extract()); 
		
		
	}
	
	public String get_fileName() {
		return _fileName;
	}
	public void set_fileName(String _fileName) {
		this._fileName = _fileName;
	}
	public String get_codeString() {
		return _codeString;
	}
	
	public void set_codeString(String _codeString) {
		this._codeString = _codeString;
	}
	
	private String _codeString = null;
	private String _fileName = null;
}
