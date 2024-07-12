package lk.Ijse.bo;

import lk.Ijse.bo.custom.impl.*;

public class BoFactory {
    private static BoFactory boFactory;

    public BoFactory() {
    }

    public static BoFactory getBoFactory (){
        return (boFactory==null)? boFactory=new BoFactory() : boFactory;
    }

    public enum BoTypes{
        CUSTOMER,ITEM,EMPLOYEE,INGREDIENT,SUPPLIER,PO
    }

    public SuperBo getBo (BoTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerBoImpl();
            case ITEM:
                return new ItemBoImpl();
            case EMPLOYEE:
                return new EmployeeBoImpl();
            case PO:
                return new PurchaseOrderBoImpl();
            case SUPPLIER:
                return new SupplierBoImpl();
            case INGREDIENT:
                return new IngredintBoImpl();
            default:
                return null;
        }
    }
}
