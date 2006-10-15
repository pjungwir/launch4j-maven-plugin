package com.akathist.maven.plugins.l4j;

public class MavenLog extends net.sf.launch4j.Log {

	org.apache.maven.plugin.logging.Log _log;

	public MavenLog(org.apache.maven.plugin.logging.Log log) {
		_log = log;
	}

	public void clear() {
		_log.info("");
	}

	public void append(String line) {
		_log.info("launch4j: " + line);
	}

}
