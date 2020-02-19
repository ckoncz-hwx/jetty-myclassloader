package com.ck.webapp1;

import java.io.IOException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.xml.XmlConfiguration;

import com.ck.myclassloader1.MyClassloader;

public class Main
{

    public static Server createServer(int port)
    {
        Server server = new Server(port);

        if(false) {
        	HandlerCollection handlerCollection = new HandlerCollection();
        	server.setHandler(handlerCollection);
        	
        	WebAppContext webAppContext = new WebAppContext();
        	webAppContext.setResourceBase("src/main/webapp");

        	setClassLoader(webAppContext);
        	handlerCollection.addHandler(webAppContext);
        }
        
        readJettyXml(server);

        return server;
    }

	private static void readJettyXml(Server server) {
		try {
			XmlConfiguration xmlConfiguration = new XmlConfiguration(Main.class.getClassLoader().getResource("jetty.xml"));
			xmlConfiguration.getIdMap().put("Server", server);
			
			xmlConfiguration.getProperties().put("jetty.deploy.monitoredPath","src/main/resources");
			xmlConfiguration.configure();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void setClassLoader(WebAppContext webAppContext) {
		try {
			webAppContext.setClassLoader(new MyClassloader(webAppContext));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public static void main(String[] args) throws Exception
    {
        // Create a basic jetty server object that will listen on port 8080.
        int port = 8080;
        Server server = createServer(port);

        // Start things up!
        server.start();

        // The use of server.join() the will make the current thread join and
        // wait until the server thread is done executing.
        server.join();
    }

}