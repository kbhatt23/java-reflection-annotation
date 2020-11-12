package configreader;

import java.util.Arrays;

public class ConfigBean {

	private String name;
	private String value;
	private String description;
	private int port;
	private String[] allowedHosts;
	
	private int allowedPorts[];
	public int[] getAllowedPorts() {
		return allowedPorts;
	}
	public void setAllowedPorts(int[] allowedPorts) {
		this.allowedPorts = allowedPorts;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String[] getAllowedHosts() {
		return allowedHosts;
	}
	public void setAllowedHosts(String[] allowedHosts) {
		this.allowedHosts = allowedHosts;
	}
	@Override
	public String toString() {
		return "ConfigBean [name=" + name + ", value=" + value + ", description=" + description + ", port=" + port
				+ ", allowedHosts=" + Arrays.toString(allowedHosts) + ", allowedPorts=" + Arrays.toString(allowedPorts)
				+ "]";
	}
	
	
	
}
