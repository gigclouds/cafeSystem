/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cafesystem;

/**
 *
 * @author asus
 */
public class CartItem {
    
    private MenuItem menuItem;
    private int quantity;
    private double customizationCost;
    
    public CartItem(MenuItem menuItem, int quantity, double customizationPrice) {
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.customizationCost = customizationPrice;
    }
    
    public double getTotalPrice() {
        return (menuItem.getPrice() + customizationCost) * quantity;
    }
    
    @Override
    public String toString() {
        String customization;
        if (customizationCost > 0) {
            customization = " (+" + String.format("%.2f", customizationCost) + ")";
        }else {
            customization = "";
        }
        
        return menuItem.getName() + customization + " x" + quantity + " - RM " + String.format("%.2f", getTotalPrice());
    }
    
   
    public MenuItem getMenuItem(){ 
        return menuItem; 
    }
    
    public int getQuantity(){ 
        return quantity; 
    }
    
    public double getCustomizationCost(){ 
        return customizationCost; 
    }
}
