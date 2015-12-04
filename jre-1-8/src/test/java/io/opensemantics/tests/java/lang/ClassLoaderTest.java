package io.opensemantics.tests.java.lang;

import static org.junit.Assert.*;

import java.net.URL;

import org.junit.Test;

public class ClassLoaderTest {

	@Test
	public void test_getResource_pathTraversal() {
	  final String resourceName = "resource.txt";
	  final String assetDir = "asset";
	  final String garbage = "aeuaoeuaou";

    // TN control
    URL resource = getClass().getClassLoader().getResource("./" + garbage + "/" +
                                                           resourceName);
    assertNull(resource);

	  // TP control
	  resource = getClass().getClassLoader().getResource("./" + assetDir + "/" +
	                                                     resourceName);
	  assertNotNull(resource);
	  
	  // Test upwards and then downwards traversal
	  resource = getClass().getClassLoader().getResource("./" + assetDir + "/" +
	                                                     "../" + assetDir + "/" +
	                                                     resourceName);
	  assertNotNull(resource);
	}
}
