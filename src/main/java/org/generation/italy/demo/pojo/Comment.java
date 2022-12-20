package org.generation.italy.demo.pojo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@NotEmpty
	@Lob
	private String comment;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = true)
	private Photo photo;
	
	public Comment() { }
	
	public Comment(String comment) { 
		setComment(comment);
	}
	
	public Comment(String comment, Photo p) { 
		setComment(comment);
		setPhoto(p);;
	}

	public Integer getId() {
		return id;
	}

	public String getComment() {
		return comment;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	
	@Override
	public String toString() {
		return getId() 
				+ "\n" + getComment()
				+ "\n" + getPhoto();
	}
}
