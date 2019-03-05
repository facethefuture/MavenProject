package org.apache.maven.archetypes.response;

import java.util.List;

public class ResponseObj {
	public class MetaData{
		private int currentPage;
		private int totalPage;
		MetaData(){
			
		}
		MetaData(int page){
			this.currentPage = page;
		}
		public void setCurrentPage(int page){
			this.currentPage = page;
		}
		
		public void settotalPage(int totalPage){
			this.totalPage = totalPage;
		}
	}
	private MetaData metaData;
	private List<?> items;
	public MetaData getMetaData(){
		return this.metaData;
	}
	public List<?> getItems(){
		return this.items;
	}
	public void setMetaData(MetaData metaData){
		this.metaData = metaData;
	}
	public void setItems(List<?> items){
		this.items = items;
	}
}
