package main.java.com.excilys.cdb.core.page;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class PageManager <T> {

	protected int limit;
	protected int max;
	protected int maxPage;
	protected int offset;
	protected String filterField;
	protected ArrayList<T> pageData = new ArrayList<>();
	
	final static Logger LOGGER = LogManager.getLogger(PageManager.class);

	@UserChoice(name = "Get previous page ", order = 1)
	public boolean previous() {
		if(offset - limit < 0)
			return false;
		offset -= limit;
		return true;
	}
	
	@UserChoice(name = "Get next page ", order = 2)
	public boolean next() {
		if(offset + limit > max - 1)
			return false;
		offset += limit;
		return true;
	}
	
	@UserChoice(name = "Get first page ", order = 3)
	public boolean first() {
		offset = 0;
		return true;
	}
	
	@UserChoice(name = "Get last page ", order = 4)
	public boolean last() {
		offset = (maxPage-1) * limit;
		return true;
	}

	public int getLimit() {
		return limit;
	}

	public int getMax() {
		return max;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public int getOffset() {
		return offset;
	}

	public int getPage() {
		return (offset / limit) + 1;
	}

	public ArrayList<T> getPageData() {
		getItems();
		return pageData;
	}
	
	public String getFilterField() {
		return filterField;
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
		setOffset(0);
		refreshMaxPage();
	}

	protected void refreshMaxPage() {
		this.maxPage = (int) Math.ceil(((double) max) / (double) limit);
	}
	
	public void setMax(int max) {
		this.max = max;
		refreshMaxPage();
	}
	
	public void setFilterField(String filterField) {
		this.filterField = filterField;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	protected abstract boolean getItems();
	
	public void goTo(int page) {
		page = Math.min(page, maxPage);
		page = Math.max(1, page);
		offset = (page - 1) * limit;
	}

}