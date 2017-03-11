package lu.arhs.odyssi.speech2text;

public interface Speech2Text {
	String rawNLPQuery="";
	String cleanNLPQuery="";
	public String process(String input);
	public String cleanNLPQuery(String rawQuery);
	void setRawNLPQuery( String rawNLPQuery);
}
