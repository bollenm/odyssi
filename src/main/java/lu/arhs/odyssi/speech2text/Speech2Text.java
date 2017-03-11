package lu.arhs.odyssi.speech2text;

public interface Speech2Text {
	String rawnLPQuery="";
	String cleanNLPQuery="";
	public String process(String input);
	public String cleanNLPQuery(String rawQuery);

}
