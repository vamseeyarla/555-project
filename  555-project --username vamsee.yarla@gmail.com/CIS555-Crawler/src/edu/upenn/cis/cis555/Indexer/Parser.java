package edu.upenn.cis.cis555.Indexer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.dappit.Dapper.parser.EnviromentController;
import com.dappit.Dapper.parser.MozillaParser;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * 
 * @author vyarlagadda and leothumma
 */
public class Parser {
	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		try {

			File parserLibraryFile = new File("native/bin/MozillaParser"
					+ EnviromentController.getSharedLibraryExtension());
			String parserLibrary = parserLibraryFile.getAbsolutePath();
			System.out.println("Loading Parser Library :" + parserLibrary);
			// mozilla.dist.bin directory :
			final File mozillaDistBinDirectory = new File("mozilla.dist.bin."
					+ EnviromentController.getOperatingSystemName());

			MozillaParser.init(parserLibrary,
					mozillaDistBinDirectory.getAbsolutePath());

			MozillaParser parser = new MozillaParser();

			URLConnection conn = new URL("http://en.wikipedia.org/wiki/Everglades_National_Park")
					.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));

			String temp, html = "";
			while ((temp = br.readLine()) != null) {
				html = html.concat(temp);
			}

			Document document = parser.parse(html);
			
			//serialize1(document,System.out);
			/*System.out.println("Root:" + document.getNodeName() + " Value="
					+ document.getNodeValue() + " NodeType:"
					+ document.getNodeType());
			System.out.println(document.getFirstChild().getNodeName());
			XPath xpath = XPathFactory.newInstance().newXPath();
			// XPath Query for showing all nodes value
			
			 ArrayList<String> body = new ArrayList<String>();
			 ArrayList<String> titles = new ArrayList<String>();
			 ArrayList<String> h1 = new ArrayList<String>();
			 ArrayList<String> h2 = new ArrayList<String>();
			 ArrayList<String> h3 = new ArrayList<String>();
			 ArrayList<String> h4 = new ArrayList<String>();
			 ArrayList<String> bold = new ArrayList<String>();
			 ArrayList<String> italics = new ArrayList<String>();
			 
			String temp1="";
			System.out.println("----------------title-------------------");
			NodeList nodes = document.getElementsByTagName("title");
			for (int i = 0; i < nodes.getLength(); i++) {
				System.out.println(nodes.item(i).getTextContent());
				titles.add( nodes.item(i).getTextContent());
			}
			
			temp1="";
			System.out.println("----------------h1-------------------");
			nodes = document.getElementsByTagName("h1");
			for (int i = 0; i < nodes.getLength(); i++) {
				System.out.println(nodes.item(i).getTextContent());
				h1.add(nodes.item(i).getTextContent());
			}
			temp1="";
			System.out.println("----------------h2-------------------");
			nodes = document.getElementsByTagName("h2");
			for (int i = 0; i < nodes.getLength(); i++) {
				System.out.println(nodes.item(i).getTextContent());
				h2.add(nodes.item(i).getTextContent());
			}
			temp="";
			System.out.println("----------------h3-------------------");
			nodes = document.getElementsByTagName("h3");
			for (int i = 0; i < nodes.getLength(); i++) {
				System.out.println(nodes.item(i).getTextContent());
				h3.add(nodes.item(i).getTextContent());
				System.out.println("$$$$$$$$"+nodes.item(i).getNodeValue());
			}
			temp1="";
			System.out.println("----------------h4-------------------");
			nodes = document.getElementsByTagName("h4");
			for (int i = 0; i < nodes.getLength(); i++) {
				System.out.println(nodes.item(i).getTextContent());
				h4.add(nodes.item(i).getTextContent());
				
			}
			temp1="";
			System.out.println("----------------bold-------------------");
			nodes = document.getElementsByTagName("b");
			for (int i = 0; i < nodes.getLength(); i++) {
				System.out.println(nodes.item(i).getTextContent());
				bold.add(nodes.item(i).getTextContent());
			
			}
			temp1="";
			System.out.println("----------------italics-------------------");
			nodes = document.getElementsByTagName("i");
			for (int i = 0; i < nodes.getLength(); i++) {
				System.out.println(nodes.item(i).getTextContent());
				italics.add(nodes.item(i).getTextContent());
				
			}
			*/
			String temp1="";
			System.out.println("----------------Body-------------------"); 
			XPath xpath = XPathFactory.newInstance().newXPath();
			XPathExpression expr = xpath.compile("//*[text()]");
			
			 Object result = expr.evaluate(document, XPathConstants.NODESET);

			 NodeList nodes1 = (NodeList) result;
			 for(int j =0; j<nodes1.getLength();j++){
				 //System.out.println("Node Type:"+nodes1.item(j).getNodeType());
				// if (nodes1.item(j).getTextContent().startsWith("["))
					// continue;
				 System.out.println(nodes1.item(j).getTextContent());
				//.add(nodes1.item(j).getTextContent());
				
				
			 }
			 /*
			
				System.out.println("----------------script-------------------");
				nodes = document.getElementsByTagName("script");
				for (int i = 0; i < nodes.getLength(); i++) {
					System.out.println(nodes.item(i).getTextContent());
					//ArrayList<String> script = new ArrayList<String>();
					//script.add(nodes.item(i).getTextContent());
					for( int j=0;j<body.size();j++){
						if (body.get(j).equals(nodes.item(i).getTextContent())){
							body.remove(j);
						}
					}
				}
				
				System.out.println("------------Body without sctipt------------");
				for(String s:body){
					System.out.println(s);
				}
				
				System.out.println("----------------style-------------------");
				nodes = document.getElementsByTagName("style");
				for (int i = 0; i < nodes.getLength(); i++) {
					System.out.println(nodes.item(i).getTextContent());
					//ArrayList<String> script = new ArrayList<String>();
					//script.add(nodes.item(i).getTextContent());
					for( int j=0;j<body.size();j++){
						if (body.get(j).equals(nodes.item(i).getTextContent())){
							body.remove(j);
						}
					}
				}
				
				System.out.println("------------Body without sctipt------------");
				for(String s:body){
					System.out.println(s);
				}
*/
		} catch (Exception e) {
			e.printStackTrace();

		}

	}
	
	 public static void serialize1(Document doc, OutputStream out) throws Exception {

		  TransformerFactory tfactory = TransformerFactory.newInstance();
		  Transformer serializer;
		  try {
		   serializer = tfactory.newTransformer();
		   //Setup indenting to "pretty print"
		   serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		   serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

		   DOMSource xmlSource = new DOMSource(doc);
		   StreamResult outputTarget = new StreamResult(out);
		   serializer.transform(xmlSource, outputTarget);
		  } catch (TransformerException e) {
		   // this is fatal, just dump the stack and throw a runtime exception
		   e.printStackTrace();

		   throw new RuntimeException(e);
		  }
		  
		 }


	public static ArrayList<String> storeinList(ArrayList<String> list,  String input){
		String temp[] = input.split("\\n");
		for(String s : temp){
			System.out.println(s);
			list.add(s);
		}
		return list;
	}
}
