package lk.Ijse.entity;

import com.jfoenix.controls.JFXButton;

import java.sql.Date;
import java.sql.Time;

public class CustomEntity {

    //customer
    private String id;
    private String name;
    private String tel;
    private String email;

    //item
    private String code;
    private String itemName;
    private String itemDescription;
    private String category;
    private Double itemPrice;
    private int itemQtyOnHand;

    //cart
   // private String code;
    private String description;
    private double unitPrice;
    private int qty;
    private double total;
    private JFXButton btnRemove;

    //employee
    private String Eid;
    private String Ename;
    private String Etel;
    private String Esalary;
    private String ERole;

    //ingredient
    private String Ingredient_id;
    private String Ingredient_name;
    private String InCategory;
    private int qty_avalible;
    private double unit_price;
    private String supplier_id;

    //order
    private String orderId;
    private Date orderDate;
    private Time orderTime;
    private String tableType;
    private String waiter;
    private String CustomerID;
    private String ItemId;
    private double orderPrice;
    private int quantity;
    private double netTotal;

    //reservation
    private String reservationId;
    private Date date_of_reservation;
    private Date reserved_date;
    private Time reserved_time;
    private String employee_id;
    private String table_Number;
    private String Customer_id;
    private String start_time;
    private String end_time;
    private String Event;

    //supplier
    private String Sid;
    private String Sname;
    private String Stel;
    private String Semail;

}
