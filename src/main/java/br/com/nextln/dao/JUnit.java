package br.com.nextln.dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class JUnit {

	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		
		WebClient webClient = new WebClient(BrowserVersion.CHROME_16);
        
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		
		HtmlPage page = webClient.getPage("http://dv.parliament.bg/DVWeb/broeveList.faces");
		HtmlAnchor link = (HtmlAnchor) page.getElementById("broi_form:dataTable1:0:_idJsp109");		
		Page click = link.click();
		
		InputStream is = click.getWebResponse().getContentAsStream();
        FileOutputStream output = new FileOutputStream("C:/Download/"+new Date().getTime()+".pdf");
        
        IOUtils.copy(is, output);
        output.close();

        System.out.println("New file created!");     

//	    for (HtmlAnchor link : (List<HtmlAnchor>) page.getByXPath("//table[@id='broi_form:dataTable1']//a/img/.."))
//	    {
//	        String commandString = link.getOnClickAttribute().replaceAll("return ", "");
//	        System.out.println(commandString);
//
//	        ScriptResult executeJavaScript = page.executeJavaScript(commandString);
//
//	        Page newPage = executeJavaScript.getNewPage();
//	        System.out.println(newPage.getWebResponse().getContentAsStream());
//	        
//	        InputStream is = newPage.getWebResponse().getContentAsStream();
//            FileOutputStream output = new FileOutputStream("C:/Download/"+new Date().getTime()+".pdf");
//	        
//            IOUtils.copy(is, output);
//            output.close();
//
//            System.out.println("New file created!");     
//	        page = webClient.getPage("http://dv.parliament.bg/DVWeb/broeveList.faces");
//	    }

	}
         
//        HtmlPage page = webClient.getPage(url);
//        HtmlAnchor anchor =  (HtmlAnchor) page.getByXPath("//table/tbody/tr[3]/td[2]/div/a").get(0);
//        anchor.click();
//        page.getEnclosingWindow().getEnclosedPage();
//        WebResponse response = page.getWebResponse();
//        String content = response.getContentAsString();
//        String pageContent =page.asText();
//         System.out.println(pageContent);
//        System.out.println(content);
	

}
