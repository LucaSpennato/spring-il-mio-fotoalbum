package org.generation.italy.demo.pojo;

import java.util.HashSet;
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
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true, length = 20)
	@NotBlank(message = "E' richiesto un nome da assegnare al tag.")
	@Size(min = 3, max = 20, message = "Il nome del tag può essere tra i 3 ed i 20 caratteri.")
	private String name;
	
	@ManyToMany(mappedBy = "tags")
	private Set<Photo> photos;
	
	public Tag() { }
	
	public Tag(String name, Set<Photo> photos) {
		setName(name);
		setPhotos(photos);
	}
	
	public Tag(String name, Photo photo) {
		setName(name);
		addPhoto(photo);
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Set<Photo> getPhotos() {
		return photos;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhotos(Set<Photo> photos) {
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
		if(!(obj instanceof Tag)) return false;
		return obj.hashCode() == hashCode();
	}
	
	@Override
	public String toString() {
		return getId() + " - " + getName() + " - " + getPhotos();
	}
}