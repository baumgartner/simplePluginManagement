package at.badgateway.osgi.plugin.manager.internal;

import java.util.Collection;

import org.springframework.beans.factory.InitializingBean;

import at.badgateway.osgi.plugin.api.SimplePlugin;
import at.badgateway.osgi.plugin.manager.api.PluginManager;
import at.badgateway.osgi.plugin.manager.api.PluginNotFoundException;

public class BlueprintPluginManagerImpl implements PluginManager,
		InitializingBean {

	private Collection<SimplePlugin> allPlugins;

	@Override
	public SimplePlugin getPlugin(String name)
			throws PluginNotFoundException {
		for (SimplePlugin plugin : allPlugins) {
			if (plugin.getPluginName().equals(name)) {
				return plugin;
			}
		}
		throw new PluginNotFoundException();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		for (SimplePlugin plugin : allPlugins) {
			plugin.invoke("hello World");
		}
	}

	public void setAllPlugins(Collection<SimplePlugin> allPlugins) {
		this.allPlugins = allPlugins;
	}
}
