/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cafesystem;

/**
 *
 * @author asus
 */
public class MenuItem {
        private String name;
    private String category;
    private double price;
    private int stock;
    private String imagelink;
    
    public MenuItem(String name, String category, double price, int stock, String imagelink) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.imagelink = imagelink;
    }
    
    public String getName(){
        return name; 
    }
    
    public String getCategory(){ 
        return category; 
    }
    
    public double getPrice(){ 
        return price; 
    }
    
    public int getStock(){ 
        return stock; 
    }
    
    public void setStock(int stock){ 
        this.stock = stock; 
    }
    
    public String getImagelink(){ 
        return imagelink; 
    }
    
}
