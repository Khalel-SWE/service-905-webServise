<%@page import="java.util.ArrayList"%>
<%@page import="com.item.model.Item"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Show Items</title>
    <link rel="stylesheet" href="css/show-items.css">
    
    <style>
        /* تحسينات التصميم الجديدة */
        .action-cell {
            display: flex;
            flex-wrap: wrap;
            gap: 5px;
        }
        .action-link {
            padding: 4px 8px;
            border-radius: 3px;
            text-decoration: none;
            font-size: 13px;
            transition: all 0.3s;
        }
        .update-btn {
            background-color: #3498db;
            color: white;
        }
        .delete-btn {
            background-color: #e74c3c;
            color: white;
        }
        .add-details-btn {
            background-color: #2ecc71;
            color: white;
        }
        .view-details-btn {
            background-color: #9b59b6;
            color: white;
        }
        .action-link:hover {
            opacity: 0.8;
            transform: translateY(-2px);
        }
        .item-row:hover {
            background-color: #f9f9f9;
        }
        /* .f {
            background-color: #3498db;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 20px;
            transition: background-color 0.3s;
        }
        .f:hover {
            background-color: #2980b9;
        }
        .f a {
            color: white;
            text-decoration: none;
        } */
        th {
        background-color: #2c3e50;
        color: white;
        padding: 12px 15px;
        text-align: left;
        }
        
    </style>
    
</head>
<body>
<div class="layer">
    <table>
        <h1>Items</h1>
        <thead>
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>PRICE</th>
            <th>TOTAL_NUMBER</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <%
        List<Item> items = (List<Item>) request.getAttribute("itemsData");
        %>
        <%
        	for(Item item: items){
        %>
        <tr>
            <td><strong><%=item.getId() %></strong></td>
            <td><%=item.getName() %></td>
            <td><%=item.getPrice() %></td>
            <td><%=item.getTotalNumber()%></td>
            
            <td class="action-cell">
                <a class="action-link update-btn" href='ItemController?action=load-item&id=<%=item.getId() %>'>Update</a>
                <a class="action-link delete-btn" href='ItemController?action=remove-item&id=<%=item.getId() %>'>Delete</a>
                
                <!-- التحقق من وجود تفاصيل -->
                <%
                // سنفترض أن لديك طريقة للتحقق من وجود تفاصيل
                boolean hasDetails = false; // سيتم استبدال هذا بقيمة حقيقية
                %>
                
                <% if(item.isHasDetails()) { %>
                <a class="action-link view-details-btn" href='ItemController?action=show-item-details&itemId=<%=item.getId() %>'>View Details</a>
                <% } else { %>
                <a class="action-link add-details-btn" href='ItemController?action=show-add-item-details&itemId=<%=item.getId() %>'>Add Details</a>
                <% } %>
            </td>
        </tr>
        <%
        	}
        %>
        
        </tbody>
    </table>

<a href="add-item.html" class="f">Add Item</a>
    
</div>

</body>
</html>
