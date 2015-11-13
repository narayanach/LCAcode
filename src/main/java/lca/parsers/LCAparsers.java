package lca.parsers;


	import java.text.ParseException;
	import java.text.SimpleDateFormat;
	import java.util.Date;

	public class LCAparsers {
		private String status;
		private String lcaCaseSubmit;
		private String lcaCaseEmployerName;
		private String fullTimePos;

		
		
		public void parse(String record) {
				 
			String[] fields = record.split("\\|");
				  
			status 					= fields[1];
			lcaCaseSubmit 				= fields[2];		
			lcaCaseEmployerName	     		= fields[7].replace(",", "\0").replace(".", "\0").replace("\" ", "\0").replace(" ", "\0");
				
		}

		
		public String getCaseSubmitYear() {
			SimpleDateFormat origCaseSubmitYearFormat   =	new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat tgtCaseSubmitYearFormat    =	new SimpleDateFormat("yyyy");
			
			
			Date origDate = new Date();
			try {
				 origDate = origCaseSubmitYearFormat.parse(this.lcaCaseSubmit);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String tgtCaseSubmitYear = tgtCaseSubmitYearFormat.format(origDate).toString();
			return tgtCaseSubmitYear;
	}


		public String getStatus() {
			return status;
		}


		public void setStatus(String status) {
			this.status = status;
		}


		public String getLcaCaseSubmit() {
			return lcaCaseSubmit;
		}


		public void setLcaCaseSubmit(String lcaCaseSubmit) {
			this.lcaCaseSubmit = lcaCaseSubmit;
		}


		public String getLcaCaseEmployerName() {
			return lcaCaseEmployerName;
		}


		public void setLcaCaseEmployerName(String lcaCaseEmployerName) {
			this.lcaCaseEmployerName = lcaCaseEmployerName;
		}
		
	}



