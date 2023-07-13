### You have the following options:

* [Passing key-values pairs via JR `parameters`](#pass-singe-key-values-pairs-via-map)
* [Programmatically pass main data in the form of a collection of items via JR `datasource` and `field` abstractions](#programmatically-pass-main-data-in-the-form-of-a-collection-of-items-via-jr-datasource-and-field-abstractions)
* [Passing data into the JR `Table` element via parameter](tables/Table.element.md)


### Pass singe key-values pairs via Map

In JR `parameters` are used for such type of data. Define parameter on the parent report level:
```xml
<jasperReport xmlns="http://jasperreports.sourceforge.net/jasperreports" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd" name="Subreport" pageWidth="595" pageHeight="842" columnWidth="555" leftMargin="20" rightMargin="20" topMargin="20" bottomMargin="20" uuid="f7917ee5-66d7-4c42-b573-9a62d6e7b27c">
	<parameter name="TEST_PARAMETER" class="java.lang.String"/>
```

Use them in the report through `Text Field` elements:
```xml
<textField>
  <reportElement ... />
  <textElement ... />
  <textFieldExpression><![CDATA[$P{TEST_PARAMETER}]]></textFieldExpression>
</textField>
```

Pass Map into the report as:
```java
Map<String, Object> map = new HashMap<>(); //cannot be immutable
map.put("TEST_PARAMETER", "Custom test value");
JasperPrint jasperPrint = JasperFillManager.fillReport(
  ...,
  map, 
  ...);
```


### Programmatically pass main data in the form of a collection of items via JR `datasource` and `field` abstractions

Please note, we mention `main data` cause only a singe datasource can be defined and used for such way of data passing.

Here is a [Java POJO `LineBean`](/src/test/java/com/example/jr/dto/LineBean.java) example. 

List of such items we are going to use in the report.

Java POJO is a bean with private properties and getters/setters defined for each property.

In the template you apply 3 steps:
* Define fields on the parent report level. Name each field according to the property in the Java bean.
In our case these names are: `name`, `date`, `money`, etc.
Type of the field must correspond to the type of the property in the Java bean:
```xml
<jasperReport ...
  <field name="name" class="java.lang.String"/>
  <field name="date" class="java.time.LocalDateTime"/>
  <field name="name" class="java.math.BigDecimal"/>
```
* Per each field locate `Text field` element on the `Detail` band of the report.
* In each field access the defined fields as:
```xml
<textField>
  <reportElement .../>
  <textElement ... >
  <textFieldExpression><![CDATA[$F{name}]]></textFieldExpression>
</textField>
```
In the expression field just put: `$F{name}`

!!! Do not define a datasource in the report itself if you want to pass data programmatically.

To pass a collection of items:
```java
JasperPrint jasperPrint = JasperFillManager.fillReport(
  ...,
  ...,
  new JRBeanCollectionDataSource(List.of(
    new LineBean(...),
    new LineBean(...),
    new LineBean(...),
    new LineBean(...))));
```
