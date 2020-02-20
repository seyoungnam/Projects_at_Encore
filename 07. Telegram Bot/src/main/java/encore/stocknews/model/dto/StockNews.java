package encore.stocknews.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class StockNews {
	@Id
	@GeneratedValue(generator = "stock_news_id_seq")
	private long id;
	@Column(length=100, nullable = false)
	private String url;
	@Column(length=150, nullable = false)
	private String title;
	@Column(name="pub_date", length=20, nullable = false)
	private String pubDate;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "stock_name")
	private Stock stock;
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("제목 : ");
		builder.append(title);
		builder.append("\n일자 : ");
		builder.append(pubDate + "\n");
		builder.append(url);
		return builder.toString();
	}
}
