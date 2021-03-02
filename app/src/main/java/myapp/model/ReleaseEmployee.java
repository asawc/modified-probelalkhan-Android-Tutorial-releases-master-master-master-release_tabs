package myapp.model;

import com.google.gson.annotations.SerializedName;

public class ReleaseEmployee {

    @SerializedName("id")
    private int employeeId;

    @SerializedName("symbol")
    private String employeeSymbol;

    @SerializedName("name")
    private String employeeName;

    @SerializedName("surname")
    private String employeeSurname;

    public int getId() {
        return employeeId;
    }

    public String getSymbol() {
        return employeeSymbol;
    }

    public String getName() {
        return employeeName;
    }

    public String getSurname() {
        return employeeSurname;
    }

}