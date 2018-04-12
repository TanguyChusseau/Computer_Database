package main.java.com.excilys.cdb.core.page;

import java.util.ArrayList;
import java.util.List;

public class PageData<T> {
	
	private List<T> dataList = new ArrayList<>();
	private long numberOfComputers;
	private long currentPage;
	private long maxPage;
	private long limit;
	private String search;
	private String orderBy;
	private String order;
	
	public List<T> getDataList() {
		return dataList;
	}
	
	public long getNumberOfComputers() {
		return numberOfComputers;
	}
	
	public long getCurrentPage() {
		return currentPage;
	}
	
	public long getMaxPage() {
		return maxPage;
	}
	
	public long getLimit() {
		return limit;
	}
	
	public String getSearch() {
		return search;
	}
	
	public String getOrderBy() {
		return orderBy;
	}
	
	public String getOrder() {
		return order;
	}

	public void setNumberOfComputers(long numberOfComputers) {
		this.numberOfComputers = numberOfComputers;
	}
	
	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}
	
	public void setMaxPage(long maxPage) {
		this.maxPage = maxPage;
	}
	
	public void setLimit(long limit) {
		this.limit = limit;
	}
	
	public void setSearch(String search) {
		this.search = search;
	}
	
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	public void setOrder(String order) {
		this.order = order;
	}
}