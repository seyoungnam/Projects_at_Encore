package encore.stocknews.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
public class NewsUser {
	@Id @Column(length=20)
	private String id;
	@Column(length=20, nullable = false)
	private String pw;
	@Column(length=20, nullable = false)
	private String name;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "stock_id")
	private Stock stock;
}
