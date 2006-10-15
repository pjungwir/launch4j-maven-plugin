package com.akathist.maven.plugins.l4j;

import java.io.*;

public class Splash {

	/**
	 * The path (relative to the executable when distributed) to the splash page image.
	 */
	File file;

	/**
	 * If true, the splash screen will close automatically as soon as an error window or java window appears.
	 * If false, the splash screen will not close until {@link #timeout} sections. Defaults to true.
	 *
	 * @parameter default-value=true;
	 */
	boolean waitForWindow;

	/**
	 * The number of seconds to keep the splash screen open before automatically closing it.
	 * Defaults to 60.
	 *
	 * @parameter default-value=60
	 */
	int timeout;

	/**
	 * If true, an error message will appear if the app hasn't started in {@link #timeout} seconds.
	 * If false, the splash screen will close quietly. Defaults to true.
	 *
	 * @parameter default-value=true
	 */
	boolean timeoutErr;

	net.sf.launch4j.config.Splash toL4j() {
		net.sf.launch4j.config.Splash ret = new net.sf.launch4j.config.Splash();

		ret.setFile(file);
		ret.setWaitForWindow(waitForWindow);
		ret.setTimeout(timeout);
		ret.setTimeoutErr(timeoutErr);

		return ret;
	}

}
