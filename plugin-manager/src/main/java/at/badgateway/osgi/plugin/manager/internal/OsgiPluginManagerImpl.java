package at.badgateway.osgi.plugin.manager.internal;

import java.util.Collection;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import at.badgateway.osgi.plugin.api.SimplePlugin;
import at.badgateway.osgi.plugin.manager.api.PluginManager;
import at.badgateway.osgi.plugin.manager.api.PluginNotFoundException;

public class OsgiPluginManagerImpl implements PluginManager {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private BundleContext bundleContext;

	public OsgiPluginManagerImpl(BundleContext bundleContext) {
		logger.info("constructor");
		this.bundleContext = bundleContext;
		
		try {
			Collection<ServiceReference<SimplePlugin>> refs = bundleContext
					.getServiceReferences(SimplePlugin.class, null);
			
			for (ServiceReference<SimplePlugin> ref : refs) {
				SimplePlugin plugin = bundleContext.getService(ref);
				logger.info("invoke plugin: {}", ref);
				plugin.invoke("osgiPluginManagerContstructor says hello World");
			}
		} catch (InvalidSyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public SimplePlugin getPlugin(String name)
			throws PluginNotFoundException {
		Assert.notNull(name);
		
		Collection<ServiceReference<SimplePlugin>> refs = null;
		try {
			refs = bundleContext.getServiceReferences(SimplePlugin.class,
					"(Bundle-SymbolicName="+name+")");
		} catch (InvalidSyntaxException e) {
			throw new RuntimeException(e);
		}
		if (CollectionUtils.isEmpty(refs)) {
			throw new PluginNotFoundException();
		}
		if (refs.size() > 1) {
			throw new PluginNotFoundException();
		}
		for (ServiceReference<SimplePlugin> ref : refs) {
			return bundleContext.getService(ref);
		}
		throw new PluginNotFoundException();
	}

}
