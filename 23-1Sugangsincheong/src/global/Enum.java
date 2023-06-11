package global;

public class Enum {
	
	public enum department{
		applicationSoftware("응용소프트웨어");

		private String text;
		private department(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
		public int getInt() {
			return Integer.parseInt(text);
		}
	}
}
