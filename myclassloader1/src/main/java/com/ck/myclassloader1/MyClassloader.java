package com.ck.myclassloader1;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

import org.eclipse.jetty.util.StringUtil;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppClassLoader;

public class MyClassloader extends WebAppClassLoader {
	private static final Logger LOG = Log.getLogger(MyClassloader.class);
	private String sortMode;

	public MyClassloader(ClassLoader parent, Context context) throws IOException {
		super(parent, context);
		this.sortMode = System.getProperty("myclassloader.sort","off");
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
		addJars0(lib);
	}
	
    private boolean isFileSupported0(String file)
    {
    	Method method;
		try {
			method = WebAppClassLoader.class.getDeclaredMethod("isFileSupported", String.class);
			method.setAccessible(true);
			return (Boolean) method.invoke(this, file);
		} catch (Exception e) {
			throw new RuntimeException("isFileSupported", e);
		}
    }
    
	
    public void addJars0(Resource lib)
    {
        if (lib.exists() && lib.isDirectory())
        {
            String[] files=lib.list();
            sort(files);
            for (int f=0;files!=null && f<files.length;f++)
            {
                try 
                {
                    Resource fn=lib.addPath(files[f]);
                    if(LOG.isDebugEnabled())
                        LOG.debug("addJar - {}", fn);
                    String fnlc=fn.getName().toLowerCase(Locale.ENGLISH);
                    // don't check if this is a directory, see Bug 353165
                    if (isFileSupported0(fnlc))
                    {
                        String jar=fn.toString();
                        jar=StringUtil.replace(jar, ",", "%2C");
                        jar=StringUtil.replace(jar, ";", "%3B");
                        addClassPath(jar);
                    }
                }
                catch (Exception ex)
                {
                    LOG.warn(Log.EXCEPTION,ex);
                }
            }
        }
    }

	private void sort(String[] files) {
		
		LOG.info("MyClassloader sort is {}", this.sortMode);

		switch (this.sortMode) {
		case "asc":
			Arrays.sort(files);
			break;
		case "desc":
			Arrays.sort(files, Collections.reverseOrder());
			break;
		default:
			LOG.info("MyClassloader does not change classpath order");
			break;
		}
	}
}
