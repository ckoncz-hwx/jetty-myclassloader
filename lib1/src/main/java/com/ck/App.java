package com.ck;

import java.net.URL;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
    
    public static URL getUrl() {
    	return App.class.getResource("App.class");
    }
}
