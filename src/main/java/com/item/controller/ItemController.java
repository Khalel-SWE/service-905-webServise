package com.item.controller;

import java.io.IOException;

import java.util.List;
import java.util.Objects;

import com.item.model.ItemDetails;
import com.item.service.ItemDetailsService;
import com.item.service.impl.ItemDetailsServiceImpl;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.item.model.Item;
import com.item.service.ItemService;
import com.item.service.impl.ItemServiceImpl;

//http://localhost:8080/item-service-905/ItemController?itemName=item55&itemPrice=54&itemTotalNumber=15&action=add-item

// http://localhost:8080/item-service-905/ItemController                        action=null
//http://localhost:8080/item-service-905/ItemController?action=srm              action=srm
//http://localhost:8080/item-service-905/ItemController?action=load-items      action=load-items
//http://localhost:8080/item-service-905/ItemController?action=add-item         action=add-item
//http://localhost:8080/item-service-905/ItemController?action=remove-items     action=remove-items
//http://localhost:8080/item-service-905/ItemController?action=load-item        action=load-item

@WebServlet("/ItemController")
public class ItemController extends HttpServlet { 
   
	private ItemDetailsService itemDetailsService;//1
	
	@Resource(name = "jdbc/item")
	private DataSource dataSource;

	private ItemService itemService;

	// public    private  protected  default
	public ItemController(){
		
	}
	
	@Override
	public void init() throws ServletException {
		itemService = new ItemServiceImpl(dataSource);
		itemDetailsService = new ItemDetailsServiceImpl(dataSource);//2
	}

	
	// action = {add-item, update-item, remove-item, load-item, load-items}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (Objects.isNull(action)) {
			action = "load-items";
		}
		
		switch (action) {
			case "add-item":
				addItem(request, response);
				break;
			case "remove-item":
				removeItem(request, response);
				break;
			case "update-item":
				updateItem(request, response);
				break;
			case "load-item":
				loadItem(request, response);
				break;
			case "load-items":
				loadItems(request, response);
				break;
			case "show-add-item-details":// from here...
                showAddItemDetails(request, response);
                break;
                
            case "add-item-details":
                addItemDetails(request, response);
                break;
                
            case "show-item-details":
                showItemDetails(request, response);
                break;// to here....
			default:
				loadItems(request, response);
		}
	}
	
	
	private void updateItem(HttpServletRequest request, HttpServletResponse response) {
		Item item = extraxtItem(request);
		Boolean updatedItem = itemService.updateItem(item);
		
		if(updatedItem) {
			loadItems(request, response);
		}
	}


	private void loadItems(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		List<Item> items =  itemService.loadItems();
		
		request.setAttribute("itemsData", items);
		try {
			request.getRequestDispatcher("/load-items.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void loadItem(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		Item item = itemService.loadItem(id);
		request.setAttribute("itemData", item);
		try {
			request.getRequestDispatcher("/update-item.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void removeItem(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("==> " + id);
		Boolean removedItem = itemService.removeItem(id);
		if (removedItem) {
			loadItems(request, response);
		}
	}
	
	private void addItem(HttpServletRequest request, HttpServletResponse response) {
		Item item = extraxtItem(request);
		Boolean addedItem = itemService.saveItem(item);
		System.out.println("====> " + addedItem);
		if (addedItem) {
			loadItems(request, response);
		}
	}

	private Item extraxtItem(HttpServletRequest request){
		String itemName = request.getParameter("itemName");
		double itemPrice = Double.parseDouble(request.getParameter("itemPrice"));
		int itemTotalNumber = Integer.parseInt(request.getParameter("itemTotalNumber"));
		
		Item item = new Item(itemName, itemPrice, itemTotalNumber);
		
		String idParam = request.getParameter("itemId");
		if (Objects.nonNull(idParam)) {
			item.setId(Integer.parseInt(idParam));
		}
		
		return item;
	}
	
	
	// from here till the end
	// دالة لعرض صفحة إضافة التفاصيل
    private void showAddItemDetails(HttpServletRequest request, HttpServletResponse response) {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        try {
            response.sendRedirect("add-item-details.jsp?itemId=" + itemId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // دالة لحفظ التفاصيل
    private void addItemDetails(HttpServletRequest request, HttpServletResponse response) {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        String description = request.getParameter("description");

        // نتحقق لو العنصر له تفاصيل قبل كده
        if (itemDetailsService.hasItemDetails(itemId)) {
            try {
                response.sendRedirect("error.jsp?message=Item already has details");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        // ننشئ كائن التفاصيل
        ItemDetails details = new ItemDetails(description, itemId);
        
        // نحفظ في الداتابيز
        if (itemDetailsService.saveItemDetails(details)) {
            loadItems(request, response); // نرجع لصفحة العناصر
        } else {
            try {
                response.sendRedirect("error.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // دالة لعرض تفاصيل عنصر معين
    private void showItemDetails(HttpServletRequest request, HttpServletResponse response) {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        ItemDetails details = itemDetailsService.getItemDetailsByItemId(itemId);
        
        request.setAttribute("itemDetails", details);
        try {
            request.getRequestDispatcher("/show-item-details.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
