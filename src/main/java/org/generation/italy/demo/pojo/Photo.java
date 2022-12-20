package org.generation.italy.demo.pojo;

import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Value;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true, length = 20)
	@NotEmpty(message = "Il titolo non può essere vuoto.")
	@Size(min = 5, max = 20, message = "Il titolo deve essere tra 5 e 20 caratteri.")
	private String title;
	
	@URL
	@Column
	@NotEmpty(message = "Almeno una foto è richiesta.")
	@Lob
	private String url;
	
	@Column
	@Value("true")
	private boolean isVisible;
	
	@ManyToMany
	private Set<Tag> tags;
	
	@ManyToMany
	private Set<Category> categories;
	
	@OneToMany(mappedBy = "photo", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@Nullable
	private List<Comment> comments;
	
	public Photo() { }
	
	public Photo(String title, String url ,boolean isVisible ,Set<Tag> tags, Set<Category> categories) {
		
		setTitle(title);
		setUrl(url);
		setVisible(isVisible);
		setTags(tags);
		setCategories(categories);
		
	}
	
	public Photo(String title, String url) {
		
		setTitle(title);
		setUrl(url);
		
	}
	
	public Photo(String title, String url ,boolean isVisible ,Set<Tag> tags, 
			Set<Category> categories, List<Comment> comments) {
		
		setTitle(title);
		setUrl(url);
		setVisible(isVisible);
		setTags(tags);
		setCategories(categories);
		setComments(comments);
		
	}

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	@Override
	public int hashCode() {
		return getId();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Photo)) return false;
		return obj.hashCode() == hashCode();
	}

	@Override
	public String toString() {
		return getId() + " - " + getTitle() 
			+ "\n" + getTags()
			+ "\n" + getCategories()
			+ "\n" + getComments();
	}
}
