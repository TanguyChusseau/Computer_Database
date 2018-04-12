package main.java.com.excilys.cdb.core.page;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PaginationTag extends SimpleTagSupport {

	private long limit;
	private long maxPage;
	private String search;
	private long span = 7;
	private long currentPage;
	
	public long getLimit() {
		return limit;
	}
	
	public long getMaxPage() {
		return maxPage;
	}
	
	public String getSearch() {
		return search;
	}
	
	public long getCurrentPage() {
		return currentPage;
	}

	public void setLimit(long limit) {
		this.limit = limit;
	}
	
	public void setMaxPage(long maxPage) {
		this.maxPage = maxPage;
	}
	
	public void setSearch(String search) {
		this.search = search;
	}
	
	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}
	
	public void doTag() throws JspException, IOException {

		JspWriter out = getJspContext().getOut();
		StringBuilder pagination = new StringBuilder();

		long normalStart = (long) (currentPage - Math.floor(span / 2));
		long normalEnd = (long) (currentPage + Math.floor(span / 2));

		long start = Math.max(1, normalStart);
		long end = Math.min(maxPage, normalEnd);

		if (start > normalStart) {
			end = Math.min(maxPage, normalEnd + (start - normalStart));
		}

		if (end < normalEnd) {
			start = Math.max(1, normalStart - (normalEnd - end));
		}

		if (currentPage > 1) {
			pagination.append(getCode(1, "<<", false));
			pagination.append(getCode((currentPage - 1), "<", false));
		}		

		for (long i = start; i <= end; ++i) {
			if (i == currentPage) {
				pagination.append(getCode(i, "-", true));
			} else {
				pagination.append(getCode(i, "" + i, false));
			}
		}

		if (currentPage < (maxPage)) {
			pagination.append(getCode((long) (currentPage + 1), ">", false));
			pagination.append(getCode(maxPage, ">>", false));
		}

		out.print(pagination.toString());
	}
	
	private String getCode(long page, String toPrint, boolean disabled) {
		return "<li><a " + (disabled ? "" : "href=dashboard?page=" + page) + "> " + toPrint + " </a><li>\n";
	}

}