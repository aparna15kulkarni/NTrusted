package ntrusted.models;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

@Entity
@Table(name="advertisement")
public class Advertisement {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int adId;
	
	@NotNull
	@Size (min=3, max=80)
	@Column(name="productName")
	private String productName;
	
	@NotNull
	@Column(name="productDescription")
	private String productDescription;
	
	@NotNull
	@Column(name="productPrice")
	private float productPrice;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="fbId")
	private User user;
	
	@NotNull
	@Column(name="postDate")
	private Date postDate;
	
	@NotNull 
	@Column(name="active")
	private Boolean active;
	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="categoryId")
	private Category category;
	

	public Advertisement()
	{
		
	}
	
	public Advertisement(int id)
	{
		this.adId = id;
	}

	public Advertisement(String productName, String productDescription, float productPrice, Date postDate,
			Boolean active, Category category, User user) {
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.postDate = postDate;
		this.active = active;
		this.category = category;
		this.user = user;
	}

	public int getAdId() {
		return adId;
	}

	public void setAdId(int adId) {
		this.adId = adId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}