* [How to fill Jasper Table using Collection of data using Java?](https://www.youtube.com/watch?v=fZtnoQpPzaw)

### Define a table with JR Table element

Notes:
* Before creating such element, please read about [JR datasources](Data_Passing.md#fields-and-datasources)
* Neither Column Header nor Column Footer of the main bands are used.

#### In case you pass data programmatically:

Add JR Table element:
* Find a `Table` in the `Basic Elements` panel and locate it on the `Detail` band.
* Create a Table using a new dataset.
* Name a new **empty** dataset for the table. We use `companyHistoryDs`
* Choose `Use an empty Data Source`

Specify `Column Header` layout:
* In the `Column Header` of our new `Table` element type, create column per record attribute.
* In each created column locate `Static Text` element with the label.
  Set `Vertical Alignment` for the field and `Textalignment`

Configure dataset/datasource
* Per each column we've created, create a field in the datasource (`companyHistoryDs`), associated with the table element.
* Per each created JR Field of the `companyHistoryDs` datasource:
  * choose `Text Field` element 
  * locate it onto the `Detail` band of the table (not global `Detail`)
  * choose exact JR Field that should be displayed by that element
* Select all the fields you located, choose `Borders` and then set left and top padding.
* Define global parameter (we named it as `companyHistory`) under the parent report. 
  * It must not be defined inside the dataset!
  * The parameter type is `net.sf.jasperreports.engine.data.JRBeanCollectionDataSource`. 
  `JRBeanCollectionDataSource` is a wrapper for our collection of java bean objects.
* Map the `companyHistoryDs` datasource to the `companyHistory` parameter defined above.
  * You don't use properties of the `companyHistoryDs` datasource itself.
  * Instead, **select the table element and on its `Dataset` property** change `Use an empty Data Source` option to:
    `Use a JRDatasource expression` with `$P{companyHistory}` as a value.
* Now you could pass list of items as POJOs:
```java
Map<String, Object> map = new HashMap<>();
map.put(
  "companyHistory",
  new JRBeanCollectionDataSource(
    List.of(
      new CompanyHistory("2001", "Created in Germany"),
      new CompanyHistory("2010", "Extended to covere all Europe"),
      new CompanyHistory("2015", "Covered Asia"))));
```

Notes about `CompanyHistory`
* It is a Java POJO with empty constructor and getters/setters
* Each property of such POJO corresponds to the JR Field defined above.
  See `companyHistoryDate` and `companyHistoryComment`.

See [`CompanyReportTest`](../src/test/java/com/example/jr/CompanyReportTest.java)