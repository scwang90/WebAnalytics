package com.kumkee.userAgent;

import com.webanalytics.useragent.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAgentParser
{
	public UserAgent parse(String useragent)
	{
		UserAgent userAgent = new UserAgent();
		userAgent.setBrowseer(this.browser(useragent));
		userAgent.setVersion(this.browserVersion(useragent, userAgent.getBrowseer()));
		userAgent.setEngine(this.engine(useragent));
		userAgent.setEngineVersion(engineVersion(useragent));
		userAgent.setOs(this.OS(useragent));
		userAgent.setPlatform(this.platform(useragent));
		userAgent.setMobile(Platform.mobilePlatforms.contains(userAgent.getPlatform()) || userAgent.getBrowseer().equalsIgnoreCase(Browseer.PSP));
		Brand brand = Brand.parser(useragent);
		userAgent.setModel(brand.getModel());
		userAgent.setBrand(brand);
		userAgent.setNetType(Nettype.parser(useragent));
		userAgent.setDevice(com.webanalytics.useragent.Platform.parser(useragent));
		userAgent.setApplication(Application.parser(useragent));
		userAgent.setOperateSystem(OperateSystem.parser(useragent));
		userAgent.setBrowser(Browser.parser(useragent));
		userAgent.setBrowserEngine(BrowserEngine.parser(useragent));
		return userAgent;
	}

	public String engineVersion(String userAgentString)
	{
		String regexp = engine(userAgentString)+"[\\/\\- ]([\\d\\w\\.\\-]+)";
		// System.out.println("Engine Version: "+regexp);
		Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE); 
		Matcher matcher = pattern.matcher(userAgentString);
		//System.out.println(matcher.groupCount());
		
		if(matcher.find())
		{
			String version = matcher.group(1);
			// System.out.println("Verison: "+version);
			return version;
		}
		
		return null;
	}
	
	public String engine(String userAgentString)
	{
		if(matches(Engine.WebkitPattern, userAgentString))
			return Engine.Webkit;
		if(matches(Engine.KHTMLPattern, userAgentString))
			return Engine.KHTML;
		if(matches(Engine.KonquerorPattern, userAgentString))
			return Engine.Konqeror;
		if(matches(Engine.ChromePattern, userAgentString))
			return Engine.Chrome;
		if(matches(Engine.PrestoPattern, userAgentString))
			return Engine.Presto;
		if(matches(Engine.GeckoPattern, userAgentString))
			return Engine.Gecko;
		if(matches(Engine.OperaPattern, userAgentString))
			return Engine.Unknown;
		if(matches(Engine.MSIEPattern, userAgentString))
			return Engine.MSIE;
		if(matches(Engine.MIDPPattern, userAgentString))
			return Engine.MIDP;
		
		return Engine.Unknown;
	}
	
	public String platform(String userAgentString)
	{
		if(matches(Platform.WindowsPhonePattern, userAgentString))
			return "Windows Phone";
		if(matches(Platform.WindowsPattern, userAgentString))
			return "Windows";
		if(matches(Platform.MacPattern, userAgentString))
			return "Mac";
		if(matches(Platform.AndroidPattern, userAgentString))
			return "Android";
		if(matches(Platform.BlackberryPattern, userAgentString))
			return "Blackberry";
		if(matches(Platform.LinuxPattern, userAgentString))
			return "Linux";
		if(matches(Platform.WiiPattern, userAgentString))
			return "Wii";
		if(matches(Platform.PlaystationPattern, userAgentString))
			return "Playstation";
		if(matches(Platform.iPadPattern, userAgentString))
			return "iPad";
		if(matches(Platform.iPodPattern, userAgentString))
			return "iPod";
		if(matches(Platform.iPhonePattern, userAgentString))
			return "iPhone";
		if(matches(Platform.SymbianPattern, userAgentString))
			return "Symbian";
		if(matches(Platform.JavaPattern, userAgentString))
			return "Java";
		
		return "Unknown";
	}

	public String browser(String userAgentString)
	{
		if(matches(Browseer.KonquerorPattern, userAgentString))
			return Browseer.Konqueror;
		else if(matches(Browseer.IEPattern, userAgentString))
			return Browseer.IE;
		else if(matches(Browseer.QQPattern, userAgentString))
			return Browseer.QQ;
		else if(matches(Browseer.MIUIPattern, userAgentString))
			return Browseer.MIUI;
		else if(matches(Browseer.XiaomiPattern, userAgentString))
			return Browseer.Xiaomi;
		else if(matches(Browseer.SougouPattern, userAgentString))
			return Browseer.Sougou;
		else if(matches(Browseer.BaiduPattern, userAgentString))
			return Browseer.Baidu;
		else if(matches(Browseer._360Pattern, userAgentString))
			return Browseer._360;
		else if(matches(Browseer.WeixinPattern, userAgentString))
			return Browseer.Weixin;
		else if(matches(Browseer.OperaPattern, userAgentString))
			return Browseer.Opera;
		else if(matches(Browseer.ChromePattern, userAgentString))
			return Browseer.Chrome;
		else if(matches(Browseer.SafariPattern, userAgentString))
			return Browseer.Safari;
		else if(matches(Browseer.PS3Pattern, userAgentString))
			return Browseer.PS3;
		else if(matches(Browseer.PSPPattern, userAgentString))
			return Browseer.PSP;
		else if(matches(Browseer.FirefoxPattern, userAgentString))
			return Browseer.Firefox;
		else if(matches(Browseer.LotusPattern, userAgentString))
			return Browseer.Lotus;
		else if(matches(Browseer.NetscapePattern, userAgentString))
			return Browseer.Netscape;
		else if(matches(Browseer.SeamonkeyPattern, userAgentString))
			return Browseer.Seamonkey;
		else if(matches(Browseer.ThunderbirdPattern, userAgentString))
			return Browseer.Thunderbird;
		else if(matches(Browseer.OutlookPattern, userAgentString))
			return Browseer.Outlook;
		else if(matches(Browseer.EvolutionPattern, userAgentString))
			return Browseer.Evolution;
		else if(matches(Browseer.MSIEMobilePattern, userAgentString))
			return Browseer.MSIEMobile;
		else if(matches(Browseer.MSIEPattern, userAgentString))
			return Browseer.MSIE;
		else if(matches(Browseer.BlackberryPattern, userAgentString))
			return Browseer.Blackberry;
		else if(matches(Browseer.GabblePattern, userAgentString))
			return Browseer.Gabble;
		else if(matches(Browseer.YammerDesktopPattern, userAgentString))
			return Browseer.YammerDesktop;
		else if(matches(Browseer.YammerMobilePattern, userAgentString))
			return Browseer.YammerMobile;
		else if(matches(Browseer.ApacheHTTPClientPattern, userAgentString))
			return Browseer.ApacheHTTPClient;
		else if(matches(Browseer.EDGEPattern, userAgentString))
			return Browseer.EDGE;
		else
			return Browseer.Unknown;
	}

	public Pattern browserPattern(String userAgentString)
	{
		if(matches(Browseer.KonquerorPattern, userAgentString))
			return Browseer.KonquerorPattern;
		else if(matches(Browseer.IEPattern, userAgentString))
			return Browseer.IEPattern;
		else if(matches(Browseer.QQPattern, userAgentString))
			return Browseer.QQPattern;
		else if(matches(Browseer.MIUIPattern, userAgentString))
			return Browseer.MIUIPattern;
		else if(matches(Browseer.XiaomiPattern, userAgentString))
			return Browseer.XiaomiPattern;
		else if(matches(Browseer.WeixinPattern, userAgentString))
			return Browseer.WeixinPattern;
		else if(matches(Browseer.SougouPattern, userAgentString))
			return Browseer.SougouPattern;
		else if(matches(Browseer.BaiduPattern, userAgentString))
			return Browseer.BaiduPattern;
		else if(matches(Browseer._360Pattern, userAgentString))
			return Browseer._360Pattern;
		else if(matches(Browseer.OperaPattern, userAgentString))
			return Browseer.OperaPattern;
		else if(matches(Browseer.ChromePattern, userAgentString))
			return Browseer.ChromePattern;
		else if(matches(Browseer.SafariPattern, userAgentString))
			return Browseer.SafariPattern;
		else if(matches(Browseer.PS3Pattern, userAgentString))
			return Browseer.PS3Pattern;
		else if(matches(Browseer.PSPPattern, userAgentString))
			return Browseer.PSPPattern;
		else if(matches(Browseer.FirefoxPattern, userAgentString))
			return Browseer.FirefoxPattern;
		else if(matches(Browseer.LotusPattern, userAgentString))
			return Browseer.LotusPattern;
		else if(matches(Browseer.NetscapePattern, userAgentString))
			return Browseer.NetscapePattern;
		else if(matches(Browseer.SeamonkeyPattern, userAgentString))
			return Browseer.SeamonkeyPattern;
		else if(matches(Browseer.ThunderbirdPattern, userAgentString))
			return Browseer.ThunderbirdPattern;
		else if(matches(Browseer.OutlookPattern, userAgentString))
			return Browseer.OutlookPattern;
		else if(matches(Browseer.EvolutionPattern, userAgentString))
			return Browseer.EvolutionPattern;
		else if(matches(Browseer.MSIEMobilePattern, userAgentString))
			return Browseer.MSIEMobilePattern;
		else if(matches(Browseer.MSIEPattern, userAgentString))
			return Browseer.MSIEPattern;
		else if(matches(Browseer.BlackberryPattern, userAgentString))
			return Browseer.BlackberryPattern;
		else if(matches(Browseer.GabblePattern, userAgentString))
			return Browseer.GabblePattern;
		else if(matches(Browseer.YammerDesktopPattern, userAgentString))
			return Browseer.YammerDesktopPattern;
		else if(matches(Browseer.YammerMobilePattern, userAgentString))
			return Browseer.YammerMobilePattern;
		else if(matches(Browseer.ApacheHTTPClientPattern, userAgentString))
			return Browseer.ApacheHTTPClientPattern;
		else if(matches(Browseer.EDGEPattern, userAgentString))
			return Browseer.EDGEPattern;
		else
			return Browseer.UnknownPattern;
	}

	public String browserVersion(String userAgentString, String browser)
	{
		Pattern pattern;

		if(browser.equalsIgnoreCase(Browseer.IE))
		{
			pattern = BrowserVersion.IEPattern;
		}
		else if(browser.equalsIgnoreCase(Browseer.Chrome))
		{
			pattern = BrowserVersion.ChromePattern;
		}
		else if(browser.equalsIgnoreCase(Browseer.Safari))
		{
			pattern = BrowserVersion.SafariPattern;
		}
		else if(browser.equalsIgnoreCase(Browseer.PS3))
		{
			pattern = BrowserVersion.PS3Pattern;
		}
		else if(browser.equalsIgnoreCase(Browseer.PSP))
		{
			pattern = BrowserVersion.PSPPattern;
		}
		else if(browser.equalsIgnoreCase(Browseer.Lotus))
		{
			pattern = BrowserVersion.LotusPattern;
		}
		else if(browser.equalsIgnoreCase(Browseer.Blackberry))
		{
			pattern = BrowserVersion.BlackberryPattern;
		}
		else if(browser.equalsIgnoreCase(Browseer.YammerDesktop))
		{
			pattern = BrowserVersion.YammerDesktopPattern;
		}
		else if(browser.equalsIgnoreCase(Browseer.YammerMobile))
		{
			pattern = BrowserVersion.YammerMobilePattern;
		}
		else if(browser.equalsIgnoreCase(Browseer.ApacheHTTPClient))
		{
			pattern = BrowserVersion.ApacheDesktopClientPattern;
		}
		else
		{
			// Append the Browsers name to the start of the generic "Other" regexp
			pattern = Pattern.compile(browserPattern(userAgentString).pattern() + BrowserVersion.Other, Pattern.CASE_INSENSITIVE);
		}

		Matcher matcher = pattern.matcher(userAgentString);
		if(matcher.find())
		{
			return matcher.group(matcher.groupCount());
		}
		else
		{
			return null;
		}
	}

	public String OS(String userAgentString)
	{
		if(matches(OS.AdobeAirPattern, userAgentString))
			return replaceTokens(OS.AdobeAirPattern, userAgentString, "Adobe Air #{$1}");
		if(matches(OS.WindowsPhonePattern, userAgentString))
			return "Windows Phone";
		if(matches(OS.WindowsVistaPattern, userAgentString))
			return "Windows Vista";
		if(matches(OS.Windows7Pattern, userAgentString))
			return "Windows 7";
		if(matches(OS.Windows2003Pattern, userAgentString))
			return "Windows 2003";
		if(matches(OS.WindowsXPPattern, userAgentString))
			return "Windows XP";
		if(matches(OS.Windows2000Pattern, userAgentString))
			return "Windows 2000";
		if(matches(OS.WindowsPattern, userAgentString))
			return "Windows";
		if(matches(OS.OSXPattern, userAgentString))
			return replaceTokens(OS.OSXPattern, userAgentString, "OS X #{$1}.#{$2}");
		if(matches(OS.AndroidPattern, userAgentString)){
			Matcher matcher = OS.AndroidPattern.matcher(userAgentString);
			matcher.find();
			return matcher.group();
		}
		if(matches(OS.LinuxPattern, userAgentString))
			return "Linux";
		if(matches(OS.WiiPattern, userAgentString))
			return "Wii";
		if(matches(OS.PS3Pattern, userAgentString))
			return "Playstation 3";
		if(matches(OS.PSPPattern, userAgentString))
			return "Playstation Portable";
		if(matches(OS.YpodPattern, userAgentString))
			return replaceTokens(OS.YpodPattern, userAgentString, "iPhone OS #{$1}.#{$2}");
		if(matches(OS.YpadPattern, userAgentString))
			return replaceTokens(OS.YpadPattern, userAgentString, "iPhone OS #{$1}.#{$2}");
		if(matches(OS.YphonePattern, userAgentString))
			return replaceTokens(OS.YphonePattern, userAgentString, "iPhone OS #{$1}.#{$2}");
		if(matches(OS.IpadPattern, userAgentString))
			return replaceTokens(OS.IpadPattern, userAgentString, "iPad OS #{$1}.#{$2}");
		if(matches(OS.IphonePattern, userAgentString))
			return replaceTokens(OS.IphonePattern, userAgentString, "iPhone OS #{$1}.#{$2}");
		if(matches(OS.DarwinPattern, userAgentString))
			return "Darwin";
		if(matches(OS.JavaPattern, userAgentString))
			return replaceTokens(OS.JavaPattern, userAgentString, "Java #{$1}");
		if(matches(OS.SymbianPattern, userAgentString))
			return "Symbian OS";
		if(matches(OS.BlackBerryPattern, userAgentString))
			return "BlackBerry OS";
		
			//return "OSX";

		return "Unknown";
	}

	public boolean matches(Pattern pattern, String userAgentStr)
	{
		return pattern.matcher(userAgentStr).find();
	}

	/**
	 * Replaces the tokens within the format String with the content of the groups found within the userAgentString. 
	 * <p>
	 * Tokens within the format string are formatted as #{$i} where i is the index of the group in the pattern. 
	 * <p>
	 * TODO: I would expect that there is an easier way to do this. 
	 * 
	 * @param pattern The regexp pattern to search for groups within the userAgentString
	 * @param userAgentString
	 * @param format The string to replace the tokens within
	 * @return The format string with the tokens replaced with the groups found in the userAgentString
	 */
	public String replaceTokens(Pattern pattern, String userAgentString, String format)
	{
		// System.out.println("UserAgent: " + userAgentString);
		// System.out.println("Regexp: " + pattern.toString());
		// System.out.println("Replacement: " + format);

		Matcher m = pattern.matcher(userAgentString);	

		// Move the group content into an array
		List<String> groupContent = new ArrayList<String>();
		// System.out.println("Group Count: "+m.groupCount());

		m.find();
		for(int i=0; i<=m.groupCount(); i++)
		{
			String s = m.group(i);
			// System.out.println("--"+s);
			groupContent.add(s);
		}
		
		// Replace the tokens in the pattern 
		for(int i=0; i<groupContent.size(); i++)
		{
			String token = "#\\{\\$"+i+"\\}";
			// System.out.println("Replacing ["+token+"] with ["+groupContent.get(i)+"]");
			format = format.replaceAll(token, groupContent.get(i));
		}
		
		// System.out.println("Response: "+format);
		return format;
	}
}
