package com.subra.funding.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {  "category",   "function",    "param"    })
public class RequestEmpty<T> {

    @XmlElement(required = true)
    protected String category;
    @XmlElement(required = true)
    protected String function;
    //protected Fmxrequestempty.Request.Param param;
    @XmlAnyElement(lax=true)  //critical line it works
    protected T param;
    
    
    public RequestEmpty(){}

    public RequestEmpty(String category, String function, T param) {
		super();
		this.category = category;
		this.function = function;
		this.param = param;
	}

	public String getCategory() {
        return category;
    }

    public void setCategory(String value) {
        this.category = value;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String value) {
        this.function = value;
    }

    //public Fmxrequestempty.Request.Param getParam() {
    public T getParam() {
        return param;
    }

    //public void setParam(Fmxrequestempty.Request.Param value) {
    public void setParam(T value) {
        this.param = value;
    }

	@Override
	public String toString() {
		return "RequestEmpty [category=" + category + ", function=" + function
				+ ", param=" + param + "]";
	}
    
}