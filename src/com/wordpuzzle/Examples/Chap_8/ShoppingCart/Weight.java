import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Weight extends HttpServlet
{
	public void doPost(HttpServletRequest request,
						HttpServletResponse response)
			throws IOException,ServletException
	{
		HttpSession cart = request.getSession();
		String currentProduct = (String)cart.getAttribute("currentProd");

		String choice = request.getParameter("Option");
		if (choice.equals("Next"))
			response.sendRedirect("ShoppingCart.html");

		if (choice.equals("Checkout"))
			response.sendRedirect("Checkout");
		if (choice.equals("Add"))
		{
			doAdd(cart,request);
			response.sendRedirect("ShoppingCart.html");
		}

		if (choice.equals("Remove"))
		//Not really possible for it to be anything else,
		//but play safe!
		{
			doRemove(cart);
			response.sendRedirect("ShoppingCart.html");
		}
	}

	private void doAdd(HttpSession cart,HttpServletRequest request)
	{
		String currentProduct = (String)cart.getAttribute("currentProd");
		String qty = request.getParameter("Qty");
		if (qty!=null)
			cart.setAttribute(currentProduct,qty);
	}

	private void doRemove(HttpSession cart)
	{
		String currentProduct = (String)cart.getAttribute("currentProd");
		Object product = cart.getAttribute(currentProduct);
		if (product!=null)
			cart.removeAttribute(currentProduct);
	}
}
