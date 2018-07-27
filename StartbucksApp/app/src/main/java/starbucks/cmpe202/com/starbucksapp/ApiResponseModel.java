package starbucks.cmpe202.com.starbucksapp;

public class ApiResponseModel {

    String balance;

    String errorCode;
    String errorMessage;
    String errorMessageDetail;


    public Boolean hasError(){
        if (errorCode == null || errorCode.isEmpty())
            return false;
        return true;
    }

}
