package mbaevaluation.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import mbaevaluation.model.dto.IpedsDTO;
import mbaevaluation.model.dto.UsnewsDTO;

   

public class MbaEvaluatiobVirtualDB {
	
	public static void main(String[] args) throws SQLException {
	/* Document - html 문서 자체의 최상위 객체로 간주
	 * Elements
	 * Element
	 */
		
	IpedsDAO ipedsdao = new IpedsDAO();
	ArrayList array1 = new ArrayList<IpedsDTO>();
		
	UsnewsDAO usnewsdao = new UsnewsDAO();
	ArrayList array2 = new ArrayList<UsnewsDTO>();
	
	// -------------IPEDS ------------------------------
		
	int[] ipedsID = {243744, 144050, 166027, 215062, 190150, 147767, 182670, 166683, 110635, 198419,
					 170976, 130794, 190415, 234076, 110662, 193900, 211440, 199120, 139658, 228778,
					 151351, 230038, 131496, 204468, 236948};
	
	for(int v : ipedsID) {
		String http = "https://nces.ed.gov/collegenavigator/?id=" + v + "#outcome";
		Document doc = null;
		try {
			doc = Jsoup.connect(http).userAgent("Mozilla").get();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		//학교별 IPEDS ID, school name, school address 추출
		String rootAdrs = "#RightContent > div.dashboard > div";
		Element rootEle = doc.select(rootAdrs).get(0);
		String school_id = rootEle.selectFirst("div.mapngo > span").text().substring(10, 17);
		String school_name = rootEle.selectFirst("div:nth-child(2) > span > span").text();
		String[] address = rootEle.selectFirst("div:nth-child(2) > span").text().split("\\s");
		String state = address[address.length-2];
		System.out.println("[IPEDS ID: " + school_id + " |School: " + school_name + " |state: " + state + "]");
		
		
		// # of faculty 추출
		String facultyRoot = "#ctl00_cphCollegeNavBody_ucInstitutionMain_divFaculty > div > table > tbody > tr";
		Element facultySelector = doc.select(facultyRoot).get(1);
		int num_faculty = 0;
		for(int i=2; i<4; i++) {
			String facultyNum = facultySelector.select("td:nth-child(" + i + ")").text();
			if(facultyNum == "-") {
				facultyNum = "0";
			}else {
				facultyNum = facultyNum.replaceAll(",", "").replaceAll("[^0-9]", "");
			}
			int facultyNumInt = Integer.parseInt(facultyNum);
			num_faculty += facultyNumInt;		
		}
		System.out.println("[# of faculty: "+ num_faculty +" ]");
		
		// Major: computer and information science 추출
		//1단계 : Education의 위치 구하기
		int i = 1;
		int j = 0;
		while (true) {
			String eduRoot = "#divctl00_cphCollegeNavBody_ucInstitutionMain_ctl07 > div > table > tbody > tr:nth-child("+i+")";				
			Element eduSelector = doc.select(eduRoot).get(0);
			String education = null;
			try {
				education = eduSelector.text();
			}catch(IndexOutOfBoundsException e) {
				education = eduSelector.select("td:nth-child(1)").text();
			}
			if(education.equals("Education") || education.equals("Engineering")) {
				j = i-1;
				break;
			}else {
				i++;	
			}
		}
		
		//2단계 : Education 위에 있는 computer 관련 degree 수 합계 구하기
		String comRoot = "#divctl00_cphCollegeNavBody_ucInstitutionMain_ctl07 > div > table > tbody > tr:nth-child("+j+")";
		Element comSelector = doc.select(comRoot).get(0);
		int comDegreeTotal = 0;
		for(int k=2; k<5; k++) {
			String comDegreeNum = comSelector.selectFirst("td:nth-child(" + k + ")").text();
			if(comDegreeNum.equals("-")) {
				comDegreeNum = "0";
			}else {
				comDegreeNum = comDegreeNum.replaceAll(",", "").replaceAll("[^0-9]", "");
			}
			int comDegreeNumInt = Integer.parseInt(comDegreeNum);
			comDegreeTotal += comDegreeNumInt;
		}
		
		//Grand Total 추출
		int grandDegreeTotal = 0;
		while(true) {
			String grandRoot = "#divctl00_cphCollegeNavBody_ucInstitutionMain_ctl07 > div > table > tbody > tr:nth-child(" + i + ")";
			Element grandSelector = doc.select(grandRoot).get(0);
			String grandTotal = grandSelector.select("td:nth-child(1)").text();
			if(grandTotal.equals("Grand total")) {							
				for(int l=2; l<5; l++) {
					String grandDegreeNum = grandSelector.select("td:nth-child("+l+")").text();
					if(grandDegreeNum.equals("-")) {
						grandDegreeNum = "0";
					}else {
						grandDegreeNum = grandDegreeNum.replaceAll(",", "").replaceAll("[^0-9]", "");
					}
					int grandDegreeNumInt = Integer.parseInt(grandDegreeNum);
					grandDegreeTotal += grandDegreeNumInt;
				}
				break;
			}
			i++;
		}	
		//컴퓨터 학위 관련 결과 도출
		float it_concentration= 0;
		try {
			it_concentration = 100*comDegreeTotal/grandDegreeTotal;
		}catch(ArithmeticException a) {
			it_concentration = 0;
		}
		
		System.out.println("[# of Computer-related Degree: "+ comDegreeTotal + " |# of Grand Total: " + grandDegreeTotal + " | Computer Related Degree Ratio: " + it_concentration + " ]");
			
		
		//room and board 추출
		int a = 1;
		String rentRoot = "#divctl00_cphCollegeNavBody_ucInstitutionMain_ctl00 > div > table:nth-child(3) > tbody";
		Element rentSelector = doc.select(rentRoot).get(0);
		String rentFee = null;
		while(true) {
			String rentTitle = rentSelector.select("tr:nth-child("+a+") > td:nth-child(1)").text();
			if(rentTitle.equals("Room and board")) {
				rentFee = rentSelector.select("tr:nth-child("+a+") > td:nth-child(5)").text();
				break;
			}else {
				a++;
			}
		}
		rentFee = rentFee.replaceAll(",", "").replaceAll("[^0-9]", "");
		int rent = Integer.parseInt(rentFee);
		System.out.println("[room and board: " + rent + "]");

		array1.add(new IpedsDTO(school_id, school_name, state, num_faculty, it_concentration));
	}
	ipedsdao.insertIpeds(array1);
		
		//-----------------	USNews-----------------------------
		
		Document doc = null;
	      try {
	         doc = Jsoup.connect("https://www.usnews.com/best-graduate-schools/top-business-schools/mba-rankings").userAgent("Mozilla").get();
	      } catch(IOException e) {
	         e.printStackTrace();
	      }
	      System.out.println(doc.title()); //<title>JavaScript Tutorial</title>
	      
	      int i=0;
			while(i<100) {
				i++;
				try {
					//rank의 selector 값은 매일 달라지는 것으로 보임
		            String rootRank = "#app > div > div:nth-child(1) > div.SearchRankings__Background-s1wg9kv4-0.dOfFUK > div > div.Grid-s1x0i6w9-0.diELCQ > div.Cell-s1jgw6rh-0.cegKak > div > div > ol > li:nth-child("+i+") > div > div.s85n6m5-0-Box-cwadsP.dlYdgX > ul.Hide-s1x4faml-0.iGesMc.DetailCardCompare__CardRankList-m2adoa-0.brnDIf.RankList__List-f42a47-0.ZlCot > li > a > div > strong";
		            Element eleRank = doc.select(rootRank).get(0);
		            String rank = eleRank.text();
		            rank = rank.replaceAll("[^0-9]", "");
		            int rankInt = Integer.parseInt(rank);
		            
		            String rootSchname = "#app > div > div:nth-child(1) > div.SearchRankings__Background-s1wg9kv4-0.dOfFUK > div > div.Grid-s1x0i6w9-0.diELCQ > div.Cell-s1jgw6rh-0.cegKak > div > div > ol > li:nth-child("+i+") > div > div.s85n6m5-0-Box-cwadsP.dlYdgX > h3 > a";
		            Element eleSch = doc.select(rootSchname).get(0);
		            String schname = eleSch.text();
		            
		            String rootTuition = "#app > div > div:nth-child(1) > div.SearchRankings__Background-s1wg9kv4-0.dOfFUK > div > div.Grid-s1x0i6w9-0.diELCQ > div.Cell-s1jgw6rh-0.cegKak > div > div > ol > li:nth-child("+i+") > div > div.Hide-s1x4faml-0.iGesMc.s85n6m5-0-Box-cwadsP.gdZCAX > div:nth-child(1) > p";
		            Element eleTui = doc.select(rootTuition).get(0);
		            String tuition = eleTui.text();
		            tuition = tuition.replaceAll(",", "").replaceAll("[^0-9]", "");
		            int tuitionInt = Integer.parseInt(tuition);
		            
		            String rootStunum = "#app > div > div:nth-child(1) > div.SearchRankings__Background-s1wg9kv4-0.dOfFUK > div > div.Grid-s1x0i6w9-0.diELCQ > div.Cell-s1jgw6rh-0.cegKak > div > div > ol > li:nth-child("+i+") > div > div.Hide-s1x4faml-0.iGesMc.s85n6m5-0-Box-cwadsP.gdZCAX > div:nth-child(2) > p";
		            Element eleStu = doc.select(rootStunum).get(0);
		            String stunum = eleStu.text();
		            stunum = stunum.replaceAll(",", "").replaceAll("[^0-9]", "");
		            int stunumInt = Integer.parseInt(stunum);
		           
		            System.out.println("[rank: " + rankInt + " |school: " + schname + " |tuition: " + tuitionInt + " |enrollment #: " + stunumInt + "]");
		            
		            array2.add(new UsnewsDTO(schname, rankInt, tuitionInt, stunumInt));
		            
		         }catch(IndexOutOfBoundsException e) {
		            continue;
		         }		
			}
			usnewsdao.insertUsnews(array2);
			
	}
	
}


	
		
		
		
		
	 // ----------------------- salary 관련 -----------------------------------
		
//    	  JSONObject jsonObject = new JSONObject();
//	      JSONArray salaryArray = new JSONArray();
//	      
//	      JSONObject salaryInfo = new JSONObject();
//	      salaryInfo.put("id", "243744");
//	      salaryInfo.put("schName", "Stanford University");
//	      salaryInfo.put("salary", "145559");
//	      salaryArray.add(salaryInfo);
//	      
//	      salaryInfo = new JSONObject();
//	      salaryInfo.put("id", "144050");
//	      salaryInfo.put("schName", "University of Chicago (Booth)");
//	      salaryInfo.put("salary", "131893");
//	      salaryArray.add(salaryInfo);
//	      
//	      salaryInfo = new JSONObject();
//	      salaryInfo.put("id", "166027");
//	      salaryInfo.put("schName", "Harvard University");
//	      salaryInfo.put("salary", "139339");
//	      salaryArray.add(salaryInfo);
//
//	      salaryInfo = new JSONObject();
//	      salaryInfo.put("id", "215062");
//	      salaryInfo.put("schName", "University of Pennsylvania (Wharton)");
//	      salaryInfo.put("salary", "139670");
//	      salaryArray.add(salaryInfo);
//	      
//	      salaryInfo = new JSONObject();
//	      salaryInfo.put("id", "190150");
//	      salaryInfo.put("schName", "Columbia University");
//	      salaryInfo.put("salary", "130924");
//	      salaryArray.add(salaryInfo);
//	      
//	      salaryInfo = new JSONObject();
//	      salaryInfo.put("id", "147767");
//	      salaryInfo.put("schName", "Northwestern University (Kellogg)");
//	      salaryInfo.put("salary", "128415");
//	      salaryArray.add(salaryInfo);
//	      
//	      salaryInfo = new JSONObject();
//	      salaryInfo.put("id", "182670");
//	      salaryInfo.put("schName", "Dartmouth College (Tuck)");
//	      salaryInfo.put("salary", "130022");
//	      salaryArray.add(salaryInfo);
//	      
//	      salaryInfo = new JSONObject();
//	      salaryInfo.put("id", "166683");
//	      salaryInfo.put("schName", "Massachusetts Institute of Technology (Sloan)");
//	      salaryInfo.put("salary", "135105");
//	      salaryArray.add(salaryInfo);
//	      
//	      salaryInfo = new JSONObject();
//	      salaryInfo.put("id", "110635");
//	      salaryInfo.put("schName", "University of California--Berkeley (Haas)");
//	      salaryInfo.put("salary", "127571");
//	      salaryArray.add(salaryInfo);
//	      
//	      salaryInfo = new JSONObject();
//	      salaryInfo.put("id", "198419");
//	      salaryInfo.put("schName", "Duke University (Fuqua)");
//	      salaryInfo.put("salary", "127874");
//	      salaryArray.add(salaryInfo);
//	      
//	      salaryInfo = new JSONObject();
//	      salaryInfo.put("id", "170976");
//	      salaryInfo.put("schName", "University of Michigan--Ann Arbor (Ross)");
//	      salaryInfo.put("salary", "126500");
//	      salaryArray.add(salaryInfo);
//	      
//	      salaryInfo = new JSONObject();
//	      salaryInfo.put("id", "130794");
//	      salaryInfo.put("schName", "Yale University");
//	      salaryInfo.put("salary", "126390");
//	      salaryArray.add(salaryInfo);
//	      
//	      salaryInfo = new JSONObject();
//	      salaryInfo.put("id", "190415");
//	      salaryInfo.put("schName", "Cornell University (Johnson)");
//	      salaryInfo.put("salary", "126353");
//	      salaryArray.add(salaryInfo);
//	      
//	      salaryInfo = new JSONObject();
//	      salaryInfo.put("id", "234076");
//	      salaryInfo.put("schName", "University of Virginia (Darden)");
//	      salaryInfo.put("salary", "127767");
//	      salaryArray.add(salaryInfo);
//	      
//	      salaryInfo = new JSONObject();
//	      salaryInfo.put("id", "110662");
//	      salaryInfo.put("schName", "University of California--Los Angeles (Anderson)");
//	      salaryInfo.put("salary", "121843");
//	      salaryArray.add(salaryInfo);
//	      
//	      salaryInfo = new JSONObject();
//	      salaryInfo.put("id", "193900");
//	      salaryInfo.put("schName", "New York University (Stern)");
//	      salaryInfo.put("salary", "129059");
//	      salaryArray.add(salaryInfo);
//	      
//	      salaryInfo = new JSONObject();
//	      salaryInfo.put("id", "211440");
//	      salaryInfo.put("schName", "Carnegie Mellon University (Tepper)");
//	      salaryInfo.put("salary", "120382");
//	      salaryArray.add(salaryInfo);
//	      
//	      salaryInfo = new JSONObject();
//	      salaryInfo.put("id", "199120");
//	      salaryInfo.put("schName", "University of North Carolina--Chapel Hill (Kenan-Flagler)");
//	      salaryInfo.put("salary", "116543");
//	      salaryArray.add(salaryInfo);
//	      
//	      salaryInfo = new JSONObject();
//	      salaryInfo.put("id", "139658");
//	      salaryInfo.put("schName", "Emory University");
//	      salaryInfo.put("salary", "120861");
//	      salaryArray.add(salaryInfo);
//	      
//	      salaryInfo = new JSONObject();
//	      salaryInfo.put("id", "139658");
//	      salaryInfo.put("schName", "Emory University");
//	      salaryInfo.put("salary", "120861");
//	      salaryArray.add(salaryInfo);
//	      
//	      salaryInfo = new JSONObject();
//	      salaryInfo.put("id", "228778");
//	      salaryInfo.put("schName", "University of Texas--Austin (McCombs)");
//	      salaryInfo.put("salary", "119036");
//	      salaryArray.add(salaryInfo);
//	      
//	      salaryInfo = new JSONObject();
//	      salaryInfo.put("id", "151351");
//	      salaryInfo.put("schName", "Indiana University Bloomington");
//	      salaryInfo.put("salary", "108164");
//	      salaryArray.add(salaryInfo);
//	      
//	      salaryInfo = new JSONObject();
//	      salaryInfo.put("id", "230038");
//	      salaryInfo.put("schName", "Brigham Young University");
//	      salaryInfo.put("salary", "110971");
//	      salaryArray.add(salaryInfo);
//	      
//	      salaryInfo = new JSONObject();
//	      salaryInfo.put("id", "131496");
//	      salaryInfo.put("schName", "Georgetown University");
//	      salaryInfo.put("salary", "116946");
//	      salaryArray.add(salaryInfo);
//	      
//	      salaryInfo = new JSONObject();
//	      salaryInfo.put("id", "204468");
//	      salaryInfo.put("schName", "Notre Dame College");
//	      salaryInfo.put("salary", "111178");
//	      salaryArray.add(salaryInfo);
//	      
//	      salaryInfo = new JSONObject();
//	      salaryInfo.put("id", "236948");
//	      salaryInfo.put("schName", "University of Washington");
//	      salaryInfo.put("salary", "118355");
//	      salaryArray.add(salaryInfo);
//	      
//	      salaryInfo = new JSONObject();
//	      salaryInfo.put("id", "123961");
//	      salaryInfo.put("schName", "University of Southern California (Marshall)");
//	      salaryInfo.put("salary", "122634");
//	      salaryArray.add(salaryInfo);
//	      
//	      
//	      jsonObject.put("salInfo", salaryArray);
//	      
//	      String jsonInfo = jsonObject.toString();
//	      
//	      System.out.print(jsonInfo);
//	      
//	      
//	   // ---------------------GDP 관련--------------------------------------
//	      String userId = "F15E7D93-529A-43F5-8D6C-69B8C9A73281";
//	      String tableId = "21";
//	      String method = "GetData";
//	      String dataSetName = "Regional";
//	      String tableName = "SAINC1";
//	      String lineCode = "60";
//	      String geoFIPS = "STATE";
//	      String year = "2018";
//	      String frequency = "A";
//	      String resultFormat = "JSON";
//	      
//	      
//	      String apiURL = "https://apps.bea.gov/api/data/?&UserID="+userId+"&TableId="+tableId+"&Method="+method+"&DatasetName="+dataSetName+"&TableName="+tableName+"&LineCode="+lineCode+"&GeoFIPS="+geoFIPS+"&Year="+year+"&Frequency="+frequency+"&ResultFormat="+resultFormat;
//	      
//	         
//	      try {
//	           URL url = new URL(apiURL);
//	           HttpURLConnection con = (HttpURLConnection)url.openConnection();
//	          
//	           int responseCode = con.getResponseCode();
//	           
//	           Object obj;
//	           
//	           JSONParser parser = new JSONParser();
//	           
//	           if (responseCode == 200) {
//	            System.out.println("성공코드 " + responseCode + " 발생");
//	            obj = parser.parse(new InputStreamReader(con.getInputStream(), "utf-8"));
//	         } else { // 에러 발생시 예외 처리
//	            System.out.println("Error" + responseCode + " 발생");
//	            obj = parser.parse(new InputStreamReader(con.getErrorStream(), "utf-8"));
//	         }
//	           
//	           JSONObject jsonObject1 = (JSONObject) obj;
//	           System.out.println(jsonObject1.toString());  //전체 출력
//	           JSONObject jsonObject2 = (JSONObject) jsonObject1.get("BEAAPI");
//	           JSONObject jsonObject3 = (JSONObject) jsonObject2.get("Results");
//	           JSONArray jsonitems_array = (JSONArray) jsonObject3.get("Data");
//	           
//	           for (int a = 0; a < jsonitems_array.size(); a++) {   
//	              JSONObject jsonobjectitems = ((JSONObject)jsonitems_array.get(a));
//	              String state = (String) jsonobjectitems.get("GeoName");
//	              
//	              String gdp = (String) jsonobjectitems.get("DataValue");
//	              gdp = gdp.replaceAll(",", "");
//	              float stateGdp = Float.parseFloat(gdp);
//	     
//	            
//	              System.out.println("[State: " + state + "|GDP in Professional, scientific, and technical services: " + stateGdp + "]");
//	           }
//	           
//	      }catch(Exception e){
//	      }
	      
	      
	      


//-------------------------------------------------------------
		

//	     array2.add(new RankingDTO(school_id, rankInt));
//		
//		try {
//			rankingdao.insertRanking(array2);
//			System.out.println(rankingdao.getRanking("243744"));
//		}catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
		
	      
		
		
		
		
		
		
// education table 만들때 사용한 것 - service로 옮겨갈것	      
	
//array1.add(new EducationDTO(school_id, school_name, num_students, num_faculty, it_concentration));
		
		
//	 	
//		try {
//		    educationdao.insertEducation(array1);
//			System.out.println(educationdao.getEducation("243744"));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	 
 
 
