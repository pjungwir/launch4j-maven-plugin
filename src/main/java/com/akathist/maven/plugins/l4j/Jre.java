package com.akathist.maven.plugins.l4j;

import java.util.*;

/**
 * Details about which jre the executable should call.
 */
public class Jre {

	/**
	 * Use this property when you are bundling a jre with your application. It holds the path to the jre.
	 * If relative, this path is from the executable.
	 * <p>
	 * If you specify path only and not minVersion, then the executable will show an error if the jre is not found.
	 * <p>
	 * If you specify path along with minVersion, then the executable will check the path first, and if no jre
	 * is found there, it will search the local system for a jre matching minVersion. If it still doesn't
	 * find anything, it will show the java download page. You may also specify maxVersion to further
	 * constrain the search.
	 */
	String path;

	/**
	 * Use this property if you want the executable to search the system for a jre.
	 * It names the minimum version acceptable, in x.x.x[_xx] format.
	 * <p>
	 * If you specify this property without giving a path, then the executable will search for a jre
	 * and, none is found, display the java download page.
	 * <p>
	 * If you include a path also, the executable will try that path before searching for jre matching minVersion.
	 * <p>
	 * In either case, you can also specify a maxVersion.
	 */
	String minVersion;

	/**
	 * If you specify minVersion, you can also use maxVersion to further constrain the search for a jre.
	 * This property should be in the format x.x.x[_xx].
	 */
	String maxVersion;

	/**
	 * If true, the private JREs of SDKs will not be used.
	 *
	 * @parameter default-value=false
	 */
	boolean dontUsePrivateJres;

	/**
	 * Sets java's initial heap size in MB, like the -Xms flag.
	 */
	int initialHeapSize;

	/**
	 * Sets java's maximum heap size in MB, like the -Xmx flag.
	 */
	int maxHeapSize;

	/**
	 * Use this to pass arbitrary options to the java/javaw program.
	 * For instance, you can say:
	 * <pre>
	 * &lt;opt&gt;-Dlaunch4j.exedir="%EXEDIR%"&lt;/opt&gt;
	 * &lt;opt&gt;-Dlaunch4j.exefile="%EXEFILE%"&lt;/opt&gt;
	 * &lt;opt&gt;-Denv.path="%Path%"&lt;/opt&gt;
	 * &lt;opt&gt;-Dsettings="%HomeDrive%%HomePath%\\settings.ini"&lt;/opt&gt;
	 * </pre>
	 */
	List opts;

	net.sf.launch4j.config.Jre toL4j() {
		net.sf.launch4j.config.Jre ret = new net.sf.launch4j.config.Jre();

		ret.setPath(path);
		ret.setMinVersion(minVersion);
		ret.setMaxVersion(maxVersion);
		ret.setDontUsePrivateJres(dontUsePrivateJres);
		ret.setInitialHeapSize(initialHeapSize);
		ret.setMaxHeapSize(maxHeapSize);
		ret.setOptions(opts);

		return ret;
	}

}
