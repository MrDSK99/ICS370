package com.metrosoftwaresolutions.inventory_application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class View_Controller {


    private final String PASSWORD = "password";
    private Stage stage;
    private Scene scene;
    private Parent root;

    //back button for all views
    @FXML
    public void back_btn(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Home_Screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //home screen controls here
    @FXML
    private TextField password_field;

    @FXML
    public void btn_switch_to_search_scene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Search_View.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void btn_switch_to_show_all_scene(ActionEvent event)  throws IOException {
        //show all inventory controls also here, very messy but works for now
        BorderPane root = new BorderPane();
        Button back = new Button("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    back_btn(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        TableView table_view = new TableView<Product>();
        TableColumn<Product, String> table_product_column = new TableColumn<>("Product");
        table_product_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Product, Integer> table_quantity_column = new TableColumn<>("Quantity");
        table_quantity_column.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        table_view.getColumns().add(table_product_column);
        table_view.getColumns().add(table_quantity_column);
        table_view.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        Inventory inventory = Inventory.getInstance();
        for (Product item: inventory.getAllInventory()) {
            table_view.getItems().add(item);
        }
        root.setCenter(table_view);
        root.setBottom(back);
        scene = new Scene(root,600, 400);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void btn_switch_to_add_item_scene(ActionEvent event) throws IOException {
        Stage mainWindow = (Stage) password_field.getScene().getWindow();
        if (password_field.getText().isEmpty() || !password_field.getText().equals(PASSWORD)) {
            password_field.clear();
            password_field.setPromptText("Please enter password.");
            return;
        }
        else {
            root = FXMLLoader.load(getClass().getResource("Add_Items_View.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    //search inventory controls here
    @FXML
    private TextField output;

    @FXML
    private TextField tfProductName;

    @FXML
    void btnSearch(ActionEvent event) {

        Stage mainWindow = (Stage) tfProductName.getScene().getWindow();
        if (tfProductName.getText().isEmpty()) {
            output.setText("Please enter a product name.");
            return;
        }
        Inventory inventory = Inventory.getInstance();
        Product item = new Product(null,-1);
        item.setName(tfProductName.getText().toLowerCase()); //accept user input and make case-insensitive
        item.setQuantity(inventory.getQuantity(item.getName()));

        if (item.getQuantity() == -1)   //if product doesn't exist in inventory, output "(Product) does not exist in inventory."
            output.setText(item.getName().substring(0,1).toUpperCase()+item.getName().substring(1)+" does not exist in inventory.");
        else                            //else output "There are # (product) in stock."
            output.setText("There are "+item.getQuantity()+" "+item.getName()+" in stock.");
    }

    //add item to inventory controls here
    @FXML
    private TextField AddProductName;

    @FXML
    private TextField AddQty;

    @FXML
    private TextField confirmation;

    @FXML
    void add_item_button(ActionEvent event) {
        boolean quantityIsNumber = true;
        try { Integer.parseInt(AddQty.getText()); }
        catch (NumberFormatException e) { quantityIsNumber = false; }
        if (AddProductName.getText().isEmpty()) {
            confirmation.setText("Please enter a product name.");
            return;
        }
        else if (AddQty.getText().isEmpty()) {
            confirmation.setText("Please enter the quantity.");
            return;
        }
        else if (!quantityIsNumber || Integer.parseInt(AddQty.getText()) < 0 ) {
            confirmation.setText("Invalid quantity.");
            return;
        }
        String itemName = AddProductName.getText().toLowerCase();
        int quantity = Integer.parseInt(AddQty.getText());
        Inventory inventory = Inventory.getInstance();
        if (inventory.getQuantity(itemName) == -1)
            inventory.addItem(itemName, quantity);
        else  // If the item already exists, update its quantity
            inventory.updateQuantity(itemName,quantity);
        confirmation.setText(quantity + " " + itemName + " added to inventory.");
    }
}