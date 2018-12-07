package br.com.nextln.dao;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class JUnit {

	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		
		WebClient webClient = new WebClient(BrowserVersion.CHROME_16);
        
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		String url = "https://facebook.com/";
         
        HtmlPage page = webClient.getPage(url);
        HtmlAnchor anchor =  (HtmlAnchor) page.getByXPath("//table/tbody/tr[3]/td[2]/div/a").get(0);
        anchor.click();
        page.getEnclosingWindow().getEnclosedPage();
        WebResponse response = page.getWebResponse();
        String content = response.getContentAsString();
        String pageContent =page.asText();
         System.out.println(pageContent);
        System.out.println(content);
	}

}
