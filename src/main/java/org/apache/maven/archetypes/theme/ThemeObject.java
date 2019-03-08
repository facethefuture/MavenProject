package org.apache.maven.archetypes.theme;

public class ThemeObject {
	private String title;
	private String imageUrl;
	private int id;
	public ThemeObject(int id,String title,String imageUrl){
		this.id = id;
		this.title = title;
		this.imageUrl = imageUrl;
	}
	public String getTitle(){
		return this.title;
	}
	public String getImageUrl(){
		return this.imageUrl;
	}
	public int getId(){
		return this.id;
	}
}
