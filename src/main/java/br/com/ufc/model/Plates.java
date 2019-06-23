package br.com.ufc.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Plates{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Cascade({CascadeType.DELETE, CascadeType.SAVE_UPDATE})
	private Long plateId;
	private String plateName;
	private String plateImage;
	private double price;
	private String category;
	
	@ManyToMany(mappedBy = "plates")
	private List<Orders> orders;
	
	public Plates(Long plateId, String plateName, String plateImage, double price, String category) {
		super();
		this.plateId = plateId;
		this.plateName = plateName;
		this.plateImage = plateImage;
		this.price = price;
		this.category = category;
	}
	
	public Plates() {}

	public Long getPlateId() {
		return plateId;
	}

	public void setPlateId(Long plateId) {
		this.plateId = plateId;
	}

	public String getPlateName() {
		return plateName;
	}

	public void setPlateName(String plateName) {
		this.plateName = plateName;
	}

	public String getPlateImage() {
		return plateImage;
	}

	public void setPlateImage(String plateImage) {
		this.plateImage = plateImage;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Orders> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	
}
