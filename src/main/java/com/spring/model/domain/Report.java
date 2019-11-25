package com.spring.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor

public class Report {

	private long number;
	private Timestamp printtime;
	private BigDecimal countCheck;
	private BigInteger countCancelCheck;
	private double totalA;
	private double ndsTotalA;
	private double totalB;
	private double ndsTotalB;
	private double totalC;
	private double ndsTotalC;
	private double sumNdsTotal;
	private double sumTotal;
	private List<Detail> detail = new ArrayList<>();

	public class Detail {
		private int nds;
		private double ndsTotal;
		private double total;
		
		public Detail() {
			super();
		}

		public Detail(int nds, double ndsTotal, double total) {
			this.nds = nds;
			this.ndsTotal = ndsTotal;
			this.total = total;
		}

		public int getNds() {
			return nds;
		}

		public void setNds(int nds) {
			this.nds = nds;
		}

		public double getNdsTotal() {
			return ndsTotal;
		}

		public void setNdsTotal(double ndsTotal) {
			this.ndsTotal = ndsTotal;
		}

		public double getTotal() {
			return total;
		}
		
		public void setTotal(double total) {
			this.total = total;
		}
	}

    public class Builder {
        private Report newReport;
 
        public Builder() {
        	newReport = new Report();
        }
 
        public Report build(){
            return newReport;
        }
		public Builder addNumber(long number) {
			newReport.setNumber(number);
			return this;
		}	
		public Builder addPrinttime(Timestamp printtime) {
			newReport.printtime = printtime;
			return this;
		}		
		public Builder addCountCheck(BigDecimal countCheck) {
			newReport.countCheck = countCheck;
			return this;
		}
		public Builder addCountCancelCheck(BigInteger countCancelCheck) {
			newReport.countCancelCheck = countCancelCheck;
			return this;
		}
		public Builder addSumTotal(double sumTotal) {
			newReport.sumTotal = sumTotal;
			return this;
		}
		public Builder addSumNdsTotal(double sumNdsTotal) {
			newReport.sumNdsTotal = sumNdsTotal;
			return this;
		}
		public Builder addTotalA(double totalA) {
			newReport.totalA = totalA;
			return this;
		}
		public Builder addTotalB(double totalB) {
			newReport.totalB = totalB;
			return this;
		}
		public Builder addTotalC(double totalC) {
			newReport.totalC = totalC;
			return this;
		}
		public Builder addNdsTotalA(double ndsTotalA) {
			newReport.ndsTotalA = ndsTotalA;
			return this;
		}
		public Builder addNdsTotalB(double ndsTotalB) {
			newReport.ndsTotalB = ndsTotalB;
			return this;
		}
		public Builder addNdsTotalC(double ndsTotalC) {
			newReport.ndsTotalC = ndsTotalC;
			return this;
		}
    }
}
