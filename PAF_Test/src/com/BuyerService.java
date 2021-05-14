package com;

import com.Buyer;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


@Path("/Buyers")


public class BuyerService {
	
	Buyer Obj = new Buyer();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	
	public String readBuyers()
	 {
		
	 return Obj.readBuyers();
	 }
	
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertBuyers(@FormParam("fname") String fname,
	 @FormParam("lname") String lname,
	 @FormParam("pnumber") String pnumber,
	 @FormParam("email") String email,
	 @FormParam("password") String password)
	{
		 
	String output = Obj.insertBuyers(fname, lname ,  pnumber, email, password);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	
	
	public String updateBuyers(String buyerData)
	{
		
	//Convert the input string to a JSON object
	 JsonObject Object = new JsonParser().parse(buyerData).getAsJsonObject();
	 
	//Read the values from the JSON object
	 
	//(`bId`, `fname`, `lname`, `pnumber`, `email`, `password`)
	 String bId = Object.get("bId").getAsString();
	 String fname = Object.get("fname").getAsString();
	 String lname = Object.get("lname").getAsString();
	 String pnumber = Object.get("pnumber").getAsString();
	 String email = Object.get("email").getAsString();
	 String password = Object.get("password").getAsString();
	 String output = Obj.updateBuyers(bId, fname, lname, pnumber, email, password);
	
	 return output;
	}
	
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	
	
	public String deleteBuyers(String buyerData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(buyerData, "", Parser.xmlParser());

	//Read the value from the element <bId>
	 String bId = doc.select("bId").text();
	 String output = Obj.deleteBuyers(bId);
	
	 return output;
	}
}