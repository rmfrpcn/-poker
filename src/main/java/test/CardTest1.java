package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CardTest1
 */
@WebServlet("/CardTest1")
public class CardTest1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CardTest1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8"); // 文字コードがUTF-8のHTML
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>CradTest1</title>");
		out.println("</head>");
		
		out.println("<body>");
		out.println("<p><img src=\"./cards/0.png\" alt=\"errore\"></p>");
		out.println("<p>スペードA</p>");
		out.println("<hr>");
		int card = (int) (Math.random() * 52);
		out.println("<p><img src=\"./cards/" + card + ".png\" alt=\"errore\"></p>");
		out.println("<p>ランダムなカード</p>");
		out.println("<hr>");
		for(int i = 0 ; i < 52 ; i++) {
			out.println("<img src=\"./cards/" + i + ".png\" width=\"100\" height=\"100\" alt=\"errore\">");
		}
		out.println("</body>");
		
		out.println("</html>");
		System.out.println("doGet()"); // コンソールに表示される(動作確認のため)
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doPost()"); // この例ではdoPost()は実行されないので表示もされない
	}
}
