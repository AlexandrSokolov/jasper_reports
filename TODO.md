### describe variablees evaluation:

			<textField evaluationTime="Report">
				<reportElement x="421" y="0" width="124" height="30" uuid="f881f983-2e54-4cff-9836-724c20308851"/>
				<textFieldExpression><![CDATA["Page " + $V{PAGE_NUMBER} + " of " + $V{PAGE_COUNT}]]></textFieldExpression>
			</textField>
			<textField pattern="HH:mm">
				<reportElement x="421" y="30" width="122" height="30" uuid="af554b15-fa7f-42c1-b4e0-5e69e6355ae2"/>
				<textFieldExpression><![CDATA["Date: " + new SimpleDateFormat("dd.MM.yyyy").format(new Date())]]></textFieldExpression>
			</textField>
		</band>

#### you cannot pass empty list to:

and that // JR cannot use immutable map! 

new JRBeanCollectionDataSource(Collections.emptyList()));

You'll get an empty report. You must use speial datasource: JREmptyDataSource

If list might be useful and it is still a valid report, 
use a wrapper method insead of construction of `JREmptyDataSource`/`JRBeanCollectionDataSource`:
```java
private JRDataSource jrDataSource(List<?> items) {
    if (items == null || items.isEmpty()) {
      return new JREmptyDataSource();
    } else {
      return new JRBeanCollectionDataSource(items);
    }
  }
```


#####

simple master/subreport with static labels to make sure it works
complex structure based on a single report (passing table data into via field)
merging independent pdf files

/home/alex/projects/private/jasper_reports/src/test/resources/jr/Subreports_via_parameters.jrxml

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
