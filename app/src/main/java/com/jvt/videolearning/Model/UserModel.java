package com.jvt.videolearning.Model;

public class UserModel {
    String mobileNumber, emailAddress, homeAddress, fullName, profilePic, userId;

    public UserModel() {
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserModel(String mobileNumber, String emailAddress, String homeAddress, String fullName, String profilePic, String userId) {
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
        this.homeAddress = homeAddress;
        this.fullName = fullName;
        this.profilePic = profilePic;
        this.userId = userId;
    }
}
