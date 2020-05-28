

/**
 *  @author Adham Khalifa

 Implementation of an Event object class 
 * @param <E>
 */


 import java.time.*;
 import java.util.regex.PatternSyntaxException;

 public class Event {
 	private String content;
 	
 	public String Name;
 	public LocalDateTime Time;
 	
		/**
		gets a string form.
		@return content - string form.
	 *	@param <E>
	 */
		public String getString()	//gets it in the form of a string
		{
			return content;
		}

			/**
		Parses strings
		@return e - parsed string.
	 *	@param (str, quotes)
	 */
		
		public static Event parse(String str, boolean quotes)	// parses the string according to the quotes condition
		{
			
			Event e = new Event();
			if(quotes) {
			str = str.substring(1, str.length()-1);				//gets a substring to eliminate the quotations.
		}
		e.content = str;
		String[] splitArray;
		try
		{
			splitArray = str.split("\\s+");
		} catch (PatternSyntaxException ex) {
			return null;
		}
		
			LocalDateTime time = LocalDateTime.of(Integer.parseInt(splitArray[2]),	//getting the localtime.
				Integer.parseInt(splitArray[0]), 
				Integer.parseInt(splitArray[1]), 
				Integer.parseInt(splitArray[3]), 
				Integer.parseInt(splitArray[4]));
			String name = "";
			for(int i=5; i < splitArray.length; i++)
			{
				name += splitArray[i] + (i == splitArray.length - 1 ? "" : " ");
			}
			
			e.Name = name;
			e.Time = time;
			return e;
			
		}
	}
