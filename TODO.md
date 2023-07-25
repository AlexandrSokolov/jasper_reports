### describe variablees evaluation:

All reports contain a set of built-in variables. 
Their value changes during the report execution. 
Built-in variables include the PAGE_NUMBER, 
which holds the current page of the report and the REPORT_COUNT which holds the number of records currently processed. 
The built-in variables can not be modified or deleted.


			<textField evaluationTime="Report">
				<reportElement x="421" y="0" width="124" height="30" uuid="f881f983-2e54-4cff-9836-724c20308851"/>
				<textFieldExpression><![CDATA["Page " + $V{PAGE_NUMBER} + " of " + $V{PAGE_COUNT}]]></textFieldExpression>
			</textField>
			<textField pattern="HH:mm">
				<reportElement x="421" y="30" width="122" height="30" uuid="af554b15-fa7f-42c1-b4e0-5e69e6355ae2"/>
				<textFieldExpression><![CDATA["Date: " + new SimpleDateFormat("dd.MM.yyyy").format(new Date())]]></textFieldExpression>
			</textField>
		</band>

There is `Page X of Y` composite element!!

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

#### show band when datasource is empty

$F{assetId} != null

For data passed via properties:
$P{variablesHistoryList} != null && !($P{variablesHistoryList}.isEmpty())

#####
page break on the title and page number 
you must calculate the page number after each break on the title band, JR cannot do tht correctly
Keep in mind, if element visibility on the page depends on some data, then page number must also use that

https://jasperreports.sourceforge.net/sample.reference/nopagebreak/index.html

page break, if it is thef irst element is the details is ignored for the first page!
as a result page break must be defined additionally as the last element of the title!!!

Multi page layout of the title band and the height issue
https://stackoverflow.com/questions/76702371/title-band-height-with-multiple-pages

#####
https://community.jaspersoft.com/questions/1100336/data-multiple-tables-overlapping-each-other

https://community.jaspersoft.com/documentation/tibco-jaspersoft-studio-user-guide/v60/using-frames

https://community.jaspersoft.com/questions/1113571/how-configure-text-fields-url

#### image
https://stackoverflow.com/questions/36457059/jasperreports-api-getting-error-when-using-image-in-report-net-sf-jasperreport

do not use InputStream, as a property type for image, you must close it. 
-When you try to load image twice as inputstream it you'll get:
Caused by: java.io.IOException: The byte array is not a recognized imageformat.
for instance for logo on each page

Use:
```xml
<parameter name="bmLogo" class="java.awt.image.BufferedImage"/>
```
Instead of:
```xml
<parameter name="logo" class="java.io.InputStream"/>
```

and java code:
```java
import javax.imageio.ImageIO;
import net.sf.jasperreports.engine.util.JRLoader;

...
try (InputStream logo = Thread.currentThread().getContextClassLoader().getResourceAsStream("images/logo.png")) {
    map.put(
      "logo",
      ImageIO.read(new ByteArrayInputStream(JRLoader.loadBytes(logo))));
    } catch (IOException | JRException e) {
      throw new IllegalStateException(e);
    }
```
instead of:
```java

```


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
