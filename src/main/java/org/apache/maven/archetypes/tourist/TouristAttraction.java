package org.apache.maven.archetypes.tourist;

public class TouristAttraction {
	private String name;
	private String description;
	private int id;
	private String coverImage;
	public TouristAttraction(int id,String name,String description,String coverImage){
		this.id = id;
		this.name = name;
		this.description = description;
		this.coverImage = coverImage;
	}
	public int getId(){
		return this.id;
	}
	public String getName(){
		return this.name;
	}
	public String getDescription(){
		return this.description;
	}
	public String getCoverImage(){
		return this.coverImage;
	}
}
