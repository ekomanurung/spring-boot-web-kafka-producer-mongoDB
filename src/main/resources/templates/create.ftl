<!DOCTYPE html>

<html lang="en">
<head>
    <title>Simple Inventory System</title>
</head>
    <body>
    <h2>Add Product Inventory</h2>
        <form name="inventory" action="create" method="post">

            <table>
                <tr>
                    <td><label>Product Name</label></td>
                    <td><label>:</label></td>
                    <td><label><input type="text" name="productName"></label></td>
                </tr>
                <tr>
                    <td><label>Stock</label></td>
                    <td><label>:</label></td>
                    <td><label><input type="text" name="stock"></label><br></td>
                </tr>
                <tr>
                    <td><label>Price</label></td>
                    <td><label>:</label></td>
                    <td><label><input type="text" name="price"></label><br></td>
                </tr>
                <tr>
                    <td><input type="submit" name="Add"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
