package org.generation.italy.demo.pojo;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	@NotBlank(message = "Un nome categoria è richiesto")
	@Size(min = 3, max = 20, message = "Il nome della categoria può essere tra i 3 ed i 20 caratteri.")
	private String name;
	
	@ManyToMany(mappedBy = "categories")
	private List<Photo> photos;
	
	public Category() { }
	
	public Category(String name) {
		setName(name);
	}
	
	public Category(String name, List<Photo> photos) {
		setName(name);
		setPhotos(photos);
	}
	public Category(String name, Photo photo) {
		setName(name);
		addPhoto(photo);
	}
	

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	
	public void addPhoto(Photo p) {
		this.photos.add(p);
	}
	
	@Override
	public int hashCode() {
		return getId();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Category)) return false;
		return obj.hashCode() == hashCode();
	}
	
	@Override
	public String toString() {
		return getId()+ " - " + getName();
	}
}
