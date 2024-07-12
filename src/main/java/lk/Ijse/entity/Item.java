package lk.Ijse.entity;

public class Item {
    String code;
    String name;
    String description;
    String category;
    double price;
    int QtyOnHand;

    public Item() {
    }

    public Item(String code, String name, String description, String category, double price, int qtyOnHand) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        QtyOnHand = qtyOnHand;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQtyOnHand() {
        return QtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        QtyOnHand = qtyOnHand;
    }

    @Override
    public String toString() {
        return "Item{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", QtyOnHand=" + QtyOnHand +
                '}';
    }
}
