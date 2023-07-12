* [How to fill Jasper Table using Collection of data using Java?](https://www.youtube.com/watch?v=fZtnoQpPzaw)

### Define a table with `table` component

You use `table` element in case you have more than a single dataset.
It is in many situations to replace the use of subreports.

You also can use it even with a single dataset, when the data is passed via the parameter to the report.

Steps to define a table and fill in with data
* You do not need `Column Header` or `Column Footer`
* Find a `Table` in the `Basic Elements` panel and locate it on the `Detail` band.
* Name a new **empty** dataset for the table. We use `Table_Demo_DS`
* Choose `Use an empty Data Source`
* Specify a layout
* In the `Column Header` of our new `Table` element type, create column per record attribute.
* In each created column locate `Static Text` element with the label.
  Set `Vertical Alignment` for the field and `Textalignment`
* Per each column we've created, created a field in the datasource (`Table_Demo_DS`), associated with the table element.
  For instance we want to pass a java pojo bean with setters/getters per each attribute, defined as:
```java
public class LineBean {
  private String name;
  private LocalDateTime date;  
  private BigDecimal money;
  private Long longField;
  private Double doubleField;
  //setters and getters
}
```
then we must name our fields exactly in the same way, as the attributes in the class are named:
`name`, `date`, `money`, `longField`, `doubleField`
Java types must also be the same.

* Locate each created field of the `Table_Demo_DS` datasource into the `Detail` band of the table (not global `Detail`)
* Select all the fields you located, choose `Borders` and then set left padding.
* Define global parameter (we named it as `CUSTOM_TABLE_PARAMETER`) under the parent report.
  It must not be a parameter inside of the dataset!

That parameter name is used when we pass tabular data to the report.
The parameter type is `net.sf.jasperreports.engine.data.JRBeanCollectionDataSource`.
`JRBeanCollectionDataSource` is a wrapper for our collection of java bean objects.
* Map the `Table_Demo_DS` datasource to the `CUSTOM_TABLE_PARAMETER` parameter defined above.
  Change `Use an empty Data Source` option of our table `Table_Demo_DS` datasource to:
  `Use a JRDatasource expression`

You'll see `$P{CUSTOM_TABLE_PARAMETER}` as a value of `Use a JRDatasource expression`

* Pass list of beans to the report via `CUSTOM_TABLE_PARAMETER` parameter.

```java
Map<String, Object> map = new HashMap<>();
    map.put(
      DATASOURCE_PARAMETER,
      new JRBeanCollectionDataSource(
      List.of(
        new LineBean("Test 1", LocalDateTime.now(), new BigDecimal(25.99), 199L, 21.99),
        new LineBean("Test 2", LocalDateTime.now(), new BigDecimal(35.99), 399L, 121.99),
        new LineBean("Test 3", LocalDateTime.now(), new BigDecimal(35.9995), 4399L, 121.999)
      )));
```

See [`TableElementReportTest`](../../src/test/java/com/example/jr/TableElementReportTest.java)
* 