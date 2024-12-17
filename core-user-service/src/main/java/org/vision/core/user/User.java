package org.vision.core.user;

import org.vision.core.common.VisionObject;

import java.sql.Timestamp;
import java.util.List;

public class User extends VisionObject {

    private String createdBy;
    private Timestamp createdOn;
    private String modifiedBy;
    private Timestamp modifiedOn;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private Timestamp dob;
    private String userName;
    private String userStatus;
    private String password;
    private Timestamp passwordExpiryDate;
    private String roleId;
    private String roleType;
    private List<UserAddressDetails> userAddressDetails;

    public User() {

    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Timestamp getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Timestamp modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getDob() {
        return dob;
    }

    public void setDob(Timestamp dob) {
        this.dob = dob;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getPasswordExpiryDate() {
        return passwordExpiryDate;
    }

    public void setPasswordExpiryDate(Timestamp passwordExpiryDate) {
        this.passwordExpiryDate = passwordExpiryDate;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public List<UserAddressDetails> getUserAddressDetails() {
        return userAddressDetails;
    }

    public void setUserAddressDetails(List<UserAddressDetails> userAddressDetails) {
        this.userAddressDetails = userAddressDetails;
    }

    public User(String createdBy, Timestamp createdOn, String modifiedBy, Timestamp modifiedOn, String firstName, String lastName, String mobileNumber, String email, Timestamp dob, String userName, String userStatus, String password, Timestamp passwordExpiryDate, String roleId, String roleType, List<UserAddressDetails> userAddressDetails) {
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.modifiedBy = modifiedBy;
        this.modifiedOn = modifiedOn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.dob = dob;
        this.userName = userName;
        this.userStatus = userStatus;
        this.password = password;
        this.passwordExpiryDate = passwordExpiryDate;
        this.roleId = roleId;
        this.roleType = roleType;
        this.userAddressDetails = userAddressDetails;
    }

    @Override
    public String toString() {
        return "User{" +
                "createdBy='" + createdBy + '\'' +
                ", createdOn=" + createdOn +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", modifiedOn=" + modifiedOn +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", userName='" + userName + '\'' +
                ", userStatus='" + userStatus + '\'' +
                ", password='" + password + '\'' +
                ", passwordExpiryDate=" + passwordExpiryDate +
                ", roleId='" + roleId + '\'' +
                ", roleType='" + roleType + '\'' +
                ", userAddressDetails=" + userAddressDetails +
                ", id='" + id + '\'' +
                '}';
    }
}
