package uk.co.newsint.cip.utilities.ua;

import java.util.Properties;

/**
 * A User Agent parser that will first ask {@link UserAgentUtilsParser} to fill
 * {@link UserAgent} properties. For all unknown values will use
 * {@link RegexpUserAgentParser} to try fill the gaps using
 * {@link UserAgent#merge(UserAgent)} method.
 * 
 * @author Georgi Petkov
 * @since 1.0
 */
public class CompositeUserAgentParser extends UserAgentParser {
	// RegexpUserAgentParser instance
	protected RegexpUserAgentParser regexpParser;

	public CompositeUserAgentParser() {
		this.regexpParser = new RegexpUserAgentParser();
	}

	@Override
	public UserAgent parse(String userAgentString) {
		// 1. Use RegexpUserAgentParser to try parse the UA string
		UserAgent regexpUserAgent = regexpParser.parse(userAgentString);
		return regexpUserAgent;
	}
}
