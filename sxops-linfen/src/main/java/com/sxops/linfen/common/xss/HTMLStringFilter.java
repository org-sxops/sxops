package com.sxops.linfen.common.xss;

import com.alibaba.fastjson.JSONObject;
import com.sxops.linfen.common.util.StringUtils;
import com.sxops.linfen.common.util.log.LogMessage;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HTMLStringFilter {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	private static final String regxpForHtml = "<([^>]*)>"; // 过滤所有以<开头以>结尾的标签

	/**
	 * <p>
	 * Description:[html白名单内容]
	 * </p>
	 */
	private static String witeHtmlContent = "";

	/**
	 * <p>
	 * Description:[白名单对象]
	 * </p>
	 */
	private static Whitelist whitelist = null;

	/**
	 * <p>
	 * Description:[过滤非白名单正则]
	 * </p>
	 */
	private static Map<String, String> regexMap = null;

	public void init() {
		initWiteHtmlContent();
		initRegexMap();
		initWhitelist();
	}

	/**
	 * <p>
	 * Description:[初始化白名单内容]
	 * </p>
	 * Created on 2017年11月21日
	 * 
	 * @author:[尹归晋]
	 */
	private void initWiteHtmlContent() {
		if (StringUtils.isEmpty(witeHtmlContent)) {
			LOGGER.info("HTMLStringFilter-initWiteHtmlContent-{}", "初始化白名单内容");
			InputStream input = null;
			Writer output = null;
			try {
				input = getClass().getClassLoader().getResourceAsStream("xss/whitelist.conf");
				output = new StringWriter();
				IOUtils.copy(input, output);
				witeHtmlContent = output.toString();
			} catch (IOException e) {
				e.printStackTrace();
				String message = "初始化白名单内容失败 ";
				String error = e.toString();
				String log = this.getClass().getSimpleName() + "-initWiteHtmlContent" + message;
				LOGGER.error(log + error, e);
			} finally {
				if (input != null) {
					IOUtils.closeQuietly(input);
				}
				if (output != null) {
					IOUtils.closeQuietly(output);
				}
			}
		}
	}

	/**
	 * <p>
	 * Description:[初始化名单正则]
	 * </p>
	 * Created on 2017年11月21日
	 * 
	 * @return
	 * @author:[尹归晋]
	 */
	private void initRegexMap() {
		if (regexMap == null && !StringUtils.isEmpty(witeHtmlContent)) {
			synchronized (new Object()) {
				try {
					LOGGER.info("HTMLStringFilter-initRegexMap" + "初始化白名单正则");
					regexMap = new HashMap<String, String>();
					JSONObject jsonObject = JSONObject.parseObject(witeHtmlContent);
					JSONObject whitelistjson = jsonObject.getJSONObject("whiteList");
					Map<String, Map<String, String>> whitelistmap = JSONObject.parseObject(whitelistjson.toJSONString(),
							HashMap.class);
					for (Map.Entry<String, Map<String, String>> entiy : whitelistmap.entrySet()) {
						String tag = entiy.getKey();
						for (Map.Entry<String, String> entiy2 : entiy.getValue().entrySet()) {
							String attribute = entiy2.getKey();
							String attributeValue = entiy2.getValue();
							if (attributeValue != null && attributeValue.trim().length() > 0) {
								regexMap.put(tag + "[" + attribute + "]", attributeValue);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					String message = "初始化名单正则失败 ";
					String error = e.toString();
					String log = (this.getClass().getSimpleName() + "-initRegexMap" + message).toString();
					LOGGER.error(log + error, e);
				}
			}
		}
	}

	/**
	 * <p>
	 * Description:[初始化白名单对象]
	 * </p>
	 * Created on 2017年11月21日
	 * 
	 * @author:[尹归晋]
	 */
	private void initWhitelist() {
		if (whitelist == null && !StringUtils.isEmpty(witeHtmlContent)) {
			synchronized (new Object()) {
				LOGGER.info("HTMLStringFilter-initWhitelist" + "初始化白名单对象");
				try {
					whitelist = new Whitelist();
					JSONObject jsonObject = JSONObject.parseObject(witeHtmlContent);
					JSONObject whitelistjson = jsonObject.getJSONObject("whiteList");
					JSONObject protocolsjson = jsonObject.getJSONObject("protocols");
					Map<String, Map<String, String>> whitelistmap = JSONObject.parseObject(whitelistjson.toJSONString(),
							HashMap.class);
					Map<String, List<String>> protocolsmap = JSONObject.parseObject(protocolsjson.toJSONString(),
							HashMap.class);
					for (Map.Entry<String, Map<String, String>> entiy : whitelistmap.entrySet()) {
						String tag = entiy.getKey();
						whitelist.addTags(tag);
						for (Map.Entry<String, String> entiy2 : entiy.getValue().entrySet()) {
							String attribute = entiy2.getKey();
							whitelist.addAttributes(tag, attribute);
						}
					}
					for (Map.Entry<String, List<String>> entiy : protocolsmap.entrySet()) {
						String tag = entiy.getKey().substring(0, entiy.getKey().indexOf("."));
						String key = entiy.getKey().substring(entiy.getKey().indexOf(".") + 1, entiy.getKey().length());
						for (String entiy2 : entiy.getValue()) {
							whitelist.addProtocols(tag, key, entiy2);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					String message = "初始化白名单对象 ";
					String error = LogMessage.getNew().add(e).toString();
					String log = LogMessage.getNew().add(this.getClass().getSimpleName() + "-initWhitelist" + message)
							.toString();
					LOGGER.error(log + error);
				}
			}
		}
	}

	public String HTMLEncode(String fString) {
		fString = fString.replaceAll(" <", "<");
		fString = fString.replaceAll(">", ">");
		fString = fString.replaceAll(new String(new char[] { 32 }), " ");
		fString = fString.replaceAll(new String(new char[] { 9 }), " ");
		fString = fString.replaceAll(new String(new char[] { 34 }), "");
		fString = fString.replaceAll(new String(new char[] { 39 }), "'");
		fString = fString.replaceAll(new String(new char[] { 13 }), "");
		fString = fString.replaceAll(new String(new char[] { 10, 10 }), " </p> <p>");
		fString = fString.replaceAll(new String(new char[] { 10 }), " <br>");
		return fString;
	}

	/**
	 * xss escape
	 */
	public String xssEscape(String input) {
		return input == null ? null : input.replaceAll("<", "<").replaceAll(">", ">")
		// .replaceAll("eval\\((.*)\\)", "")
		// .replaceAll("[\"'][\\s]*((?i)javascript):(.*)[\"']", "\"\"")
		// .replaceAll("((?i)script)", "")
		;
	}

	/**
	 * 除指定标签之外的html标签编码
	 *
	 * @param str
	 * @param tag
	 * @return
	 */
	public String xssEscapeExceptTag(String str, String tag) {
		String replaceTag = "@" + tag + "@";
		str = str.replaceAll("<" + tag, replaceTag);
		str = xssEscape(str);
		str = str.replaceAll(replaceTag, "<" + tag);

		return str;
	}

	public static void main(String[] args) {
		String htmlStr = "<script>alert(1)</script><span style='font-size:18;color:red;' onclick='javascript:alert(b);'></span><embed src=\"data:text/html;base64,   PHNjcmlwdD5hbGVydCgxKTwvc2NyaXB0Pg==\"><scrit>alert(1)</script><html>bb&lt;script&gt;alert(1)&lt;script&gt;<img style='display:inline;' alt='[挤眼]' src='http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/c3/zy_thumb.gif' height='22' width='22' />bb<img style='display:inline;' alt='[挤眼]' src='http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/c3/zy_thumb.gif' height='22' width='22' />aaaa</html>";

		HTMLStringFilter htmlFilter = new HTMLStringFilter();
		htmlFilter.init();
		String s = htmlFilter.cleanSafeHtml(htmlStr);
		HTMLStringFilter htmlFilter2 = new HTMLStringFilter();
		htmlFilter2.init();
		System.out.println("--------------------");
		System.out.println(s);
		System.out.println("--------------------");
		List<String> srcs = htmlFilter2.getImgHTML(htmlStr);
		for (String src : srcs) {
			System.out.println("=======" + src);
		}

	}

	/**
	 * 过滤一下字符串，连同前后< xxx >yyy< / xxx >全部消除。 不区分大小写、空格可识别 <br>
	 * "function", "window\\.", "javascript:", "script", <br>
	 * "js:", "about:", "file:", "document\\.", "vbs:", "frame", <br>
	 * "cookie", "onclick", "onfinish", "onmouse", "onexit=", <br>
	 * "onerror", "onclick", "onkey", "onload", "onfocus", "onblur"
	 *
	 * @param htmlStr
	 * @return
	 */
	public String filterSafe(String htmlStr) {
		Pattern p = null; // 正则表达式
		Matcher m = null; // 操作的字符串
		StringBuffer tmp = null;
		String str = "";
		boolean isHave = false;
		String[] Rstr = { "meta", "script", "object", "embed" };
		if (htmlStr == null || !(htmlStr.length() > 0)) {
			return "";
		}
		str = htmlStr.toLowerCase();
		for (int i = 0; i < Rstr.length; i++) {
			p = Pattern.compile("<" + Rstr[i] + "(.[^>])*>");
			m = p.matcher(str);
			tmp = new StringBuffer();
			if (m.find()) {
				m.appendReplacement(tmp, "<" + Rstr[i] + ">");
				while (m.find()) {

					m.appendReplacement(tmp, "<" + Rstr[i] + ">");
				}
				isHave = true;
			}
			m.appendTail(tmp);
			str = tmp.toString();
			p = Pattern.compile("</" + Rstr[i] + "(.[^>])*>");
			m = p.matcher(str);
			tmp = new StringBuffer();
			if (m.find()) {
				m.appendReplacement(tmp, "</" + Rstr[i] + ">");
				while (m.find()) {
					m.appendReplacement(tmp, "</" + Rstr[i] + ">");
				}
				isHave = true;
			}
			m.appendTail(tmp);
			str = tmp.toString();

		}
		String[] Rstr1 = { "function", "window\\.", "javascript:", "script", "js:", "about:", "file:", "document\\.",
				"vbs:", "frame", "cookie", "onclick", "onfinish", "onmouse", "onexit=", "onerror", "onclick", "onkey",
				"onload", "onfocus", "onblur" };
		for (int i = 0; i < Rstr1.length; i++) {
			p = Pattern.compile("<([^<>])*" + Rstr1[i] + "([^<>])*>([^<>])*</([^<>])*>");
			m = p.matcher(str);
			tmp = new StringBuffer();
			if (m.find()) {
				m.appendReplacement(tmp, "");
				while (m.find()) {
					m.appendReplacement(tmp, "");
				}
				isHave = true;
			}
			m.appendTail(tmp);
			str = tmp.toString();
		}
		if (isHave) {
			htmlStr = str;
		}
		htmlStr = htmlStr.replaceAll("%3C", "<");
		htmlStr = htmlStr.replaceAll("%3E", ">");
		htmlStr = htmlStr.replaceAll("%2F", "");
		htmlStr = htmlStr.replaceAll("&#", "<b>&#</b>");
		return htmlStr;
	}

	/**
	 * 采用jsoup白名单方式过滤非法的html字符。 原理： 1.首先通过白名单过滤掉非法的html标签，即只允许输出白名单内的标签
	 * 2.对特殊的属性（主要是style）用正则过滤，只允许安全的属性值存在
	 *
	 * @param htmlStr
	 *            原始的html片段（用户通过富文本编辑器提交的html代码）
	 * @return 过滤后的安全的html片段
	 */
	public String cleanSafeHtml(String htmlStr) {
		if (StringUtils.isEmpty(htmlStr) || whitelist == null) {
			return htmlStr;
		}
		LOGGER.info(LogMessage.getNew().add("HTMLStringFilter-cleanSafeHtml" + "采用jsoup白名单方式过滤非法的html字符").toString());
		Document doc = Jsoup.parseBodyFragment(htmlStr);
		Document.OutputSettings outSet = new Document.OutputSettings();
		outSet.prettyPrint(false);
		outSet.outline(false);
		doc.outputSettings(outSet);
		if (regexMap != null) {
			for (Map.Entry<String, String> entiy : regexMap.entrySet()) {
				String key = entiy.getKey();
				Elements els = doc.select(key);
				for (Element el : els) {
					String attribute = key.substring(key.indexOf("[") + 1, key.indexOf("]"));
					String attributeValue = el.attr(attribute);
					Matcher valueMatcher = Pattern.compile(entiy.getValue()).matcher(attributeValue);
					if (valueMatcher.find()) {
						String safeValue = valueMatcher.group();
						el.attr(attribute, safeValue);
					}
				}
			}
		}
		String safeString = Jsoup.clean(doc.html(), whitelist);
		if(safeString != null) {
//			Pattern emoji = Pattern.compile ("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",Pattern.UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;
			Pattern emoji = Pattern.compile ("(?:[\uD83C\uDF00-\uD83D\uDDFF]|[\uD83E\uDD00-\uD83E\uDDFF]|[\uD83D\uDE00-\uD83D\uDE4F]|[\uD83D\uDE80-\uD83D\uDEFF]|[\u2600-\u26FF]\uFE0F?|[\u2700-\u27BF]\uFE0F?|\u24C2\uFE0F?|[\uD83C\uDDE6-\uD83C\uDDFF]{1,2}|[\uD83C\uDD70\uD83C\uDD71\uD83C\uDD7E\uD83C\uDD7F\uD83C\uDD8E\uD83C\uDD91-\uD83C\uDD9A]\uFE0F?|[\u0023\u002A\u0030-\u0039]\uFE0F?\u20E3|[\u2194-\u2199\u21A9-\u21AA]\uFE0F?|[\u2B05-\u2B07\u2B1B\u2B1C\u2B50\u2B55]\uFE0F?|[\u2934\u2935]\uFE0F?|[\u3030\u303D]\uFE0F?|[\u3297\u3299]\uFE0F?|[\uD83C\uDE01\uD83C\uDE02\uD83C\uDE1A\uD83C\uDE2F\uD83C\uDE32-\uD83C\uDE3A\uD83C\uDE50\uD83C\uDE51]\uFE0F?|[\u203C\u2049]\uFE0F?|[\u25AA\u25AB\u25B6\u25C0\u25FB-\u25FE]\uFE0F?|[\u00A9\u00AE]\uFE0F?|[\u2122\u2139]\uFE0F?|\uD83C\uDC04\uFE0F?|\uD83C\uDCCF\uFE0F?|[\u231A\u231B\u2328\u23CF\u23E9-\u23F3\u23F8-\u23FA]\uFE0F?)",Pattern.UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;
			Matcher emojiMatcher = emoji.matcher(safeString);
			if (emojiMatcher.find()) {
				safeString = emojiMatcher.replaceAll("");
				return safeString ;
			}
			return safeString;
		}
		LOGGER.info(LogMessage.getNew().add("HTMLStringFilter-cleanSafeHtml" + "采用jsoup白名单方式过滤非法的html字符成功").toString());
		return safeString;
	}

	public String filter(String input) {
		if (!hasSpecialChars(input)) {
			return input;
		}
		StringBuffer filtered = new StringBuffer(input.length());
		char c;
		for (int i = 0; i <= input.length() - 1; i++) {
			c = input.charAt(i);
			switch (c) {
			case '<':
				filtered.append("<");
				break;
			case '>':
				filtered.append(">");
				break;
			case '"':
				filtered.append("&uot;");
				break;
			case '&':
				filtered.append("&");
				break;
			default:
				filtered.append(c);
			}
		}
		return (filtered.toString());
	}

	public boolean hasSpecialChars(String input) {
		boolean flag = false;
		if ((input != null) && (input.length() > 0)) {
			char c;
			for (int i = 0; i <= input.length() - 1; i++) {
				c = input.charAt(i);
				switch (c) {
				case '>':
					flag = true;
					break;
				case '<':
					flag = true;
					break;
				case '"':
					flag = true;
					break;
				case '&':
					flag = true;
					break;

				}
			}
		}
		return flag;
	}

	/**
	 *
	 * 基本功能：过滤所有以"<"开头以">"结尾的标签
	 * <p>
	 *
	 * @param str
	 * @return String
	 */
	public String filterHtml(String str) {
		Pattern pattern = Pattern.compile(regxpForHtml);
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result1 = matcher.find();
		while (result1) {
			matcher.appendReplacement(sb, "");
			result1 = matcher.find();
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 过滤除指定tag之外的html标签
	 *
	 * @param str
	 * @param tag
	 * @return
	 */
	public String filterHtmlExceptTag(String str, String tag) {
		String replaceTag = "@" + tag + "@";
		str = str.replaceAll("<" + tag, replaceTag);
		str = filterHtml(str);
		str = str.replaceAll(replaceTag, "<" + tag);
		return str;
	}

	/**
	 *
	 * 基本功能：过滤指定标签
	 * <p>
	 *
	 * @param str
	 * @param tag
	 *            指定标签
	 * @return String
	 */
	public String fiterHtmlTag(String str, String tag) {
		String regxp = "<\\s*" + tag + "\\s+([^>]*)\\s*>";
		Pattern pattern = Pattern.compile(regxp);
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result1 = matcher.find();
		while (result1) {
			matcher.appendReplacement(sb, "");
			result1 = matcher.find();
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	/**
	 *
	 * 基本功能：替换指定的标签
	 * <p>
	 *
	 * @param str
	 * @param beforeTag
	 *            要替换的标签
	 * @param tagAttrib
	 *            要替换的标签属性值
	 * @param startTag
	 *            新标签开始标记
	 * @param endTag
	 *            新标签结束标记
	 * @return String @如：替换img标签的src属性值为[img]属性值[/img]
	 */
	public String replaceHtmlTag(String str, String beforeTag, String tagAttrib, String startTag, String endTag) {
		String regxpForTag = "<\\s*" + beforeTag + "\\s+([^>]*)\\s*>";
		String regxpForTagAttrib = tagAttrib + "=\"([^\"]+)\"";
		Pattern patternForTag = Pattern.compile(regxpForTag);
		Pattern patternForAttrib = Pattern.compile(regxpForTagAttrib);
		Matcher matcherForTag = patternForTag.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result = matcherForTag.find();
		while (result) {
			StringBuffer sbreplace = new StringBuffer();
			Matcher matcherForAttrib = patternForAttrib.matcher(matcherForTag.group(1));
			if (matcherForAttrib.find()) {
				matcherForAttrib.appendReplacement(sbreplace, startTag + matcherForAttrib.group(1) + endTag);
			}
			matcherForTag.appendReplacement(sb, sbreplace.toString());
			result = matcherForTag.find();
		}
		matcherForTag.appendTail(sb);
		return sb.toString();
	}

	/**
	 * html标签替换为指定字符
	 *
	 * @param str
	 * @param tag
	 * @param text
	 * @return
	 */
	public String replaceHtmlTagOfText(String str, String tag, String text) {
		String regxp = "<\\s*" + tag + "\\s+([^>]*)\\s*>";
		Pattern pattern = Pattern.compile(regxp);
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result1 = matcher.find();
		while (result1) {
			matcher.appendReplacement(sb, text);
			result1 = matcher.find();
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 获取指定HTML标签的指定属性的值
	 *
	 * @param source
	 *            要匹配的源文本
	 * @param element
	 *            标签名称
	 * @param attr
	 *            标签的属性名称
	 * @return 属性值列表
	 */
	public List<String> match(String source, String element, String attr) {
		List<String> result = new ArrayList<String>();
		String reg = "<" + element + "[^<>]*?\\s" + attr + "=['\"]?(.*?)['\"]?\\s.*?>";
		Matcher m = Pattern.compile(reg).matcher(source);
		while (m.find()) {
			String r = m.group(1);
			result.add(r);
		}
		return result;
	}

	public List<String> getImgHTML(String html) {
		List<String> resultList = new ArrayList<String>();
		Pattern p = Pattern.compile("<img ([^>]*)");// <img开头 >结尾
		Matcher m = p.matcher(html);// 开始编译
		while (m.find()) {
			resultList.add("<img " + m.group(1) + ">");// 获取匹配的部分
		}

		return resultList;
	}

	public List<String> getImgSrc(String htmlStr) {
		String img = "";
		Pattern p_image;
		Matcher m_image;
		List<String> pics = new ArrayList<String>();

		String regEx_img = "<img.*src=(.*?)[^>]*?>"; // 图片链接地址
		p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
		m_image = p_image.matcher(htmlStr);
		while (m_image.find()) {
			img = m_image.group();
			Matcher m = Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(img); // 匹配src
			while (m.find()) {
				pics.add(m.group(1));
			}
		}
		return pics;
	}

	public List<String> getImgAlt(String htmlStr) {
		String img = "";
		Pattern p_image;
		Matcher m_image;
		List<String> alts = new ArrayList<String>();

		String regEx_img = "<img.*src=(.*?)[^>]*?>"; // 图片链接地址
		p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
		m_image = p_image.matcher(htmlStr);
		while (m_image.find()) {
			img = m_image.group();
			Matcher m = Pattern.compile("alt=\"?(.*?)(\"|>|\\s+)").matcher(img); // 匹配src
			while (m.find()) {
				alts.add(m.group(1));
			}
		}
		return alts;
	}

	/**
	 * 
	 * 基本功能：过滤所有以"<"开头以">"结尾的标签,但是替换为空格
	 * <p>
	 * 
	 * @param str
	 * @return String
	 */
	public String filterHtmlWithSapce(String str) {
		Pattern pattern = Pattern.compile(regxpForHtml);
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result1 = matcher.find();
		while (result1) {
			matcher.appendReplacement(sb, " ");
			result1 = matcher.find();
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

}