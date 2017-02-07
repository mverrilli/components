
package com.netsuite.webservices.v2016_2.transactions.inventory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.netsuite.webservices.v2016_2.platform.common.InventoryDetail;
import com.netsuite.webservices.v2016_2.platform.common.types.ItemSource;
import com.netsuite.webservices.v2016_2.platform.core.CustomFieldList;
import com.netsuite.webservices.v2016_2.platform.core.RecordRef;
import com.netsuite.webservices.v2016_2.transactions.inventory.types.WorkOrderItemItemCommitInventory;


/**
 * <p>Java class for WorkOrderItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WorkOrderItem"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="line" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="item" type="{urn:core_2016_2.platform.webservices.netsuite.com}RecordRef" minOccurs="0"/&gt;
 *         &lt;element name="operationSequenceNumber" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="componentYield" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="bomQuantity" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="quantityCommitted" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="quantityBackOrdered" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="quantityAvailable" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="averageCost" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="lastPurchasePrice" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="quantityOnHand" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="units" type="{urn:core_2016_2.platform.webservices.netsuite.com}RecordRef" minOccurs="0"/&gt;
 *         &lt;element name="inventoryDetail" type="{urn:common_2016_2.platform.webservices.netsuite.com}InventoryDetail" minOccurs="0"/&gt;
 *         &lt;element name="serialNumbers" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="orderPriority" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="options" type="{urn:core_2016_2.platform.webservices.netsuite.com}CustomFieldList" minOccurs="0"/&gt;
 *         &lt;element name="itemSource" type="{urn:types.common_2016_2.platform.webservices.netsuite.com}ItemSource" minOccurs="0"/&gt;
 *         &lt;element name="department" type="{urn:core_2016_2.platform.webservices.netsuite.com}RecordRef" minOccurs="0"/&gt;
 *         &lt;element name="class" type="{urn:core_2016_2.platform.webservices.netsuite.com}RecordRef" minOccurs="0"/&gt;
 *         &lt;element name="location" type="{urn:core_2016_2.platform.webservices.netsuite.com}RecordRef" minOccurs="0"/&gt;
 *         &lt;element name="poVendor" type="{urn:core_2016_2.platform.webservices.netsuite.com}RecordRef" minOccurs="0"/&gt;
 *         &lt;element name="poRate" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="percentComplete" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="contribution" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="commitInventory" type="{urn:types.inventory_2016_2.transactions.webservices.netsuite.com}WorkOrderItemItemCommitInventory" minOccurs="0"/&gt;
 *         &lt;element name="plannedIssueDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="customFieldList" type="{urn:core_2016_2.platform.webservices.netsuite.com}CustomFieldList" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WorkOrderItem", propOrder = {
    "line",
    "item",
    "operationSequenceNumber",
    "componentYield",
    "bomQuantity",
    "quantityCommitted",
    "quantityBackOrdered",
    "quantityAvailable",
    "averageCost",
    "lastPurchasePrice",
    "quantityOnHand",
    "quantity",
    "units",
    "inventoryDetail",
    "serialNumbers",
    "orderPriority",
    "options",
    "itemSource",
    "department",
    "clazz",
    "location",
    "poVendor",
    "poRate",
    "percentComplete",
    "contribution",
    "description",
    "commitInventory",
    "plannedIssueDate",
    "customFieldList"
})
public class WorkOrderItem {

    protected Long line;
    protected RecordRef item;
    protected Long operationSequenceNumber;
    protected Double componentYield;
    protected Double bomQuantity;
    protected Double quantityCommitted;
    protected Double quantityBackOrdered;
    protected Double quantityAvailable;
    protected Double averageCost;
    protected Double lastPurchasePrice;
    protected Double quantityOnHand;
    protected Double quantity;
    protected RecordRef units;
    protected InventoryDetail inventoryDetail;
    protected String serialNumbers;
    protected Double orderPriority;
    protected CustomFieldList options;
    @XmlSchemaType(name = "string")
    protected ItemSource itemSource;
    protected RecordRef department;
    @XmlElement(name = "class")
    protected RecordRef clazz;
    protected RecordRef location;
    protected RecordRef poVendor;
    protected Double poRate;
    protected Double percentComplete;
    protected Double contribution;
    protected String description;
    @XmlSchemaType(name = "string")
    protected WorkOrderItemItemCommitInventory commitInventory;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar plannedIssueDate;
    protected CustomFieldList customFieldList;

    /**
     * Gets the value of the line property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLine() {
        return line;
    }

    /**
     * Sets the value of the line property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLine(Long value) {
        this.line = value;
    }

    /**
     * Gets the value of the item property.
     * 
     * @return
     *     possible object is
     *     {@link RecordRef }
     *     
     */
    public RecordRef getItem() {
        return item;
    }

    /**
     * Sets the value of the item property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecordRef }
     *     
     */
    public void setItem(RecordRef value) {
        this.item = value;
    }

    /**
     * Gets the value of the operationSequenceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOperationSequenceNumber() {
        return operationSequenceNumber;
    }

    /**
     * Sets the value of the operationSequenceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOperationSequenceNumber(Long value) {
        this.operationSequenceNumber = value;
    }

    /**
     * Gets the value of the componentYield property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getComponentYield() {
        return componentYield;
    }

    /**
     * Sets the value of the componentYield property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setComponentYield(Double value) {
        this.componentYield = value;
    }

    /**
     * Gets the value of the bomQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getBomQuantity() {
        return bomQuantity;
    }

    /**
     * Sets the value of the bomQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setBomQuantity(Double value) {
        this.bomQuantity = value;
    }

    /**
     * Gets the value of the quantityCommitted property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getQuantityCommitted() {
        return quantityCommitted;
    }

    /**
     * Sets the value of the quantityCommitted property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setQuantityCommitted(Double value) {
        this.quantityCommitted = value;
    }

    /**
     * Gets the value of the quantityBackOrdered property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getQuantityBackOrdered() {
        return quantityBackOrdered;
    }

    /**
     * Sets the value of the quantityBackOrdered property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setQuantityBackOrdered(Double value) {
        this.quantityBackOrdered = value;
    }

    /**
     * Gets the value of the quantityAvailable property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getQuantityAvailable() {
        return quantityAvailable;
    }

    /**
     * Sets the value of the quantityAvailable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setQuantityAvailable(Double value) {
        this.quantityAvailable = value;
    }

    /**
     * Gets the value of the averageCost property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAverageCost() {
        return averageCost;
    }

    /**
     * Sets the value of the averageCost property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAverageCost(Double value) {
        this.averageCost = value;
    }

    /**
     * Gets the value of the lastPurchasePrice property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getLastPurchasePrice() {
        return lastPurchasePrice;
    }

    /**
     * Sets the value of the lastPurchasePrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setLastPurchasePrice(Double value) {
        this.lastPurchasePrice = value;
    }

    /**
     * Gets the value of the quantityOnHand property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getQuantityOnHand() {
        return quantityOnHand;
    }

    /**
     * Sets the value of the quantityOnHand property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setQuantityOnHand(Double value) {
        this.quantityOnHand = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setQuantity(Double value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the units property.
     * 
     * @return
     *     possible object is
     *     {@link RecordRef }
     *     
     */
    public RecordRef getUnits() {
        return units;
    }

    /**
     * Sets the value of the units property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecordRef }
     *     
     */
    public void setUnits(RecordRef value) {
        this.units = value;
    }

    /**
     * Gets the value of the inventoryDetail property.
     * 
     * @return
     *     possible object is
     *     {@link InventoryDetail }
     *     
     */
    public InventoryDetail getInventoryDetail() {
        return inventoryDetail;
    }

    /**
     * Sets the value of the inventoryDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link InventoryDetail }
     *     
     */
    public void setInventoryDetail(InventoryDetail value) {
        this.inventoryDetail = value;
    }

    /**
     * Gets the value of the serialNumbers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerialNumbers() {
        return serialNumbers;
    }

    /**
     * Sets the value of the serialNumbers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerialNumbers(String value) {
        this.serialNumbers = value;
    }

    /**
     * Gets the value of the orderPriority property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getOrderPriority() {
        return orderPriority;
    }

    /**
     * Sets the value of the orderPriority property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setOrderPriority(Double value) {
        this.orderPriority = value;
    }

    /**
     * Gets the value of the options property.
     * 
     * @return
     *     possible object is
     *     {@link CustomFieldList }
     *     
     */
    public CustomFieldList getOptions() {
        return options;
    }

    /**
     * Sets the value of the options property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomFieldList }
     *     
     */
    public void setOptions(CustomFieldList value) {
        this.options = value;
    }

    /**
     * Gets the value of the itemSource property.
     * 
     * @return
     *     possible object is
     *     {@link ItemSource }
     *     
     */
    public ItemSource getItemSource() {
        return itemSource;
    }

    /**
     * Sets the value of the itemSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemSource }
     *     
     */
    public void setItemSource(ItemSource value) {
        this.itemSource = value;
    }

    /**
     * Gets the value of the department property.
     * 
     * @return
     *     possible object is
     *     {@link RecordRef }
     *     
     */
    public RecordRef getDepartment() {
        return department;
    }

    /**
     * Sets the value of the department property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecordRef }
     *     
     */
    public void setDepartment(RecordRef value) {
        this.department = value;
    }

    /**
     * Gets the value of the clazz property.
     * 
     * @return
     *     possible object is
     *     {@link RecordRef }
     *     
     */
    public RecordRef getClazz() {
        return clazz;
    }

    /**
     * Sets the value of the clazz property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecordRef }
     *     
     */
    public void setClazz(RecordRef value) {
        this.clazz = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link RecordRef }
     *     
     */
    public RecordRef getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecordRef }
     *     
     */
    public void setLocation(RecordRef value) {
        this.location = value;
    }

    /**
     * Gets the value of the poVendor property.
     * 
     * @return
     *     possible object is
     *     {@link RecordRef }
     *     
     */
    public RecordRef getPoVendor() {
        return poVendor;
    }

    /**
     * Sets the value of the poVendor property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecordRef }
     *     
     */
    public void setPoVendor(RecordRef value) {
        this.poVendor = value;
    }

    /**
     * Gets the value of the poRate property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPoRate() {
        return poRate;
    }

    /**
     * Sets the value of the poRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPoRate(Double value) {
        this.poRate = value;
    }

    /**
     * Gets the value of the percentComplete property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPercentComplete() {
        return percentComplete;
    }

    /**
     * Sets the value of the percentComplete property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPercentComplete(Double value) {
        this.percentComplete = value;
    }

    /**
     * Gets the value of the contribution property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getContribution() {
        return contribution;
    }

    /**
     * Sets the value of the contribution property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setContribution(Double value) {
        this.contribution = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the commitInventory property.
     * 
     * @return
     *     possible object is
     *     {@link WorkOrderItemItemCommitInventory }
     *     
     */
    public WorkOrderItemItemCommitInventory getCommitInventory() {
        return commitInventory;
    }

    /**
     * Sets the value of the commitInventory property.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkOrderItemItemCommitInventory }
     *     
     */
    public void setCommitInventory(WorkOrderItemItemCommitInventory value) {
        this.commitInventory = value;
    }

    /**
     * Gets the value of the plannedIssueDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPlannedIssueDate() {
        return plannedIssueDate;
    }

    /**
     * Sets the value of the plannedIssueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPlannedIssueDate(XMLGregorianCalendar value) {
        this.plannedIssueDate = value;
    }

    /**
     * Gets the value of the customFieldList property.
     * 
     * @return
     *     possible object is
     *     {@link CustomFieldList }
     *     
     */
    public CustomFieldList getCustomFieldList() {
        return customFieldList;
    }

    /**
     * Sets the value of the customFieldList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomFieldList }
     *     
     */
    public void setCustomFieldList(CustomFieldList value) {
        this.customFieldList = value;
    }

}
