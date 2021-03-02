package myapp.modelView;

import java.time.ZonedDateTime;

public class ReleaseView {

    private int id_release;
    private String creationDateTime;
    private String status;
    private String surname;
    private String employeeName;
//    private String productName;
//    private int req_quantity;


    public ReleaseView(int id, String creationDateTime, String status,String surname, String employeeName/*, String productName, int req_quantity*/) {
        this.id_release = id;
        this.creationDateTime = creationDateTime;
        this.status = status;
        this.surname = surname;
        this.employeeName = employeeName;
    //    this.productName = productName;
    //    this.req_quantity = req_quantity;
    }

    public int getId() {
        return id_release;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmployeeName() { return employeeName; }

    public String getStatus() { return status; }

//    public String getProductName() { return productName; }

/*    public int getReqQuantity() {
        return req_quantity;
    }
*/
}
