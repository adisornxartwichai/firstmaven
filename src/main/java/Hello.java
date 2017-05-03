

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

@WebServlet("/Hello")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset = utf-8");
		PrintWriter out = response.getWriter();
		
		response.getWriter().println("Hello");
		// 1. อ่านไฟล์ JSON เก็บลง String
		String json = new String(Files.readAllBytes(Paths.get("D:/customer.json")), "UTF8");
		// 2. แปลข้อมูลจาก String ให้อย่ใ ู นรป ู แบบ Object ของ JSON
		Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);
		// 3. ดึงข้อมูลด้วย JSON Path
		// 3.1 กรณีที่ผลลพ ั ธไ ์ ม่เป็น Array
		String cusName = JsonPath.read(document, "$.customers[2].name");
		System.out.println("ชื่อลูกค้ำคนสุดท้ำย คือ " + cusName);
		// 3.2 กรณีที่ผลลัพธ์เป็น Array ต้องระบุ type ใน Array ให้ถูกด้วย ว่าเป็น String หรือ Integer
		List<String> name = JsonPath.read(document, "$..item[?(@.price>100)].item_name");
		System.out.println("สินค้ำที่มีรำคำมำกกว่ำ 100 บำท ได้แก่");
		
	out.print("<br>");
		List<String> Aname = JsonPath.read(document, "$..name");
		for(int i=0; i<Aname.size(); i++) {
			
			out.println(Aname.get(i));
			out.println("<br>");
		}
		
		
	
		
	}

}
