package encore.stocknews.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
public class Stock {
	@Id
	@Column(length = 30, nullable = false)
	private String name;
	@Column(precision = 7, nullable = false)
	private BigDecimal price;
	@Column(length = 20)
	private String netUpDown;
	@Column(precision = 6)
	private BigDecimal net;
	@Column(length = 20)
	private String upDown;
	@Column(precision = 8, nullable = false)
	private BigDecimal marketCap;
	@OneToMany(mappedBy = "stock")
	List<NewsUser> newsUsers = new ArrayList<NewsUser>();

	@Override
	public String toString() {
		return "종목명 : " + name + ", 현재가 : " + price + ", 등락 : " + netUpDown + ", 전일비 : " + net
				+ ", 등락률 : " + upDown + ", 시가총액 : " + marketCap + "]";
	}
}
