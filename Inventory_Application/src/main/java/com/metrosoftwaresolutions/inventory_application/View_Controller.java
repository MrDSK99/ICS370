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


    private Stage stage;
    private Scene scene;
    private Parent root;

    //back button for all views
    @FXML
    public void back_btn(ActionEvent event) throws IOException {
        //this code can be put in a method getScene(scene.fxml), used 3 times
        root = FXMLLoader.load(getClass().getResource("Home_Screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //home screen controls here

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
        //had to manually create view here, not using fxml file due to populate table issues
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
        //create table
        TableView table_view = new TableView<Product>();
        TableColumn<Product, String> table_product_column = new TableColumn<>("Product");
        table_product_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Product, Integer> table_quantity_column = new TableColumn<>("Quantity");
        table_quantity_column.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        table_view.getColumns().add(table_product_column);
        table_view.getColumns().add(table_quantity_column);
        table_view.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        Inventory inventory = Inventory.getInstance();
        //populate table
        for (Product item: inventory.getAllInventory()) {
            table_view.getItems().add(item);
        }
        root.setCenter(table_view);
        root.setBottom(back);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,600, 400);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void btn_switch_to_modify_scene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Modify_Items_View.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //search inventory controls here
    @FXML
    private TextField output;

    @FXML
    private TextField tfProductName;

    @FXML
    void btnSearch(ActionEvent event) {

        if (tfProductName.getText().isEmpty()) {
            output.setText("Please enter a product name.");
            return;
        }
        Inventory inventory = Inventory.getInstance();
        Product item = new Product();
        item.setName(tfProductName.getText().toLowerCase()); //accept user input and make case-insensitive
        item.setQuantity(inventory.getQuantity(item.getName()));

        if (item.getQuantity() == -1)   //if product doesn't exist in inventory, output "(Product) does not exist in inventory."
            output.setText(item.getName().substring(0,1).toUpperCase()+item.getName().substring(1)+" does not exist in inventory.");
        else                            //else output "There are # (product) in stock."
            output.setText("There are "+item.getQuantity()+" "+item.getName()+" in stock.");
    }

    //add item to inventory controls here
    @FXML
    private TextField tfProduct;

    @FXML
    private TextField tfQuantity;

    @FXML
    private TextField confirmation;

    @FXML
    void add_item_button(ActionEvent event) {
        //checking for valid input first
        boolean quantityIsNumber = true;
        try { Integer.parseInt(tfQuantity.getText()); }
        catch (NumberFormatException e) { quantityIsNumber = false; }
        if (tfProduct.getText().isEmpty()) {
            confirmation.setText("Please enter a product name.");
            return;
        }
        else if (tfQuantity.getText().isEmpty()) {
            confirmation.setText("Please enter the quantity.");
            return;
        }
        else if (!quantityIsNumber || Integer.parseInt(tfQuantity.getText()) < 0 ) {
            confirmation.setText("Invalid quantity.");
            return;
        }
        String name = tfProduct.getText().toLowerCase();
        int quantity = Integer.parseInt(tfQuantity.getText());
        Product product = new Product(name, quantity);
        Add_Product_Command new_command = new Add_Product_Command(product);
        new_command.execute();
        confirmation.setText(quantity + " " + name + " added to inventory.");
    }

    @FXML
    void remove_item_button(ActionEvent event) {
        confirmation.setText("Implement this.");
    }

    @FXML
    void undo_button(ActionEvent event) {
        confirmation.setText("Implement this too.");
    }
}