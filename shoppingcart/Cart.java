package shoppingcart;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> cartItems = new ArrayList<>();

    public void addProduct(Product product) {
        if (product.getStock() > 0) {
            cartItems.add(product);
            product.reduceStock();
            JOptionPane.showMessageDialog(null, product.getName() + " added to cart.");
        } else {
            JOptionPane.showMessageDialog(null, "Out of stock.");
        }
    }

    public void removeProduct(String productName) {
        boolean found = false;
        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getName().equalsIgnoreCase(productName)) {
                cartItems.get(i).increaseStock();
                cartItems.remove(i);
                JOptionPane.showMessageDialog(null, productName + " removed from cart.");
                found = true;
                break;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(null, "Product not found in cart.");
        }
    }

    public void displayCart() {
        if (cartItems.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Your cart is empty.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Items in Cart:\n");
        double total = 0;
        for (Product p : cartItems) {
            sb.append("- ").append(p.getName()).append(" | $").append(String.format("%.2f", p.getPrice())).append("\n");
            total += p.getPrice();
        }
        sb.append("Total: $").append(String.format("%.2f", total));
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public void checkout() {
        if (cartItems.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cart is empty. Cannot checkout.");
            return;
        }
        displayCart();
        int confirm = JOptionPane.showConfirmDialog(null, "Proceed to payment?", "Checkout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Payment successful! Thank you for your purchase.");
            cartItems.clear();
        } else {
            JOptionPane.showMessageDialog(null, "Checkout cancelled.");
        }
    }
}
