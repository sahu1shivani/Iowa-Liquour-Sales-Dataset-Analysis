
package talend_assignment.iowa_liquor_sales_1_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.SQLike;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: Iowa_Liquor_Sales Purpose: <br>
 * Description: <br>
 * 
 * @author patil.nis@northeastern.edu
 * @version 8.0.1.20220923_1236-patch
 * @status
 */
public class Iowa_Liquor_Sales implements TalendJob {
	static {
		System.setProperty("TalendJob.log", "Iowa_Liquor_Sales.log");
	}

	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger(Iowa_Liquor_Sales.class);

	protected static void logIgnoredError(String message, Throwable cause) {
		log.error(message, cause);

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "1.1";
	private final String jobName = "Iowa_Liquor_Sales";
	private final String projectName = "TALEND_ASSIGNMENT";
	public Integer errorCode = null;
	private String currentComponent = "";

	private String cLabel = null;

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private final JobStructureCatcherUtils talendJobLog = new JobStructureCatcherUtils(jobName,
			"_SaGEYNHTEe2dbdqIb9qmwg", "1.1");
	private org.talend.job.audit.JobAuditLogger auditLogger_talendJobLog = null;

	private RunStat runStat = new RunStat(talendJobLog, System.getProperty("audit.interval"));

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;

		private String currentComponent = null;
		private String cLabel = null;

		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		private TalendException(Exception e, String errorComponent, String errorComponentLabel,
				final java.util.Map<String, Object> globalMap) {
			this(e, errorComponent, globalMap);
			this.cLabel = errorComponentLabel;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					Iowa_Liquor_Sales.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(Iowa_Liquor_Sales.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
						if (enableLogStash) {
							talendJobLog.addJobExceptionMessage(currentComponent, cLabel, null, e);
							talendJobLogProcess(globalMap);
						}
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tFileInputDelimited_10_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_10_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_11_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_10_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_10_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_10_onSubJobError(exception, errorComponent, globalMap);
	}

	public void talendJobLog_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		talendJobLog_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_10_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void talendJobLog_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class iowa_Liquor_Sales_notcleaned_StagingStruct
			implements routines.system.IPersistableRow<iowa_Liquor_Sales_notcleaned_StagingStruct> {
		final static byte[] commonByteArrayLock_TALEND_ASSIGNMENT_Iowa_Liquor_Sales = new byte[0];
		static byte[] commonByteArray_TALEND_ASSIGNMENT_Iowa_Liquor_Sales = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int iowa_Liquor_sales_notcleaned_SK;

		public int getIowa_Liquor_sales_notcleaned_SK() {
			return this.iowa_Liquor_sales_notcleaned_SK;
		}

		public Boolean iowa_Liquor_sales_notcleaned_SKIsNullable() {
			return false;
		}

		public Boolean iowa_Liquor_sales_notcleaned_SKIsKey() {
			return true;
		}

		public Integer iowa_Liquor_sales_notcleaned_SKLength() {
			return null;
		}

		public Integer iowa_Liquor_sales_notcleaned_SKPrecision() {
			return null;
		}

		public String iowa_Liquor_sales_notcleaned_SKDefault() {

			return "";

		}

		public String iowa_Liquor_sales_notcleaned_SKComment() {

			return "";

		}

		public String iowa_Liquor_sales_notcleaned_SKPattern() {

			return "";

		}

		public String iowa_Liquor_sales_notcleaned_SKOriginalDbColumnName() {

			return "iowa_Liquor_sales_notcleaned_SK";

		}

		public String Invoice_Item_Number;

		public String getInvoice_Item_Number() {
			return this.Invoice_Item_Number;
		}

		public Boolean Invoice_Item_NumberIsNullable() {
			return true;
		}

		public Boolean Invoice_Item_NumberIsKey() {
			return false;
		}

		public Integer Invoice_Item_NumberLength() {
			return 15;
		}

		public Integer Invoice_Item_NumberPrecision() {
			return 0;
		}

		public String Invoice_Item_NumberDefault() {

			return null;

		}

		public String Invoice_Item_NumberComment() {

			return "";

		}

		public String Invoice_Item_NumberPattern() {

			return "dd-MM-yyyy";

		}

		public String Invoice_Item_NumberOriginalDbColumnName() {

			return "Invoice_Item_Number";

		}

		public String Date;

		public String getDate() {
			return this.Date;
		}

		public Boolean DateIsNullable() {
			return true;
		}

		public Boolean DateIsKey() {
			return false;
		}

		public Integer DateLength() {
			return 10;
		}

		public Integer DatePrecision() {
			return 0;
		}

		public String DateDefault() {

			return null;

		}

		public String DateComment() {

			return "";

		}

		public String DatePattern() {

			return "dd-MM-yyyy";

		}

		public String DateOriginalDbColumnName() {

			return "Date";

		}

		public Integer Store_Number;

		public Integer getStore_Number() {
			return this.Store_Number;
		}

		public Boolean Store_NumberIsNullable() {
			return true;
		}

		public Boolean Store_NumberIsKey() {
			return false;
		}

		public Integer Store_NumberLength() {
			return 4;
		}

		public Integer Store_NumberPrecision() {
			return 0;
		}

		public String Store_NumberDefault() {

			return null;

		}

		public String Store_NumberComment() {

			return "";

		}

		public String Store_NumberPattern() {

			return "dd-MM-yyyy";

		}

		public String Store_NumberOriginalDbColumnName() {

			return "Store_Number";

		}

		public String Store_Name;

		public String getStore_Name() {
			return this.Store_Name;
		}

		public Boolean Store_NameIsNullable() {
			return true;
		}

		public Boolean Store_NameIsKey() {
			return false;
		}

		public Integer Store_NameLength() {
			return 254;
		}

		public Integer Store_NamePrecision() {
			return 0;
		}

		public String Store_NameDefault() {

			return null;

		}

		public String Store_NameComment() {

			return "";

		}

		public String Store_NamePattern() {

			return "dd-MM-yyyy";

		}

		public String Store_NameOriginalDbColumnName() {

			return "Store_Name";

		}

		public String Address;

		public String getAddress() {
			return this.Address;
		}

		public Boolean AddressIsNullable() {
			return true;
		}

		public Boolean AddressIsKey() {
			return false;
		}

		public Integer AddressLength() {
			return 254;
		}

		public Integer AddressPrecision() {
			return 0;
		}

		public String AddressDefault() {

			return null;

		}

		public String AddressComment() {

			return "";

		}

		public String AddressPattern() {

			return "dd-MM-yyyy";

		}

		public String AddressOriginalDbColumnName() {

			return "Address";

		}

		public String City;

		public String getCity() {
			return this.City;
		}

		public Boolean CityIsNullable() {
			return true;
		}

		public Boolean CityIsKey() {
			return false;
		}

		public Integer CityLength() {
			return 100;
		}

		public Integer CityPrecision() {
			return 0;
		}

		public String CityDefault() {

			return null;

		}

		public String CityComment() {

			return "";

		}

		public String CityPattern() {

			return "dd-MM-yyyy";

		}

		public String CityOriginalDbColumnName() {

			return "City";

		}

		public String Zip_Code;

		public String getZip_Code() {
			return this.Zip_Code;
		}

		public Boolean Zip_CodeIsNullable() {
			return true;
		}

		public Boolean Zip_CodeIsKey() {
			return false;
		}

		public Integer Zip_CodeLength() {
			return 254;
		}

		public Integer Zip_CodePrecision() {
			return 0;
		}

		public String Zip_CodeDefault() {

			return null;

		}

		public String Zip_CodeComment() {

			return "";

		}

		public String Zip_CodePattern() {

			return "dd-MM-yyyy";

		}

		public String Zip_CodeOriginalDbColumnName() {

			return "Zip_Code";

		}

		public String Store_Location;

		public String getStore_Location() {
			return this.Store_Location;
		}

		public Boolean Store_LocationIsNullable() {
			return true;
		}

		public Boolean Store_LocationIsKey() {
			return false;
		}

		public Integer Store_LocationLength() {
			return 254;
		}

		public Integer Store_LocationPrecision() {
			return 0;
		}

		public String Store_LocationDefault() {

			return null;

		}

		public String Store_LocationComment() {

			return "";

		}

		public String Store_LocationPattern() {

			return "dd-MM-yyyy";

		}

		public String Store_LocationOriginalDbColumnName() {

			return "Store_Location";

		}

		public Integer County_Number;

		public Integer getCounty_Number() {
			return this.County_Number;
		}

		public Boolean County_NumberIsNullable() {
			return true;
		}

		public Boolean County_NumberIsKey() {
			return false;
		}

		public Integer County_NumberLength() {
			return 254;
		}

		public Integer County_NumberPrecision() {
			return 0;
		}

		public String County_NumberDefault() {

			return null;

		}

		public String County_NumberComment() {

			return "";

		}

		public String County_NumberPattern() {

			return "dd-MM-yyyy";

		}

		public String County_NumberOriginalDbColumnName() {

			return "County_Number";

		}

		public String County;

		public String getCounty() {
			return this.County;
		}

		public Boolean CountyIsNullable() {
			return true;
		}

		public Boolean CountyIsKey() {
			return false;
		}

		public Integer CountyLength() {
			return 100;
		}

		public Integer CountyPrecision() {
			return 0;
		}

		public String CountyDefault() {

			return null;

		}

		public String CountyComment() {

			return "";

		}

		public String CountyPattern() {

			return "dd-MM-yyyy";

		}

		public String CountyOriginalDbColumnName() {

			return "County";

		}

		public Integer Category;

		public Integer getCategory() {
			return this.Category;
		}

		public Boolean CategoryIsNullable() {
			return true;
		}

		public Boolean CategoryIsKey() {
			return false;
		}

		public Integer CategoryLength() {
			return 7;
		}

		public Integer CategoryPrecision() {
			return 0;
		}

		public String CategoryDefault() {

			return null;

		}

		public String CategoryComment() {

			return "";

		}

		public String CategoryPattern() {

			return "dd-MM-yyyy";

		}

		public String CategoryOriginalDbColumnName() {

			return "Category";

		}

		public String Category_Name;

		public String getCategory_Name() {
			return this.Category_Name;
		}

		public Boolean Category_NameIsNullable() {
			return true;
		}

		public Boolean Category_NameIsKey() {
			return false;
		}

		public Integer Category_NameLength() {
			return 254;
		}

		public Integer Category_NamePrecision() {
			return 0;
		}

		public String Category_NameDefault() {

			return null;

		}

		public String Category_NameComment() {

			return "";

		}

		public String Category_NamePattern() {

			return "dd-MM-yyyy";

		}

		public String Category_NameOriginalDbColumnName() {

			return "Category_Name";

		}

		public String Vendor_Number;

		public String getVendor_Number() {
			return this.Vendor_Number;
		}

		public Boolean Vendor_NumberIsNullable() {
			return true;
		}

		public Boolean Vendor_NumberIsKey() {
			return false;
		}

		public Integer Vendor_NumberLength() {
			return 10;
		}

		public Integer Vendor_NumberPrecision() {
			return 0;
		}

		public String Vendor_NumberDefault() {

			return null;

		}

		public String Vendor_NumberComment() {

			return "";

		}

		public String Vendor_NumberPattern() {

			return "dd-MM-yyyy";

		}

		public String Vendor_NumberOriginalDbColumnName() {

			return "Vendor_Number";

		}

		public String Vendor_Name;

		public String getVendor_Name() {
			return this.Vendor_Name;
		}

		public Boolean Vendor_NameIsNullable() {
			return true;
		}

		public Boolean Vendor_NameIsKey() {
			return false;
		}

		public Integer Vendor_NameLength() {
			return 254;
		}

		public Integer Vendor_NamePrecision() {
			return 0;
		}

		public String Vendor_NameDefault() {

			return null;

		}

		public String Vendor_NameComment() {

			return "";

		}

		public String Vendor_NamePattern() {

			return "dd-MM-yyyy";

		}

		public String Vendor_NameOriginalDbColumnName() {

			return "Vendor_Name";

		}

		public String Item_Number;

		public String getItem_Number() {
			return this.Item_Number;
		}

		public Boolean Item_NumberIsNullable() {
			return true;
		}

		public Boolean Item_NumberIsKey() {
			return false;
		}

		public Integer Item_NumberLength() {
			return 100;
		}

		public Integer Item_NumberPrecision() {
			return 0;
		}

		public String Item_NumberDefault() {

			return null;

		}

		public String Item_NumberComment() {

			return "";

		}

		public String Item_NumberPattern() {

			return "dd-MM-yyyy";

		}

		public String Item_NumberOriginalDbColumnName() {

			return "Item_Number";

		}

		public String Item_Description;

		public String getItem_Description() {
			return this.Item_Description;
		}

		public Boolean Item_DescriptionIsNullable() {
			return true;
		}

		public Boolean Item_DescriptionIsKey() {
			return false;
		}

		public Integer Item_DescriptionLength() {
			return 500;
		}

		public Integer Item_DescriptionPrecision() {
			return 0;
		}

		public String Item_DescriptionDefault() {

			return null;

		}

		public String Item_DescriptionComment() {

			return "";

		}

		public String Item_DescriptionPattern() {

			return "dd-MM-yyyy";

		}

		public String Item_DescriptionOriginalDbColumnName() {

			return "Item_Description";

		}

		public Integer Pack;

		public Integer getPack() {
			return this.Pack;
		}

		public Boolean PackIsNullable() {
			return true;
		}

		public Boolean PackIsKey() {
			return false;
		}

		public Integer PackLength() {
			return 254;
		}

		public Integer PackPrecision() {
			return 0;
		}

		public String PackDefault() {

			return null;

		}

		public String PackComment() {

			return "";

		}

		public String PackPattern() {

			return "dd-MM-yyyy";

		}

		public String PackOriginalDbColumnName() {

			return "Pack";

		}

		public Integer Bottle_Volume__ml;

		public Integer getBottle_Volume__ml() {
			return this.Bottle_Volume__ml;
		}

		public Boolean Bottle_Volume__mlIsNullable() {
			return true;
		}

		public Boolean Bottle_Volume__mlIsKey() {
			return false;
		}

		public Integer Bottle_Volume__mlLength() {
			return 254;
		}

		public Integer Bottle_Volume__mlPrecision() {
			return 0;
		}

		public String Bottle_Volume__mlDefault() {

			return null;

		}

		public String Bottle_Volume__mlComment() {

			return "";

		}

		public String Bottle_Volume__mlPattern() {

			return "dd-MM-yyyy";

		}

		public String Bottle_Volume__mlOriginalDbColumnName() {

			return "Bottle_Volume__ml";

		}

		public Float State_Bottle_Cost;

		public Float getState_Bottle_Cost() {
			return this.State_Bottle_Cost;
		}

		public Boolean State_Bottle_CostIsNullable() {
			return true;
		}

		public Boolean State_Bottle_CostIsKey() {
			return false;
		}

		public Integer State_Bottle_CostLength() {
			return 254;
		}

		public Integer State_Bottle_CostPrecision() {
			return 4;
		}

		public String State_Bottle_CostDefault() {

			return null;

		}

		public String State_Bottle_CostComment() {

			return "";

		}

		public String State_Bottle_CostPattern() {

			return "dd-MM-yyyy";

		}

		public String State_Bottle_CostOriginalDbColumnName() {

			return "State_Bottle_Cost";

		}

		public Float State_Bottle_Retail;

		public Float getState_Bottle_Retail() {
			return this.State_Bottle_Retail;
		}

		public Boolean State_Bottle_RetailIsNullable() {
			return true;
		}

		public Boolean State_Bottle_RetailIsKey() {
			return false;
		}

		public Integer State_Bottle_RetailLength() {
			return 254;
		}

		public Integer State_Bottle_RetailPrecision() {
			return 3;
		}

		public String State_Bottle_RetailDefault() {

			return null;

		}

		public String State_Bottle_RetailComment() {

			return "";

		}

		public String State_Bottle_RetailPattern() {

			return "dd-MM-yyyy";

		}

		public String State_Bottle_RetailOriginalDbColumnName() {

			return "State_Bottle_Retail";

		}

		public Integer Bottles_Sold;

		public Integer getBottles_Sold() {
			return this.Bottles_Sold;
		}

		public Boolean Bottles_SoldIsNullable() {
			return true;
		}

		public Boolean Bottles_SoldIsKey() {
			return false;
		}

		public Integer Bottles_SoldLength() {
			return 254;
		}

		public Integer Bottles_SoldPrecision() {
			return 0;
		}

		public String Bottles_SoldDefault() {

			return null;

		}

		public String Bottles_SoldComment() {

			return "";

		}

		public String Bottles_SoldPattern() {

			return "dd-MM-yyyy";

		}

		public String Bottles_SoldOriginalDbColumnName() {

			return "Bottles_Sold";

		}

		public Float Sale__Dollars;

		public Float getSale__Dollars() {
			return this.Sale__Dollars;
		}

		public Boolean Sale__DollarsIsNullable() {
			return true;
		}

		public Boolean Sale__DollarsIsKey() {
			return false;
		}

		public Integer Sale__DollarsLength() {
			return 254;
		}

		public Integer Sale__DollarsPrecision() {
			return 4;
		}

		public String Sale__DollarsDefault() {

			return null;

		}

		public String Sale__DollarsComment() {

			return "";

		}

		public String Sale__DollarsPattern() {

			return "dd-MM-yyyy";

		}

		public String Sale__DollarsOriginalDbColumnName() {

			return "Sale__Dollars";

		}

		public Float Volume_Sold__Liters;

		public Float getVolume_Sold__Liters() {
			return this.Volume_Sold__Liters;
		}

		public Boolean Volume_Sold__LitersIsNullable() {
			return true;
		}

		public Boolean Volume_Sold__LitersIsKey() {
			return false;
		}

		public Integer Volume_Sold__LitersLength() {
			return 254;
		}

		public Integer Volume_Sold__LitersPrecision() {
			return 4;
		}

		public String Volume_Sold__LitersDefault() {

			return null;

		}

		public String Volume_Sold__LitersComment() {

			return "";

		}

		public String Volume_Sold__LitersPattern() {

			return "dd-MM-yyyy";

		}

		public String Volume_Sold__LitersOriginalDbColumnName() {

			return "Volume_Sold__Liters";

		}

		public Float Volume_Sold__Gallons;

		public Float getVolume_Sold__Gallons() {
			return this.Volume_Sold__Gallons;
		}

		public Boolean Volume_Sold__GallonsIsNullable() {
			return true;
		}

		public Boolean Volume_Sold__GallonsIsKey() {
			return false;
		}

		public Integer Volume_Sold__GallonsLength() {
			return 254;
		}

		public Integer Volume_Sold__GallonsPrecision() {
			return 4;
		}

		public String Volume_Sold__GallonsDefault() {

			return null;

		}

		public String Volume_Sold__GallonsComment() {

			return "";

		}

		public String Volume_Sold__GallonsPattern() {

			return "dd-MM-yyyy";

		}

		public String Volume_Sold__GallonsOriginalDbColumnName() {

			return "Volume_Sold__Gallons";

		}

		public String DI_CreateDate;

		public String getDI_CreateDate() {
			return this.DI_CreateDate;
		}

		public Boolean DI_CreateDateIsNullable() {
			return false;
		}

		public Boolean DI_CreateDateIsKey() {
			return false;
		}

		public Integer DI_CreateDateLength() {
			return 254;
		}

		public Integer DI_CreateDatePrecision() {
			return 4;
		}

		public String DI_CreateDateDefault() {

			return null;

		}

		public String DI_CreateDateComment() {

			return "";

		}

		public String DI_CreateDatePattern() {

			return "";

		}

		public String DI_CreateDateOriginalDbColumnName() {

			return "DI_CreateDate";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.iowa_Liquor_sales_notcleaned_SK;

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final iowa_Liquor_Sales_notcleaned_StagingStruct other = (iowa_Liquor_Sales_notcleaned_StagingStruct) obj;

			if (this.iowa_Liquor_sales_notcleaned_SK != other.iowa_Liquor_sales_notcleaned_SK)
				return false;

			return true;
		}

		public void copyDataTo(iowa_Liquor_Sales_notcleaned_StagingStruct other) {

			other.iowa_Liquor_sales_notcleaned_SK = this.iowa_Liquor_sales_notcleaned_SK;
			other.Invoice_Item_Number = this.Invoice_Item_Number;
			other.Date = this.Date;
			other.Store_Number = this.Store_Number;
			other.Store_Name = this.Store_Name;
			other.Address = this.Address;
			other.City = this.City;
			other.Zip_Code = this.Zip_Code;
			other.Store_Location = this.Store_Location;
			other.County_Number = this.County_Number;
			other.County = this.County;
			other.Category = this.Category;
			other.Category_Name = this.Category_Name;
			other.Vendor_Number = this.Vendor_Number;
			other.Vendor_Name = this.Vendor_Name;
			other.Item_Number = this.Item_Number;
			other.Item_Description = this.Item_Description;
			other.Pack = this.Pack;
			other.Bottle_Volume__ml = this.Bottle_Volume__ml;
			other.State_Bottle_Cost = this.State_Bottle_Cost;
			other.State_Bottle_Retail = this.State_Bottle_Retail;
			other.Bottles_Sold = this.Bottles_Sold;
			other.Sale__Dollars = this.Sale__Dollars;
			other.Volume_Sold__Liters = this.Volume_Sold__Liters;
			other.Volume_Sold__Gallons = this.Volume_Sold__Gallons;
			other.DI_CreateDate = this.DI_CreateDate;

		}

		public void copyKeysDataTo(iowa_Liquor_Sales_notcleaned_StagingStruct other) {

			other.iowa_Liquor_sales_notcleaned_SK = this.iowa_Liquor_sales_notcleaned_SK;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_ASSIGNMENT_Iowa_Liquor_Sales.length) {
					if (length < 1024 && commonByteArray_TALEND_ASSIGNMENT_Iowa_Liquor_Sales.length == 0) {
						commonByteArray_TALEND_ASSIGNMENT_Iowa_Liquor_Sales = new byte[1024];
					} else {
						commonByteArray_TALEND_ASSIGNMENT_Iowa_Liquor_Sales = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TALEND_ASSIGNMENT_Iowa_Liquor_Sales, 0, length);
				strReturn = new String(commonByteArray_TALEND_ASSIGNMENT_Iowa_Liquor_Sales, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_ASSIGNMENT_Iowa_Liquor_Sales.length) {
					if (length < 1024 && commonByteArray_TALEND_ASSIGNMENT_Iowa_Liquor_Sales.length == 0) {
						commonByteArray_TALEND_ASSIGNMENT_Iowa_Liquor_Sales = new byte[1024];
					} else {
						commonByteArray_TALEND_ASSIGNMENT_Iowa_Liquor_Sales = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TALEND_ASSIGNMENT_Iowa_Liquor_Sales, 0, length);
				strReturn = new String(commonByteArray_TALEND_ASSIGNMENT_Iowa_Liquor_Sales, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TALEND_ASSIGNMENT_Iowa_Liquor_Sales) {

				try {

					int length = 0;

					this.iowa_Liquor_sales_notcleaned_SK = dis.readInt();

					this.Invoice_Item_Number = readString(dis);

					this.Date = readString(dis);

					this.Store_Number = readInteger(dis);

					this.Store_Name = readString(dis);

					this.Address = readString(dis);

					this.City = readString(dis);

					this.Zip_Code = readString(dis);

					this.Store_Location = readString(dis);

					this.County_Number = readInteger(dis);

					this.County = readString(dis);

					this.Category = readInteger(dis);

					this.Category_Name = readString(dis);

					this.Vendor_Number = readString(dis);

					this.Vendor_Name = readString(dis);

					this.Item_Number = readString(dis);

					this.Item_Description = readString(dis);

					this.Pack = readInteger(dis);

					this.Bottle_Volume__ml = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.State_Bottle_Cost = null;
					} else {
						this.State_Bottle_Cost = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.State_Bottle_Retail = null;
					} else {
						this.State_Bottle_Retail = dis.readFloat();
					}

					this.Bottles_Sold = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Sale__Dollars = null;
					} else {
						this.Sale__Dollars = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Volume_Sold__Liters = null;
					} else {
						this.Volume_Sold__Liters = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Volume_Sold__Gallons = null;
					} else {
						this.Volume_Sold__Gallons = dis.readFloat();
					}

					this.DI_CreateDate = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TALEND_ASSIGNMENT_Iowa_Liquor_Sales) {

				try {

					int length = 0;

					this.iowa_Liquor_sales_notcleaned_SK = dis.readInt();

					this.Invoice_Item_Number = readString(dis);

					this.Date = readString(dis);

					this.Store_Number = readInteger(dis);

					this.Store_Name = readString(dis);

					this.Address = readString(dis);

					this.City = readString(dis);

					this.Zip_Code = readString(dis);

					this.Store_Location = readString(dis);

					this.County_Number = readInteger(dis);

					this.County = readString(dis);

					this.Category = readInteger(dis);

					this.Category_Name = readString(dis);

					this.Vendor_Number = readString(dis);

					this.Vendor_Name = readString(dis);

					this.Item_Number = readString(dis);

					this.Item_Description = readString(dis);

					this.Pack = readInteger(dis);

					this.Bottle_Volume__ml = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.State_Bottle_Cost = null;
					} else {
						this.State_Bottle_Cost = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.State_Bottle_Retail = null;
					} else {
						this.State_Bottle_Retail = dis.readFloat();
					}

					this.Bottles_Sold = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Sale__Dollars = null;
					} else {
						this.Sale__Dollars = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Volume_Sold__Liters = null;
					} else {
						this.Volume_Sold__Liters = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Volume_Sold__Gallons = null;
					} else {
						this.Volume_Sold__Gallons = dis.readFloat();
					}

					this.DI_CreateDate = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.iowa_Liquor_sales_notcleaned_SK);

				// String

				writeString(this.Invoice_Item_Number, dos);

				// String

				writeString(this.Date, dos);

				// Integer

				writeInteger(this.Store_Number, dos);

				// String

				writeString(this.Store_Name, dos);

				// String

				writeString(this.Address, dos);

				// String

				writeString(this.City, dos);

				// String

				writeString(this.Zip_Code, dos);

				// String

				writeString(this.Store_Location, dos);

				// Integer

				writeInteger(this.County_Number, dos);

				// String

				writeString(this.County, dos);

				// Integer

				writeInteger(this.Category, dos);

				// String

				writeString(this.Category_Name, dos);

				// String

				writeString(this.Vendor_Number, dos);

				// String

				writeString(this.Vendor_Name, dos);

				// String

				writeString(this.Item_Number, dos);

				// String

				writeString(this.Item_Description, dos);

				// Integer

				writeInteger(this.Pack, dos);

				// Integer

				writeInteger(this.Bottle_Volume__ml, dos);

				// Float

				if (this.State_Bottle_Cost == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.State_Bottle_Cost);
				}

				// Float

				if (this.State_Bottle_Retail == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.State_Bottle_Retail);
				}

				// Integer

				writeInteger(this.Bottles_Sold, dos);

				// Float

				if (this.Sale__Dollars == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Sale__Dollars);
				}

				// Float

				if (this.Volume_Sold__Liters == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Volume_Sold__Liters);
				}

				// Float

				if (this.Volume_Sold__Gallons == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Volume_Sold__Gallons);
				}

				// String

				writeString(this.DI_CreateDate, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.iowa_Liquor_sales_notcleaned_SK);

				// String

				writeString(this.Invoice_Item_Number, dos);

				// String

				writeString(this.Date, dos);

				// Integer

				writeInteger(this.Store_Number, dos);

				// String

				writeString(this.Store_Name, dos);

				// String

				writeString(this.Address, dos);

				// String

				writeString(this.City, dos);

				// String

				writeString(this.Zip_Code, dos);

				// String

				writeString(this.Store_Location, dos);

				// Integer

				writeInteger(this.County_Number, dos);

				// String

				writeString(this.County, dos);

				// Integer

				writeInteger(this.Category, dos);

				// String

				writeString(this.Category_Name, dos);

				// String

				writeString(this.Vendor_Number, dos);

				// String

				writeString(this.Vendor_Name, dos);

				// String

				writeString(this.Item_Number, dos);

				// String

				writeString(this.Item_Description, dos);

				// Integer

				writeInteger(this.Pack, dos);

				// Integer

				writeInteger(this.Bottle_Volume__ml, dos);

				// Float

				if (this.State_Bottle_Cost == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.State_Bottle_Cost);
				}

				// Float

				if (this.State_Bottle_Retail == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.State_Bottle_Retail);
				}

				// Integer

				writeInteger(this.Bottles_Sold, dos);

				// Float

				if (this.Sale__Dollars == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Sale__Dollars);
				}

				// Float

				if (this.Volume_Sold__Liters == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Volume_Sold__Liters);
				}

				// Float

				if (this.Volume_Sold__Gallons == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Volume_Sold__Gallons);
				}

				// String

				writeString(this.DI_CreateDate, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("iowa_Liquor_sales_notcleaned_SK=" + String.valueOf(iowa_Liquor_sales_notcleaned_SK));
			sb.append(",Invoice_Item_Number=" + Invoice_Item_Number);
			sb.append(",Date=" + Date);
			sb.append(",Store_Number=" + String.valueOf(Store_Number));
			sb.append(",Store_Name=" + Store_Name);
			sb.append(",Address=" + Address);
			sb.append(",City=" + City);
			sb.append(",Zip_Code=" + Zip_Code);
			sb.append(",Store_Location=" + Store_Location);
			sb.append(",County_Number=" + String.valueOf(County_Number));
			sb.append(",County=" + County);
			sb.append(",Category=" + String.valueOf(Category));
			sb.append(",Category_Name=" + Category_Name);
			sb.append(",Vendor_Number=" + Vendor_Number);
			sb.append(",Vendor_Name=" + Vendor_Name);
			sb.append(",Item_Number=" + Item_Number);
			sb.append(",Item_Description=" + Item_Description);
			sb.append(",Pack=" + String.valueOf(Pack));
			sb.append(",Bottle_Volume__ml=" + String.valueOf(Bottle_Volume__ml));
			sb.append(",State_Bottle_Cost=" + String.valueOf(State_Bottle_Cost));
			sb.append(",State_Bottle_Retail=" + String.valueOf(State_Bottle_Retail));
			sb.append(",Bottles_Sold=" + String.valueOf(Bottles_Sold));
			sb.append(",Sale__Dollars=" + String.valueOf(Sale__Dollars));
			sb.append(",Volume_Sold__Liters=" + String.valueOf(Volume_Sold__Liters));
			sb.append(",Volume_Sold__Gallons=" + String.valueOf(Volume_Sold__Gallons));
			sb.append(",DI_CreateDate=" + DI_CreateDate);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			sb.append(iowa_Liquor_sales_notcleaned_SK);

			sb.append("|");

			if (Invoice_Item_Number == null) {
				sb.append("<null>");
			} else {
				sb.append(Invoice_Item_Number);
			}

			sb.append("|");

			if (Date == null) {
				sb.append("<null>");
			} else {
				sb.append(Date);
			}

			sb.append("|");

			if (Store_Number == null) {
				sb.append("<null>");
			} else {
				sb.append(Store_Number);
			}

			sb.append("|");

			if (Store_Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Store_Name);
			}

			sb.append("|");

			if (Address == null) {
				sb.append("<null>");
			} else {
				sb.append(Address);
			}

			sb.append("|");

			if (City == null) {
				sb.append("<null>");
			} else {
				sb.append(City);
			}

			sb.append("|");

			if (Zip_Code == null) {
				sb.append("<null>");
			} else {
				sb.append(Zip_Code);
			}

			sb.append("|");

			if (Store_Location == null) {
				sb.append("<null>");
			} else {
				sb.append(Store_Location);
			}

			sb.append("|");

			if (County_Number == null) {
				sb.append("<null>");
			} else {
				sb.append(County_Number);
			}

			sb.append("|");

			if (County == null) {
				sb.append("<null>");
			} else {
				sb.append(County);
			}

			sb.append("|");

			if (Category == null) {
				sb.append("<null>");
			} else {
				sb.append(Category);
			}

			sb.append("|");

			if (Category_Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Category_Name);
			}

			sb.append("|");

			if (Vendor_Number == null) {
				sb.append("<null>");
			} else {
				sb.append(Vendor_Number);
			}

			sb.append("|");

			if (Vendor_Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Vendor_Name);
			}

			sb.append("|");

			if (Item_Number == null) {
				sb.append("<null>");
			} else {
				sb.append(Item_Number);
			}

			sb.append("|");

			if (Item_Description == null) {
				sb.append("<null>");
			} else {
				sb.append(Item_Description);
			}

			sb.append("|");

			if (Pack == null) {
				sb.append("<null>");
			} else {
				sb.append(Pack);
			}

			sb.append("|");

			if (Bottle_Volume__ml == null) {
				sb.append("<null>");
			} else {
				sb.append(Bottle_Volume__ml);
			}

			sb.append("|");

			if (State_Bottle_Cost == null) {
				sb.append("<null>");
			} else {
				sb.append(State_Bottle_Cost);
			}

			sb.append("|");

			if (State_Bottle_Retail == null) {
				sb.append("<null>");
			} else {
				sb.append(State_Bottle_Retail);
			}

			sb.append("|");

			if (Bottles_Sold == null) {
				sb.append("<null>");
			} else {
				sb.append(Bottles_Sold);
			}

			sb.append("|");

			if (Sale__Dollars == null) {
				sb.append("<null>");
			} else {
				sb.append(Sale__Dollars);
			}

			sb.append("|");

			if (Volume_Sold__Liters == null) {
				sb.append("<null>");
			} else {
				sb.append(Volume_Sold__Liters);
			}

			sb.append("|");

			if (Volume_Sold__Gallons == null) {
				sb.append("<null>");
			} else {
				sb.append(Volume_Sold__Gallons);
			}

			sb.append("|");

			if (DI_CreateDate == null) {
				sb.append("<null>");
			} else {
				sb.append(DI_CreateDate);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(iowa_Liquor_Sales_notcleaned_StagingStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.iowa_Liquor_sales_notcleaned_SK,
					other.iowa_Liquor_sales_notcleaned_SK);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row12Struct implements routines.system.IPersistableRow<row12Struct> {
		final static byte[] commonByteArrayLock_TALEND_ASSIGNMENT_Iowa_Liquor_Sales = new byte[0];
		static byte[] commonByteArray_TALEND_ASSIGNMENT_Iowa_Liquor_Sales = new byte[0];

		public String Invoice_Item_Number;

		public String getInvoice_Item_Number() {
			return this.Invoice_Item_Number;
		}

		public Boolean Invoice_Item_NumberIsNullable() {
			return true;
		}

		public Boolean Invoice_Item_NumberIsKey() {
			return false;
		}

		public Integer Invoice_Item_NumberLength() {
			return 15;
		}

		public Integer Invoice_Item_NumberPrecision() {
			return 0;
		}

		public String Invoice_Item_NumberDefault() {

			return null;

		}

		public String Invoice_Item_NumberComment() {

			return "";

		}

		public String Invoice_Item_NumberPattern() {

			return "dd-MM-yyyy";

		}

		public String Invoice_Item_NumberOriginalDbColumnName() {

			return "Invoice_Item_Number";

		}

		public String Date;

		public String getDate() {
			return this.Date;
		}

		public Boolean DateIsNullable() {
			return true;
		}

		public Boolean DateIsKey() {
			return false;
		}

		public Integer DateLength() {
			return 10;
		}

		public Integer DatePrecision() {
			return 0;
		}

		public String DateDefault() {

			return null;

		}

		public String DateComment() {

			return "";

		}

		public String DatePattern() {

			return "dd-MM-yyyy";

		}

		public String DateOriginalDbColumnName() {

			return "Date";

		}

		public Integer Store_Number;

		public Integer getStore_Number() {
			return this.Store_Number;
		}

		public Boolean Store_NumberIsNullable() {
			return true;
		}

		public Boolean Store_NumberIsKey() {
			return false;
		}

		public Integer Store_NumberLength() {
			return 4;
		}

		public Integer Store_NumberPrecision() {
			return 0;
		}

		public String Store_NumberDefault() {

			return null;

		}

		public String Store_NumberComment() {

			return "";

		}

		public String Store_NumberPattern() {

			return "dd-MM-yyyy";

		}

		public String Store_NumberOriginalDbColumnName() {

			return "Store_Number";

		}

		public String Store_Name;

		public String getStore_Name() {
			return this.Store_Name;
		}

		public Boolean Store_NameIsNullable() {
			return true;
		}

		public Boolean Store_NameIsKey() {
			return false;
		}

		public Integer Store_NameLength() {
			return 43;
		}

		public Integer Store_NamePrecision() {
			return 0;
		}

		public String Store_NameDefault() {

			return null;

		}

		public String Store_NameComment() {

			return "";

		}

		public String Store_NamePattern() {

			return "dd-MM-yyyy";

		}

		public String Store_NameOriginalDbColumnName() {

			return "Store_Name";

		}

		public String Address;

		public String getAddress() {
			return this.Address;
		}

		public Boolean AddressIsNullable() {
			return true;
		}

		public Boolean AddressIsKey() {
			return false;
		}

		public Integer AddressLength() {
			return 31;
		}

		public Integer AddressPrecision() {
			return 0;
		}

		public String AddressDefault() {

			return null;

		}

		public String AddressComment() {

			return "";

		}

		public String AddressPattern() {

			return "dd-MM-yyyy";

		}

		public String AddressOriginalDbColumnName() {

			return "Address";

		}

		public String City;

		public String getCity() {
			return this.City;
		}

		public Boolean CityIsNullable() {
			return true;
		}

		public Boolean CityIsKey() {
			return false;
		}

		public Integer CityLength() {
			return 17;
		}

		public Integer CityPrecision() {
			return 0;
		}

		public String CityDefault() {

			return null;

		}

		public String CityComment() {

			return "";

		}

		public String CityPattern() {

			return "dd-MM-yyyy";

		}

		public String CityOriginalDbColumnName() {

			return "City";

		}

		public String Zip_Code;

		public String getZip_Code() {
			return this.Zip_Code;
		}

		public Boolean Zip_CodeIsNullable() {
			return true;
		}

		public Boolean Zip_CodeIsKey() {
			return false;
		}

		public Integer Zip_CodeLength() {
			return 5;
		}

		public Integer Zip_CodePrecision() {
			return 0;
		}

		public String Zip_CodeDefault() {

			return null;

		}

		public String Zip_CodeComment() {

			return "";

		}

		public String Zip_CodePattern() {

			return "dd-MM-yyyy";

		}

		public String Zip_CodeOriginalDbColumnName() {

			return "Zip_Code";

		}

		public String Store_Location;

		public String getStore_Location() {
			return this.Store_Location;
		}

		public Boolean Store_LocationIsNullable() {
			return true;
		}

		public Boolean Store_LocationIsKey() {
			return false;
		}

		public Integer Store_LocationLength() {
			return 37;
		}

		public Integer Store_LocationPrecision() {
			return 0;
		}

		public String Store_LocationDefault() {

			return null;

		}

		public String Store_LocationComment() {

			return "";

		}

		public String Store_LocationPattern() {

			return "dd-MM-yyyy";

		}

		public String Store_LocationOriginalDbColumnName() {

			return "Store_Location";

		}

		public Integer County_Number;

		public Integer getCounty_Number() {
			return this.County_Number;
		}

		public Boolean County_NumberIsNullable() {
			return true;
		}

		public Boolean County_NumberIsKey() {
			return false;
		}

		public Integer County_NumberLength() {
			return 2;
		}

		public Integer County_NumberPrecision() {
			return 0;
		}

		public String County_NumberDefault() {

			return null;

		}

		public String County_NumberComment() {

			return "";

		}

		public String County_NumberPattern() {

			return "dd-MM-yyyy";

		}

		public String County_NumberOriginalDbColumnName() {

			return "County_Number";

		}

		public String County;

		public String getCounty() {
			return this.County;
		}

		public Boolean CountyIsNullable() {
			return true;
		}

		public Boolean CountyIsKey() {
			return false;
		}

		public Integer CountyLength() {
			return 10;
		}

		public Integer CountyPrecision() {
			return 0;
		}

		public String CountyDefault() {

			return null;

		}

		public String CountyComment() {

			return "";

		}

		public String CountyPattern() {

			return "dd-MM-yyyy";

		}

		public String CountyOriginalDbColumnName() {

			return "County";

		}

		public Integer Category;

		public Integer getCategory() {
			return this.Category;
		}

		public Boolean CategoryIsNullable() {
			return true;
		}

		public Boolean CategoryIsKey() {
			return false;
		}

		public Integer CategoryLength() {
			return 7;
		}

		public Integer CategoryPrecision() {
			return 0;
		}

		public String CategoryDefault() {

			return null;

		}

		public String CategoryComment() {

			return "";

		}

		public String CategoryPattern() {

			return "dd-MM-yyyy";

		}

		public String CategoryOriginalDbColumnName() {

			return "Category";

		}

		public String Category_Name;

		public String getCategory_Name() {
			return this.Category_Name;
		}

		public Boolean Category_NameIsNullable() {
			return true;
		}

		public Boolean Category_NameIsKey() {
			return false;
		}

		public Integer Category_NameLength() {
			return 30;
		}

		public Integer Category_NamePrecision() {
			return 0;
		}

		public String Category_NameDefault() {

			return null;

		}

		public String Category_NameComment() {

			return "";

		}

		public String Category_NamePattern() {

			return "dd-MM-yyyy";

		}

		public String Category_NameOriginalDbColumnName() {

			return "Category_Name";

		}

		public String Vendor_Number;

		public String getVendor_Number() {
			return this.Vendor_Number;
		}

		public Boolean Vendor_NumberIsNullable() {
			return true;
		}

		public Boolean Vendor_NumberIsKey() {
			return false;
		}

		public Integer Vendor_NumberLength() {
			return 3;
		}

		public Integer Vendor_NumberPrecision() {
			return 0;
		}

		public String Vendor_NumberDefault() {

			return null;

		}

		public String Vendor_NumberComment() {

			return "";

		}

		public String Vendor_NumberPattern() {

			return "dd-MM-yyyy";

		}

		public String Vendor_NumberOriginalDbColumnName() {

			return "Vendor_Number";

		}

		public String Vendor_Name;

		public String getVendor_Name() {
			return this.Vendor_Name;
		}

		public Boolean Vendor_NameIsNullable() {
			return true;
		}

		public Boolean Vendor_NameIsKey() {
			return false;
		}

		public Integer Vendor_NameLength() {
			return 29;
		}

		public Integer Vendor_NamePrecision() {
			return 0;
		}

		public String Vendor_NameDefault() {

			return null;

		}

		public String Vendor_NameComment() {

			return "";

		}

		public String Vendor_NamePattern() {

			return "dd-MM-yyyy";

		}

		public String Vendor_NameOriginalDbColumnName() {

			return "Vendor_Name";

		}

		public String Item_Number;

		public String getItem_Number() {
			return this.Item_Number;
		}

		public Boolean Item_NumberIsNullable() {
			return true;
		}

		public Boolean Item_NumberIsKey() {
			return false;
		}

		public Integer Item_NumberLength() {
			return 5;
		}

		public Integer Item_NumberPrecision() {
			return 0;
		}

		public String Item_NumberDefault() {

			return null;

		}

		public String Item_NumberComment() {

			return "";

		}

		public String Item_NumberPattern() {

			return "dd-MM-yyyy";

		}

		public String Item_NumberOriginalDbColumnName() {

			return "Item_Number";

		}

		public String Item_Description;

		public String getItem_Description() {
			return this.Item_Description;
		}

		public Boolean Item_DescriptionIsNullable() {
			return true;
		}

		public Boolean Item_DescriptionIsKey() {
			return false;
		}

		public Integer Item_DescriptionLength() {
			return 32;
		}

		public Integer Item_DescriptionPrecision() {
			return 0;
		}

		public String Item_DescriptionDefault() {

			return null;

		}

		public String Item_DescriptionComment() {

			return "";

		}

		public String Item_DescriptionPattern() {

			return "dd-MM-yyyy";

		}

		public String Item_DescriptionOriginalDbColumnName() {

			return "Item_Description";

		}

		public Integer Pack;

		public Integer getPack() {
			return this.Pack;
		}

		public Boolean PackIsNullable() {
			return true;
		}

		public Boolean PackIsKey() {
			return false;
		}

		public Integer PackLength() {
			return 2;
		}

		public Integer PackPrecision() {
			return 0;
		}

		public String PackDefault() {

			return null;

		}

		public String PackComment() {

			return "";

		}

		public String PackPattern() {

			return "dd-MM-yyyy";

		}

		public String PackOriginalDbColumnName() {

			return "Pack";

		}

		public Integer Bottle_Volume__ml;

		public Integer getBottle_Volume__ml() {
			return this.Bottle_Volume__ml;
		}

		public Boolean Bottle_Volume__mlIsNullable() {
			return true;
		}

		public Boolean Bottle_Volume__mlIsKey() {
			return false;
		}

		public Integer Bottle_Volume__mlLength() {
			return 4;
		}

		public Integer Bottle_Volume__mlPrecision() {
			return 0;
		}

		public String Bottle_Volume__mlDefault() {

			return null;

		}

		public String Bottle_Volume__mlComment() {

			return "";

		}

		public String Bottle_Volume__mlPattern() {

			return "dd-MM-yyyy";

		}

		public String Bottle_Volume__mlOriginalDbColumnName() {

			return "Bottle_Volume__ml";

		}

		public Float State_Bottle_Cost;

		public Float getState_Bottle_Cost() {
			return this.State_Bottle_Cost;
		}

		public Boolean State_Bottle_CostIsNullable() {
			return true;
		}

		public Boolean State_Bottle_CostIsKey() {
			return false;
		}

		public Integer State_Bottle_CostLength() {
			return 5;
		}

		public Integer State_Bottle_CostPrecision() {
			return 4;
		}

		public String State_Bottle_CostDefault() {

			return null;

		}

		public String State_Bottle_CostComment() {

			return "";

		}

		public String State_Bottle_CostPattern() {

			return "dd-MM-yyyy";

		}

		public String State_Bottle_CostOriginalDbColumnName() {

			return "State_Bottle_Cost";

		}

		public Float State_Bottle_Retail;

		public Float getState_Bottle_Retail() {
			return this.State_Bottle_Retail;
		}

		public Boolean State_Bottle_RetailIsNullable() {
			return true;
		}

		public Boolean State_Bottle_RetailIsKey() {
			return false;
		}

		public Integer State_Bottle_RetailLength() {
			return 5;
		}

		public Integer State_Bottle_RetailPrecision() {
			return 3;
		}

		public String State_Bottle_RetailDefault() {

			return null;

		}

		public String State_Bottle_RetailComment() {

			return "";

		}

		public String State_Bottle_RetailPattern() {

			return "dd-MM-yyyy";

		}

		public String State_Bottle_RetailOriginalDbColumnName() {

			return "State_Bottle_Retail";

		}

		public Integer Bottles_Sold;

		public Integer getBottles_Sold() {
			return this.Bottles_Sold;
		}

		public Boolean Bottles_SoldIsNullable() {
			return true;
		}

		public Boolean Bottles_SoldIsKey() {
			return false;
		}

		public Integer Bottles_SoldLength() {
			return 2;
		}

		public Integer Bottles_SoldPrecision() {
			return 0;
		}

		public String Bottles_SoldDefault() {

			return null;

		}

		public String Bottles_SoldComment() {

			return "";

		}

		public String Bottles_SoldPattern() {

			return "dd-MM-yyyy";

		}

		public String Bottles_SoldOriginalDbColumnName() {

			return "Bottles_Sold";

		}

		public Float Sale__Dollars;

		public Float getSale__Dollars() {
			return this.Sale__Dollars;
		}

		public Boolean Sale__DollarsIsNullable() {
			return true;
		}

		public Boolean Sale__DollarsIsKey() {
			return false;
		}

		public Integer Sale__DollarsLength() {
			return 7;
		}

		public Integer Sale__DollarsPrecision() {
			return 4;
		}

		public String Sale__DollarsDefault() {

			return null;

		}

		public String Sale__DollarsComment() {

			return "";

		}

		public String Sale__DollarsPattern() {

			return "dd-MM-yyyy";

		}

		public String Sale__DollarsOriginalDbColumnName() {

			return "Sale__Dollars";

		}

		public Float Volume_Sold__Liters;

		public Float getVolume_Sold__Liters() {
			return this.Volume_Sold__Liters;
		}

		public Boolean Volume_Sold__LitersIsNullable() {
			return true;
		}

		public Boolean Volume_Sold__LitersIsKey() {
			return false;
		}

		public Integer Volume_Sold__LitersLength() {
			return 5;
		}

		public Integer Volume_Sold__LitersPrecision() {
			return 4;
		}

		public String Volume_Sold__LitersDefault() {

			return null;

		}

		public String Volume_Sold__LitersComment() {

			return "";

		}

		public String Volume_Sold__LitersPattern() {

			return "dd-MM-yyyy";

		}

		public String Volume_Sold__LitersOriginalDbColumnName() {

			return "Volume_Sold__Liters";

		}

		public Float Volume_Sold__Gallons;

		public Float getVolume_Sold__Gallons() {
			return this.Volume_Sold__Gallons;
		}

		public Boolean Volume_Sold__GallonsIsNullable() {
			return true;
		}

		public Boolean Volume_Sold__GallonsIsKey() {
			return false;
		}

		public Integer Volume_Sold__GallonsLength() {
			return 5;
		}

		public Integer Volume_Sold__GallonsPrecision() {
			return 4;
		}

		public String Volume_Sold__GallonsDefault() {

			return null;

		}

		public String Volume_Sold__GallonsComment() {

			return "";

		}

		public String Volume_Sold__GallonsPattern() {

			return "dd-MM-yyyy";

		}

		public String Volume_Sold__GallonsOriginalDbColumnName() {

			return "Volume_Sold__Gallons";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_ASSIGNMENT_Iowa_Liquor_Sales.length) {
					if (length < 1024 && commonByteArray_TALEND_ASSIGNMENT_Iowa_Liquor_Sales.length == 0) {
						commonByteArray_TALEND_ASSIGNMENT_Iowa_Liquor_Sales = new byte[1024];
					} else {
						commonByteArray_TALEND_ASSIGNMENT_Iowa_Liquor_Sales = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TALEND_ASSIGNMENT_Iowa_Liquor_Sales, 0, length);
				strReturn = new String(commonByteArray_TALEND_ASSIGNMENT_Iowa_Liquor_Sales, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_ASSIGNMENT_Iowa_Liquor_Sales.length) {
					if (length < 1024 && commonByteArray_TALEND_ASSIGNMENT_Iowa_Liquor_Sales.length == 0) {
						commonByteArray_TALEND_ASSIGNMENT_Iowa_Liquor_Sales = new byte[1024];
					} else {
						commonByteArray_TALEND_ASSIGNMENT_Iowa_Liquor_Sales = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TALEND_ASSIGNMENT_Iowa_Liquor_Sales, 0, length);
				strReturn = new String(commonByteArray_TALEND_ASSIGNMENT_Iowa_Liquor_Sales, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TALEND_ASSIGNMENT_Iowa_Liquor_Sales) {

				try {

					int length = 0;

					this.Invoice_Item_Number = readString(dis);

					this.Date = readString(dis);

					this.Store_Number = readInteger(dis);

					this.Store_Name = readString(dis);

					this.Address = readString(dis);

					this.City = readString(dis);

					this.Zip_Code = readString(dis);

					this.Store_Location = readString(dis);

					this.County_Number = readInteger(dis);

					this.County = readString(dis);

					this.Category = readInteger(dis);

					this.Category_Name = readString(dis);

					this.Vendor_Number = readString(dis);

					this.Vendor_Name = readString(dis);

					this.Item_Number = readString(dis);

					this.Item_Description = readString(dis);

					this.Pack = readInteger(dis);

					this.Bottle_Volume__ml = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.State_Bottle_Cost = null;
					} else {
						this.State_Bottle_Cost = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.State_Bottle_Retail = null;
					} else {
						this.State_Bottle_Retail = dis.readFloat();
					}

					this.Bottles_Sold = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Sale__Dollars = null;
					} else {
						this.Sale__Dollars = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Volume_Sold__Liters = null;
					} else {
						this.Volume_Sold__Liters = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Volume_Sold__Gallons = null;
					} else {
						this.Volume_Sold__Gallons = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TALEND_ASSIGNMENT_Iowa_Liquor_Sales) {

				try {

					int length = 0;

					this.Invoice_Item_Number = readString(dis);

					this.Date = readString(dis);

					this.Store_Number = readInteger(dis);

					this.Store_Name = readString(dis);

					this.Address = readString(dis);

					this.City = readString(dis);

					this.Zip_Code = readString(dis);

					this.Store_Location = readString(dis);

					this.County_Number = readInteger(dis);

					this.County = readString(dis);

					this.Category = readInteger(dis);

					this.Category_Name = readString(dis);

					this.Vendor_Number = readString(dis);

					this.Vendor_Name = readString(dis);

					this.Item_Number = readString(dis);

					this.Item_Description = readString(dis);

					this.Pack = readInteger(dis);

					this.Bottle_Volume__ml = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.State_Bottle_Cost = null;
					} else {
						this.State_Bottle_Cost = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.State_Bottle_Retail = null;
					} else {
						this.State_Bottle_Retail = dis.readFloat();
					}

					this.Bottles_Sold = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Sale__Dollars = null;
					} else {
						this.Sale__Dollars = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Volume_Sold__Liters = null;
					} else {
						this.Volume_Sold__Liters = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Volume_Sold__Gallons = null;
					} else {
						this.Volume_Sold__Gallons = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Invoice_Item_Number, dos);

				// String

				writeString(this.Date, dos);

				// Integer

				writeInteger(this.Store_Number, dos);

				// String

				writeString(this.Store_Name, dos);

				// String

				writeString(this.Address, dos);

				// String

				writeString(this.City, dos);

				// String

				writeString(this.Zip_Code, dos);

				// String

				writeString(this.Store_Location, dos);

				// Integer

				writeInteger(this.County_Number, dos);

				// String

				writeString(this.County, dos);

				// Integer

				writeInteger(this.Category, dos);

				// String

				writeString(this.Category_Name, dos);

				// String

				writeString(this.Vendor_Number, dos);

				// String

				writeString(this.Vendor_Name, dos);

				// String

				writeString(this.Item_Number, dos);

				// String

				writeString(this.Item_Description, dos);

				// Integer

				writeInteger(this.Pack, dos);

				// Integer

				writeInteger(this.Bottle_Volume__ml, dos);

				// Float

				if (this.State_Bottle_Cost == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.State_Bottle_Cost);
				}

				// Float

				if (this.State_Bottle_Retail == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.State_Bottle_Retail);
				}

				// Integer

				writeInteger(this.Bottles_Sold, dos);

				// Float

				if (this.Sale__Dollars == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Sale__Dollars);
				}

				// Float

				if (this.Volume_Sold__Liters == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Volume_Sold__Liters);
				}

				// Float

				if (this.Volume_Sold__Gallons == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Volume_Sold__Gallons);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Invoice_Item_Number, dos);

				// String

				writeString(this.Date, dos);

				// Integer

				writeInteger(this.Store_Number, dos);

				// String

				writeString(this.Store_Name, dos);

				// String

				writeString(this.Address, dos);

				// String

				writeString(this.City, dos);

				// String

				writeString(this.Zip_Code, dos);

				// String

				writeString(this.Store_Location, dos);

				// Integer

				writeInteger(this.County_Number, dos);

				// String

				writeString(this.County, dos);

				// Integer

				writeInteger(this.Category, dos);

				// String

				writeString(this.Category_Name, dos);

				// String

				writeString(this.Vendor_Number, dos);

				// String

				writeString(this.Vendor_Name, dos);

				// String

				writeString(this.Item_Number, dos);

				// String

				writeString(this.Item_Description, dos);

				// Integer

				writeInteger(this.Pack, dos);

				// Integer

				writeInteger(this.Bottle_Volume__ml, dos);

				// Float

				if (this.State_Bottle_Cost == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.State_Bottle_Cost);
				}

				// Float

				if (this.State_Bottle_Retail == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.State_Bottle_Retail);
				}

				// Integer

				writeInteger(this.Bottles_Sold, dos);

				// Float

				if (this.Sale__Dollars == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Sale__Dollars);
				}

				// Float

				if (this.Volume_Sold__Liters == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Volume_Sold__Liters);
				}

				// Float

				if (this.Volume_Sold__Gallons == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Volume_Sold__Gallons);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Invoice_Item_Number=" + Invoice_Item_Number);
			sb.append(",Date=" + Date);
			sb.append(",Store_Number=" + String.valueOf(Store_Number));
			sb.append(",Store_Name=" + Store_Name);
			sb.append(",Address=" + Address);
			sb.append(",City=" + City);
			sb.append(",Zip_Code=" + Zip_Code);
			sb.append(",Store_Location=" + Store_Location);
			sb.append(",County_Number=" + String.valueOf(County_Number));
			sb.append(",County=" + County);
			sb.append(",Category=" + String.valueOf(Category));
			sb.append(",Category_Name=" + Category_Name);
			sb.append(",Vendor_Number=" + Vendor_Number);
			sb.append(",Vendor_Name=" + Vendor_Name);
			sb.append(",Item_Number=" + Item_Number);
			sb.append(",Item_Description=" + Item_Description);
			sb.append(",Pack=" + String.valueOf(Pack));
			sb.append(",Bottle_Volume__ml=" + String.valueOf(Bottle_Volume__ml));
			sb.append(",State_Bottle_Cost=" + String.valueOf(State_Bottle_Cost));
			sb.append(",State_Bottle_Retail=" + String.valueOf(State_Bottle_Retail));
			sb.append(",Bottles_Sold=" + String.valueOf(Bottles_Sold));
			sb.append(",Sale__Dollars=" + String.valueOf(Sale__Dollars));
			sb.append(",Volume_Sold__Liters=" + String.valueOf(Volume_Sold__Liters));
			sb.append(",Volume_Sold__Gallons=" + String.valueOf(Volume_Sold__Gallons));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (Invoice_Item_Number == null) {
				sb.append("<null>");
			} else {
				sb.append(Invoice_Item_Number);
			}

			sb.append("|");

			if (Date == null) {
				sb.append("<null>");
			} else {
				sb.append(Date);
			}

			sb.append("|");

			if (Store_Number == null) {
				sb.append("<null>");
			} else {
				sb.append(Store_Number);
			}

			sb.append("|");

			if (Store_Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Store_Name);
			}

			sb.append("|");

			if (Address == null) {
				sb.append("<null>");
			} else {
				sb.append(Address);
			}

			sb.append("|");

			if (City == null) {
				sb.append("<null>");
			} else {
				sb.append(City);
			}

			sb.append("|");

			if (Zip_Code == null) {
				sb.append("<null>");
			} else {
				sb.append(Zip_Code);
			}

			sb.append("|");

			if (Store_Location == null) {
				sb.append("<null>");
			} else {
				sb.append(Store_Location);
			}

			sb.append("|");

			if (County_Number == null) {
				sb.append("<null>");
			} else {
				sb.append(County_Number);
			}

			sb.append("|");

			if (County == null) {
				sb.append("<null>");
			} else {
				sb.append(County);
			}

			sb.append("|");

			if (Category == null) {
				sb.append("<null>");
			} else {
				sb.append(Category);
			}

			sb.append("|");

			if (Category_Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Category_Name);
			}

			sb.append("|");

			if (Vendor_Number == null) {
				sb.append("<null>");
			} else {
				sb.append(Vendor_Number);
			}

			sb.append("|");

			if (Vendor_Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Vendor_Name);
			}

			sb.append("|");

			if (Item_Number == null) {
				sb.append("<null>");
			} else {
				sb.append(Item_Number);
			}

			sb.append("|");

			if (Item_Description == null) {
				sb.append("<null>");
			} else {
				sb.append(Item_Description);
			}

			sb.append("|");

			if (Pack == null) {
				sb.append("<null>");
			} else {
				sb.append(Pack);
			}

			sb.append("|");

			if (Bottle_Volume__ml == null) {
				sb.append("<null>");
			} else {
				sb.append(Bottle_Volume__ml);
			}

			sb.append("|");

			if (State_Bottle_Cost == null) {
				sb.append("<null>");
			} else {
				sb.append(State_Bottle_Cost);
			}

			sb.append("|");

			if (State_Bottle_Retail == null) {
				sb.append("<null>");
			} else {
				sb.append(State_Bottle_Retail);
			}

			sb.append("|");

			if (Bottles_Sold == null) {
				sb.append("<null>");
			} else {
				sb.append(Bottles_Sold);
			}

			sb.append("|");

			if (Sale__Dollars == null) {
				sb.append("<null>");
			} else {
				sb.append(Sale__Dollars);
			}

			sb.append("|");

			if (Volume_Sold__Liters == null) {
				sb.append("<null>");
			} else {
				sb.append(Volume_Sold__Liters);
			}

			sb.append("|");

			if (Volume_Sold__Gallons == null) {
				sb.append("<null>");
			} else {
				sb.append(Volume_Sold__Gallons);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row12Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_10Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_10_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		org.slf4j.MDC.put("_subJobName", "tFileInputDelimited_10");
		org.slf4j.MDC.put("_subJobPid", TalendString.getAsciiRandomString(6));

		String iterateId = "";

		String currentComponent = "";
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row12Struct row12 = new row12Struct();
				iowa_Liquor_Sales_notcleaned_StagingStruct iowa_Liquor_Sales_notcleaned_Staging = new iowa_Liquor_Sales_notcleaned_StagingStruct();

				/**
				 * [tDBOutput_10 begin ] start
				 */

				ok_Hash.put("tDBOutput_10", false);
				start_Hash.put("tDBOutput_10", System.currentTimeMillis());

				currentComponent = "tDBOutput_10";

				cLabel = "Iowa";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0,
						"iowa_Liquor_Sales_notcleaned_Staging");

				int tos_count_tDBOutput_10 = 0;

				if (log.isDebugEnabled())
					log.debug("tDBOutput_10 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tDBOutput_10 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tDBOutput_10 = new StringBuilder();
							log4jParamters_tDBOutput_10.append("Parameters:");
							log4jParamters_tDBOutput_10.append("USE_EXISTING_CONNECTION" + " = " + "false");
							log4jParamters_tDBOutput_10.append(" | ");
							log4jParamters_tDBOutput_10.append("DRIVER" + " = " + "MSSQL_PROP");
							log4jParamters_tDBOutput_10.append(" | ");
							log4jParamters_tDBOutput_10.append("HOST" + " = " + "\"10.110.243.138\"");
							log4jParamters_tDBOutput_10.append(" | ");
							log4jParamters_tDBOutput_10.append("PORT" + " = " + "\"1433\"");
							log4jParamters_tDBOutput_10.append(" | ");
							log4jParamters_tDBOutput_10.append("DB_SCHEMA" + " = " + "\"\"");
							log4jParamters_tDBOutput_10.append(" | ");
							log4jParamters_tDBOutput_10.append("DBNAME" + " = " + "\"Iowa\"");
							log4jParamters_tDBOutput_10.append(" | ");
							log4jParamters_tDBOutput_10.append("USER" + " = " + "\"SA\"");
							log4jParamters_tDBOutput_10.append(" | ");
							log4jParamters_tDBOutput_10.append("PASS" + " = " + String.valueOf(
									"enc:routine.encryption.key.v1:qsRA8Dt0dsnDZqFRjV0kdx7BDVpOhq4JyA4cVnPR2qWXtdgOnep+g3GQ")
									.substring(0, 4) + "...");
							log4jParamters_tDBOutput_10.append(" | ");
							log4jParamters_tDBOutput_10
									.append("TABLE" + " = " + "\"stg_iowa_liquor_sales_notcleaned\"");
							log4jParamters_tDBOutput_10.append(" | ");
							log4jParamters_tDBOutput_10.append("TABLE_ACTION" + " = " + "DROP_IF_EXISTS_AND_CREATE");
							log4jParamters_tDBOutput_10.append(" | ");
							log4jParamters_tDBOutput_10.append("IDENTITY_INSERT" + " = " + "false");
							log4jParamters_tDBOutput_10.append(" | ");
							log4jParamters_tDBOutput_10.append("DATA_ACTION" + " = " + "INSERT");
							log4jParamters_tDBOutput_10.append(" | ");
							log4jParamters_tDBOutput_10.append("SPECIFY_IDENTITY_FIELD" + " = " + "false");
							log4jParamters_tDBOutput_10.append(" | ");
							log4jParamters_tDBOutput_10.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
							log4jParamters_tDBOutput_10.append(" | ");
							log4jParamters_tDBOutput_10.append("DIE_ON_ERROR" + " = " + "false");
							log4jParamters_tDBOutput_10.append(" | ");
							log4jParamters_tDBOutput_10.append("PROPERTIES" + " = " + "\"\"");
							log4jParamters_tDBOutput_10.append(" | ");
							log4jParamters_tDBOutput_10.append("ACTIVE_DIR_AUTH" + " = " + "false");
							log4jParamters_tDBOutput_10.append(" | ");
							log4jParamters_tDBOutput_10.append("COMMIT_EVERY" + " = " + "10000");
							log4jParamters_tDBOutput_10.append(" | ");
							log4jParamters_tDBOutput_10.append("ADD_COLS" + " = " + "[]");
							log4jParamters_tDBOutput_10.append(" | ");
							log4jParamters_tDBOutput_10.append("USE_FIELD_OPTIONS" + " = " + "false");
							log4jParamters_tDBOutput_10.append(" | ");
							log4jParamters_tDBOutput_10.append("IGNORE_DATE_OUTOF_RANGE" + " = " + "false");
							log4jParamters_tDBOutput_10.append(" | ");
							log4jParamters_tDBOutput_10.append("ENABLE_DEBUG_MODE" + " = " + "false");
							log4jParamters_tDBOutput_10.append(" | ");
							log4jParamters_tDBOutput_10.append("SUPPORT_NULL_WHERE" + " = " + "false");
							log4jParamters_tDBOutput_10.append(" | ");
							log4jParamters_tDBOutput_10.append("USE_BATCH_SIZE" + " = " + "true");
							log4jParamters_tDBOutput_10.append(" | ");
							log4jParamters_tDBOutput_10.append("BATCH_SIZE" + " = " + "10000");
							log4jParamters_tDBOutput_10.append(" | ");
							log4jParamters_tDBOutput_10.append("SET_QUERY_TIMEOUT" + " = " + "false");
							log4jParamters_tDBOutput_10.append(" | ");
							log4jParamters_tDBOutput_10.append("UNIFIED_COMPONENTS" + " = " + "tMSSqlOutput");
							log4jParamters_tDBOutput_10.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tDBOutput_10 - " + (log4jParamters_tDBOutput_10));
						}
					}
					new BytesLimit65535_tDBOutput_10().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tDBOutput_10", "Iowa", "tMSSqlOutput");
					talendJobLogProcess(globalMap);
				}

				int nb_line_tDBOutput_10 = 0;
				int nb_line_update_tDBOutput_10 = 0;
				int nb_line_inserted_tDBOutput_10 = 0;
				int nb_line_deleted_tDBOutput_10 = 0;
				int nb_line_rejected_tDBOutput_10 = 0;

				int deletedCount_tDBOutput_10 = 0;
				int updatedCount_tDBOutput_10 = 0;
				int insertedCount_tDBOutput_10 = 0;
				int rowsToCommitCount_tDBOutput_10 = 0;
				int rejectedCount_tDBOutput_10 = 0;
				String dbschema_tDBOutput_10 = null;
				String tableName_tDBOutput_10 = null;
				boolean whetherReject_tDBOutput_10 = false;

				java.util.Calendar calendar_tDBOutput_10 = java.util.Calendar.getInstance();
				long year1_tDBOutput_10 = TalendDate.parseDate("yyyy-MM-dd", "0001-01-01").getTime();
				long year2_tDBOutput_10 = TalendDate.parseDate("yyyy-MM-dd", "1753-01-01").getTime();
				long year10000_tDBOutput_10 = TalendDate.parseDate("yyyy-MM-dd HH:mm:ss", "9999-12-31 24:00:00")
						.getTime();
				long date_tDBOutput_10;

				java.util.Calendar calendar_datetimeoffset_tDBOutput_10 = java.util.Calendar
						.getInstance(java.util.TimeZone.getTimeZone("UTC"));

				java.sql.Connection conn_tDBOutput_10 = null;
				String dbUser_tDBOutput_10 = null;
				dbschema_tDBOutput_10 = "";
				String driverClass_tDBOutput_10 = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

				if (log.isDebugEnabled())
					log.debug("tDBOutput_10 - " + ("Driver ClassName: ") + (driverClass_tDBOutput_10) + ("."));
				java.lang.Class.forName(driverClass_tDBOutput_10);
				String port_tDBOutput_10 = "1433";
				String dbname_tDBOutput_10 = "Iowa";
				String url_tDBOutput_10 = "jdbc:sqlserver://" + "10.110.243.138";
				if (!"".equals(port_tDBOutput_10)) {
					url_tDBOutput_10 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBOutput_10)) {
					url_tDBOutput_10 += ";databaseName=" + "Iowa";

				}
				url_tDBOutput_10 += ";appName=" + projectName + ";" + "";
				dbUser_tDBOutput_10 = "SA";

				final String decryptedPassword_tDBOutput_10 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:OJIbe8q0o3bIlaC57hBx7PE9cDtNP7/iZ2ckygTjsNEGPj8vHZuJ7LY8");

				String dbPwd_tDBOutput_10 = decryptedPassword_tDBOutput_10;
				if (log.isDebugEnabled())
					log.debug("tDBOutput_10 - " + ("Connection attempts to '") + (url_tDBOutput_10)
							+ ("' with the username '") + (dbUser_tDBOutput_10) + ("'."));
				conn_tDBOutput_10 = java.sql.DriverManager.getConnection(url_tDBOutput_10, dbUser_tDBOutput_10,
						dbPwd_tDBOutput_10);
				if (log.isDebugEnabled())
					log.debug("tDBOutput_10 - " + ("Connection to '") + (url_tDBOutput_10) + ("' has succeeded."));

				resourceMap.put("conn_tDBOutput_10", conn_tDBOutput_10);

				conn_tDBOutput_10.setAutoCommit(false);
				int commitEvery_tDBOutput_10 = 10000;
				int commitCounter_tDBOutput_10 = 0;

				if (log.isDebugEnabled())
					log.debug("tDBOutput_10 - " + ("Connection is set auto commit to '")
							+ (conn_tDBOutput_10.getAutoCommit()) + ("'."));
				int batchSize_tDBOutput_10 = 10000;
				int batchSizeCounter_tDBOutput_10 = 0;

				if (dbschema_tDBOutput_10 == null || dbschema_tDBOutput_10.trim().length() == 0) {
					tableName_tDBOutput_10 = "stg_iowa_liquor_sales_notcleaned";
				} else {
					tableName_tDBOutput_10 = dbschema_tDBOutput_10 + "].[" + "stg_iowa_liquor_sales_notcleaned";
				}
				int count_tDBOutput_10 = 0;

				boolean whetherExist_tDBOutput_10 = false;
				try (java.sql.Statement isExistStmt_tDBOutput_10 = conn_tDBOutput_10.createStatement()) {
					try {

						isExistStmt_tDBOutput_10.execute("SELECT TOP 1 1 FROM [" + tableName_tDBOutput_10 + "]");
						whetherExist_tDBOutput_10 = true;
					} catch (java.lang.Exception e) {
						globalMap.put("tDBOutput_10_ERROR_MESSAGE", e.getMessage());
						whetherExist_tDBOutput_10 = false;
					}
				}
				if (whetherExist_tDBOutput_10) {
					try (java.sql.Statement stmtDrop_tDBOutput_10 = conn_tDBOutput_10.createStatement()) {
						if (log.isDebugEnabled())
							log.debug("tDBOutput_10 - " + ("Dropping") + (" table '")
									+ ("[" + tableName_tDBOutput_10 + "]") + ("'."));
						stmtDrop_tDBOutput_10.execute("DROP TABLE [" + tableName_tDBOutput_10 + "]");
						if (log.isDebugEnabled())
							log.debug("tDBOutput_10 - " + ("Drop") + (" table '") + ("[" + tableName_tDBOutput_10 + "]")
									+ ("' has succeeded."));
					}
				}
				try (java.sql.Statement stmtCreate_tDBOutput_10 = conn_tDBOutput_10.createStatement()) {
					if (log.isDebugEnabled())
						log.debug("tDBOutput_10 - " + ("Creating") + (" table '") + ("[" + tableName_tDBOutput_10 + "]")
								+ ("'."));
					stmtCreate_tDBOutput_10.execute("CREATE TABLE [" + tableName_tDBOutput_10
							+ "]([iowa_Liquor_sales_notcleaned_SK] INT  not null ,[Invoice_Item_Number] VARCHAR(15)  ,[Date] VARCHAR(10)  ,[Store_Number] INT ,[Store_Name] VARCHAR(254)  ,[Address] VARCHAR(254)  ,[City] VARCHAR(100)  ,[Zip_Code] VARCHAR(254)  ,[Store_Location] VARCHAR(254)  ,[County_Number] INT ,[County] VARCHAR(100)  ,[Category] INT ,[Category_Name] VARCHAR(254)  ,[Vendor_Number] VARCHAR(10)  ,[Vendor_Name] VARCHAR(254)  ,[Item_Number] VARCHAR(100)  ,[Item_Description] VARCHAR(500)  ,[Pack] INT ,[Bottle_Volume__ml] INT ,[State_Bottle_Cost] REAL ,[State_Bottle_Retail] REAL ,[Bottles_Sold] INT ,[Sale__Dollars] REAL ,[Volume_Sold__Liters] REAL ,[Volume_Sold__Gallons] REAL ,[DI_CreateDate] VARCHAR(254)   not null ,primary key([iowa_Liquor_sales_notcleaned_SK]))");
					if (log.isDebugEnabled())
						log.debug("tDBOutput_10 - " + ("Create") + (" table '") + ("[" + tableName_tDBOutput_10 + "]")
								+ ("' has succeeded."));
				}
				java.sql.PreparedStatement pstmt_tDBOutput_10 = null;
				java.sql.PreparedStatement pstmtInsert_tDBOutput_10 = null;
				java.sql.PreparedStatement pstmtUpdate_tDBOutput_10 = null;
				String insert_tDBOutput_10 = "INSERT INTO [" + tableName_tDBOutput_10
						+ "] ([iowa_Liquor_sales_notcleaned_SK],[Invoice_Item_Number],[Date],[Store_Number],[Store_Name],[Address],[City],[Zip_Code],[Store_Location],[County_Number],[County],[Category],[Category_Name],[Vendor_Number],[Vendor_Name],[Item_Number],[Item_Description],[Pack],[Bottle_Volume__ml],[State_Bottle_Cost],[State_Bottle_Retail],[Bottles_Sold],[Sale__Dollars],[Volume_Sold__Liters],[Volume_Sold__Gallons],[DI_CreateDate]) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstmt_tDBOutput_10 = conn_tDBOutput_10.prepareStatement(insert_tDBOutput_10);
				resourceMap.put("pstmt_tDBOutput_10", pstmt_tDBOutput_10);

				/**
				 * [tDBOutput_10 begin ] stop
				 */

				/**
				 * [tMap_11 begin ] start
				 */

				ok_Hash.put("tMap_11", false);
				start_Hash.put("tMap_11", System.currentTimeMillis());

				currentComponent = "tMap_11";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row12");

				int tos_count_tMap_11 = 0;

				if (log.isDebugEnabled())
					log.debug("tMap_11 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tMap_11 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tMap_11 = new StringBuilder();
							log4jParamters_tMap_11.append("Parameters:");
							log4jParamters_tMap_11.append("LINK_STYLE" + " = " + "AUTO");
							log4jParamters_tMap_11.append(" | ");
							log4jParamters_tMap_11.append("TEMPORARY_DATA_DIRECTORY" + " = " + "");
							log4jParamters_tMap_11.append(" | ");
							log4jParamters_tMap_11.append("ROWS_BUFFER_SIZE" + " = " + "2000000");
							log4jParamters_tMap_11.append(" | ");
							log4jParamters_tMap_11.append("CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL" + " = " + "true");
							log4jParamters_tMap_11.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tMap_11 - " + (log4jParamters_tMap_11));
						}
					}
					new BytesLimit65535_tMap_11().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tMap_11", "tMap_11", "tMap");
					talendJobLogProcess(globalMap);
				}

// ###############################
// # Lookup's keys initialization
				int count_row12_tMap_11 = 0;

// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_11__Struct {
					String DI_CreateDate;
					int iowa_Liquor_sales_notcleaned_SK;
				}
				Var__tMap_11__Struct Var__tMap_11 = new Var__tMap_11__Struct();
// ###############################

// ###############################
// # Outputs initialization
				int count_iowa_Liquor_Sales_notcleaned_Staging_tMap_11 = 0;

				iowa_Liquor_Sales_notcleaned_StagingStruct iowa_Liquor_Sales_notcleaned_Staging_tmp = new iowa_Liquor_Sales_notcleaned_StagingStruct();
// ###############################

				/**
				 * [tMap_11 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_10 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_10", false);
				start_Hash.put("tFileInputDelimited_10", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_10";

				cLabel = "iowa_Liquour_Sales_20221002_notCleaned";

				int tos_count_tFileInputDelimited_10 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileInputDelimited_10 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileInputDelimited_10 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileInputDelimited_10 = new StringBuilder();
							log4jParamters_tFileInputDelimited_10.append("Parameters:");
							log4jParamters_tFileInputDelimited_10.append("FILENAME" + " = "
									+ "\"/Users/nishapatil/Downloads/Iowa_Liquor_Sales_20221002_notCleaned.tsv\"");
							log4jParamters_tFileInputDelimited_10.append(" | ");
							log4jParamters_tFileInputDelimited_10.append("CSV_OPTION" + " = " + "true");
							log4jParamters_tFileInputDelimited_10.append(" | ");
							log4jParamters_tFileInputDelimited_10.append("CSVROWSEPARATOR" + " = " + "\"\\r\"");
							log4jParamters_tFileInputDelimited_10.append(" | ");
							log4jParamters_tFileInputDelimited_10.append("FIELDSEPARATOR" + " = " + "\"\\t\"");
							log4jParamters_tFileInputDelimited_10.append(" | ");
							log4jParamters_tFileInputDelimited_10.append("ESCAPE_CHAR" + " = " + "\"\"\"");
							log4jParamters_tFileInputDelimited_10.append(" | ");
							log4jParamters_tFileInputDelimited_10.append("TEXT_ENCLOSURE" + " = " + "\"\"\"");
							log4jParamters_tFileInputDelimited_10.append(" | ");
							log4jParamters_tFileInputDelimited_10.append("HEADER" + " = " + "1");
							log4jParamters_tFileInputDelimited_10.append(" | ");
							log4jParamters_tFileInputDelimited_10.append("FOOTER" + " = " + "0");
							log4jParamters_tFileInputDelimited_10.append(" | ");
							log4jParamters_tFileInputDelimited_10.append("LIMIT" + " = " + "");
							log4jParamters_tFileInputDelimited_10.append(" | ");
							log4jParamters_tFileInputDelimited_10.append("REMOVE_EMPTY_ROW" + " = " + "false");
							log4jParamters_tFileInputDelimited_10.append(" | ");
							log4jParamters_tFileInputDelimited_10.append("UNCOMPRESS" + " = " + "false");
							log4jParamters_tFileInputDelimited_10.append(" | ");
							log4jParamters_tFileInputDelimited_10.append("DIE_ON_ERROR" + " = " + "false");
							log4jParamters_tFileInputDelimited_10.append(" | ");
							log4jParamters_tFileInputDelimited_10.append("ADVANCED_SEPARATOR" + " = " + "false");
							log4jParamters_tFileInputDelimited_10.append(" | ");
							log4jParamters_tFileInputDelimited_10.append("TRIMALL" + " = " + "false");
							log4jParamters_tFileInputDelimited_10.append(" | ");
							log4jParamters_tFileInputDelimited_10.append("TRIMSELECT" + " = " + "[{TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("Invoice_Item_Number") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("Date") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("Store_Number") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("Store_Name")
									+ "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("Address") + "}, {TRIM="
									+ ("false") + ", SCHEMA_COLUMN=" + ("City") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("Zip_Code") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("Store_Location") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("County_Number") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("County")
									+ "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("Category") + "}, {TRIM="
									+ ("false") + ", SCHEMA_COLUMN=" + ("Category_Name") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("Vendor_Number") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("Vendor_Name") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("Item_Number") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("Item_Description") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("Pack") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("Bottle_Volume__ml") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("State_Bottle_Cost") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("State_Bottle_Retail") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("Bottles_Sold") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("Sale__Dollars") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("Volume_Sold__Liters") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("Volume_Sold__Gallons") + "}]");
							log4jParamters_tFileInputDelimited_10.append(" | ");
							log4jParamters_tFileInputDelimited_10.append("CHECK_FIELDS_NUM" + " = " + "false");
							log4jParamters_tFileInputDelimited_10.append(" | ");
							log4jParamters_tFileInputDelimited_10.append("CHECK_DATE" + " = " + "false");
							log4jParamters_tFileInputDelimited_10.append(" | ");
							log4jParamters_tFileInputDelimited_10.append("ENCODING" + " = " + "\"UTF-8\"");
							log4jParamters_tFileInputDelimited_10.append(" | ");
							log4jParamters_tFileInputDelimited_10.append("ENABLE_DECODE" + " = " + "false");
							log4jParamters_tFileInputDelimited_10.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFileInputDelimited_10 - " + (log4jParamters_tFileInputDelimited_10));
						}
					}
					new BytesLimit65535_tFileInputDelimited_10().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFileInputDelimited_10", "iowa_Liquour_Sales_20221002_notCleaned",
							"tFileInputDelimited");
					talendJobLogProcess(globalMap);
				}

				final routines.system.RowState rowstate_tFileInputDelimited_10 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_10 = 0;
				int footer_tFileInputDelimited_10 = 0;
				int totalLinetFileInputDelimited_10 = 0;
				int limittFileInputDelimited_10 = -1;
				int lastLinetFileInputDelimited_10 = -1;

				char fieldSeparator_tFileInputDelimited_10[] = null;

				// support passing value (property: Field Separator) by 'context.fs' or
				// 'globalMap.get("fs")'.
				if (((String) "\t").length() > 0) {
					fieldSeparator_tFileInputDelimited_10 = ((String) "\t").toCharArray();
				} else {
					throw new IllegalArgumentException("Field Separator must be assigned a char.");
				}

				char rowSeparator_tFileInputDelimited_10[] = null;

				// support passing value (property: Row Separator) by 'context.rs' or
				// 'globalMap.get("rs")'.
				if (((String) "\r").length() > 0) {
					rowSeparator_tFileInputDelimited_10 = ((String) "\r").toCharArray();
				} else {
					throw new IllegalArgumentException("Row Separator must be assigned a char.");
				}

				Object filename_tFileInputDelimited_10 = /** Start field tFileInputDelimited_10:FILENAME */
						"/Users/nishapatil/Downloads/Iowa_Liquor_Sales_20221002_notCleaned.tsv"/**
																								 * End field
																								 * tFileInputDelimited_10:FILENAME
																								 */
				;
				com.talend.csv.CSVReader csvReadertFileInputDelimited_10 = null;

				try {

					String[] rowtFileInputDelimited_10 = null;
					int currentLinetFileInputDelimited_10 = 0;
					int outputLinetFileInputDelimited_10 = 0;
					try {// TD110 begin
						if (filename_tFileInputDelimited_10 instanceof java.io.InputStream) {

							int footer_value_tFileInputDelimited_10 = 0;
							if (footer_value_tFileInputDelimited_10 > 0) {
								throw new java.lang.Exception(
										"When the input source is a stream,footer shouldn't be bigger than 0.");
							}

							csvReadertFileInputDelimited_10 = new com.talend.csv.CSVReader(
									(java.io.InputStream) filename_tFileInputDelimited_10,
									fieldSeparator_tFileInputDelimited_10[0], "UTF-8");
						} else {
							csvReadertFileInputDelimited_10 = new com.talend.csv.CSVReader(
									String.valueOf(filename_tFileInputDelimited_10),
									fieldSeparator_tFileInputDelimited_10[0], "UTF-8");
						}

						csvReadertFileInputDelimited_10.setTrimWhitespace(false);
						if ((rowSeparator_tFileInputDelimited_10[0] != '\n')
								&& (rowSeparator_tFileInputDelimited_10[0] != '\r'))
							csvReadertFileInputDelimited_10.setLineEnd("" + rowSeparator_tFileInputDelimited_10[0]);

						csvReadertFileInputDelimited_10.setQuoteChar('"');

						csvReadertFileInputDelimited_10.setEscapeChar(csvReadertFileInputDelimited_10.getQuoteChar());

						if (footer_tFileInputDelimited_10 > 0) {
							for (totalLinetFileInputDelimited_10 = 0; totalLinetFileInputDelimited_10 < 1; totalLinetFileInputDelimited_10++) {
								csvReadertFileInputDelimited_10.readNext();
							}
							csvReadertFileInputDelimited_10.setSkipEmptyRecords(false);
							while (csvReadertFileInputDelimited_10.readNext()) {

								totalLinetFileInputDelimited_10++;

							}
							int lastLineTemptFileInputDelimited_10 = totalLinetFileInputDelimited_10
									- footer_tFileInputDelimited_10 < 0 ? 0
											: totalLinetFileInputDelimited_10 - footer_tFileInputDelimited_10;
							if (lastLinetFileInputDelimited_10 > 0) {
								lastLinetFileInputDelimited_10 = lastLinetFileInputDelimited_10 < lastLineTemptFileInputDelimited_10
										? lastLinetFileInputDelimited_10
										: lastLineTemptFileInputDelimited_10;
							} else {
								lastLinetFileInputDelimited_10 = lastLineTemptFileInputDelimited_10;
							}

							csvReadertFileInputDelimited_10.close();
							if (filename_tFileInputDelimited_10 instanceof java.io.InputStream) {
								csvReadertFileInputDelimited_10 = new com.talend.csv.CSVReader(
										(java.io.InputStream) filename_tFileInputDelimited_10,
										fieldSeparator_tFileInputDelimited_10[0], "UTF-8");
							} else {
								csvReadertFileInputDelimited_10 = new com.talend.csv.CSVReader(
										String.valueOf(filename_tFileInputDelimited_10),
										fieldSeparator_tFileInputDelimited_10[0], "UTF-8");
							}
							csvReadertFileInputDelimited_10.setTrimWhitespace(false);
							if ((rowSeparator_tFileInputDelimited_10[0] != '\n')
									&& (rowSeparator_tFileInputDelimited_10[0] != '\r'))
								csvReadertFileInputDelimited_10.setLineEnd("" + rowSeparator_tFileInputDelimited_10[0]);

							csvReadertFileInputDelimited_10.setQuoteChar('"');

							csvReadertFileInputDelimited_10
									.setEscapeChar(csvReadertFileInputDelimited_10.getQuoteChar());

						}

						if (limittFileInputDelimited_10 != 0) {
							for (currentLinetFileInputDelimited_10 = 0; currentLinetFileInputDelimited_10 < 1; currentLinetFileInputDelimited_10++) {
								csvReadertFileInputDelimited_10.readNext();
							}
						}
						csvReadertFileInputDelimited_10.setSkipEmptyRecords(false);

					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE", e.getMessage());

						log.error("tFileInputDelimited_10 - " + e.getMessage());

						System.err.println(e.getMessage());

					} // TD110 end

					log.info("tFileInputDelimited_10 - Retrieving records from the datasource.");

					while (limittFileInputDelimited_10 != 0 && csvReadertFileInputDelimited_10 != null
							&& csvReadertFileInputDelimited_10.readNext()) {
						rowstate_tFileInputDelimited_10.reset();

						rowtFileInputDelimited_10 = csvReadertFileInputDelimited_10.getValues();

						currentLinetFileInputDelimited_10++;

						if (lastLinetFileInputDelimited_10 > -1
								&& currentLinetFileInputDelimited_10 > lastLinetFileInputDelimited_10) {
							break;
						}
						outputLinetFileInputDelimited_10++;
						if (limittFileInputDelimited_10 > 0
								&& outputLinetFileInputDelimited_10 > limittFileInputDelimited_10) {
							break;
						}

						row12 = null;

						boolean whetherReject_tFileInputDelimited_10 = false;
						row12 = new row12Struct();
						try {

							char fieldSeparator_tFileInputDelimited_10_ListType[] = null;
							// support passing value (property: Field Separator) by 'context.fs' or
							// 'globalMap.get("fs")'.
							if (((String) "\t").length() > 0) {
								fieldSeparator_tFileInputDelimited_10_ListType = ((String) "\t").toCharArray();
							} else {
								throw new IllegalArgumentException("Field Separator must be assigned a char.");
							}
							if (rowtFileInputDelimited_10.length == 1
									&& ("\015").equals(rowtFileInputDelimited_10[0])) {// empty line when row separator
																						// is '\n'

								row12.Invoice_Item_Number = null;

								row12.Date = null;

								row12.Store_Number = null;

								row12.Store_Name = null;

								row12.Address = null;

								row12.City = null;

								row12.Zip_Code = null;

								row12.Store_Location = null;

								row12.County_Number = null;

								row12.County = null;

								row12.Category = null;

								row12.Category_Name = null;

								row12.Vendor_Number = null;

								row12.Vendor_Name = null;

								row12.Item_Number = null;

								row12.Item_Description = null;

								row12.Pack = null;

								row12.Bottle_Volume__ml = null;

								row12.State_Bottle_Cost = null;

								row12.State_Bottle_Retail = null;

								row12.Bottles_Sold = null;

								row12.Sale__Dollars = null;

								row12.Volume_Sold__Liters = null;

								row12.Volume_Sold__Gallons = null;

							} else {

								int columnIndexWithD_tFileInputDelimited_10 = 0; // Column Index

								columnIndexWithD_tFileInputDelimited_10 = 0;

								if (columnIndexWithD_tFileInputDelimited_10 < rowtFileInputDelimited_10.length) {

									row12.Invoice_Item_Number = rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10];

								} else {

									row12.Invoice_Item_Number = null;

								}

								columnIndexWithD_tFileInputDelimited_10 = 1;

								if (columnIndexWithD_tFileInputDelimited_10 < rowtFileInputDelimited_10.length) {

									row12.Date = rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10];

								} else {

									row12.Date = null;

								}

								columnIndexWithD_tFileInputDelimited_10 = 2;

								if (columnIndexWithD_tFileInputDelimited_10 < rowtFileInputDelimited_10.length) {

									if (rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10]
											.length() > 0) {
										try {

											row12.Store_Number = ParserUtils.parseTo_Integer(
													rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10]);

										} catch (java.lang.Exception ex_tFileInputDelimited_10) {
											globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE",
													ex_tFileInputDelimited_10.getMessage());
											rowstate_tFileInputDelimited_10.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"Store_Number", "row12",
															rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10],
															ex_tFileInputDelimited_10),
													ex_tFileInputDelimited_10));
										}
									} else {

										row12.Store_Number = null;

									}

								} else {

									row12.Store_Number = null;

								}

								columnIndexWithD_tFileInputDelimited_10 = 3;

								if (columnIndexWithD_tFileInputDelimited_10 < rowtFileInputDelimited_10.length) {

									row12.Store_Name = rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10];

								} else {

									row12.Store_Name = null;

								}

								columnIndexWithD_tFileInputDelimited_10 = 4;

								if (columnIndexWithD_tFileInputDelimited_10 < rowtFileInputDelimited_10.length) {

									row12.Address = rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10];

								} else {

									row12.Address = null;

								}

								columnIndexWithD_tFileInputDelimited_10 = 5;

								if (columnIndexWithD_tFileInputDelimited_10 < rowtFileInputDelimited_10.length) {

									row12.City = rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10];

								} else {

									row12.City = null;

								}

								columnIndexWithD_tFileInputDelimited_10 = 6;

								if (columnIndexWithD_tFileInputDelimited_10 < rowtFileInputDelimited_10.length) {

									row12.Zip_Code = rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10];

								} else {

									row12.Zip_Code = null;

								}

								columnIndexWithD_tFileInputDelimited_10 = 7;

								if (columnIndexWithD_tFileInputDelimited_10 < rowtFileInputDelimited_10.length) {

									row12.Store_Location = rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10];

								} else {

									row12.Store_Location = null;

								}

								columnIndexWithD_tFileInputDelimited_10 = 8;

								if (columnIndexWithD_tFileInputDelimited_10 < rowtFileInputDelimited_10.length) {

									if (rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10]
											.length() > 0) {
										try {

											row12.County_Number = ParserUtils.parseTo_Integer(
													rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10]);

										} catch (java.lang.Exception ex_tFileInputDelimited_10) {
											globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE",
													ex_tFileInputDelimited_10.getMessage());
											rowstate_tFileInputDelimited_10.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"County_Number", "row12",
															rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10],
															ex_tFileInputDelimited_10),
													ex_tFileInputDelimited_10));
										}
									} else {

										row12.County_Number = null;

									}

								} else {

									row12.County_Number = null;

								}

								columnIndexWithD_tFileInputDelimited_10 = 9;

								if (columnIndexWithD_tFileInputDelimited_10 < rowtFileInputDelimited_10.length) {

									row12.County = rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10];

								} else {

									row12.County = null;

								}

								columnIndexWithD_tFileInputDelimited_10 = 10;

								if (columnIndexWithD_tFileInputDelimited_10 < rowtFileInputDelimited_10.length) {

									if (rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10]
											.length() > 0) {
										try {

											row12.Category = ParserUtils.parseTo_Integer(
													rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10]);

										} catch (java.lang.Exception ex_tFileInputDelimited_10) {
											globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE",
													ex_tFileInputDelimited_10.getMessage());
											rowstate_tFileInputDelimited_10.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"Category", "row12",
															rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10],
															ex_tFileInputDelimited_10),
													ex_tFileInputDelimited_10));
										}
									} else {

										row12.Category = null;

									}

								} else {

									row12.Category = null;

								}

								columnIndexWithD_tFileInputDelimited_10 = 11;

								if (columnIndexWithD_tFileInputDelimited_10 < rowtFileInputDelimited_10.length) {

									row12.Category_Name = rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10];

								} else {

									row12.Category_Name = null;

								}

								columnIndexWithD_tFileInputDelimited_10 = 12;

								if (columnIndexWithD_tFileInputDelimited_10 < rowtFileInputDelimited_10.length) {

									row12.Vendor_Number = rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10];

								} else {

									row12.Vendor_Number = null;

								}

								columnIndexWithD_tFileInputDelimited_10 = 13;

								if (columnIndexWithD_tFileInputDelimited_10 < rowtFileInputDelimited_10.length) {

									row12.Vendor_Name = rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10];

								} else {

									row12.Vendor_Name = null;

								}

								columnIndexWithD_tFileInputDelimited_10 = 14;

								if (columnIndexWithD_tFileInputDelimited_10 < rowtFileInputDelimited_10.length) {

									row12.Item_Number = rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10];

								} else {

									row12.Item_Number = null;

								}

								columnIndexWithD_tFileInputDelimited_10 = 15;

								if (columnIndexWithD_tFileInputDelimited_10 < rowtFileInputDelimited_10.length) {

									row12.Item_Description = rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10];

								} else {

									row12.Item_Description = null;

								}

								columnIndexWithD_tFileInputDelimited_10 = 16;

								if (columnIndexWithD_tFileInputDelimited_10 < rowtFileInputDelimited_10.length) {

									if (rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10]
											.length() > 0) {
										try {

											row12.Pack = ParserUtils.parseTo_Integer(
													rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10]);

										} catch (java.lang.Exception ex_tFileInputDelimited_10) {
											globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE",
													ex_tFileInputDelimited_10.getMessage());
											rowstate_tFileInputDelimited_10.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"Pack", "row12",
															rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10],
															ex_tFileInputDelimited_10),
													ex_tFileInputDelimited_10));
										}
									} else {

										row12.Pack = null;

									}

								} else {

									row12.Pack = null;

								}

								columnIndexWithD_tFileInputDelimited_10 = 17;

								if (columnIndexWithD_tFileInputDelimited_10 < rowtFileInputDelimited_10.length) {

									if (rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10]
											.length() > 0) {
										try {

											row12.Bottle_Volume__ml = ParserUtils.parseTo_Integer(
													rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10]);

										} catch (java.lang.Exception ex_tFileInputDelimited_10) {
											globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE",
													ex_tFileInputDelimited_10.getMessage());
											rowstate_tFileInputDelimited_10.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"Bottle_Volume__ml", "row12",
															rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10],
															ex_tFileInputDelimited_10),
													ex_tFileInputDelimited_10));
										}
									} else {

										row12.Bottle_Volume__ml = null;

									}

								} else {

									row12.Bottle_Volume__ml = null;

								}

								columnIndexWithD_tFileInputDelimited_10 = 18;

								if (columnIndexWithD_tFileInputDelimited_10 < rowtFileInputDelimited_10.length) {

									if (rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10]
											.length() > 0) {
										try {

											row12.State_Bottle_Cost = ParserUtils.parseTo_Float(
													rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10]);

										} catch (java.lang.Exception ex_tFileInputDelimited_10) {
											globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE",
													ex_tFileInputDelimited_10.getMessage());
											rowstate_tFileInputDelimited_10.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"State_Bottle_Cost", "row12",
															rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10],
															ex_tFileInputDelimited_10),
													ex_tFileInputDelimited_10));
										}
									} else {

										row12.State_Bottle_Cost = null;

									}

								} else {

									row12.State_Bottle_Cost = null;

								}

								columnIndexWithD_tFileInputDelimited_10 = 19;

								if (columnIndexWithD_tFileInputDelimited_10 < rowtFileInputDelimited_10.length) {

									if (rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10]
											.length() > 0) {
										try {

											row12.State_Bottle_Retail = ParserUtils.parseTo_Float(
													rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10]);

										} catch (java.lang.Exception ex_tFileInputDelimited_10) {
											globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE",
													ex_tFileInputDelimited_10.getMessage());
											rowstate_tFileInputDelimited_10.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"State_Bottle_Retail", "row12",
															rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10],
															ex_tFileInputDelimited_10),
													ex_tFileInputDelimited_10));
										}
									} else {

										row12.State_Bottle_Retail = null;

									}

								} else {

									row12.State_Bottle_Retail = null;

								}

								columnIndexWithD_tFileInputDelimited_10 = 20;

								if (columnIndexWithD_tFileInputDelimited_10 < rowtFileInputDelimited_10.length) {

									if (rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10]
											.length() > 0) {
										try {

											row12.Bottles_Sold = ParserUtils.parseTo_Integer(
													rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10]);

										} catch (java.lang.Exception ex_tFileInputDelimited_10) {
											globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE",
													ex_tFileInputDelimited_10.getMessage());
											rowstate_tFileInputDelimited_10.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"Bottles_Sold", "row12",
															rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10],
															ex_tFileInputDelimited_10),
													ex_tFileInputDelimited_10));
										}
									} else {

										row12.Bottles_Sold = null;

									}

								} else {

									row12.Bottles_Sold = null;

								}

								columnIndexWithD_tFileInputDelimited_10 = 21;

								if (columnIndexWithD_tFileInputDelimited_10 < rowtFileInputDelimited_10.length) {

									if (rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10]
											.length() > 0) {
										try {

											row12.Sale__Dollars = ParserUtils.parseTo_Float(
													rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10]);

										} catch (java.lang.Exception ex_tFileInputDelimited_10) {
											globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE",
													ex_tFileInputDelimited_10.getMessage());
											rowstate_tFileInputDelimited_10.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"Sale__Dollars", "row12",
															rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10],
															ex_tFileInputDelimited_10),
													ex_tFileInputDelimited_10));
										}
									} else {

										row12.Sale__Dollars = null;

									}

								} else {

									row12.Sale__Dollars = null;

								}

								columnIndexWithD_tFileInputDelimited_10 = 22;

								if (columnIndexWithD_tFileInputDelimited_10 < rowtFileInputDelimited_10.length) {

									if (rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10]
											.length() > 0) {
										try {

											row12.Volume_Sold__Liters = ParserUtils.parseTo_Float(
													rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10]);

										} catch (java.lang.Exception ex_tFileInputDelimited_10) {
											globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE",
													ex_tFileInputDelimited_10.getMessage());
											rowstate_tFileInputDelimited_10.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"Volume_Sold__Liters", "row12",
															rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10],
															ex_tFileInputDelimited_10),
													ex_tFileInputDelimited_10));
										}
									} else {

										row12.Volume_Sold__Liters = null;

									}

								} else {

									row12.Volume_Sold__Liters = null;

								}

								columnIndexWithD_tFileInputDelimited_10 = 23;

								if (columnIndexWithD_tFileInputDelimited_10 < rowtFileInputDelimited_10.length) {

									if (rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10]
											.length() > 0) {
										try {

											row12.Volume_Sold__Gallons = ParserUtils.parseTo_Float(
													rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10]);

										} catch (java.lang.Exception ex_tFileInputDelimited_10) {
											globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE",
													ex_tFileInputDelimited_10.getMessage());
											rowstate_tFileInputDelimited_10.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"Volume_Sold__Gallons", "row12",
															rowtFileInputDelimited_10[columnIndexWithD_tFileInputDelimited_10],
															ex_tFileInputDelimited_10),
													ex_tFileInputDelimited_10));
										}
									} else {

										row12.Volume_Sold__Gallons = null;

									}

								} else {

									row12.Volume_Sold__Gallons = null;

								}

							}

							if (rowstate_tFileInputDelimited_10.getException() != null) {
								throw rowstate_tFileInputDelimited_10.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_10 = true;

							log.error("tFileInputDelimited_10 - " + e.getMessage());

							System.err.println(e.getMessage());
							row12 = null;

							globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE", e.getMessage());

						}

						log.debug("tFileInputDelimited_10 - Retrieving the record "
								+ (nb_line_tFileInputDelimited_10 + 1) + ".");

						/**
						 * [tFileInputDelimited_10 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_10 main ] start
						 */

						currentComponent = "tFileInputDelimited_10";

						cLabel = "iowa_Liquour_Sales_20221002_notCleaned";

						tos_count_tFileInputDelimited_10++;

						/**
						 * [tFileInputDelimited_10 main ] stop
						 */

						/**
						 * [tFileInputDelimited_10 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_10";

						cLabel = "iowa_Liquour_Sales_20221002_notCleaned";

						/**
						 * [tFileInputDelimited_10 process_data_begin ] stop
						 */
// Start of branch "row12"
						if (row12 != null) {

							/**
							 * [tMap_11 main ] start
							 */

							currentComponent = "tMap_11";

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "row12", "tFileInputDelimited_10", "iowa_Liquour_Sales_20221002_notCleaned",
									"tFileInputDelimited", "tMap_11", "tMap_11", "tMap"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row12 - " + (row12 == null ? "" : row12.toLogString()));
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_11 = false;

							// ###############################
							// # Input tables (lookups)

							boolean rejectedInnerJoin_tMap_11 = false;
							boolean mainRowRejected_tMap_11 = false;
							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_11__Struct Var = Var__tMap_11;
								Var.DI_CreateDate = TalendDate.getDate("CCYY-MM-DD hh:mm:ss");
								Var.iowa_Liquor_sales_notcleaned_SK = Numeric
										.sequence("iowa_Liquor_sales_notcleaned_SK", 1, 1);// ###############################
								// ###############################
								// # Output tables

								iowa_Liquor_Sales_notcleaned_Staging = null;

// # Output table : 'iowa_Liquor_Sales_notcleaned_Staging'
								count_iowa_Liquor_Sales_notcleaned_Staging_tMap_11++;

								iowa_Liquor_Sales_notcleaned_Staging_tmp.iowa_Liquor_sales_notcleaned_SK = Var.iowa_Liquor_sales_notcleaned_SK;
								iowa_Liquor_Sales_notcleaned_Staging_tmp.Invoice_Item_Number = row12.Invoice_Item_Number;
								iowa_Liquor_Sales_notcleaned_Staging_tmp.Date = row12.Date;
								iowa_Liquor_Sales_notcleaned_Staging_tmp.Store_Number = row12.Store_Number;
								iowa_Liquor_Sales_notcleaned_Staging_tmp.Store_Name = row12.Store_Name;
								iowa_Liquor_Sales_notcleaned_Staging_tmp.Address = row12.Address;
								iowa_Liquor_Sales_notcleaned_Staging_tmp.City = row12.City;
								iowa_Liquor_Sales_notcleaned_Staging_tmp.Zip_Code = row12.Zip_Code;
								iowa_Liquor_Sales_notcleaned_Staging_tmp.Store_Location = row12.Store_Location;
								iowa_Liquor_Sales_notcleaned_Staging_tmp.County_Number = row12.County_Number;
								iowa_Liquor_Sales_notcleaned_Staging_tmp.County = row12.County;
								iowa_Liquor_Sales_notcleaned_Staging_tmp.Category = row12.Category;
								iowa_Liquor_Sales_notcleaned_Staging_tmp.Category_Name = row12.Category_Name;
								iowa_Liquor_Sales_notcleaned_Staging_tmp.Vendor_Number = row12.Vendor_Number;
								iowa_Liquor_Sales_notcleaned_Staging_tmp.Vendor_Name = row12.Vendor_Name;
								iowa_Liquor_Sales_notcleaned_Staging_tmp.Item_Number = row12.Item_Number;
								iowa_Liquor_Sales_notcleaned_Staging_tmp.Item_Description = row12.Item_Description;
								iowa_Liquor_Sales_notcleaned_Staging_tmp.Pack = row12.Pack;
								iowa_Liquor_Sales_notcleaned_Staging_tmp.Bottle_Volume__ml = row12.Bottle_Volume__ml;
								iowa_Liquor_Sales_notcleaned_Staging_tmp.State_Bottle_Cost = row12.State_Bottle_Cost;
								iowa_Liquor_Sales_notcleaned_Staging_tmp.State_Bottle_Retail = row12.State_Bottle_Retail;
								iowa_Liquor_Sales_notcleaned_Staging_tmp.Bottles_Sold = row12.Bottles_Sold;
								iowa_Liquor_Sales_notcleaned_Staging_tmp.Sale__Dollars = row12.Sale__Dollars;
								iowa_Liquor_Sales_notcleaned_Staging_tmp.Volume_Sold__Liters = row12.Volume_Sold__Liters;
								iowa_Liquor_Sales_notcleaned_Staging_tmp.Volume_Sold__Gallons = row12.Volume_Sold__Gallons;
								iowa_Liquor_Sales_notcleaned_Staging_tmp.DI_CreateDate = Var.DI_CreateDate;
								iowa_Liquor_Sales_notcleaned_Staging = iowa_Liquor_Sales_notcleaned_Staging_tmp;
								log.debug("tMap_11 - Outputting the record "
										+ count_iowa_Liquor_Sales_notcleaned_Staging_tMap_11
										+ " of the output table 'iowa_Liquor_Sales_notcleaned_Staging'.");

// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_11 = false;

							tos_count_tMap_11++;

							/**
							 * [tMap_11 main ] stop
							 */

							/**
							 * [tMap_11 process_data_begin ] start
							 */

							currentComponent = "tMap_11";

							/**
							 * [tMap_11 process_data_begin ] stop
							 */
// Start of branch "iowa_Liquor_Sales_notcleaned_Staging"
							if (iowa_Liquor_Sales_notcleaned_Staging != null) {

								/**
								 * [tDBOutput_10 main ] start
								 */

								currentComponent = "tDBOutput_10";

								cLabel = "Iowa";

								if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

										, "iowa_Liquor_Sales_notcleaned_Staging", "tMap_11", "tMap_11", "tMap",
										"tDBOutput_10", "Iowa", "tMSSqlOutput"

								)) {
									talendJobLogProcess(globalMap);
								}

								if (log.isTraceEnabled()) {
									log.trace("iowa_Liquor_Sales_notcleaned_Staging - "
											+ (iowa_Liquor_Sales_notcleaned_Staging == null ? ""
													: iowa_Liquor_Sales_notcleaned_Staging.toLogString()));
								}

								whetherReject_tDBOutput_10 = false;
								pstmt_tDBOutput_10.setInt(1,
										iowa_Liquor_Sales_notcleaned_Staging.iowa_Liquor_sales_notcleaned_SK);

								if (iowa_Liquor_Sales_notcleaned_Staging.Invoice_Item_Number == null) {
									pstmt_tDBOutput_10.setNull(2, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_10.setString(2,
											iowa_Liquor_Sales_notcleaned_Staging.Invoice_Item_Number);
								}

								if (iowa_Liquor_Sales_notcleaned_Staging.Date == null) {
									pstmt_tDBOutput_10.setNull(3, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_10.setString(3, iowa_Liquor_Sales_notcleaned_Staging.Date);
								}

								if (iowa_Liquor_Sales_notcleaned_Staging.Store_Number == null) {
									pstmt_tDBOutput_10.setNull(4, java.sql.Types.INTEGER);
								} else {
									pstmt_tDBOutput_10.setInt(4, iowa_Liquor_Sales_notcleaned_Staging.Store_Number);
								}

								if (iowa_Liquor_Sales_notcleaned_Staging.Store_Name == null) {
									pstmt_tDBOutput_10.setNull(5, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_10.setString(5, iowa_Liquor_Sales_notcleaned_Staging.Store_Name);
								}

								if (iowa_Liquor_Sales_notcleaned_Staging.Address == null) {
									pstmt_tDBOutput_10.setNull(6, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_10.setString(6, iowa_Liquor_Sales_notcleaned_Staging.Address);
								}

								if (iowa_Liquor_Sales_notcleaned_Staging.City == null) {
									pstmt_tDBOutput_10.setNull(7, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_10.setString(7, iowa_Liquor_Sales_notcleaned_Staging.City);
								}

								if (iowa_Liquor_Sales_notcleaned_Staging.Zip_Code == null) {
									pstmt_tDBOutput_10.setNull(8, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_10.setString(8, iowa_Liquor_Sales_notcleaned_Staging.Zip_Code);
								}

								if (iowa_Liquor_Sales_notcleaned_Staging.Store_Location == null) {
									pstmt_tDBOutput_10.setNull(9, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_10.setString(9,
											iowa_Liquor_Sales_notcleaned_Staging.Store_Location);
								}

								if (iowa_Liquor_Sales_notcleaned_Staging.County_Number == null) {
									pstmt_tDBOutput_10.setNull(10, java.sql.Types.INTEGER);
								} else {
									pstmt_tDBOutput_10.setInt(10, iowa_Liquor_Sales_notcleaned_Staging.County_Number);
								}

								if (iowa_Liquor_Sales_notcleaned_Staging.County == null) {
									pstmt_tDBOutput_10.setNull(11, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_10.setString(11, iowa_Liquor_Sales_notcleaned_Staging.County);
								}

								if (iowa_Liquor_Sales_notcleaned_Staging.Category == null) {
									pstmt_tDBOutput_10.setNull(12, java.sql.Types.INTEGER);
								} else {
									pstmt_tDBOutput_10.setInt(12, iowa_Liquor_Sales_notcleaned_Staging.Category);
								}

								if (iowa_Liquor_Sales_notcleaned_Staging.Category_Name == null) {
									pstmt_tDBOutput_10.setNull(13, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_10.setString(13,
											iowa_Liquor_Sales_notcleaned_Staging.Category_Name);
								}

								if (iowa_Liquor_Sales_notcleaned_Staging.Vendor_Number == null) {
									pstmt_tDBOutput_10.setNull(14, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_10.setString(14,
											iowa_Liquor_Sales_notcleaned_Staging.Vendor_Number);
								}

								if (iowa_Liquor_Sales_notcleaned_Staging.Vendor_Name == null) {
									pstmt_tDBOutput_10.setNull(15, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_10.setString(15, iowa_Liquor_Sales_notcleaned_Staging.Vendor_Name);
								}

								if (iowa_Liquor_Sales_notcleaned_Staging.Item_Number == null) {
									pstmt_tDBOutput_10.setNull(16, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_10.setString(16, iowa_Liquor_Sales_notcleaned_Staging.Item_Number);
								}

								if (iowa_Liquor_Sales_notcleaned_Staging.Item_Description == null) {
									pstmt_tDBOutput_10.setNull(17, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_10.setString(17,
											iowa_Liquor_Sales_notcleaned_Staging.Item_Description);
								}

								if (iowa_Liquor_Sales_notcleaned_Staging.Pack == null) {
									pstmt_tDBOutput_10.setNull(18, java.sql.Types.INTEGER);
								} else {
									pstmt_tDBOutput_10.setInt(18, iowa_Liquor_Sales_notcleaned_Staging.Pack);
								}

								if (iowa_Liquor_Sales_notcleaned_Staging.Bottle_Volume__ml == null) {
									pstmt_tDBOutput_10.setNull(19, java.sql.Types.INTEGER);
								} else {
									pstmt_tDBOutput_10.setInt(19,
											iowa_Liquor_Sales_notcleaned_Staging.Bottle_Volume__ml);
								}

								if (iowa_Liquor_Sales_notcleaned_Staging.State_Bottle_Cost == null) {
									pstmt_tDBOutput_10.setNull(20, java.sql.Types.FLOAT);
								} else {
									pstmt_tDBOutput_10.setFloat(20,
											iowa_Liquor_Sales_notcleaned_Staging.State_Bottle_Cost);
								}

								if (iowa_Liquor_Sales_notcleaned_Staging.State_Bottle_Retail == null) {
									pstmt_tDBOutput_10.setNull(21, java.sql.Types.FLOAT);
								} else {
									pstmt_tDBOutput_10.setFloat(21,
											iowa_Liquor_Sales_notcleaned_Staging.State_Bottle_Retail);
								}

								if (iowa_Liquor_Sales_notcleaned_Staging.Bottles_Sold == null) {
									pstmt_tDBOutput_10.setNull(22, java.sql.Types.INTEGER);
								} else {
									pstmt_tDBOutput_10.setInt(22, iowa_Liquor_Sales_notcleaned_Staging.Bottles_Sold);
								}

								if (iowa_Liquor_Sales_notcleaned_Staging.Sale__Dollars == null) {
									pstmt_tDBOutput_10.setNull(23, java.sql.Types.FLOAT);
								} else {
									pstmt_tDBOutput_10.setFloat(23, iowa_Liquor_Sales_notcleaned_Staging.Sale__Dollars);
								}

								if (iowa_Liquor_Sales_notcleaned_Staging.Volume_Sold__Liters == null) {
									pstmt_tDBOutput_10.setNull(24, java.sql.Types.FLOAT);
								} else {
									pstmt_tDBOutput_10.setFloat(24,
											iowa_Liquor_Sales_notcleaned_Staging.Volume_Sold__Liters);
								}

								if (iowa_Liquor_Sales_notcleaned_Staging.Volume_Sold__Gallons == null) {
									pstmt_tDBOutput_10.setNull(25, java.sql.Types.FLOAT);
								} else {
									pstmt_tDBOutput_10.setFloat(25,
											iowa_Liquor_Sales_notcleaned_Staging.Volume_Sold__Gallons);
								}

								if (iowa_Liquor_Sales_notcleaned_Staging.DI_CreateDate == null) {
									pstmt_tDBOutput_10.setNull(26, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_10.setString(26,
											iowa_Liquor_Sales_notcleaned_Staging.DI_CreateDate);
								}

								pstmt_tDBOutput_10.addBatch();
								nb_line_tDBOutput_10++;

								if (log.isDebugEnabled())
									log.debug("tDBOutput_10 - " + ("Adding the record ") + (nb_line_tDBOutput_10)
											+ (" to the ") + ("INSERT") + (" batch."));
								batchSizeCounter_tDBOutput_10++;

								////////// batch execute by batch size///////
								class LimitBytesHelper_tDBOutput_10 {
									public int limitBytePart1(int counter,
											java.sql.PreparedStatement pstmt_tDBOutput_10) throws Exception {
										try {

											if (log.isDebugEnabled())
												log.debug("tDBOutput_10 - " + ("Executing the ") + ("INSERT")
														+ (" batch."));
											for (int countEach_tDBOutput_10 : pstmt_tDBOutput_10.executeBatch()) {
												if (countEach_tDBOutput_10 == -2 || countEach_tDBOutput_10 == -3) {
													break;
												}
												counter += countEach_tDBOutput_10;
											}

											if (log.isDebugEnabled())
												log.debug("tDBOutput_10 - " + ("The ") + ("INSERT")
														+ (" batch execution has succeeded."));
										} catch (java.sql.BatchUpdateException e) {
											globalMap.put("tDBOutput_10_ERROR_MESSAGE", e.getMessage());

											int countSum_tDBOutput_10 = 0;
											for (int countEach_tDBOutput_10 : e.getUpdateCounts()) {
												counter += (countEach_tDBOutput_10 < 0 ? 0 : countEach_tDBOutput_10);
											}

											log.error("tDBOutput_10 - " + (e.getMessage()));
											System.err.println(e.getMessage());

										}
										return counter;
									}

									public int limitBytePart2(int counter,
											java.sql.PreparedStatement pstmt_tDBOutput_10) throws Exception {
										try {

											if (log.isDebugEnabled())
												log.debug("tDBOutput_10 - " + ("Executing the ") + ("INSERT")
														+ (" batch."));
											for (int countEach_tDBOutput_10 : pstmt_tDBOutput_10.executeBatch()) {
												if (countEach_tDBOutput_10 == -2 || countEach_tDBOutput_10 == -3) {
													break;
												}
												counter += countEach_tDBOutput_10;
											}

											if (log.isDebugEnabled())
												log.debug("tDBOutput_10 - " + ("The ") + ("INSERT")
														+ (" batch execution has succeeded."));
										} catch (java.sql.BatchUpdateException e) {
											globalMap.put("tDBOutput_10_ERROR_MESSAGE", e.getMessage());

											for (int countEach_tDBOutput_10 : e.getUpdateCounts()) {
												counter += (countEach_tDBOutput_10 < 0 ? 0 : countEach_tDBOutput_10);
											}

											log.error("tDBOutput_10 - " + (e.getMessage()));
											System.err.println(e.getMessage());

										}
										return counter;
									}
								}
								if ((batchSize_tDBOutput_10 > 0)
										&& (batchSize_tDBOutput_10 <= batchSizeCounter_tDBOutput_10)) {

									insertedCount_tDBOutput_10 = new LimitBytesHelper_tDBOutput_10()
											.limitBytePart1(insertedCount_tDBOutput_10, pstmt_tDBOutput_10);
									rowsToCommitCount_tDBOutput_10 = insertedCount_tDBOutput_10;

									batchSizeCounter_tDBOutput_10 = 0;
								}

								//////////// commit every////////////

								commitCounter_tDBOutput_10++;
								if (commitEvery_tDBOutput_10 <= commitCounter_tDBOutput_10) {
									if ((batchSize_tDBOutput_10 > 0) && (batchSizeCounter_tDBOutput_10 > 0)) {

										insertedCount_tDBOutput_10 = new LimitBytesHelper_tDBOutput_10()
												.limitBytePart1(insertedCount_tDBOutput_10, pstmt_tDBOutput_10);

										batchSizeCounter_tDBOutput_10 = 0;
									}
									if (rowsToCommitCount_tDBOutput_10 != 0) {

										if (log.isDebugEnabled())
											log.debug("tDBOutput_10 - " + ("Connection starting to commit ")
													+ (rowsToCommitCount_tDBOutput_10) + (" record(s)."));
									}
									conn_tDBOutput_10.commit();
									if (rowsToCommitCount_tDBOutput_10 != 0) {

										if (log.isDebugEnabled())
											log.debug("tDBOutput_10 - " + ("Connection commit has succeeded."));
										rowsToCommitCount_tDBOutput_10 = 0;
									}
									commitCounter_tDBOutput_10 = 0;
								}

								tos_count_tDBOutput_10++;

								/**
								 * [tDBOutput_10 main ] stop
								 */

								/**
								 * [tDBOutput_10 process_data_begin ] start
								 */

								currentComponent = "tDBOutput_10";

								cLabel = "Iowa";

								/**
								 * [tDBOutput_10 process_data_begin ] stop
								 */

								/**
								 * [tDBOutput_10 process_data_end ] start
								 */

								currentComponent = "tDBOutput_10";

								cLabel = "Iowa";

								/**
								 * [tDBOutput_10 process_data_end ] stop
								 */

							} // End of branch "iowa_Liquor_Sales_notcleaned_Staging"

							/**
							 * [tMap_11 process_data_end ] start
							 */

							currentComponent = "tMap_11";

							/**
							 * [tMap_11 process_data_end ] stop
							 */

						} // End of branch "row12"

						/**
						 * [tFileInputDelimited_10 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_10";

						cLabel = "iowa_Liquour_Sales_20221002_notCleaned";

						/**
						 * [tFileInputDelimited_10 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_10 end ] start
						 */

						currentComponent = "tFileInputDelimited_10";

						cLabel = "iowa_Liquour_Sales_20221002_notCleaned";

						nb_line_tFileInputDelimited_10++;
					}

				} finally {
					if (!(filename_tFileInputDelimited_10 instanceof java.io.InputStream)) {
						if (csvReadertFileInputDelimited_10 != null) {
							csvReadertFileInputDelimited_10.close();
						}
					}
					if (csvReadertFileInputDelimited_10 != null) {
						globalMap.put("tFileInputDelimited_10_NB_LINE", nb_line_tFileInputDelimited_10);
					}

					log.info("tFileInputDelimited_10 - Retrieved records count: " + nb_line_tFileInputDelimited_10
							+ ".");

				}

				if (log.isDebugEnabled())
					log.debug("tFileInputDelimited_10 - " + ("Done."));

				ok_Hash.put("tFileInputDelimited_10", true);
				end_Hash.put("tFileInputDelimited_10", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_10 end ] stop
				 */

				/**
				 * [tMap_11 end ] start
				 */

				currentComponent = "tMap_11";

// ###############################
// # Lookup hashes releasing
// ###############################      
				log.debug("tMap_11 - Written records count in the table 'iowa_Liquor_Sales_notcleaned_Staging': "
						+ count_iowa_Liquor_Sales_notcleaned_Staging_tMap_11 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row12", 2, 0,
						"tFileInputDelimited_10", "iowa_Liquour_Sales_20221002_notCleaned", "tFileInputDelimited",
						"tMap_11", "tMap_11", "tMap", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tMap_11 - " + ("Done."));

				ok_Hash.put("tMap_11", true);
				end_Hash.put("tMap_11", System.currentTimeMillis());

				/**
				 * [tMap_11 end ] stop
				 */

				/**
				 * [tDBOutput_10 end ] start
				 */

				currentComponent = "tDBOutput_10";

				cLabel = "Iowa";

				try {
					int countSum_tDBOutput_10 = 0;
					if (pstmt_tDBOutput_10 != null && batchSizeCounter_tDBOutput_10 > 0) {

						if (log.isDebugEnabled())
							log.debug("tDBOutput_10 - " + ("Executing the ") + ("INSERT") + (" batch."));
						for (int countEach_tDBOutput_10 : pstmt_tDBOutput_10.executeBatch()) {
							if (countEach_tDBOutput_10 == -2 || countEach_tDBOutput_10 == -3) {
								break;
							}
							countSum_tDBOutput_10 += countEach_tDBOutput_10;
						}
						rowsToCommitCount_tDBOutput_10 += countSum_tDBOutput_10;

						if (log.isDebugEnabled())
							log.debug("tDBOutput_10 - " + ("The ") + ("INSERT") + (" batch execution has succeeded."));
					}

					insertedCount_tDBOutput_10 += countSum_tDBOutput_10;

				} catch (java.sql.BatchUpdateException e) {
					globalMap.put("tDBOutput_10_ERROR_MESSAGE", e.getMessage());

					int countSum_tDBOutput_10 = 0;
					for (int countEach_tDBOutput_10 : e.getUpdateCounts()) {
						countSum_tDBOutput_10 += (countEach_tDBOutput_10 < 0 ? 0 : countEach_tDBOutput_10);
					}
					rowsToCommitCount_tDBOutput_10 += countSum_tDBOutput_10;

					insertedCount_tDBOutput_10 += countSum_tDBOutput_10;

					log.error("tDBOutput_10 - " + (e.getMessage()));
					System.err.println(e.getMessage());

				}
				if (pstmt_tDBOutput_10 != null) {

					pstmt_tDBOutput_10.close();
					resourceMap.remove("pstmt_tDBOutput_10");

				}
				resourceMap.put("statementClosed_tDBOutput_10", true);
				if (rowsToCommitCount_tDBOutput_10 != 0) {

					if (log.isDebugEnabled())
						log.debug("tDBOutput_10 - " + ("Connection starting to commit ")
								+ (rowsToCommitCount_tDBOutput_10) + (" record(s)."));
				}
				conn_tDBOutput_10.commit();
				if (rowsToCommitCount_tDBOutput_10 != 0) {

					if (log.isDebugEnabled())
						log.debug("tDBOutput_10 - " + ("Connection commit has succeeded."));
					rowsToCommitCount_tDBOutput_10 = 0;
				}
				commitCounter_tDBOutput_10 = 0;
				if (log.isDebugEnabled())
					log.debug("tDBOutput_10 - " + ("Closing the connection to the database."));
				conn_tDBOutput_10.close();
				if (log.isDebugEnabled())
					log.debug("tDBOutput_10 - " + ("Connection to the database has closed."));
				resourceMap.put("finish_tDBOutput_10", true);

				nb_line_deleted_tDBOutput_10 = nb_line_deleted_tDBOutput_10 + deletedCount_tDBOutput_10;
				nb_line_update_tDBOutput_10 = nb_line_update_tDBOutput_10 + updatedCount_tDBOutput_10;
				nb_line_inserted_tDBOutput_10 = nb_line_inserted_tDBOutput_10 + insertedCount_tDBOutput_10;
				nb_line_rejected_tDBOutput_10 = nb_line_rejected_tDBOutput_10 + rejectedCount_tDBOutput_10;

				globalMap.put("tDBOutput_10_NB_LINE", nb_line_tDBOutput_10);
				globalMap.put("tDBOutput_10_NB_LINE_UPDATED", nb_line_update_tDBOutput_10);
				globalMap.put("tDBOutput_10_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_10);
				globalMap.put("tDBOutput_10_NB_LINE_DELETED", nb_line_deleted_tDBOutput_10);
				globalMap.put("tDBOutput_10_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_10);

				if (log.isDebugEnabled())
					log.debug("tDBOutput_10 - " + ("Has ") + ("inserted") + (" ") + (nb_line_inserted_tDBOutput_10)
							+ (" record(s)."));

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId,
						"iowa_Liquor_Sales_notcleaned_Staging", 2, 0, "tMap_11", "tMap_11", "tMap", "tDBOutput_10",
						"Iowa", "tMSSqlOutput", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tDBOutput_10 - " + ("Done."));

				ok_Hash.put("tDBOutput_10", true);
				end_Hash.put("tDBOutput_10", System.currentTimeMillis());

				/**
				 * [tDBOutput_10 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_10 finally ] start
				 */

				currentComponent = "tFileInputDelimited_10";

				cLabel = "iowa_Liquour_Sales_20221002_notCleaned";

				/**
				 * [tFileInputDelimited_10 finally ] stop
				 */

				/**
				 * [tMap_11 finally ] start
				 */

				currentComponent = "tMap_11";

				/**
				 * [tMap_11 finally ] stop
				 */

				/**
				 * [tDBOutput_10 finally ] start
				 */

				currentComponent = "tDBOutput_10";

				cLabel = "Iowa";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_10") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_10 = null;
						if ((pstmtToClose_tDBOutput_10 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_10")) != null) {
							pstmtToClose_tDBOutput_10.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_10") == null) {
						java.sql.Connection ctn_tDBOutput_10 = null;
						if ((ctn_tDBOutput_10 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_10")) != null) {
							try {
								if (log.isDebugEnabled())
									log.debug("tDBOutput_10 - " + ("Closing the connection to the database."));
								ctn_tDBOutput_10.close();
								if (log.isDebugEnabled())
									log.debug("tDBOutput_10 - " + ("Connection to the database has closed."));
							} catch (java.sql.SQLException sqlEx_tDBOutput_10) {
								String errorMessage_tDBOutput_10 = "failed to close the connection in tDBOutput_10 :"
										+ sqlEx_tDBOutput_10.getMessage();
								log.error("tDBOutput_10 - " + (errorMessage_tDBOutput_10));
								System.err.println(errorMessage_tDBOutput_10);
							}
						}
					}
				}

				/**
				 * [tDBOutput_10 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_10_SUBPROCESS_STATE", 1);
	}

	public void talendJobLogProcess(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("talendJobLog_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		org.slf4j.MDC.put("_subJobName", "talendJobLog");
		org.slf4j.MDC.put("_subJobPid", TalendString.getAsciiRandomString(6));

		String iterateId = "";

		String currentComponent = "";
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				/**
				 * [talendJobLog begin ] start
				 */

				ok_Hash.put("talendJobLog", false);
				start_Hash.put("talendJobLog", System.currentTimeMillis());

				currentComponent = "talendJobLog";

				int tos_count_talendJobLog = 0;

				for (JobStructureCatcherUtils.JobStructureCatcherMessage jcm : talendJobLog.getMessages()) {
					org.talend.job.audit.JobContextBuilder builder_talendJobLog = org.talend.job.audit.JobContextBuilder
							.create().jobName(jcm.job_name).jobId(jcm.job_id).jobVersion(jcm.job_version)
							.custom("process_id", jcm.pid).custom("thread_id", jcm.tid).custom("pid", pid)
							.custom("father_pid", fatherPid).custom("root_pid", rootPid);
					org.talend.logging.audit.Context log_context_talendJobLog = null;

					if (jcm.log_type == JobStructureCatcherUtils.LogType.PERFORMANCE) {
						long timeMS = jcm.end_time - jcm.start_time;
						String duration = String.valueOf(timeMS);

						log_context_talendJobLog = builder_talendJobLog.sourceId(jcm.sourceId)
								.sourceLabel(jcm.sourceLabel).sourceConnectorType(jcm.sourceComponentName)
								.targetId(jcm.targetId).targetLabel(jcm.targetLabel)
								.targetConnectorType(jcm.targetComponentName).connectionName(jcm.current_connector)
								.rows(jcm.row_count).duration(duration).build();
						auditLogger_talendJobLog.flowExecution(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.JOBSTART) {
						log_context_talendJobLog = builder_talendJobLog.timestamp(jcm.moment).build();
						auditLogger_talendJobLog.jobstart(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.JOBEND) {
						long timeMS = jcm.end_time - jcm.start_time;
						String duration = String.valueOf(timeMS);

						log_context_talendJobLog = builder_talendJobLog.timestamp(jcm.moment).duration(duration)
								.status(jcm.status).build();
						auditLogger_talendJobLog.jobstop(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.RUNCOMPONENT) {
						log_context_talendJobLog = builder_talendJobLog.timestamp(jcm.moment)
								.connectorType(jcm.component_name).connectorId(jcm.component_id)
								.connectorLabel(jcm.component_label).build();
						auditLogger_talendJobLog.runcomponent(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.FLOWINPUT) {// log current component
																							// input line
						long timeMS = jcm.end_time - jcm.start_time;
						String duration = String.valueOf(timeMS);

						log_context_talendJobLog = builder_talendJobLog.connectorType(jcm.component_name)
								.connectorId(jcm.component_id).connectorLabel(jcm.component_label)
								.connectionName(jcm.current_connector).connectionType(jcm.current_connector_type)
								.rows(jcm.total_row_number).duration(duration).build();
						auditLogger_talendJobLog.flowInput(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.FLOWOUTPUT) {// log current component
																								// output/reject line
						long timeMS = jcm.end_time - jcm.start_time;
						String duration = String.valueOf(timeMS);

						log_context_talendJobLog = builder_talendJobLog.connectorType(jcm.component_name)
								.connectorId(jcm.component_id).connectorLabel(jcm.component_label)
								.connectionName(jcm.current_connector).connectionType(jcm.current_connector_type)
								.rows(jcm.total_row_number).duration(duration).build();
						auditLogger_talendJobLog.flowOutput(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.JOBERROR) {
						java.lang.Exception e_talendJobLog = jcm.exception;
						if (e_talendJobLog != null) {
							try (java.io.StringWriter sw_talendJobLog = new java.io.StringWriter();
									java.io.PrintWriter pw_talendJobLog = new java.io.PrintWriter(sw_talendJobLog)) {
								e_talendJobLog.printStackTrace(pw_talendJobLog);
								builder_talendJobLog.custom("stacktrace", sw_talendJobLog.getBuffer().substring(0,
										java.lang.Math.min(sw_talendJobLog.getBuffer().length(), 512)));
							}
						}

						if (jcm.extra_info != null) {
							builder_talendJobLog.connectorId(jcm.component_id).custom("extra_info", jcm.extra_info);
						}

						log_context_talendJobLog = builder_talendJobLog
								.connectorType(jcm.component_id.substring(0, jcm.component_id.lastIndexOf('_')))
								.connectorId(jcm.component_id)
								.connectorLabel(jcm.component_label == null ? jcm.component_id : jcm.component_label)
								.build();

						auditLogger_talendJobLog.exception(log_context_talendJobLog);
					}

				}

				/**
				 * [talendJobLog begin ] stop
				 */

				/**
				 * [talendJobLog main ] start
				 */

				currentComponent = "talendJobLog";

				tos_count_talendJobLog++;

				/**
				 * [talendJobLog main ] stop
				 */

				/**
				 * [talendJobLog process_data_begin ] start
				 */

				currentComponent = "talendJobLog";

				/**
				 * [talendJobLog process_data_begin ] stop
				 */

				/**
				 * [talendJobLog process_data_end ] start
				 */

				currentComponent = "talendJobLog";

				/**
				 * [talendJobLog process_data_end ] stop
				 */

				/**
				 * [talendJobLog end ] start
				 */

				currentComponent = "talendJobLog";

				ok_Hash.put("talendJobLog", true);
				end_Hash.put("talendJobLog", System.currentTimeMillis());

				/**
				 * [talendJobLog end ] stop
				 */
			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [talendJobLog finally ] start
				 */

				currentComponent = "talendJobLog";

				/**
				 * [talendJobLog finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("talendJobLog_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	private final static java.util.Properties jobInfo = new java.util.Properties();

	public static void main(String[] args) {
		final Iowa_Liquor_Sales Iowa_Liquor_SalesClass = new Iowa_Liquor_Sales();

		int exitCode = Iowa_Liquor_SalesClass.runJobInTOS(args);
		if (exitCode == 0) {
			log.info("TalendJob: 'Iowa_Liquor_Sales' - Done.");
		}

		System.exit(exitCode);
	}

	private void getjobInfo() {
		final String TEMPLATE_PATH = "src/main/templates/jobInfo_template.properties";
		final String BUILD_PATH = "../jobInfo.properties";
		final String path = this.getClass().getResource("").getPath();
		if (path.lastIndexOf("target") > 0) {
			final java.io.File templateFile = new java.io.File(
					path.substring(0, path.lastIndexOf("target")).concat(TEMPLATE_PATH));
			if (templateFile.exists()) {
				readJobInfo(templateFile);
				return;
			}
		}
		readJobInfo(new java.io.File(BUILD_PATH));
	}

	private void readJobInfo(java.io.File jobInfoFile) {

		if (jobInfoFile.exists()) {
			try {
				jobInfo.load(new java.io.FileInputStream(jobInfoFile));
			} catch (IOException e) {

				log.debug("Read jobInfo.properties file fail: " + e.getMessage());

			}
		}
		log.info(String.format("Project name: %s\tJob name: %s\tGIT Commit ID: %s\tTalend Version: %s", projectName,
				jobName, jobInfo.getProperty("gitCommitId"), "8.0.1.20220923_1236-patch"));

	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (!"".equals(log4jLevel)) {

			if ("trace".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.TRACE);
			} else if ("debug".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.DEBUG);
			} else if ("info".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.INFO);
			} else if ("warn".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.WARN);
			} else if ("error".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.ERROR);
			} else if ("fatal".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.FATAL);
			} else if ("off".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.OFF);
			}
			org.apache.logging.log4j.core.config.Configurator
					.setLevel(org.apache.logging.log4j.LogManager.getRootLogger().getName(), log.getLevel());

		}

		getjobInfo();
		log.info("TalendJob: 'Iowa_Liquor_Sales' - Start.");

		java.util.Set<Object> jobInfoKeys = jobInfo.keySet();
		for (Object jobInfoKey : jobInfoKeys) {
			org.slf4j.MDC.put("_" + jobInfoKey.toString(), jobInfo.get(jobInfoKey).toString());
		}
		org.slf4j.MDC.put("_pid", pid);
		org.slf4j.MDC.put("_rootPid", rootPid);
		org.slf4j.MDC.put("_fatherPid", fatherPid);
		org.slf4j.MDC.put("_projectName", projectName);
		org.slf4j.MDC.put("_startTimestamp", java.time.ZonedDateTime.now(java.time.ZoneOffset.UTC)
				.format(java.time.format.DateTimeFormatter.ISO_INSTANT));
		org.slf4j.MDC.put("_jobRepositoryId", "_SaGEYNHTEe2dbdqIb9qmwg");
		org.slf4j.MDC.put("_compiledAtTimestamp", "2023-04-08T02:10:53.021564Z");

		java.lang.management.RuntimeMXBean mx = java.lang.management.ManagementFactory.getRuntimeMXBean();
		String[] mxNameTable = mx.getName().split("@"); //$NON-NLS-1$
		if (mxNameTable.length == 2) {
			org.slf4j.MDC.put("_systemPid", mxNameTable[0]);
		} else {
			org.slf4j.MDC.put("_systemPid", String.valueOf(java.lang.Thread.currentThread().getId()));
		}

		if (enableLogStash) {
			java.util.Properties properties_talendJobLog = new java.util.Properties();
			properties_talendJobLog.setProperty("root.logger", "audit");
			properties_talendJobLog.setProperty("encoding", "UTF-8");
			properties_talendJobLog.setProperty("application.name", "Talend Studio");
			properties_talendJobLog.setProperty("service.name", "Talend Studio Job");
			properties_talendJobLog.setProperty("instance.name", "Talend Studio Job Instance");
			properties_talendJobLog.setProperty("propagate.appender.exceptions", "none");
			properties_talendJobLog.setProperty("log.appender", "file");
			properties_talendJobLog.setProperty("appender.file.path", "audit.json");
			properties_talendJobLog.setProperty("appender.file.maxsize", "52428800");
			properties_talendJobLog.setProperty("appender.file.maxbackup", "20");
			properties_talendJobLog.setProperty("host", "false");

			System.getProperties().stringPropertyNames().stream().filter(it -> it.startsWith("audit.logger."))
					.forEach(key -> properties_talendJobLog.setProperty(key.substring("audit.logger.".length()),
							System.getProperty(key)));

			org.apache.logging.log4j.core.config.Configurator
					.setLevel(properties_talendJobLog.getProperty("root.logger"), org.apache.logging.log4j.Level.DEBUG);

			auditLogger_talendJobLog = org.talend.job.audit.JobEventAuditLoggerFactory
					.createJobAuditLogger(properties_talendJobLog);
		}

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		org.slf4j.MDC.put("_pid", pid);

		if (rootPid == null) {
			rootPid = pid;
		}

		org.slf4j.MDC.put("_rootPid", rootPid);

		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}
		org.slf4j.MDC.put("_fatherPid", fatherPid);

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		try {
			java.util.Dictionary<String, Object> jobProperties = null;
			if (inOSGi) {
				jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

				if (jobProperties != null && jobProperties.get("context") != null) {
					contextStr = (String) jobProperties.get("context");
				}
			}
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = Iowa_Liquor_Sales.class.getClassLoader().getResourceAsStream(
					"talend_assignment/iowa_liquor_sales_1_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = Iowa_Liquor_Sales.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						if (inOSGi && jobProperties != null) {
							java.util.Enumeration<String> keys = jobProperties.keys();
							while (keys.hasMoreElements()) {
								String propKey = keys.nextElement();
								if (defaultProps.containsKey(propKey)) {
									defaultProps.put(propKey, (String) jobProperties.get(propKey));
								}
							}
						}
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, ContextProperties.class, parametersToEncrypt));

		org.slf4j.MDC.put("_context", contextStr);
		log.info("TalendJob: 'Iowa_Liquor_Sales' - Started.");

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		if (enableLogStash) {
			talendJobLog.addJobStartMessage();
			try {
				talendJobLogProcess(globalMap);
			} catch (java.lang.Exception e) {
				e.printStackTrace();
			}
		}

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tFileInputDelimited_10Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_10) {
			globalMap.put("tFileInputDelimited_10_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_10.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println(
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : Iowa_Liquor_Sales");
		}
		if (enableLogStash) {
			talendJobLog.addJobEndMessage(startTime, end, status);
			try {
				talendJobLogProcess(globalMap);
			} catch (java.lang.Exception e) {
				e.printStackTrace();
			}
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");
		resumeUtil.flush();

		org.slf4j.MDC.remove("_subJobName");
		org.slf4j.MDC.remove("_subJobPid");
		org.slf4j.MDC.remove("_systemPid");
		log.info("TalendJob: 'Iowa_Liquor_Sales' - Finished - status: " + status + " returnCode: " + returnCode);

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 196379 characters generated by Talend Real-time Big Data Platform on the
 * April 7, 2023 at 10:10:53 PM EDT
 ************************************************************************************************/