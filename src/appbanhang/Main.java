package appbanhang;
import java.util.Scanner;

	public class Main {
	    public static void main(String[] args) {
	        Database.init();
	        Scanner sc = new Scanner(System.in);
	        Connection conn = Database.connect();

	        while (true) {
	            System.out.println("\n=== QUẢN LÝ BÁN HÀNG ===");
	            System.out.println("1. Thêm sản phẩm");
	            System.out.println("2. Xem danh sách sản phẩm");
	            System.out.println("3. Tạo đơn hàng (giả lập)");
	            System.out.println("0. Thoát");
	            System.out.print("Chọn: ");
	            int c = sc.nextInt();
	            sc.nextLine();

	            try {
	                if (c == 1) {
	                    System.out.print("Tên sản phẩm: ");
	                    String name = sc.nextLine();
	                    System.out.print("Giá: ");
	                    double price = sc.nextDouble();
	                    System.out.print("Số lượng: ");
	                    int qty = sc.nextInt();
	                    PreparedStatement ps = conn.prepareStatement("INSERT INTO Product(name,price,quantity) VALUES(?,?,?)");
	                    ps.setString(1, name);
	                    ps.setDouble(2, price);
	                    ps.setInt(3, qty);
	                    ps.executeUpdate();
	                    System.out.println("✅ Đã thêm sản phẩm!");
	                } else if (c == 2) {
	                    ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Product");
	                    System.out.println("\n-- DANH SÁCH SẢN PHẨM --");
	                    while (rs.next()) {
	                        System.out.println(rs.getInt("productID") + " | " +
	                                           rs.getString("name") + " | " +
	                                           rs.getDouble("price") + " | " +
	                                           rs.getInt("quantity"));
	                    }
	                } else if (c == 3) {
	                    System.out.print("Nhập userID: ");
	                    int userID = sc.nextInt();
	                    sc.nextLine();

	                    // Tạo đơn hàng đơn giản 
	                    System.out.print("Nhập productID: ");
	                    int productID = sc.nextInt();
	                    System.out.print("Số lượng: ");
	                    int quantity = sc.nextInt();

	                    ResultSet rs = conn.createStatement().executeQuery("SELECT price FROM Product WHERE productID=" + productID);
	                    if (rs.next()) {
	                        double price = rs.getDouble("price");
	                        double total = price * quantity;

	                        PreparedStatement order = conn.prepareStatement(
	                            "INSERT INTO `Order`(userID,totalAmount,orderDate) VALUES(?,?,datetime('now'))",
	                            Statement.RETURN_GENERATED_KEYS);
	                        order.setInt(1, userID);
	                        order.setDouble(2, total);
	                        order.executeUpdate();

	                        ResultSet keys = order.getGeneratedKeys();
	                        if (keys.next()) {
	                            int orderID = keys.getInt(1);
	                            PreparedStatement detail = conn.prepareStatement(
	                                "INSERT INTO OrderDetail(orderID,productID,quantity,price) VALUES(?,?,?,?)");
	                            detail.setInt(1, orderID);
	                            detail.setInt(2, productID);
	                            detail.setInt(3, quantity);
	                            detail.setDouble(4, price);
	                            detail.executeUpdate();
	                            System.out.println("Đã tạo đơn hàng #" + orderID);
	                        }
	                    } else {
	                        System.out.println("Không tìm thấy sản phẩm!");
	                    }
	                } else if (c == 0) {
	                    System.out.println("Tạm biệt!");
	                    break;
	                }
	            } catch (SQLException e) {
	                System.out.println("Lỗi SQL: " + e.getMessage());
	            }
	        }
	    }
	}

