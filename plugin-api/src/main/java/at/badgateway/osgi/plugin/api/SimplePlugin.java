package at.badgateway.osgi.plugin.api;

public interface SimplePlugin {

	void invoke(String message);
	
	String getPluginName();

}
