package com.object_repository;

public class TPMGEM {

	public static class TPMApplication {
		// id Login Page
		public static final String UNAME = "UserAccount";
		public static final String UNAMEVALUE = "UserPassword";
		public static final String LOGIN = "login_btn";

		// xpath for Logout
		public static final String LOGOUTUNAME = "//span[@class = \"LoginName\"]//ancestor :: a[@href ='javascript:;']";
		public static final String LNKLOGOUT = "//a[@href = \"Login.html\"]";

		// xpath Header
		public static final String PAYMENTOPTION = "//ul[@id='LAY-system-side-menu']//child::li[5]/a";
		public static final String ADDCLAIM = "//label[contains(text(), 'Add Claim')]//parent::a";
		public static final String TODOOPTION = "//*[@id='LAY-system-side-menu']//child::li[2]/a";
		public static final String PAYMENTAPPROVAL = "//label[contains(@lang, 'Menu.ToDo.PaymentApprove')]";
		public static final String CONTRACTAPPROVAL = "//label[contains(@lang, 'Menu.ToDo.ContractApprove')]";
		public static final String CONTRACTOPTION = "//*[@id=\"LAY-system-side-menu\"]/li[4]/a";
		public static final String CONTRACTUPLOAD = "//*[@id='LAY-system-side-menu']/li[4]/dl/dd[3]";
		public static final String CHANGEFRAME = "iframeForm";
	}

	public static class PaymentTPM {
		// id Payment -> Add Claim Page
		public static final String PAYMENTNO = "PaymentNo";
		public static final String INVOICENO = "InvoiceNo";
		public static final String INVOICEDATE = "InvoiceDate";

		// xpath Payment -> Add Claim Page
		public static final String PAYTOCODESEARCH = "//*[@id=\"divContent\"]/div[1]/div[2]/div/div/div/i[2]";
		public static final String ADDLNK = "Payment_Detail_add";

//		public static final String ADDLNK ="Payment_Detail_add";	
		public static final String OKTIPSPOPUP = "//*[@id=\"layui-layer23\"]/div[3]/a";
		// id
		public static final String SUBMITADDCLAIM = "Submit";

		// xpath for To-Do -> Payment Approval -> Pending
		public static final String LNKPAYMENTNOR1C4 = "//tr[@data-index = '0']//child :: td[@data-field=\"PaymentNo\"]/div/a";
		public static final String STATUS = "//*[@id=\"LAY_app_body\"]//child :: td[@data-field=\"StatusName\"]/div";
		public static final String APPROVEDBYME = "//li[@lay-id ='PayApproved']";

		// xpath for To-Do -> Payment Approval -> Approved By Me

		public static final String VERIFYAPPROVEDPAYMENTINFO = "//tr[@data-index='0']//td[@data-content='Approved']//following-sibling::td[@data-field='PaymentNo']";
		public static final String APPROVEDSTATUS = "//tr[@data-index='0']//td[@data-field='StatusName']";

		// xpath for Payment Info
		public static final String VERIFYPAYMENTINFO = "//div[@id='divContent'] //child :: input[@id ='PaymentNo']/preceding-sibling::label";
		public static final String VERIFYINVOICEINFO = "//div[@id='divContent'] //child :: input[@id ='InvoiceNo']/preceding-sibling::label";
		public static final String APPROVEBTN = "Approve";
		public static final String OKAPPROVEINFO = "//*[@id=\"layui-layer5\"]//child :: a[@class =\"layui-layer-btn0\"]";

		// xpath Pay To Code Search Page
		public static final String CUSTOMERSEARCH = "txtCustomerSearch";
		public static final String CUSTOMERSEARCHBTN = "btn_Query";
		public static final String RADIOVENDOR = "//tr[@data-index = '0']//child ::input[1]/..";

		// xpath for Payment detail Popup
		public static final String SEARCHCONTACTNO = "//*[@id=\"divContent\"]/div[1]/div/div[1]/div/div/i[2]";
		public static final String SAVEPAYMENTDETAIL = "//*[@id=\"divBtn\"]/button";

		// xpath for Customer Search
		public static final String RADIOCUSTOMERSEARCH = "//tr[@data-index=\"0\"]//child :: td[@data-field =\"cb\"]/div";

		// xpath for Payment ->Payment List Page
		public static final String CELLPAYMENTNO = "//tr[@data-index =\"0\"]//child :: td[@data-field =\"PaymentNo\"]";
		public static final String CELLINVOICENO = "//tr[@data-index =\"0\"]//child :: td[@data-field =\"InvoiceNo\"]/div";

	}

	public static class ContractTPM {
		// id for Contact Upload Page
		public static final String BROWSEBTN = "Browse";
		public static final String CONTRACTSUBMIT = "submit";

		// xpath for TO-DO -> Contract approval Page
		public static final String CONTRACTSTATUS = "//tr[@data-index='0']/td[@data-field ='StatusName']";
		public static final String CONTRACTCREATEDBY = "//tr[@data-index='0']/td[@data-field ='CreateName']";
		public static final String CLICKRADIOR1C1 = "//tr[@data-index='0']/td[@data-field ='cb']";
		public static final String CONTRACTNOR1C5 = "//tr[@data-index='0']/td[@data-field ='ContractNo']";
		public static final String BATCHAPPROVAL = "//*[@id='layerDemo']/a[@title='Batch Approval']";

		// xpath to submit Batch Approval
		public static final String BATCHSUBMIT = "//*[@id='ApprovePageDiv']//child::button[contains(text(),'Submit')]";

	}

}
