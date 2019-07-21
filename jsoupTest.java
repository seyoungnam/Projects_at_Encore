package step11_jsoupTest;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class jsoupTest {

	public static void main(String[] args) {
		/* Document - html 문서 자체의 최상위 객체로 간주
		 * Elements
		 * Element
		 */
		
		
		Document doc = null;
		try {
			doc = Jsoup.connect("http://www.fashionplus.co.kr/mall/include/comment_list.asp?goods_id_comment=88635313&mall_id=2&redirectPage=/mall/goods/goods.asp?goods_id=88635313").get();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(doc.title()); //<title>JavaScript Tutorial</title>
		for (int i=1; i<11; i++) {
			String address = "#review_list > dd > ul > li:nth-child("+ i +")";
			Elements newsHeadlines = doc.select(address);
			
			for (Element headline : newsHeadlines) {
				int m = 0;
				int n = 0;
				while (m < 5) {
					Element star = headline.select("div.icon_star > p").get(m);
					if(star.hasClass("reviewbg star_on")) {
						n++;
					}
					m++;
				}
				String msg = headline.select("div.contents > p").text();
				System.out.println("별점: " + n + " " + "상품평: "+  msg);
			}
		}
	}
}	
	
		
		
		
		
