package crawler.example;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.github.abola.crawler.CrawlerPack;


/**
 * 資料探索練習 Facebook Graph Api Search 
 * 
 * 重點
 * 1. 利用Graph Api調整出需要的資料
 * 2. 取得一組Access Token (試著使用 long term token)
 * 3. 試著用『excel』或任何最簡易的方式，對資料進行探索
 * 
 * @author Abola Lee
 *
 */
public class FacebookExample {
	
	public static void main(String[] args) {
		
		// 遠端資料路徑
		// [query sample]
		// search?fields=name,id,likes,talking_about_count&limit=1000&q=靠北&type=page
		String uri =
				"https://graph.facebook.com/v2.10"
						+ "/search?q=%e5%91%8a%e7%99%bd&type=page&limit=1000&fields=name,id,talking_about_count,fan_count"
						+ "&access_token=EAACEdEose0cBANkmucxqXk692iFMulOYhZCpnXNFPux4V4CPQYM98Fv01gcAZCrMeCWX4onzVX8LsGUEJNEdDDwqJS4VzluzvhY7HGEQQBn5w94j7ZCJcKR03lyA79WuHcMP8le7XSIIDAL4oqjztZARTBQx1dfZCb6RMbCRW85rNdjcTBJFWeOEFdrxoz8IhLTeJOII1HwZDZD";
    //search 告白


		// Jsoup select 後回傳的是  Elements 物件
//		[data sample]
//		----
//		{
//			"data": [
//			{
//				"name": "靠北工程師",
//					"id": "1632027893700148",
//					"likes": 174587,
//					"talking_about_count": 188119
//			}
//		}
		Elements elems =
				CrawlerPack.start()
				.getFromJson(uri)
				.select("data");
		
		String output = "id,名稱,按讚數,討論人數\n";
		
		// 遂筆處理
		for( Element data: elems ){
			String id = data.select("id").text();
			String name = data.select("name").text();
			String likes = data.select("fan_count").text();
			String talking_about_count = data.select("talking_about_count").text();
			
			output += id+",\""+name+"\","+likes+","+talking_about_count+"\n";
		}
		
		System.out.println( output );
	} 
}
