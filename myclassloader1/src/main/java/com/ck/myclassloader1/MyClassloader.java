package com.ck.myclassloader1;

import java.io.IOException;

import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppClassLoader;

public class MyClassloader extends WebAppClassLoader {
	private static final Logger LOG = Log.getLogger(MyClassloader.class);

	public MyClassloader(ClassLoader parent, Context context) throws IOException {
		super(parent, context);
	}

	public MyClassloader(Context context) throws IOException {
		this(null, context);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		LOG.info("findClass {}", name);
		return super.findClass(name);
	}
	
	@Override
	public void addJars(Resource lib) {
		LOG.info("addJars {}", lib);
		super.addJars(lib);
	}
}
