/*
	Launch4j (http://launch4j.sourceforge.net/)
	Cross-platform Java application wrapper for creating Windows native executables.

	Copyright (C) 2004, 2006 Grzegorz Kowal

	This program is free software; you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation; either version 2 of the License, or
	(at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with this program; if not, write to the Free Software
	Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*/

/*
 * Created on Apr 21, 2005
 */
package net.sf.launch4j.config;

import java.io.File;
import java.util.List;

import net.sf.launch4j.binding.IValidatable;
import net.sf.launch4j.binding.Validator;

/**
 * @author Copyright (C) 2005 Grzegorz Kowal
 */
public class Config implements IValidatable {

	// 1.x config properties_____________________________________________________________
	public static final String HEADER = "header";
	public static final String JAR = "jar";
	public static final String OUTFILE = "outfile";
	public static final String ERR_TITLE = "errTitle";
	public static final String JAR_ARGS = "jarArgs";
	public static final String CHDIR = "chdir";
	public static final String CUSTOM_PROC_NAME = "customProcName";
	public static final String STAY_ALIVE = "stayAlive";
	public static final String ICON = "icon";

	// __________________________________________________________________________________
	public static final String GUI_HEADER = "gui";
	public static final String CONSOLE_HEADER = "console";

	private static final String[] HEADER_TYPES = new String[] { GUI_HEADER,
																	CONSOLE_HEADER };

	private boolean dontWrapJar;
	private String headerType = GUI_HEADER;
	private List headerObjects;
	private List libs;
	private File jar;
	private File outfile;

	// Runtime header configuration
	private String errTitle;
	private String cmdLine;
	private String chdir;
	private boolean customProcName;
	private boolean stayAlive;
	private File icon;
	private List variables;
	private ClassPath classPath;
	private Jre jre;
	private Splash splash;
	private VersionInfo versionInfo;

	public void checkInvariants() {
		Validator.checkTrue(outfile != null && outfile.getPath().endsWith(".exe"),
				"outfile", Messages.getString("Config.specify.output.exe"));
		if (dontWrapJar) {
			if (jar != null && !jar.getPath().equals("")) {
				Validator.checkRelativeWinPath(jar.getPath(), "jar",
						Messages.getString("Config.application.jar.path"));
			} else {
				Validator.checkTrue(classPath != null, "classPath",
						Messages.getString("ClassPath.or.jar"));
			}
		} else {
			Validator.checkFile(jar, "jar",
					Messages.getString("Config.application.jar"));
		}
		if (!Validator.isEmpty(chdir)) {
			Validator.checkRelativeWinPath(chdir, "chdir",
					Messages.getString("Config.chdir.relative"));
			Validator.checkFalse(chdir.toLowerCase().equals("true")	
					|| chdir.toLowerCase().equals("false"),
					"chdir", Messages.getString("Config.chdir.path"));
		}
		Validator.checkOptFile(icon, "icon", Messages.getString("Config.icon"));
		Validator.checkOptString(cmdLine, Validator.MAX_BIG_STR, "jarArgs",
				Messages.getString("Config.jar.arguments"));
		Validator.checkOptString(errTitle, Validator.MAX_STR, "errTitle",
				Messages.getString("Config.error.title"));
		Validator.checkIn(getHeaderType(), HEADER_TYPES, "headerType",
				Messages.getString("Config.header.type"));
		Validator.checkFalse(getHeaderType().equals(CONSOLE_HEADER) && splash != null,
				"headerType",
				Messages.getString("Config.splash.not.impl.by.console.hdr"));
		Validator.checkOptStrings(variables,
				Validator.MAX_ARGS,
				Validator.MAX_ARGS,
				"[^=%\t]+=[^=\t]+",
				"variables",
				Messages.getString("Config.variables"),
				Messages.getString("Config.variables.err"));
		jre.checkInvariants();
	}
	
	public void validate() {
		checkInvariants();
		if (classPath != null) {
			classPath.checkInvariants();
		}
		if (splash != null) {
			splash.checkInvariants();
		}
		if (versionInfo != null) {
			versionInfo.checkInvariants();
		}
	}

	/** Change current directory to EXE location. */
	public String getChdir() {
		return chdir;
	}

	public void setChdir(String chdir) {
		this.chdir = chdir;
	}

	/** Constant command line arguments passed to the application. */
	public String getCmdLine() {
		return cmdLine;
	}

	public void setCmdLine(String cmdLine) {
		this.cmdLine = cmdLine;
	}

	/** Optional, error message box title. */
	public String getErrTitle() {
		return errTitle;
	}

	public void setErrTitle(String errTitle) {
		this.errTitle = errTitle;
	}

	/** launch4j header file. */
	public String getHeaderType() {
		return headerType.toLowerCase();
	}

	public void setHeaderType(String headerType) {
		this.headerType = headerType;
	}

	/** launch4j header file index - used by GUI. */
	public int getHeaderTypeIndex() {
		String type = getHeaderType();
		for (int i = 0; i < HEADER_TYPES.length; i++) {
			if (type.equals(HEADER_TYPES[i])) {
				return i;
			}
		}
		return 0;
	}

	public void setHeaderTypeIndex(int headerTypeIndex) {
		this.headerType = HEADER_TYPES[headerTypeIndex];
	}

	public boolean isCustomHeaderObjects() {
		return headerObjects != null && !headerObjects.isEmpty();
	}

	public List getHeaderObjects() {
		return isCustomHeaderObjects() ? headerObjects
				: getHeaderType().equals(GUI_HEADER)
						? LdDefaults.GUI_HEADER_OBJECTS
						: LdDefaults.CONSOLE_HEADER_OBJECTS;
	}

	public void setHeaderObjects(List headerObjects) {
		this.headerObjects = headerObjects;
	}

	public boolean isCustomLibs() {
		return libs != null && !libs.isEmpty();
	}

	public List getLibs() {
		return isCustomLibs() ? libs : LdDefaults.LIBS;
	}

	public void setLibs(List libs) {
		this.libs = libs;
	}

	/** ICO file. */
	public File getIcon() {
		return icon;
	}

	public void setIcon(File icon) {
		this.icon = icon;
	}

	/** Jar to wrap. */
	public File getJar() {
		return jar;
	}

	public void setJar(File jar) {
		this.jar = jar;
	}

	public List getVariables() {
		return variables;
	}

	public void setVariables(List variables) {
		this.variables = variables;
	}

	public ClassPath getClassPath() {
		return classPath;
	}
	
	public void setClassPath(ClassPath classpath) {
		this.classPath = classpath;
	}

	/** JRE configuration */
	public Jre getJre() {
		return jre;
	}

	public void setJre(Jre jre) {
		this.jre = jre;
	}

	/** Output EXE file. */
	public File getOutfile() {
		return outfile;
	}

	public void setOutfile(File outfile) {
		this.outfile = outfile;
	}

	/** Custom process name as the output EXE file name. */
	public boolean isCustomProcName() {
		return customProcName;
	}

	public void setCustomProcName(boolean customProcName) {
		this.customProcName = customProcName;
	}

	/** Splash screen configuration. */
	public Splash getSplash() {
		return splash;
	}

	public void setSplash(Splash splash) {
		this.splash = splash;
	}

	/** Stay alive after launching the application. */
	public boolean isStayAlive() {
		return stayAlive;
	}

	public void setStayAlive(boolean stayAlive) {
		this.stayAlive = stayAlive;
	}
	
	public VersionInfo getVersionInfo() {
		return versionInfo;
	}

	public void setVersionInfo(VersionInfo versionInfo) {
		this.versionInfo = versionInfo;
	}

	public boolean isDontWrapJar() {
		return dontWrapJar;
	}

	public void setDontWrapJar(boolean dontWrapJar) {
		this.dontWrapJar = dontWrapJar;
	}
}
