### programatical workflow of pdf creation

#### you cannot pass empty list to:

and that // JR cannot use immutable map! 

new JRBeanCollectionDataSource(Collections.emptyList()));

You'll get an empty report. You must use speial datasource: JREmptyDataSource

If list might be useful and it is still a valid report, 
use a wrapper method insead of construction of `JREmptyDataSource`/`JRBeanCollectionDataSource`:
this sutiable ONLY for main datasource and report fields
```java
//works only for the main datasource,does not work if datasorce is passed via property
private JRDataSource jrMainDataSource(List<?> items) {
  if (items == null || items.isEmpty()) {
  return new JREmptyDataSource();
  } else {
  return new JRBeanCollectionDataSource(items);
  }
}
```

If datasoruce is passed via property it must be of type JRBeanCollectionDataSource
and you cannot pass JREmptyDataSource when the wrapped list is empty

#####
https://community.jaspersoft.com/questions/1100336/data-multiple-tables-overlapping-each-other

https://community.jaspersoft.com/documentation/tibco-jaspersoft-studio-user-guide/v60/using-frames

https://community.jaspersoft.com/questions/1113571/how-configure-text-fields-url


#####
Exporting Multiple Reports into a Single Output File (Batch Export)
https://jasperreports.sourceforge.net/sample.reference/batchexport/index.html

simple master/subreport with static labels to make sure it works
complex structure based on a single report (passing table data into via field)
merging independent pdf files

/home/alex/projects/private/jasper_reports/src/test/resources/jr/Subreports_via_parameters.jrxml

### 
groups
https://stackoverflow.com/questions/34198746/page-break-in-jasper-report

Keep together for a group and page break:
https://stackoverflow.com/questions/11760228/avoiding-unwanted-page-break-in-jasperreports

### Merge pdf:
https://www.geeksforgeeks.org/merging-pdfs-using-java/
https://stackoverflow.com/questions/3585329/how-to-merge-two-pdf-files-into-one-in-java


### Passing parameters to the template.sections.xml
### Subreports
https://www.tutorialspoint.com/jasper_reports/jasper_create_subreports.htm
https://community.jaspersoft.com/documentation/tibco-jaspersoft-studio-user-guide/v60/subreports

### Group- what is it?

### Elements of jr page
### Variables, fields, parameters
### Tables 

### Expressions, predefined variables


* [Steps to create Alternate Background Row Color in Jaspersoft Reports](https://www.youtube.com/watch?v=kP2lEPRn0rs)
* [How to change report locale on fly](https://community.jaspersoft.com/wiki/how-change-report-locale-fly)
