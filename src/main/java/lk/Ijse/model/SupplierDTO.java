package lk.Ijse.model;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierDTO {
    private String supplierId;
    private String supplierName;
    private String contactNumber;
    private String contactEmail;
}
