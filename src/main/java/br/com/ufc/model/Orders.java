package br.com.ufc.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Orders{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Cascade(CascadeType.ALL)
	private Long orderId;
	
	@ManyToOne()
	private Users user;
	
	private double total;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date orderDate;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "Cart",
			joinColumns = @JoinColumn(
					name = "orderId",
					referencedColumnName = "orderId"),
			inverseJoinColumns = @JoinColumn(
					name = "plateId",
					referencedColumnName = "plateId"))
	private List<Plates> plates;
	
	public Orders(Long orderId, Users user, double total, Date orderDate) {
		this.orderId = orderId;
		this.user = user;
		this.total = total;
		this.orderDate = orderDate;
	}
	
	public Orders() {}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public double getTotal() {
		double sum = 0;
		for(Plates p : this.plates) {
			sum += p.getPrice();
		}
		this.total = sum;
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public List<Plates> getPlates() {
		return plates;
	}

	public void setPlates(List<Plates> plates) {
		this.plates = plates;
	}
	
}
