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
	
	//��� ��ǰ ��ȯ(In:X, Out: productList)
	public ArrayList<ReadyToSell> getAllProducts(){
		return productInfo.getProductList();
	}
	
	//Ÿ������ ��ǰ �˻� (In : Type��, Out: ReadyToSell()�� ��ü)
	public ReadyToSell getProduct1(String ingreType) throws NotExistException{
		for(ReadyToSell v : productInfo.getProductList()) {
			if(v != null && v.getIngredient().getIngreType().equals(ingreType)) {
				return v;
			}
		}
		throw new NotExistException("============= �˻��Ͻ� ��ǰ�� �������� �ʽ��ϴ�.=============");
	}
	
	//id�� ��ǰ �˻� (In : packId, Out: ReadyToSell()�� ��ü)
	public ReadyToSell getProduct2(String packId) throws NotExistException{
		for(ReadyToSell v : productInfo.getProductList()) {
			if(v != null && v.getPackId().equals(packId)) {
				return v;
			}
		}
		throw new NotExistException("============= �˻��Ͻ� ��ǰ�� �������� �ʽ��ϴ�.=============");
	}
	
	// ��ǰ �߰�(��� ����)
	public void insertProduct(ReadyToSell product) {
		productInfo.insertProduct(product);
	}
	
	// ��ǰ ���� ���� - In:��ǰ��, Out: ������ ����
	public void updateProduct(String packId, int deliveryCost) throws NotExistException{
		ReadyToSell product = getProduct2(packId);
		if(product == null) {
			throw new NotExistException("============= ���� ��ǰ�� �������� �ʽ��ϴ�.=============");
		}else {
			product.setDeliveryCost(deliveryCost);
		}
	}
	
	// ��ǰ ����(��ǰ �Ǹ�)
	public void deleteProduct(String packId) throws NotExistException{
		ReadyToSell product = getProduct2(packId);
		if(product == null) {
			throw new NotExistException("============= �̹� �Ǹŵ� ��ǰ �Դϴ�.=============");
		}
		soldProductInfo.insertProduct(product);
		productInfo.getProductList().remove(product);
//		productInfo.remove(product);
	}
	
	//���� ������Ʈ�� JSON ��ȯ
	public JSONArray toJson(String packId) throws NotExistException{
		ReadyToSell product = getProduct2(packId);
		JSONArray ary = JSONArray.fromObject(product);
		if (ary == null) {
			throw new NotExistException("xxxxxxx JSON���� ��ȯ�ϰ��� �ϴ� Project�� �� �����մϴ�. xxxxxxx\n");
		}
		return ary;
	}
	
	//������Ʈ ����Ʈ�� JSON ��ȯ
	public JSONArray toJsonList() {
		ArrayList<ReadyToSell> pjList = getAllProducts();
		JSONArray ary = JSONArray.fromObject( pjList );
		return ary;
	}
	
	//���尡�� ��Ī �޼ҵ�
	public int getMarketPrice(ReadyToSell productList) {

		for(MarketPrice m : marketPrice.getMarketPriceList()) {
				if((m.getProductType()).equals(productList.getIngredient().getIngreType())) {
					return m.getSellPrice();
				}
			}return 0;
		
	}
	
	//��� ����(Outflow)
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
	
	//�Ǹż���(Inflow)
	public int getCashInflow() {
		int totalInflow = 0;
		for(ReadyToSell v : soldProductInfo.getProductList()) {
			int inflow = getMarketPrice(v);
			totalInflow += inflow;
		}
		return totalInflow;
	}
}
