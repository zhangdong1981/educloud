package com.google.educloud.cloudnode;

import com.google.educloud.cloudnode.configuration.InvalidConfigurationException;
import com.google.educloud.cloudnode.configuration.NodeConfig;
import com.google.educloud.cloudnode.launchers.JettyLauncher;


public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			NodeConfig.setup();
		} catch (InvalidConfigurationException e) {
			System.exit(-1);
		}

		/* try start jetty */
		JettyLauncher.main(args);
	}

}
