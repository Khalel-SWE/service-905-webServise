<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Item Details</title>
    <style>
        .container { max-width: 500px; margin: 30px auto; padding: 20px; border: 1px solid #ccc; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; }
        textarea { width: 100%; padding: 8px; }
        button { padding: 10px 20px; background: #4CAF50; color: white; border: none; cursor: pointer; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Add Details for Item #${param.itemId}</h2>
        <form action="ItemController" method="get">
            <input type="hidden" name="action" value="add-item-details">
            <input type="hidden" name="itemId" value="${param.itemId}">
            
            <div class="form-group">
                <label>Description:</label>
                <textarea name="description" rows="4" required></textarea>
            </div>
            
            <button type="submit">Save Details</button>
        </form>
    </div>
</body>
</html>