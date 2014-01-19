package at.badgateway.osgi.plugin.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import at.badgateway.osgi.plugin.api.SimplePlugin;

public class FirstPluginImpl implements SimplePlugin {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public String pluginName;

	public void setPluginName(String pluginName) {
		logger.info("new Plugin name: {}", pluginName);
		this.pluginName = pluginName;
	}

	public void invoke(String message) {
		logger.info("this is specific code for Plugin A");
		logger.info("{}: {}", pluginName, message);
	}

	@Override
	public String getPluginName() {
		return pluginName;
	}
}
