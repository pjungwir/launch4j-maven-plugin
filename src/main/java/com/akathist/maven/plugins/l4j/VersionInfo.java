package com.akathist.maven.plugins.l4j;

/**
 * Information that appears in the Windows Explorer.
 */
public class VersionInfo {

	/**
	 * Version number in x.x.x.x format.
	 */
	String fileVersion;

	/**
	 * Free-form version number, like "1.20.RC1."
	 */
	String txtFileVersion;

	/**
	 * File description shown to the user.
	 */
	String fileDescription;

	/**
	 * Legal copyright.
	 */
	String copyright;

	/**
	 * Version number in x.x.x.x format.
	 */
	String productVersion;

	/**
	 * Free-form version number, like "1.20.RC1."
	 */
	String txtProductVersion;

	/**
	 * The product name.
	 */
	String productName;

	/**
	 * The company name.
	 */
	String companyName;

	/**
	 * The internal name. For instance, you could use the filename without extension or the module name.
	 */
	String internalName;

	/**
	 * The original filename without path. Setting this lets you determine whether a user has renamed the file.
	 */
	String originalFilename;

	net.sf.launch4j.config.VersionInfo toL4j() {
		net.sf.launch4j.config.VersionInfo ret = new net.sf.launch4j.config.VersionInfo();

		ret.setFileVersion(fileVersion);
		ret.setTxtFileVersion(txtFileVersion);
		ret.setFileDescription(fileDescription);
		ret.setCopyright(copyright);
		ret.setProductVersion(productVersion);
		ret.setTxtProductVersion(txtProductVersion);
		ret.setProductName(productName);
		ret.setCompanyName(companyName);
		ret.setInternalName(internalName);
		ret.setOriginalFilename(originalFilename);

		return ret;
	}

}
