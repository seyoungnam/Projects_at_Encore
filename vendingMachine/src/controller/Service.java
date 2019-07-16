package controller;

import java.util.ArrayList;

import exception.NotExistException;
import model.DB.DBMarketPrice;
import model.DB.DBafterSell;
import model.DB.DBbeforeSell;
import model.dto.MarketPrice;
import model.dto.ReadyToSell;
import net.sf.json.JSONArray;


public class Service {
	
	
	private static Service instance = new Service();
	private DBbeforeSell productInfo = DBbeforeSell.getInstance();
	private DBafterSell soldProductInfo = DBafterSell.getInstance();
	private DBMarketPrice marketPrice = DBMarketPrice.getInstance();
	
	private Service() {}
	public static Service getInstance() {
		return instance;
	}
	
	//모든 제품 반환(In:X, Out: productList)
	public ArrayList<ReadyToSell> getAllProducts(){
		return productInfo.getProductList();
	}
	
	//타입으로 제품 검색 (In : Type명, Out: ReadyToSell()의 객체)
	public ReadyToSell getProduct1(String ingreType) throws NotExistException{
		for(ReadyToSell v : productInfo.getProductList()) {
			if(v != null && v.getIngredient().getIngreType().equals(ingreType)) {
				return v;
			}
		}
		throw new NotExistException("============= 검색하신 제품이 존재하지 않습니다.=============");
	}
	
	//id로 제품 검색 (In : packId, Out: ReadyToSell()의 객체)
	public ReadyToSell getProduct2(String packId) throws NotExistException{
		for(ReadyToSell v : productInfo.getProductList()) {
			if(v != null && v.getPackId().equals(packId)) {
				return v;
			}
		}
		throw new NotExistException("============= 검색하신 제품이 존재하지 않습니다.=============");
	}
	
	// 제품 추가(재고 증가)
	public void insertProduct(ReadyToSell product) {
		productInfo.insertProduct(product);
	}
	
	// 제품 원가 수정 - In:제품명, Out: 수정된 가격
	public void updateProduct(String packId, int deliveryCost) throws NotExistException{
		ReadyToSell product = getProduct2(packId);
		if(product == null) {
			throw new NotExistException("============= 관련 제품이 존재하지 않습니다.=============");
		}else {
			product.setDeliveryCost(deliveryCost);
		}
	}
	
	// 제품 삭제(제품 판매)
	public void deleteProduct(String packId) throws NotExistException{
		ReadyToSell product = getProduct2(packId);
		if(product == null) {
			throw new NotExistException("============= 이미 판매된 제품 입니다.=============");
		}
		soldProductInfo.insertProduct(product);
		productInfo.getProductList().remove(product);
//		productInfo.remove(product);
	}
	
	//개별 프로젝트의 JSON 전환
	public JSONArray toJson(String packId) throws NotExistException{
		ReadyToSell product = getProduct2(packId);
		JSONArray ary = JSONArray.fromObject(product);
		if (ary == null) {
			throw new NotExistException("xxxxxxx JSON으로 변환하고자 하는 Project가 미 존재합니다. xxxxxxx\n");
		}
		return ary;
	}
	
	//프로젝트 리스트의 JSON 전환
	public JSONArray toJsonList() {
		ArrayList<ReadyToSell> pjList = getAllProducts();
		JSONArray ary = JSONArray.fromObject( pjList );
		return ary;
	}
	
	//시장가격 매칭 메소드
	public int getMarketPrice(ReadyToSell productList) {

		for(MarketPrice m : marketPrice.getMarketPriceList()) {
				if((m.getProductType()).equals(productList.getIngredient().getIngreType())) {
					return m.getSellPrice();
				}
			}return 0;
		
	}
	
	//재고 원가(Outflow)
	public int getCashOutflow() {
		int totalOutflow = 0;
		for(ReadyToSell v : productInfo.getProductList()) {
			int outflow = v.getIngredient().getIngreCost()
						+ v.getCover().getCoverCost()
						+ v.getPackCost()
						+ v.getDeliveryCost();
			totalOutflow += outflow;
		}
		return totalOutflow;
			
	}
	
	//판매수익(Inflow)
	public int getCashInflow() {
		int totalInflow = 0;
		for(ReadyToSell v : soldProductInfo.getProductList()) {
			int inflow = getMarketPrice(v);
			totalInflow += inflow;
		}
		return totalInflow;
	}
}
