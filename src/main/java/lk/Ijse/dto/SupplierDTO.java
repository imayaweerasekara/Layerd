package lk.Ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierDTO {
    private String supplierId;
    private String supplierName;
    private String contactNumber;
    private String contactEmail;
}
