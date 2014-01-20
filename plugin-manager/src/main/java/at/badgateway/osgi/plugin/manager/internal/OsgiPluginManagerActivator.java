package at.badgateway.osgi.plugin.manager.internal;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.badgateway.osgi.plugin.manager.api.PluginManager;

public class OsgiPluginManagerActivator implements BundleActivator {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		PluginManager manager = new OsgiPluginManagerImpl(bundleContext);
		
		Dictionary<String, String> properties = new Hashtable<String, String>();
		properties.put("myPluginManager", "OsgiPluginManager");
		
		logger.info("add service");
		bundleContext.registerService(PluginManager.class, manager, properties);
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
	}
}
