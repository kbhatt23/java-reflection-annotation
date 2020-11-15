package configreader;

import java.util.Arrays;

@Service
public class ConfigService {

	@Value(property = "name")
	private String serivceName;
	
	@Value
	private String description;
	
	@Value
	private int port;
	
	@Value
	private String[] allowedHosts;
	
	private String fakeProperty ;
;
	@Override
	public String toString() {
		return "ConfigService [serivceName=" + serivceName + ", description=" + description + ", port=" + port + ", allowedHosts="
				+ Arrays.toString(allowedHosts) + ",fakeProperty="+fakeProperty+"]";
	}
	
	
	
}
