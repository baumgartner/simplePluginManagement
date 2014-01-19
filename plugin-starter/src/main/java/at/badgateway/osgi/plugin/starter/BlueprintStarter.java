package at.badgateway.osgi.plugin.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import at.badgateway.osgi.plugin.api.SimplePlugin;
import at.badgateway.osgi.plugin.manager.api.PluginManager;
import at.badgateway.osgi.plugin.manager.api.PluginNotFoundException;

public class BlueprintStarter implements InitializingBean {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private PluginManager pluginManager;

	@Override
	public void afterPropertiesSet() throws Exception {
		callPlugin("plugin A");
		callPlugin("plugin B");
	}

	private void callPlugin(String name) {
		SimplePlugin plugin;
		try {
			plugin = pluginManager.getPlugin(name);
			plugin.invoke("called by blueprintStarter");
		} catch (PluginNotFoundException e) {
			logger.error("plugin with name: {} not found", name);
		}
	}

	public void setPluginManager(PluginManager pluginManager) {
		this.pluginManager = pluginManager;
	}
}
