package org.vision.core.validation;

import org.vision.core.common.VisionObject;

public class Validation extends VisionObject {

    private String module;
    private String fieldNameKey;
    private String fieldType;
    private String fieldDisplayName;
    private Boolean mandatory;
    private Integer minLength;
    private Integer maxLength;
    private String regex;
    private String charsNotAllowed;
    private Boolean numbersAllowed;
    private Boolean pastDateAllowed;
    private Integer lengthBeforeDecimal;
    private Integer lengthAfterDecimal;
    private String fieldInfo;
    private String fieldErrorMessage;


    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getFieldNameKey() {
        return fieldNameKey;
    }

    public void setFieldNameKey(String fieldNameKey) {
        this.fieldNameKey = fieldNameKey;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldDisplayName() {
        return fieldDisplayName;
    }

    public void setFieldDisplayName(String fieldDisplayName) {
        this.fieldDisplayName = fieldDisplayName;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getCharsNotAllowed() {
        return charsNotAllowed;
    }

    public void setCharsNotAllowed(String charsNotAllowed) {
        this.charsNotAllowed = charsNotAllowed;
    }

    public Boolean getNumbersAllowed() {
        return numbersAllowed;
    }

    public void setNumbersAllowed(Boolean numbersAllowed) {
        this.numbersAllowed = numbersAllowed;
    }

    public Boolean getPastDateAllowed() {
        return pastDateAllowed;
    }

    public void setPastDateAllowed(Boolean pastDateAllowed) {
        this.pastDateAllowed = pastDateAllowed;
    }

    public Integer getLengthBeforeDecimal() {
        return lengthBeforeDecimal;
    }

    public void setLengthBeforeDecimal(Integer lengthBeforeDecimal) {
        this.lengthBeforeDecimal = lengthBeforeDecimal;
    }

    public Integer getLengthAfterDecimal() {
        return lengthAfterDecimal;
    }

    public void setLengthAfterDecimal(Integer lengthAfterDecimal) {
        this.lengthAfterDecimal = lengthAfterDecimal;
    }

    public String getFieldInfo() {
        return fieldInfo;
    }

    public void setFieldInfo(String fieldInfo) {
        this.fieldInfo = fieldInfo;
    }

    public String getFieldErrorMessage() {
        return fieldErrorMessage;
    }

    public void setFieldErrorMessage(String fieldErrorMessage) {
        this.fieldErrorMessage = fieldErrorMessage;
    }

    @Override
    public String toString() {
        return "Validation{" +
                "module='" + module + '\'' +
                ", fieldNameKey='" + fieldNameKey + '\'' +
                ", fieldType='" + fieldType + '\'' +
                ", fieldDisplayName='" + fieldDisplayName + '\'' +
                ", mandatory=" + mandatory +
                ", minLength=" + minLength +
                ", maxLength=" + maxLength +
                ", regex='" + regex + '\'' +
                ", charsNotAllowed='" + charsNotAllowed + '\'' +
                ", numbersAllowed=" + numbersAllowed +
                ", pastDateAllowed=" + pastDateAllowed +
                ", lengthBeforeDecimal=" + lengthBeforeDecimal +
                ", lengthAfterDecimal=" + lengthAfterDecimal +
                ", fieldInfo='" + fieldInfo + '\'' +
                ", fieldErrorMessage='" + fieldErrorMessage + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
