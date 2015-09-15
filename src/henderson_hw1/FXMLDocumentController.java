package henderson_hw1;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 * This class contains all the controls for the GUI.
 * It will add the pizzas ordered to the grid.
 * @author gxhender
 */
public class FXMLDocumentController implements Initializable 
{
    //Obtain the FXML objects that need to be loaded to the grid
    
    @FXML
    private TextField txtLastName;
    
    @FXML
    private ComboBox cbxPizzaName;
    
    @FXML
    private ComboBox cbxPizzaSize;
    
    @FXML
    private ComboBox cbxExtraToppings;
    
    @FXML
    private TableView<Pizza> tblPizza;
    
    private ObservableList<Pizza> pizzas = FXCollections.observableArrayList();
    
    @FXML
    private void btnAddPressed(ActionEvent event) 
    {
        double price = Pizza.getPrice((String)cbxPizzaName.getValue(), 
                        (String)cbxPizzaSize.getValue());
        
        pizzas.add(new Pizza(txtLastName.getText(), 
                (String)cbxPizzaName.getValue(), 
                (String)cbxPizzaSize.getValue(), 
                (String)cbxExtraToppings.getValue(),
                price));
    }
    
    @FXML
    private void btnOrderPressed(ActionEvent event) 
    {
        tblPizza.setItems(pizzas);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }    
    
    public static class Pizza
    {
        private final String lastName;
        private final String pizzaName;
        private final String pizzaSize;
        private final String extraTopping;
        private final double price;
                
        //Pepperoni Prices
        private final static double PEP = 5.00;
        
        //Hawaiian Prices
        private final static double HAW = 7.00;
        
        //Veggie Prices
        private final static double VEG = 6.00;
        
        //Meat Prices
        private final static double MEAT = 7.00;
        
        //Special Prices
        private final static double SPEC = 8.00;
        
        //Size Charges
        private final static double MED = 2.00;
        private final static double LARGE = 4.00;
        
        //Topping Price
        private final static double TOPSMALL = 0.50;
        private final static double TOPMED = 1.00;
        private final static double TOPLARGE = 1.50;
        
        /**
         * This class creates the pizzas being ordered
         * 
         * @param lastName
         * @param pizzaName
         * @param pizzaSize
         * @param extraTopping
         * @param price
         */
        public Pizza(String lastName, String pizzaName, 
                String pizzaSize, String extraTopping, 
                double price)
        {
            this.lastName = lastName;
            this.pizzaName = pizzaName;
            this.pizzaSize = pizzaSize;
            this.extraTopping = extraTopping;
            this.price = price;
        }
        
        // Get functions to add objects to the grid
        
        public String getLastName()
        {
            return this.lastName;
        }
        
        public String getPizzaName()
        {
            return this.pizzaName;
        }
        
        public String getPizzaSize()
        {
            return this.pizzaSize;
        }
        
        public String getExtraTopping()
        {
            return this.extraTopping;
        }
        
        public String getPrice()
        {
            NumberFormat formatter = new DecimalFormat("#0.00"); 
            return "$" + (formatter.format(price));
        }
        
        /**
         * Returns the price of the specified pizza
         * @param pizzaName
         * @param pizzaSize
         * @return
         */
        public static double getPrice(String pizzaName, String pizzaSize)
        {
            double tempPrice = 0.00;
            
            // Price of the specified pizza
            switch(pizzaName)
            {
                case "Pepperoni": 
                    tempPrice += PEP;
                    break;
                case "Hawaiian":
                    tempPrice += HAW;
                    break;
                case "Veggie":
                    tempPrice += VEG;
                    break;
                case "Meat":
                    tempPrice += MEAT;
                    break;
                case "Special":
                    tempPrice += SPEC;
                    break;
            }
            
            // Adds additional fees for different size pizzas,
            // including topping fees
            switch(pizzaSize)
            {
                case "Medium":
                    tempPrice += MED + TOPMED;
                    break;
                case "Large":
                    tempPrice += LARGE + TOPLARGE;
                    break;
                //If the pizza is small
                default:
                    tempPrice += TOPSMALL;
                    break;
            }
            
            return tempPrice;
        }
    } 
}
