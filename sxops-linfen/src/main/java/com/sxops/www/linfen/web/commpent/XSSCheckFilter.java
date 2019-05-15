package com.sxops.www.linfen.web.commpent;

import com.sxops.www.common.xss.XSSHttpRequestParamsFirst;
import com.sxops.www.common.xss.XSSHttpServletRequest;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName="xssFilter",urlPatterns="/*")
public class XSSCheckFilter implements Filter {

	private String[] urlarr = {"/web/reserve/add", "/web/reserve/update", "/overTimeAudit/update", "/notice/add", "/notice/edit"};

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		if(whiteList(request)){
			filterChain.doFilter(request, resp);
			return;
		}
		//过滤相同名称的参数
		request = new XSSHttpRequestParamsFirst(request);
		//过滤非白名单下html
		request = new XSSHttpServletRequest(request);
        filterChain.doFilter(request, resp);
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}


	private Boolean whiteList(HttpServletRequest request){
		String requestURI = request.getRequestURI();
		for (String url : urlarr) {
			int index = requestURI.indexOf(url);
			if (index > -1) {
				return true;
			}
		}
		return false;
	}
}
