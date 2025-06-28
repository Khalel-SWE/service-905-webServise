<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.item.model.ItemDetails" %>
<%
    // الحصول على بيانات التفاصيل من الطلب
    ItemDetails itemDetails = (ItemDetails) request.getAttribute("itemDetails");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Item Details</title>
    <style>
        .details-box { 
            max-width: 600px; 
            margin: 30px auto; 
            padding: 20px; 
            border: 1px solid #ccc; 
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            background-color: #f9f9f9;
        }
        .detail-row { 
            margin-bottom: 15px; 
            padding-bottom: 15px; 
            border-bottom: 1px solid #eee; 
            display: flex;
        }
        .detail-label { 
            font-weight: bold; 
            width: 120px;
            color: #333;
        }
        .detail-value {
            flex: 1;
            color: #555;
        }
        h2 {
            color: #2c3e50;
            border-bottom: 2px solid #3498db;
            padding-bottom: 10px;
            margin-top: 0;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            padding: 8px 15px;
            background-color: #3498db;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s;
        }
        a:hover {
            background-color: #2980b9;
        }
        .no-details {
            color: #e74c3c;
            font-style: italic;
        }
    </style>
</head>
<body>
    <div class="details-box">
        <h2>Item Details</h2>
        
        <% if (itemDetails != null) { %>
            <div class="detail-row">
                <span class="detail-label">Item ID:</span>
                <span class="detail-value"><%= itemDetails.getItemId() %></span>
            </div>
            <div class="detail-row">
                <span class="detail-label">Description:</span>
                <span class="detail-value"><%= itemDetails.getDescription() %></span>
            </div>
        <% } else { %>
            <p class="no-details">No details found for this item.</p>
        <% } %>
        
        <a href="ItemController?action=load-items">Back to Items List</a>
    </div>
</body>
</html>