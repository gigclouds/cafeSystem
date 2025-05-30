/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cafesystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class CafeGUI extends Application {

    

    private ObservableList<MenuItem> menuItems = FXCollections.observableArrayList();
    private ObservableList<Order> orders = FXCollections.observableArrayList();
    private int orderCounter = 1;

    private Stage primaryStage;
    private Scene mainScene; 
    private Scene placeOrderScene;
    private Scene viewOrderScene;
    
    private TableView<Order> orderTable;
    private TextArea orderDetailsArea;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initializeMenuItems();

        // main scene
        createMainScene();

        primaryStage.setTitle("Restaurant Ordering System");
        primaryStage.setScene(mainScene);
        primaryStage.setWidth(1000);
        primaryStage.setHeight(700);
        primaryStage.show();
    }

    private void initializeMenuItems() {
        menuItems.add(new MenuItem("Chicken Rice", "Rice", 5.00, 50, "https://static01.nyt.com/images/2025/01/28/multimedia/KP-Hainan-Chicken-Rice-hcgv/KP-Hainan-Chicken-Rice-hcgv-mediumSquareAt3X.jpg"));
        menuItems.add(new MenuItem("Gepuk Rice", "Rice", 6.00, 30, "https://ayamgepuktopglobal.my/wp-content/uploads/2023/12/ayam-gepok-top-global-set-b-min.jpg"));
        menuItems.add(new MenuItem("Mee Goreng", "Mee", 6.00, 40, "https://ucarecdn.com/e2fd02f3-bfeb-43a5-a564-72bab62a13f5/-/scale_crop/1280x1280/center/-/quality/normal/-/format/jpeg/mee-goreng-mamak.jpg"));
        menuItems.add(new MenuItem("Bihun Soup", "Mee", 4.00, 35, "https://ronamy.b-cdn.net/wp-content/uploads/2021/12/resepi-bihun-sup-chinese-style.png"));
        menuItems.add(new MenuItem("Sandwich", "Snack", 3.50, 25, "https://static.toiimg.com/thumb/83740315.cms?width=1200&height=900"));
        menuItems.add(new MenuItem("Latte", "Drink", 3.00, 100, "https://cdn.vox-cdn.com/uploads/chorus_image/image/73942014/IMG_1503.0.jpg"));
        menuItems.add(new MenuItem("Tea", "Drink", 2.00, 80, "https://www.rappler.com/tachyon/2019/09/Screen-Shot-2022-05-17-at-4.42.48-PM.png"));
        menuItems.add(new MenuItem("Chocolate", "Drink", 2.00, 60, "https://princesspinkygirl.com/wp-content/uploads/2021/01/Dirty-Snowman-square.jpg"));
        menuItems.add(new MenuItem("Water", "Drink", 1.50, 200, "https://cdn1.npcdn.net/images/2ee7f03265ee71980dae20eaedcc9a24_1658811657.jpeg"));
        menuItems.add(new MenuItem("Orange Juice", "Drink", 3.00, 50, "https://www.organicfacts.net/wp-content/uploads/orangejuice-1.jpg"));
    }

    private void createMainScene() {
        
        VBox mainLayout = new VBox(20);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(50));
 

        // Welcome 
        Label welcomeLabel = new Label("Welcome to Our Restaurant");
        welcomeLabel.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");//gray-blue color

        VBox buttonBox = new VBox(20);
        buttonBox.setAlignment(Pos.CENTER);

        Button placeOrderBtn = new Button("Place Order");
        Button viewOrderBtn = new Button("View Orders");
        Button exitBtn = new Button("Exit");

        // Style buttons
        String buttonStyle = "-fx-font-size: 20px; -fx-padding: 20 40; -fx-background-color: blue; -fx-text-fill: white; -fx-background-radius: 10; -fx-min-width: 200px;";
        placeOrderBtn.setStyle(buttonStyle);//color blue
        viewOrderBtn.setStyle(buttonStyle);
        exitBtn.setStyle(buttonStyle + "-fx-background-color: #e74c3c;");//color red

        // Button hover effects
        placeOrderBtn.setOnMouseEntered(e -> placeOrderBtn.setStyle(buttonStyle + "-fx-background-color: #2980b9;"));
        placeOrderBtn.setOnMouseExited(e -> placeOrderBtn.setStyle(buttonStyle));

        viewOrderBtn.setOnMouseEntered(e -> viewOrderBtn.setStyle(buttonStyle + "-fx-background-color: #2980b9;"));
        viewOrderBtn.setOnMouseExited(e -> viewOrderBtn.setStyle(buttonStyle));

        exitBtn.setOnMouseEntered(e -> exitBtn.setStyle(buttonStyle + "-fx-background-color: #c0392b;"));
        exitBtn.setOnMouseExited(e -> exitBtn.setStyle(buttonStyle + "-fx-background-color: #e74c3c;"));

        // Button actions
        placeOrderBtn.setOnAction(e -> showPlaceOrderScene());
        viewOrderBtn.setOnAction(e -> showViewOrderScene());
        exitBtn.setOnAction(e -> primaryStage.close());

        buttonBox.getChildren().addAll(placeOrderBtn, viewOrderBtn, exitBtn);
        mainLayout.getChildren().addAll(welcomeLabel, buttonBox);

        mainScene = new Scene(mainLayout);
    }

    private void showPlaceOrderScene() {
        VBox layout = new VBox(20);
    layout.setPadding(new Insets(30));
    layout.setAlignment(Pos.TOP_CENTER);
    layout.setStyle("-fx-background-color: #f8f9fa;");

    Label title = new Label("Place Your Order");
    title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

    // Customer info form
    GridPane form = new GridPane();
    form.setHgap(15);
    form.setVgap(15);
    form.setAlignment(Pos.CENTER);

    TextField nameField = new TextField();
    nameField.setPromptText("Enter your name");
    nameField.setPrefWidth(200);

    ComboBox<String> orderTypeBox = new ComboBox<>();
    orderTypeBox.setItems(FXCollections.observableArrayList("Takeaway", "Dine In"));
    orderTypeBox.setPromptText("Select order type");
    orderTypeBox.setPrefWidth(200);

    TextField tableField = new TextField();
    tableField.setPromptText("Table number");
    tableField.setPrefWidth(200);
    tableField.setDisable(true);

    // Payment method selection
    ComboBox<String> paymentBox = new ComboBox<>();
    paymentBox.setItems(FXCollections.observableArrayList("Cash", "Card", "E-Wallet"));
    paymentBox.setPromptText("Select payment method");
    paymentBox.setPrefWidth(200);

    orderTypeBox.setOnAction(e -> {
        tableField.setDisable(!orderTypeBox.getValue().equals("Dine In"));
    });

    form.add(new Label("Name:"), 0, 0);
    form.add(nameField, 1, 0);
    form.add(new Label("Order Type:"), 0, 1);
    form.add(orderTypeBox, 1, 1);
    form.add(new Label("Table Number:"), 0, 2);
    form.add(tableField, 1, 2);
    form.add(new Label("Payment Method:"), 0, 3);
    form.add(paymentBox, 1, 3);

    // Cart display 
    ObservableList<CartItem> cartObservable = FXCollections.observableArrayList();
    ListView<CartItem> cartView = new ListView<>(cartObservable);
    cartView.setPrefHeight(200);

    // Total label
    Label totalLabel = new Label("Total: RM 0.00");
    totalLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #e74c3c;");

    // CHANGED: Add listener to automatically update total when cart changes
    cartObservable.addListener((javafx.collections.ListChangeListener<CartItem>) change -> {
        updateTotal(cartObservable, totalLabel);
    });

    // Menu items for ordering
    GridPane orderMenuGrid = new GridPane();
    orderMenuGrid.setHgap(15);
    orderMenuGrid.setVgap(15);
    orderMenuGrid.setAlignment(Pos.CENTER);

    int col = 0, row = 0;
    for (MenuItem item : menuItems) {
        VBox itemBox = createOrderItemBox(item, cartObservable, cartView);
        itemBox.setAlignment(Pos.CENTER);
        orderMenuGrid.add(itemBox, col, row);
        col++;
        if (col > 3) {
            col = 0;
            row++;
        }
    }

    ScrollPane menuScrollPane = new ScrollPane(orderMenuGrid);
    menuScrollPane.setPrefHeight(400);
    menuScrollPane.setStyle("-fx-background-color: white;");

    // Buttons
    HBox menuContainer = new HBox();
    menuContainer.setAlignment(Pos.CENTER); 
    menuContainer.getChildren().add(menuScrollPane);
    
    HBox buttonBox = new HBox(15);
    buttonBox.setAlignment(Pos.CENTER);

    Button checkoutBtn = new Button("Checkout");
    Button backBtn = new Button("Back to Main");

    checkoutBtn.setStyle("-fx-font-size: 14px; -fx-padding: 10 20; -fx-background-color: #27ae60; -fx-text-fill: white; -fx-background-radius: 5;");
    backBtn.setStyle("-fx-font-size: 14px; -fx-padding: 10 20; -fx-background-color: #95a5a6; -fx-text-fill: white; -fx-background-radius: 5;");

    checkoutBtn.setOnAction(e -> {
        if (nameField.getText().trim().isEmpty() || orderTypeBox.getValue() == null || paymentBox.getValue() == null) {
            showAlert("Error", "Please fill in all required fields including payment method.");
            return;
        }

        if (orderTypeBox.getValue().equals("Dine In") && tableField.getText().trim().isEmpty()) {
            showAlert("Error", "Please enter table number for dine-in orders.");
            return;
        }

        if (cartObservable.isEmpty()) {
            showAlert("Error", "Please add items to your cart.");
            return;
        }

        
        processOrder(nameField.getText().trim(), orderTypeBox.getValue(),
                tableField.getText().trim(), paymentBox.getValue(), new ArrayList<>(cartObservable));
    });

    backBtn.setOnAction(e -> primaryStage.setScene(mainScene));

    buttonBox.getChildren().addAll(checkoutBtn, backBtn);

    layout.getChildren().addAll(title, form, new Label("Menu:"), menuScrollPane,
            new Label("Cart:"), cartView, totalLabel, buttonBox);

    ScrollPane scrollPane = new ScrollPane(layout);
    scrollPane.setFitToWidth(true);
    placeOrderScene = new Scene(scrollPane, 1000, 700);
    primaryStage.setScene(placeOrderScene);
}

private VBox createOrderItemBox(MenuItem item, ObservableList<CartItem> cart, ListView<CartItem> cartView) {
    VBox itemBox = new VBox(8);
    itemBox.setAlignment(Pos.CENTER);
    itemBox.setPadding(new Insets(10));
    itemBox.setStyle("-fx-border-color: #bdc3c7; -fx-border-radius: 5; -fx-background-color: white; -fx-background-radius: 5;");
    itemBox.setPrefWidth(180);

    Label nameLabel = new Label(item.getName());
    nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12px;");

    Label priceLabel = new Label("RM " + String.format("%.2f", item.getPrice()));
    priceLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #e74c3c;");

    Label stockLabel = new Label("Stock: " + item.getStock());
    stockLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: #95a5a6;");

    Button addBtn = new Button("Add to Cart");
    addBtn.setStyle("-fx-font-size: 10px; -fx-padding: 5 10; -fx-background-color: #3498db; -fx-text-fill: white; -fx-background-radius: 3;");

    // Declare imageView outside try block so it's visible here
    ImageView imageView = null;
    try {
        Image image = new Image(item.getImagelink(), 150, 100, true, true);
        imageView = new ImageView(image);
    } catch (Exception e) {
        System.out.println("Failed to load image for " + item.getName());
        // You could set a placeholder image here if desired
    }

    addBtn.setOnAction(e -> {
        if (item.getStock() <= 0) {
            showAlert("Out of Stock", item.getName() + " is out of stock!");
            return;
        }

        if (item.getCategory().equals("Drink")) {
            showDrinkCustomization(item, cart, cartView);
        } else {
            CartItem cartItem = new CartItem(item, 1, 0);
            cart.add(cartItem); 
            item.setStock(item.getStock() - 1);
            stockLabel.setText("Stock: " + item.getStock());
            
        }
    });

    if (imageView != null) {
        itemBox.getChildren().add(imageView);
    }
    itemBox.getChildren().addAll(nameLabel, priceLabel, stockLabel, addBtn);
    return itemBox;
}

private void showDrinkCustomization(MenuItem item, ObservableList<CartItem> cart, ListView<CartItem> cartView) {
    Dialog<CartItem> dialog = new Dialog<>();
    dialog.setTitle("Customize " + item.getName());
    dialog.setHeaderText("Add customizations:");

    VBox content = new VBox(10);
    content.setPadding(new Insets(20));

    CheckBox honeyBox = new CheckBox("Add Honey (+RM 0.50)");
    CheckBox sugarBox = new CheckBox("Add Sugar (+RM 0.50)");
    CheckBox iceBox = new CheckBox("Add Ice (+RM 0.50)");

    Label totalLabel = new Label("Total: RM " + String.format("%.2f", item.getPrice()));
    totalLabel.setStyle("-fx-font-weight: bold;");

    Runnable updateTotal = () -> {
        double total = item.getPrice();
        if (honeyBox.isSelected()) total += 0.50;
        if (sugarBox.isSelected()) total += 0.50;
        if (iceBox.isSelected()) total += 0.50;
        totalLabel.setText("Total: RM " + String.format("%.2f", total));
    };

    honeyBox.setOnAction(e -> updateTotal.run());
    sugarBox.setOnAction(e -> updateTotal.run());
    iceBox.setOnAction(e -> updateTotal.run());

    content.getChildren().addAll(honeyBox, sugarBox, iceBox, totalLabel);
    dialog.getDialogPane().setContent(content);

    ButtonType addButtonType = new ButtonType("Add to Cart", ButtonBar.ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

    dialog.setResultConverter(dialogButton -> {
        if (dialogButton == addButtonType) {
            double customizationCost = 0;
            if (honeyBox.isSelected()) customizationCost += 0.50;
            if (sugarBox.isSelected()) customizationCost += 0.50;
            if (iceBox.isSelected()) customizationCost += 0.50;

            return new CartItem(item, 1, customizationCost);
        }
        return null;
    });

    Optional<CartItem> result = dialog.showAndWait();
    result.ifPresent(cartItem -> {
        cart.add(cartItem); 
        item.setStock(item.getStock() - 1);

    });
}

    private VBox createOrderItemBox(MenuItem item, List<CartItem> cart, ListView<CartItem> cartView) {
        VBox itemBox = new VBox(8);
        itemBox.setAlignment(Pos.CENTER);
        itemBox.setPadding(new Insets(10));
        itemBox.setStyle("-fx-border-color: #bdc3c7; -fx-border-radius: 5; -fx-background-color: white; -fx-background-radius: 5;");
        itemBox.setPrefWidth(180);

        Label nameLabel = new Label(item.getName());
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12px;");

        Label priceLabel = new Label("RM " + String.format("%.2f", item.getPrice()));
        priceLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #e74c3c;");

        Label stockLabel = new Label("Stock: " + item.getStock());
        stockLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: #95a5a6;");

        Button addBtn = new Button("Add to Cart");
        addBtn.setStyle("-fx-font-size: 10px; -fx-padding: 5 10; -fx-background-color: #3498db; -fx-text-fill: white; -fx-background-radius: 3;");

       
        ImageView imageView = null;
        try {
            Image image = new Image(item.getImagelink(), 150, 100, true, true);
            imageView = new ImageView(image);
        } catch (Exception e) {
            System.out.println("Failed to load image for " + item.getName());

        }

        addBtn.setOnAction(e -> {
            if (item.getStock() <= 0) {
                showAlert("Out of Stock", item.getName() + " is out of stock!");
                return;
            }

            if (item.getCategory().equals("Drink")) {
                showDrinkCustomization(item, cart, cartView);
            } else {
                CartItem cartItem = new CartItem(item, 1, 0);
                cart.add(cartItem);
                item.setStock(item.getStock() - 1);
                stockLabel.setText("Stock: " + item.getStock());
                cartView.setItems(FXCollections.observableArrayList(cart));
            }
        });

        if (imageView != null) {
            itemBox.getChildren().add(imageView);
        }
        itemBox.getChildren().addAll(nameLabel, priceLabel, stockLabel, addBtn);
        return itemBox;
    }

    private void showDrinkCustomization(MenuItem item, List<CartItem> cart, ListView<CartItem> cartView) {
        Dialog<CartItem> dialog = new Dialog<>();
        dialog.setTitle("Customize " + item.getName());
        dialog.setHeaderText("Add customizations:");

        VBox content = new VBox(10);
        content.setPadding(new Insets(20));

        CheckBox honeyBox = new CheckBox("Add Honey (+RM 0.50)");
        CheckBox sugarBox = new CheckBox("Add Sugar (+RM 0.50)");
        CheckBox iceBox = new CheckBox("Add Ice (+RM 0.50)");

        Label totalLabel = new Label("Total: RM " + String.format("%.2f", item.getPrice()));
        totalLabel.setStyle("-fx-font-weight: bold;");

        Runnable updateTotal = () -> {
            double total = item.getPrice();
            if (honeyBox.isSelected()) total += 0.50;
            if (sugarBox.isSelected()) total += 0.50;
            if (iceBox.isSelected()) total += 0.50;
            totalLabel.setText("Total: RM " + String.format("%.2f", total));
        };

        honeyBox.setOnAction(e -> updateTotal.run());
        sugarBox.setOnAction(e -> updateTotal.run());
        iceBox.setOnAction(e -> updateTotal.run());

        content.getChildren().addAll(honeyBox, sugarBox, iceBox, totalLabel);
        dialog.getDialogPane().setContent(content);

        ButtonType addButtonType = new ButtonType("Add to Cart", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                double customizationCost = 0;
                if (honeyBox.isSelected()) customizationCost += 0.50;
                if (sugarBox.isSelected()) customizationCost += 0.50;
                if (iceBox.isSelected()) customizationCost += 0.50;

                return new CartItem(item, 1, customizationCost);
            }
            return null;
        });

        Optional<CartItem> result = dialog.showAndWait();
        result.ifPresent(cartItem -> {
            cart.add(cartItem);
            item.setStock(item.getStock() - 1);
            cartView.setItems(FXCollections.observableArrayList(cart));
        });
    }

    private void updateTotal(List<CartItem> cart, Label totalLabel) {
        double total = cart.stream().mapToDouble(CartItem::getTotalPrice).sum();
        totalLabel.setText("Total: RM " + String.format("%.2f", total));
    }

    private void processOrder(String customerName, String orderType, String tableNumber, String paymentMethod, List<CartItem> cart) {
        double total = cart.stream().mapToDouble(CartItem::getTotalPrice).sum();

        Order order = new Order(orderCounter++, customerName, orderType, tableNumber, paymentMethod, cart, total);
        orders.add(order);

        showAlert("Order Confirmed",
                "Order #" + order.getOrderId() + " has been placed successfully!\n" +
                        "Payment Method: " + paymentMethod + "\n" +
                        "Estimated time: 15 minutes\n" +
                        "Total: RM " + String.format("%.2f", total));

        primaryStage.setScene(mainScene);
    }

    private void showViewOrderScene() {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #f8f9fa;");

        Label title = new Label("Your Orders");
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        orderTable = new TableView<>();
        orderTable.setItems(orders);
        orderTable.setPrefHeight(300);//

        TableColumn<Order, Integer> idCol = new TableColumn<>("Order ID");
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getOrderId()).asObject());
        idCol.setPrefWidth(80);

        TableColumn<Order, String> nameCol = new TableColumn<>("Customer");
        nameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCustomerName()));
        nameCol.setPrefWidth(120);

        TableColumn<Order, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getOrderType()));
        typeCol.setPrefWidth(100);

        TableColumn<Order, String> tableCol = new TableColumn<>("Table");
        tableCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTableNumber()));
        tableCol.setPrefWidth(80);

        TableColumn<Order, String> paymentCol = new TableColumn<>("Payment");
        paymentCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPaymentMethod()));
        paymentCol.setPrefWidth(100);

        TableColumn<Order, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus()));
        statusCol.setPrefWidth(100);

        TableColumn<Order, Double> totalCol = new TableColumn<>("Total (RM)");
        totalCol.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getTotal()).asObject());
        totalCol.setPrefWidth(100);

        TableColumn<Order, String> itemsCol = new TableColumn<>("Items");
        itemsCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getItems().size() + " item(s)"));
        itemsCol.setPrefWidth(80);

        orderTable.getColumns().addAll(idCol, nameCol, typeCol, tableCol, paymentCol, statusCol, totalCol, itemsCol);

        // Order details area
        orderDetailsArea = new TextArea();
        orderDetailsArea.setPrefRowCount(10);
        orderDetailsArea.setPrefHeight(200);
        orderDetailsArea.setEditable(false);
        orderDetailsArea.setPromptText("Select an order to view details...");

        orderTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                StringBuilder details = new StringBuilder();
                details.append("Order #").append(newSelection.getOrderId()).append(" Details:\n");
                details.append("Customer: ").append(newSelection.getCustomerName()).append("\n");
                details.append("Type: ").append(newSelection.getOrderType()).append("\n");
                if (!newSelection.getTableNumber().isEmpty()) {
                    details.append("Table: ").append(newSelection.getTableNumber()).append("\n");
                }
                details.append("Payment: ").append(newSelection.getPaymentMethod()).append("\n");
                details.append("Status: ").append(newSelection.getStatus()).append("\n");
                details.append("Items:\n");
                for (CartItem item : newSelection.getItems()) {
                    details.append("- ").append(item.toString()).append("\n");
                }
                details.append("Total: RM ").append(String.format("%.2f", newSelection.getTotal()));
                orderDetailsArea.setText(details.toString());
            }
        });

        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.CENTER);

        Button editBtn = new Button("Edit Order");
        Button deleteBtn = new Button("Delete Order");
        Button backBtn = new Button("Back to Main");

        editBtn.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white; -fx-padding: 10 15; -fx-background-radius: 5;");
        deleteBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-padding: 10 15; -fx-background-radius: 5;");
        backBtn.setStyle("-fx-background-color: #95a5a6; -fx-text-fill: white; -fx-padding: 10 15; -fx-background-radius: 5;");

        editBtn.setOnAction(e -> {
            Order selected = orderTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                showEditOrderDialog(selected);
            } else {
                showAlert("No Selection", "Please select an order to edit.");
            }
        });

        deleteBtn.setOnAction(e -> {
            Order selected = orderTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete Order");
                alert.setHeaderText("Are you sure you want to delete this order?");
                alert.setContentText("Order #" + selected.getOrderId() + " will be permanently deleted.\nStock will be restored for all items.");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    
                    // Restore stock for deleted order
                    for (CartItem item : selected.getItems()) {
                        MenuItem menuItem = item.getMenuItem();
                        menuItem.setStock(menuItem.getStock() + item.getQuantity());
                    }

                    orders.remove(selected);
                    orderDetailsArea.clear();
                    showAlert("Order Deleted", "Order #" + selected.getOrderId() + " has been deleted successfully.\nStock has been restored.");
                }
            } else {
                showAlert("No Selection", "Please select an order to delete.");
            }
        });

        backBtn.setOnAction(e -> primaryStage.setScene(mainScene));

        Button refreshBtn = new Button("Refresh");
        refreshBtn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-padding: 10 15; -fx-background-radius: 5;");
        refreshBtn.setOnAction(e -> {
            orderTable.refresh();
            showAlert("Refreshed", "Order list has been refreshed!");
        });

        buttonBox.getChildren().addAll(editBtn, deleteBtn, refreshBtn, backBtn);

        Label detailsLabel = new Label("Order Details:");
        detailsLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        layout.getChildren().addAll(title, orderTable, detailsLabel, orderDetailsArea, buttonBox);

        viewOrderScene = new Scene(layout, 1000, 700);
        primaryStage.setScene(viewOrderScene);
    }

    private void showEditOrderDialog(Order order) {
        Dialog<Boolean> dialog = new Dialog<>();
        dialog.setTitle("Edit Order #" + order.getOrderId());
        dialog.setHeaderText("Edit order details:");

        VBox content = new VBox(15);
        content.setPadding(new Insets(20));

        // Customer name 
        TextField nameField = new TextField(order.getCustomerName());
        nameField.setPromptText("Customer name");

        // Order type
        ComboBox<String> typeBox = new ComboBox<>();
        typeBox.setItems(FXCollections.observableArrayList("Takeaway", "Dine In"));
        typeBox.setValue(order.getOrderType());

        // Table number
        TextField tableField = new TextField(order.getTableNumber());
        tableField.setPromptText("Table number");
        tableField.setDisable(!order.getOrderType().equals("Dine In"));

        // Payment method
        ComboBox<String> paymentBox = new ComboBox<>();
        paymentBox.setItems(FXCollections.observableArrayList("Cash", "Card", "E-Wallet"));
        paymentBox.setValue(order.getPaymentMethod());

        typeBox.setOnAction(e -> {
            tableField.setDisable(!typeBox.getValue().equals("Dine In"));
            if (!typeBox.getValue().equals("Dine In")) {
                tableField.clear();
            }
        });

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);

        form.add(new Label("Customer Name:"), 0, 0);
        form.add(nameField, 1, 0);
        form.add(new Label("Order Type:"), 0, 1);
        form.add(typeBox, 1, 1);
        form.add(new Label("Table Number:"), 0, 2);
        form.add(tableField, 1, 2);
        form.add(new Label("Payment Method:"), 0, 3);
        form.add(paymentBox, 1, 3);

        content.getChildren().add(form);
        dialog.getDialogPane().setContent(content);

        ButtonType saveButtonType = new ButtonType("Save Changes", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                if (nameField.getText().trim().isEmpty()) {
                    showAlert("Error", "Customer name cannot be empty.");
                    return false;
                }

                if (typeBox.getValue().equals("Dine In") && tableField.getText().trim().isEmpty()) {
                    showAlert("Error", "Table number is required for dine-in orders.");
                    return false;
                }

                order.setCustomerName(nameField.getText().trim());
                order.setOrderType(typeBox.getValue());
                order.setTableNumber(tableField.getText().trim());
                order.setPaymentMethod(paymentBox.getValue());

                return true;
            }
            return false;
        });

        Optional<Boolean> result = dialog.showAndWait();
        if (result.isPresent() && result.get()) {
          
            orderTable.refresh();

            
            if (orderTable.getSelectionModel().getSelectedItem() == order) {
                StringBuilder details = new StringBuilder();
                details.append("Order #").append(order.getOrderId()).append(" Details:\n");
                details.append("Customer: ").append(order.getCustomerName()).append("\n");
                details.append("Type: ").append(order.getOrderType()).append("\n");
                if (!order.getTableNumber().isEmpty()) {
                    details.append("Table: ").append(order.getTableNumber()).append("\n");
                }
                details.append("Payment: ").append(order.getPaymentMethod()).append("\n");
                details.append("Status: ").append(order.getStatus()).append("\n");
                details.append("Items:\n");
                for (CartItem item : order.getItems()) {
                    details.append("- ").append(item.toString()).append("\n");
                }
                details.append("Total: RM ").append(String.format("%.2f", order.getTotal()));
                orderDetailsArea.setText(details.toString());
            }

            showAlert("Order Updated", "Order #" + order.getOrderId() + " has been updated successfully!");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

