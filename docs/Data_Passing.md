### Topics

* [Ways to pass data: programmatically vs db datasources in the Jasper Report](#ways-to-pass-data-to-the-jasper-report)
* [JR Parameters vs Fields vs Variables](#jr-parameters-vs-fields-vs-variables)
* [Parameters](#parameters)
* [Fields and datasources](#fields-and-datasources)
* [Tables]



### Ways to pass data to the Jasper Report

#### Using database datasources defined in the Jasper Report itself. 

In this case JR Engine is responsible for connection and data pulling from a database.

** (It seeems) Only this option is available when you use Jasper Report Server to generate reports.**

#### Data passing programmatically

The application is responsible for data accumulation and passing it to the report.

**Only this option is available when you have no direct db access and communicate only via external api.**

#### Conclusion:

When the application, but not Jasper Report Server generates a report make a decision based on having direct db access:
* If you have db access, prefer db datasources configured in the Jasper Report itself
* If you use external API, pass data programmatically


### JR Parameters vs Fields vs Variables

* [Parameters](https://community.jaspersoft.com/wiki/jasperreports-library-tutorial#Parameters)
* [Fields](https://community.jaspersoft.com/wiki/jasperreports-library-tutorial#Fields)
* [Variables and expressions](https://community.jaspersoft.com/wiki/jasperreports-library-tutorial#Expressions)

**Parameters** are data input that belongs to the whole report. 
They do not belong to a record/item of the datasource.
This can be an input parameter to SQL query.

**Fields** represent attributes/properties of a record/item of the datasource.
They are always connected to a datasource.

If you use
* database datasource, then JR Fields are the column names of the ResultSet.
* datasource to pass list of items (Java Bean), then JR Fields are the Java class field names.
* datasource to pass list of Map instances, then JR Fields are keys of the Map.

**Variables** actually are named expressions, nothing else.
In JR there are predefined variables like `PAGE_NUMBER`, `REPORT_COUNT`, and others. 

You can also define a custom complicated expression, that you want to share and reuse in different parts of the report.
To refer to those expressions by name, you use JR Variables - abstractions, 
that store a mapping between name and the expression.


### Parameters

Define parameter on the parent report level:
```xml
<jasperReport xmlns="http://jasperreports.sourceforge.net/jasperreports" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd" name="Subreport" pageWidth="595" pageHeight="842" columnWidth="555" leftMargin="20" rightMargin="20" topMargin="20" bottomMargin="20" uuid="f7917ee5-66d7-4c42-b573-9a62d6e7b27c">
	<parameter name="param1" class="java.lang.String"/>
```

To use the parameter in the report through `Text Field` element with `$P{param1}` expression.
In xml it looks like:
```xml
<textField>
  <reportElement />
  <textElement />
  <textFieldExpression><![CDATA[$P{param1}]]></textFieldExpression>
</textField>
```

Pass (cannot be immutable) Map into the report as:
```java
Map<String, Object> map = new HashMap<>(); //cannot be immutable
map.put("param1", "Custom test value");
JasperPrint jasperPrint = JasperFillManager.fillReport(
  ...,
  map, 
  ...);
```

### Fields and datasources

We use `Datasources` when we want to pass list of items or multiple records into the report.
To use item attributes in JR report JR Fields are defined.

There are 2 ways to represent the data from a datasource:
* as a table
* as a list of forms (for instance, each form shows information about a person: his personal data and his photo)

#### Master datasource

JR report can have only a single - **master datasource**.

Only a **master datasource** can be used to show data in a list of forms.

Just locate JR Fields on JR Detail Band.

If you want to use a master datasource to show data as a table, but not as a list of forms, 
use the following JR Bands (sections):
* Column Header
* Detail
* Column Footer (if needed)

You can also connect a JR Table element to the **master datasource**.

Fields for a master datasource are defined on the report level:
```xml
<jasperReport>
  <field name="field1"/>
  <field name="field"/>
</jasperReport>
```

If you pass data programmatically, you don't define master datasource in a JR template.
The master datasource is passed as the 3rd parameter of `JasperFillManager#fillReport` method:
```java
JasperPrint jasperPrint = JasperFillManager.fillReport(
  ...,
  ...,
  new JRBeanCollectionDataSource(List.of(
    new SomePojoBean(...),
    new SomePojoBean(...),
    new SomePojoBean(...),
    new SomePojoBean(...))));
```

The most popular types of datasource, passed programmatically, are: 
* `JRBeanCollectionDataSource` to pass list of items in form of Java Bean (POJO with getters and setters)
* `JRMapCollectionDataSource` to pass list of Map instances

TODO: using master datasource, based on sql and connected to a database, defined in the JR template itself.

#### Datasources defined as `subDataset`

The other datasources (not master) can be used only in a connection to a JR Table element.
They are defined as `subDataset`.


[Define JR Table element together with its datasource and the related JR Fields](Table.element.md)


### Ability to pass both empty and non-empty datasources

Be careful with choosing datasource type in the JR template. 

If you choose `JRBeanCollectionDataSource` then you cannot pass `JREmptyDataSource`.

It might make sense to choose `JRDataSource` instead (or `null` as a value).

You could use utility method like `ReportData#asJrDatasource` to hide such details.

### Using datasources example

In [`CompanyReportTest`](../src/test/java/com/example/jr/CompanyReportTest.java) 3 datasources are used:

* Master datasource to show personal data of each employee as a form per a report page (not as a table)
* A single `companyHistoryDs` subdatasource - for the history of the company. 
  The data is passed via `companyHistory` global report parameter.
* A nested `educationHistoryDs` subdatasource per each employee - to show his/her education history.
  The data is passed via `educationHistory` JR Field, but not parameter! Each employee has his own education history.