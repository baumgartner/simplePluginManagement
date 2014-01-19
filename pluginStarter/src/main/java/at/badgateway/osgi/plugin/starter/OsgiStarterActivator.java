package at.badgateway.osgi.plugin.starter;

import java.util.Collection;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import at.badgateway.osgi.plugin.manager.api.PluginManager;
import at.badgateway.osgi.plugin.manager.api.PluginNotFoundException;

public class OsgiStarterActivator implements BundleActivator {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		logger.info("fetching osgi plugin manager");
		Collection<ServiceReference<PluginManager>> refs = null;
		PluginManager pluginManager = null; 
		
		try {
			refs = bundleContext.getServiceReferences(PluginManager.class,
					"(myPluginManager=OsgiPluginManager)");
		} catch (InvalidSyntaxException e) {
			throw new RuntimeException(e);
		}
		if (CollectionUtils.isEmpty(refs)) {
			throw new PluginNotFoundException();
		}
		if (refs.size() > 1) {
			throw new PluginNotFoundException();
		}
		for (ServiceReference<PluginManager> ref : refs) {
			pluginManager = bundleContext.getService(ref);
		}
		pluginManager.getPlugin("pluginA").invoke("osgiStarter says hello");
		pluginManager.getPlugin("pluginB").invoke("osgiStarter says hello");
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
	}
}
