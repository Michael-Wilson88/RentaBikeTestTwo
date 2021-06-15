package com.example.RentaBikeTestTwo.payload.request;


import javax.validation.constraints.NotBlank;

public class AddBikeRequest {
    @NotBlank(message = "Bike number  is mandatory")
    private String bikeNumber;

    @NotBlank(message = "BeginDate is mandatory")
    private CharSequence beginDate;

    @NotBlank(message = "EndDate is mandatory")
    private CharSequence endDate;

    public String getBikeNumber() {
        return bikeNumber;
    }

    public void setBikeNumber(String bikeNumber) {
        this.bikeNumber = bikeNumber;
    }

    public CharSequence getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(CharSequence beginDate) {
        this.beginDate = beginDate;
    }

    public CharSequence getEndDate() {
        return endDate;
    }

    public void setEndDate(CharSequence endDate) {
        this.endDate = endDate;
    }
}




