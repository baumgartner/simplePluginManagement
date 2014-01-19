package at.badgateway.osgi.plugin.manager.api;

import at.badgateway.osgi.plugin.api.SimplePlugin;

public interface PluginManager {
	
	SimplePlugin getPlugin(String name) throws PluginNotFoundException;
}
