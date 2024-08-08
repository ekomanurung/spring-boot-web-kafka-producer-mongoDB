<!DOCTYPE html>

<html lang="en">
<head>
    <title>Simple Inventory System</title>
</head>
<body>
<h2>Update Price Product Inventory</h2>
<form name="inventory" action="/updatePrice" method="post">
    <table>
        <tr>
            <td><label>Product Name</label></td>
            <td><label>:</label></td>
            <td><label><input type="text" name="productName" value="${inventory.productName}" disabled="true"></label></td>
        </tr>
        <tr>
            <td><label>Stock</label></td>
            <td><label>:</label></td>
            <td><label><input type="text" name="stock" value="${inventory.stock}" disabled="true"></label><br></td>
        </tr>
        <tr>
            <td><label>Price</label></td>
            <td><label>:</label></td>
            <td><label><input type="text" name="price" value="${inventory.price}"></label><br></td>
            <td><input type="hidden" name="id" value="${inventory.id}"></td>
        </tr>
        <tr>
            <td><input type="submit" name="update"></td>
        </tr>
    </table>
</form>
</body>
</html>
