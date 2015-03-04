package cucumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

public class HttpHelper {

	private static final Logger LOGGER = Logger.getLogger(ServerCallsStepDefinitions.class.getName());


	public static boolean FindStringInUrlContent(String urlString, String matchString, String uastring){

		URL url;

		try {
			// get URL content
			url = new URL(urlString);
			URLConnection conn = url.openConnection();

			conn.setRequestProperty("user-agent", uastring);
			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));

			String inputLine;

			while ((inputLine = br.readLine()) != null) {
				//bw.write(inputLine);
				//LOGGER.info(inputLine);
				if (inputLine.contains(matchString)){
					br.close();
					return true;
				}

			}

			br.close();


		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
