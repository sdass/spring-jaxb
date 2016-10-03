//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 

package com.drfbets.funding.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {      "limit"     })
@XmlRootElement(name = "result")
public class LimitAvailableResponseResult {

	protected List<LimitAvailableResponseResult.Limit> limit;
	
	public LimitAvailableResponseResult() {	}
	
	public LimitAvailableResponseResult(List<Limit> limit) {
		super();
		this.limit = limit;
	}
	
	@Override
	public String toString() {
		return "LimitAvailableResponseResult [limit=" + limit + "]";
	}
	
	public List<LimitAvailableResponseResult.Limit> getLimit() {
	    if (limit == null) {
	        limit = new ArrayList<LimitAvailableResponseResult.Limit>();
	    }
	    return this.limit;
	}
	
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "fundingmethodid",  "timeframe",  "type", "authorized", "remaining", "next" })
	public static class Limit {
	
	    protected BigInteger fundingmethodid;
	    @XmlElement(required = true)
	    protected String timeframe;
	    @XmlElement(required = true)
	    protected String type;
	    @XmlElement(required = true)
	    protected BigInteger authorized;
	    @XmlElement(required = true)
	    protected LimitAvailableResponseResult.Limit.Remaining remaining;
	    protected LimitAvailableResponseResult.Limit.Next next;

	    public Limit(){}
	    
	    public Limit(BigInteger fundingmethodid, String timeframe, String type, BigInteger authorized, Remaining remaining, Next next) {
			super();
			this.fundingmethodid = fundingmethodid;
			this.timeframe = timeframe;
			this.type = type;
			this.authorized = authorized;
			this.remaining = remaining;
			this.next = next;
		}
	    
		@Override
		public String toString() {
			return "Limit [fundingmethodid=" + fundingmethodid + ", timeframe="
					+ timeframe + ", type=" + type + ", authorized="
					+ authorized + ", remaining=" + remaining + ", next="
					+ next + "]";
		}

		public BigInteger getFundingmethodid() {
	        return fundingmethodid;
	    }
	
	    public void setFundingmethodid(BigInteger value) {
	        this.fundingmethodid = value;
	    }
	
	    public String getTimeframe() {
	        return timeframe;
	    }
	
	    public void setTimeframe(String value) {
	        this.timeframe = value;
	    }
	
	    public String getType() {
	        return type;
	    }
	
	    public void setType(String value) {
	        this.type = value;
	    }
	
	    public BigInteger getAuthorized() {
	        return authorized;
	    }
	
	    public void setAuthorized(BigInteger value) {
	        this.authorized = value;
	    }
	
	    public LimitAvailableResponseResult.Limit.Remaining getRemaining() {
	        return remaining;
	    }
	
	    public void setRemaining(LimitAvailableResponseResult.Limit.Remaining value) {
	        this.remaining = value;
	    }
	
	    public LimitAvailableResponseResult.Limit.Next getNext() {
	        return next;
	    }
	
	    public void setNext(LimitAvailableResponseResult.Limit.Next value) {
	        this.next = value;
	    }
	
	    @XmlAccessorType(XmlAccessType.FIELD)
	    @XmlType(name = "", propOrder = {   "date",     "amount"       })
	    public static class Next {
	
	        @XmlElement(required = true)
	        protected String date;
	        @XmlElement(required = true)
	        protected BigDecimal amount;
	        
	        public Next(){}
	
	        public Next(String date, BigDecimal amount) {
				super();
				this.date = date;
				this.amount = amount;
			}

			@Override
			public String toString() {
				return "Next [date=" + date + ", amount=" + amount + "]";
			}

			public String getDate() {
	            return date;
	        }
	
	        public void setDate(String value) {
	            this.date = value;
	        }
	
	        public BigDecimal getAmount() {
	            return amount;
	        }
	
	        public void setAmount(BigDecimal value) {
	            this.amount = value;
	        }
	
	    }
	
	    @XmlAccessorType(XmlAccessType.FIELD)
	    @XmlType(name = "", propOrder = { "amount", "number"    })
	    public static class Remaining {
	
	        @XmlElement(required = true)
	        protected BigDecimal amount;
	        @XmlElement(required = true)
	        protected BigInteger number;

	        public Remaining(){}
	        
	        public Remaining(BigDecimal amount, BigInteger number) {
				super();
				this.amount = amount;
				this.number = number;
			}

			@Override
			public String toString() {
				return "Remaining [amount=" + amount + ", number=" + number
						+ "]";
			}

			public BigDecimal getAmount() {
	            return amount;
	        }
	
	        public void setAmount(BigDecimal value) {
	            this.amount = value;
	        }
	
	        public BigInteger getNumber() {
	            return number;
	        }
	
	        public void setNumber(BigInteger value) {
	            this.number = value;
	        }
	
	    }
	
	}

}